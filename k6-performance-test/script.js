import http from 'k6/http';
import { check, sleep } from 'k6';

function pad(number, length) {
  let str = '' + number;
  while (str.length < length) {
    str = '0' + str;
  }
  return str;
}

Date.prototype.format = function () {
  let yyyy = this.getFullYear().toString();
  let MM = pad(this.getMonth() + 1,2);
  let dd = pad(this.getDate(), 2);
  let hh = pad(this.getHours(), 2);
  let mm = pad(this.getMinutes(), 2)
  let ss = pad(this.getSeconds(), 2)

  return yyyy +  MM + dd+  hh + mm + ss;
};

const runId = (new Date()).format();

export const options = {
    stages: [
        { duration: '30s', target: 50 },
        { duration: '2m10s', target: 50 },
        { duration: '20s', target: 0 }
    ],
    tags: {
        // 실행하는 테스트에 ID를 부여하여, 각 테스트에 해당하는 메트릭 필터링이 가능함(Prometheus remote write 시 사용)
        testid: runId
    }
}

function login() {

}

export default function() {
    let res = http.get("http://localhost:8080/hello");
    check(res, { 'status was 200': (r) => r.status === 200 });
    sleep(1)
}
