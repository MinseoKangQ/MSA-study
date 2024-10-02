# RabbitMQ 설치하기
- MacOS M1

<br><br>

### RabbitMQ 설치
- `arch -arm64 brew update`
- `arch -arm64 brew upgrade`
- `arch -arm64 brew install rabbitmq` 

<br><br>

### 오류 발생 & 해결

```
Error: Cannot install mariadb because conflicting formulae are installed.
  mysql: because mariadb, mysql, and percona install the same binaries

Please `brew unlink mysql` before continuing.

Unlinking removes a formula's symlinks from /opt/homebrew. You can
link the formula again after the install finishes. You can `--force` this
install, but the build may fail or cause obscure side effects in the
resulting software.
```

- `brew unlink mysql`
- `arch -arm64 brew install rabbitmq`
- `brew link mysql`

<br><br>

### 실행
- `export PATH=$PATH:/usr/local/sbin`
- `brew info rabbitmq` 로 RabbitMQ가 설치된 경로 찾기
- `/opt/homebrew/opt/rabbitmq/sbin/rabbitmq-server` (설치된 경로)로 RabbitMQ 실행
- `127.0.0.1:15672`로 접속
- default id/pw는 guest