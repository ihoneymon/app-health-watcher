# 애플리케이션 건강 점검기

## 사용을 위해 추가해야할 작업내용
* 정기(1분)적으로 대상의 건강을 확인
* `application.yml` 에 관찰하려 하는 대상의 URL을 `target.url` 을 등록해야함
* `SlackTarget` 에 Slack Incoming Webhook 추가해야함

## 제작목적
* 배포관리하는 앱이 정상적으로 배포되었는지 확인하려함

## 개선사항
* 대상을 동적으로 관리하는 방법도 있겠다.