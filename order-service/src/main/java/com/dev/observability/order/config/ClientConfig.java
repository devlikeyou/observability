package com.dev.observability.order.config;

import com.dev.observability.order.clients.notificator.NotificatorApiClient;
import com.dev.observability.order.clients.payment.PaymentApiClient;
import com.dev.observability.order.clients.picking.PickingApiClient;
import com.dev.observability.order.config.properties.ApisProperties;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

@Configuration
@EnableFeignClients
public class ClientConfig {

    /**
     * We tell Feign to use OkHttpClient instead of the default one to support HTTP/2
     *
     * @return OkHttpClient
     */
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean("jacksonDecoder")
    public Decoder jacksonDecoder() {
        return new JacksonDecoder(Collections.singleton(new JavaTimeModule()));
    }

    @Bean("jacksonEncoder")
    public Encoder jacksonEncoder() {
        return new JacksonEncoder();
    }

    @Bean("formEncoder")
    public Encoder formEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new FormEncoder(new SpringEncoder(converters));
    }

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Logger logger() {
        return new Slf4jLogger();
    }

    private RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        };
    }

    @Bean
    public NotificatorApiClient notificatorApiClient(@Qualifier("jacksonDecoder") Decoder decoder,
                                                     @Qualifier("jacksonEncoder") Encoder encoder,
                                                     Logger logger,
                                                     Logger.Level loggerLevel,
                                                     ApisProperties apisProperties) {
        return Feign.builder()
                .decoder(decoder)
                .encoder(encoder)
                .logger(logger)
                .logLevel(loggerLevel)
                .errorDecoder(new ErrorDecoder.Default())
                .requestInterceptor(requestInterceptor())
                .target(NotificatorApiClient.class, apisProperties.getNotificator().getUrl());
    }

    @Bean
    public PaymentApiClient paymentApiClient(@Qualifier("jacksonDecoder") Decoder decoder,
                                             @Qualifier("jacksonEncoder") Encoder encoder,
                                             Logger logger,
                                             Logger.Level loggerLevel,
                                             ApisProperties apisProperties) {
        return Feign.builder()
                .decoder(decoder)
                .encoder(encoder)
                .logger(logger)
                .logLevel(loggerLevel)
                .errorDecoder(new ErrorDecoder.Default())
                .requestInterceptor(requestInterceptor())
                .target(PaymentApiClient.class, apisProperties.getPayment().getUrl());
    }

    @Bean
    public PickingApiClient pickingApiClient(@Qualifier("jacksonDecoder") Decoder decoder,
                                             @Qualifier("jacksonEncoder") Encoder encoder,
                                             Logger logger,
                                             Logger.Level loggerLevel,
                                             ApisProperties apisProperties) {
        return Feign.builder()
                .decoder(decoder)
                .encoder(encoder)
                .logger(logger)
                .logLevel(loggerLevel)
                .errorDecoder(new ErrorDecoder.Default())
                .requestInterceptor(requestInterceptor())
                .target(PickingApiClient.class, apisProperties.getPicking().getUrl());
    }

}
