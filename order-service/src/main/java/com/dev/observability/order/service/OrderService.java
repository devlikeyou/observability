package com.dev.observability.order.service;

import com.dev.observability.order.clients.notificator.NotificatorApiClient;
import com.dev.observability.order.clients.notificator.vo.NotificatorRequest;
import com.dev.observability.order.clients.payment.PaymentApiClient;
import com.dev.observability.order.clients.payment.vo.PaymentRequest;
import com.dev.observability.order.clients.payment.vo.PaymentResponse;
import com.dev.observability.order.clients.picking.PickingApiClient;
import com.dev.observability.order.clients.picking.vo.PickingRequest;
import com.dev.observability.order.clients.picking.vo.PickingResponse;
import com.dev.observability.order.dto.ItemRequest;
import com.dev.observability.order.dto.OrderRequest;
import com.dev.observability.order.exceptions.InternalServerErrorException;
import com.dev.observability.order.model.Item;
import com.dev.observability.order.model.NotificationType;
import com.dev.observability.order.model.Order;
import com.dev.observability.order.model.OrderStatus;
import com.dev.observability.order.repository.OrderRepository;
import feign.FeignException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentApiClient paymentApi;

    @Autowired
    private PickingApiClient pickingApi;

    @Autowired
    private NotificatorApiClient notificatorApi;

    private static String MSG_ORDER = "Notification to Order: ";


    @Override
    public Order createOrder(final OrderRequest orderRequest) {

        Order order = Order.builder()
                .customerId(orderRequest.getCustomerId())
                .storeId(orderRequest.getStoreId())
                .createdDate(OffsetDateTime.now())
                .items(itemRequestToItem(orderRequest.getItems()))
                .status(OrderStatus.CREATED).build();

        Order orderInserted = orderRepository.insert(order);


        PaymentResponse paymentResponse = postPayment(orderRequest.getPayment(), orderInserted.getId());
        orderInserted.setPaymentId(paymentResponse.getTransactionPaymentId());
        orderInserted.setStatus(OrderStatus.PAYMENT_APPROVED);
        orderRepository.save(orderInserted);


        PickingResponse pickingResponse = postPicking(order.getId(), order.getStoreId());
        orderInserted.setPaymentId(pickingResponse.getPickingId());
        orderInserted.setStatus(OrderStatus.PICKING_PENDING);
        orderRepository.save(orderInserted);

        postNotification(   orderRequest.getStoreId(),
                            orderRequest.getStoreId(),
                            NotificationType.EMAIL.name(),
                            orderInserted.getId());

        return orderInserted;
    }




    protected List<Item> itemRequestToItem(List<ItemRequest> itemRequests) {
        return itemRequests.stream()
                .map( rItem ->
                    Item.builder()
                            .productId(rItem.getProductId())
                            .quantity(rItem.getQuantity())
                            .sequence(rItem.getSequence())
                            .unitPrice(rItem.getUnitPrice())
                            .build()
                ).collect(Collectors.toList());
    }


    public void updateOrderStatusById(String orderId, OrderStatus newStatus) {
        Optional<Order> order = orderRepository.findById(orderId);

        if(!order.isEmpty()){
            order.get().setStatus(newStatus);
            orderRepository.save(order.get());
        }
    }


    protected PaymentResponse postPayment(PaymentRequest paymentRequest, String orderId) {

        try {
            return paymentApi.postPayment(paymentRequest);
        } catch(FeignException exception) {
            updateOrderStatusById(orderId, OrderStatus.PAYMENT_FAILED);
            throw new InternalServerErrorException("Error to Create Payment for order "+orderId);
        }
    }



    protected PickingResponse postPicking(String orderId, String storeId) {

        PickingRequest picking = PickingRequest.builder().storeId(storeId).orderId(orderId).build();

        try {
            return pickingApi.postPicking(picking);
        } catch(FeignException exception) {
            updateOrderStatusById(orderId, OrderStatus.PICKING_FAILED);
            throw new InternalServerErrorException("Error to Create Picking for order "+orderId);
        }
    }



    protected void postNotification(String sender,
                                    String receiver,
                                    String type,
                                    String orderId ) {

        NotificatorRequest notificatorRequest = NotificatorRequest.builder()
                .sender(sender)
                .receiver(receiver)
                .msg(MSG_ORDER+orderId)
                .type(type)
                .build();

        try {
            notificatorApi.postNotificator(notificatorRequest);
        } catch(FeignException exception) {
        }
    }

}
