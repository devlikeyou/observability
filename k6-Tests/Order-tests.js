import http from 'k6/http';
import { group, sleep } from 'k6';
import { Trend, Rate, Counter } from 'k6/metrics';
import { check, fail } from 'k6';
import { jUnit, textSummary } from 'https://jslib.k6.io/k6-summary/0.0.1/index.js';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { randomString } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';



export function handleSummary(data) {
  console.log('Preparing the end-of-test summary...');

  const summaryFormat = {
    stdout: textSummary(data, {indent: ' ', enableColors: true}) + '\n',
  };

  const htmlReportFileName = __ENV.K6_OPTION_HTML_REPORT_FILE_NAME;
  summaryFormat[htmlReportFileName] = htmlReport(data);
  return summaryFormat;
}


export let GetMSDuration = new Trend('get_k6_duration');
export let GetMSFailRate = new Rate('get_k6_fail_rate');
export let GetMSSuccessRate = new Rate('get_k6_success_rate');
export let GetMSReqs = new Counter('get_k6_reqs');


export const options = {
  stages: [
    { duration: '30s', target: 5 },
    { duration: '30s', target: 10 },
    { duration: '30s', target: 5 }
  ]
};

export default function () {

    const mockCustomer  = randomString(10, `aeioubcdfghijpqrstuvxyz0123456789`);
    const mockEmail     = randomString( 6, `aeioubcdfghijpqrstuvxyz0123456789`);
    const mockRequester = randomString(20, `aeioubcdfghijpqrstuvxyz0123456789`);


  const params = {
    headers: {
      'requestTraceId': mockRequester,
      'Content-Type':'application/json'
    },
  };

  const jsonData =
  {
    "customerId": mockCustomer,
    "customerEmail": mockEmail+"@devlikeyou.com",
    "payment": {
      "type": "credit",
      "cardNumber": "1234-5678-9012-3456",
      "value": 1000
    },
    "storeId": "123456",
    "items": [
      {
        "productId": "1",
        "quantity": 2,
        "unitPrice": 500,
        "sequence": 1
      }
    ]
  };

  const url =  `http://localhost:8080/orders`;
  const payload = JSON.stringify(jsonData);
  console.log(mockRequester);

  const res = http.post(url, payload, params);



  GetMSDuration.add(res.timings.duration);
  GetMSReqs.add(1);
  GetMSFailRate.add(res.status == 0 || res.status > 399);
  GetMSSuccessRate.add(res.status < 399);

  if(!check(res, {
    'is status 200': (r) => r.status === 200,
    'max duration': (r) => r.timings.duration < 2000,
  })){
  }

  sleep(1);
}