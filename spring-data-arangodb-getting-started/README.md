문서 작성 중..........









# ArangoDB Getting Started

## Overview
Arango DB 를 빠르게 이해한다. 


## 처음 설치
필자는 Cent 6 버전에서 설치하였다.  
```
cd /etc/yum.repos.d/
curl -OL https://download.arangodb.com/arangodb33/CentOS_6/arangodb.repo
yum -y install arangodb3-3.3.4
```
CentOS 에서의 설치에 대해서는 아래 링크를 참고하자. 
[https://www.arangodb.com/download-major/centos/](https://www.arangodb.com/download-major/centos/)

설치를 완료하면 /etc/init.d/ 디렉토리에 arangodb3 실행 명령어로 서버를 시작할 수 있다. 
```
/etc/init.d/arangodb3 start
or
service arangodb3 start
```
설치 후에, 
```
netstat -tnlp
```
8529 포트가 생겼다면 정상적으로 실행된 것이겠지?

다른 OS 에서 환경에서 설치를 한다면 아래 가이드에서 확인하자. 
[https://docs.arangodb.com/2.8/FirstSteps/GettingFamiliar.html](https://docs.arangodb.com/2.8/FirstSteps/GettingFamiliar.html)

도커 이미지로 실행하겠다면, docker search arangodb , 및 pull 땡겨서 실행하면 될것 같다. (나는, 해보진 않았다.)

8529포트가 실행되었으니 리눅스 서버에서 아래와 같이 Curl 때리니깐 정상적으로 Response 가 온다. 
```
curl http://127.0.0.1:8529
```

근데 외부에서 접근할려니깐 안되더라. 방화벽을 열어야 하겠다. 

```
# 포트 설정, 추가
vi /etc/sysconfig/iptables
-A INPUT -P tcp -m tcp --dport 8529 -j ACCEPT

# iptables 재시작
service iptables restart
```

근데, 나는 포트 오픈을 했는데도, 접속이 안된다. netstat 로 확인해보니 다른 미들웨어 소프트웨어는 0.0.0.1:포트 로 설정이 되어있는데, ArangoDB 는 127.0.0.1 로 되어있다. 내가 아주 빠르게 생각해낸 방법은 두가지인데

- nginx 에서 Reverse Proxy 를 적용해서 포트 전환을 시켜준다.
- 아니면 ArangoDB 컨피그 설정에서 바꿔준다

일단, 둘다 검증되지 않은 방법이지만 두번째 방법을 사용해서  /etc/arangodb3 에 있는 arangod.conf 파일에서 endpoint 를 변경한다. 

```
#endpoint = tcp://127.0.0.1:8529
endpoint = tcp://0.0.0.1:8529 
```
적용하면 외부에서 http://119.205.비밀.비밀:8529 로 접속하니 ArangoDB 콘솔 화면으로 접속된다. 하지만, 아직 내가 만든 계정이 없으므로 접속할 수는 없다.

## DB 생성 및 계정 생성

#### 루트 비번 초기화

루트 계정이 있을텐데, 가이드에서는 패스워드가 empty 라고 하는데, 뭔가 설정이 잘못된 것인지 접속이 안된다. 접속이 안된다는 의미는 ArangoDB 쉘 에 root 계정으로 접속이 안된다는 의미다. 물론, 쉘 뿐만 아니라 콘솔화면에서도 로그인이 안된다.

일단, 편법이지만 ArangoDB 를 권한 없이 접속할 수 있도록 띄울수 있다. 

```
service arangodb3 stop
/usr/sbin/arangod --server.authentication false
```

그리고 쉘에 접속을 해보자
```
arangosh
```
비번을 제대로 입력 안해도 접속이 잘 된다. 사실 아까도 접속은 되는데 명령창이 빨간색으로 표시된다. 제대로 접속하면 연두색으로 표시 된다. 그리고 루트 비번을 바꾸자

```
require("@arangodb/users").replace("root", "my-changed-password");
exit
service arangodb3 restart
```

어쨋든 루트의 계정 비번이 변경이 잘 되었다. 콘솔 화면에서도 접속이 잘 된다! 가즈아!!!


#### 디비 생성




문서 작성 중..........
