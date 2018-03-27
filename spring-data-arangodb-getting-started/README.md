���� �ۼ� ��..........









# ArangoDB Getting Started

## Overview
Arango DB �� ������ �����Ѵ�. 


## ó�� ��ġ
���ڴ� Cent 6 �������� ��ġ�Ͽ���.  
```
cd /etc/yum.repos.d/
curl -OL https://download.arangodb.com/arangodb33/CentOS_6/arangodb.repo
yum -y install arangodb3-3.3.4
```
CentOS ������ ��ġ�� ���ؼ��� �Ʒ� ��ũ�� ��������. 
[https://www.arangodb.com/download-major/centos/](https://www.arangodb.com/download-major/centos/)

��ġ�� �Ϸ��ϸ� /etc/init.d/ ���丮�� arangodb3 ���� ��ɾ�� ������ ������ �� �ִ�. 
```
/etc/init.d/arangodb3 start
or
service arangodb3 start
```
��ġ �Ŀ�, 
```
netstat -tnlp
```
8529 ��Ʈ�� ����ٸ� ���������� ����� ���̰���?

�ٸ� OS ���� ȯ�濡�� ��ġ�� �Ѵٸ� �Ʒ� ���̵忡�� Ȯ������. 
[https://docs.arangodb.com/2.8/FirstSteps/GettingFamiliar.html](https://docs.arangodb.com/2.8/FirstSteps/GettingFamiliar.html)

��Ŀ �̹����� �����ϰڴٸ�, docker search arangodb , �� pull ���ܼ� �����ϸ� �ɰ� ����. (����, �غ��� �ʾҴ�.)

8529��Ʈ�� ����Ǿ����� ������ �������� �Ʒ��� ���� Curl �����ϱ� ���������� Response �� �´�. 
```
curl http://127.0.0.1:8529
```

�ٵ� �ܺο��� �����ҷ��ϱ� �ȵǴ���. ��ȭ���� ����� �ϰڴ�. 

```
# ��Ʈ ����, �߰�
vi /etc/sysconfig/iptables
-A INPUT -P tcp -m tcp --dport 8529 -j ACCEPT

# iptables �����
service iptables restart
```

�ٵ�, ���� ��Ʈ ������ �ߴµ���, ������ �ȵȴ�. netstat �� Ȯ���غ��� �ٸ� �̵���� ����Ʈ����� 0.0.0.1:��Ʈ �� ������ �Ǿ��ִµ�, ArangoDB �� 127.0.0.1 �� �Ǿ��ִ�. ���� ���� ������ �����س� ����� �ΰ����ε�

- nginx ���� Reverse Proxy �� �����ؼ� ��Ʈ ��ȯ�� �����ش�.
- �ƴϸ� ArangoDB ���Ǳ� �������� �ٲ��ش�

�ϴ�, �Ѵ� �������� ���� ��������� �ι�° ����� ����ؼ�  /etc/arangodb3 �� �ִ� arangod.conf ���Ͽ��� endpoint �� �����Ѵ�. 

```
#endpoint = tcp://127.0.0.1:8529
endpoint = tcp://0.0.0.1:8529 
```
�����ϸ� �ܺο��� http://119.205.���.���:8529 �� �����ϴ� ArangoDB �ܼ� ȭ������ ���ӵȴ�. ������, ���� ���� ���� ������ �����Ƿ� ������ ���� ����.

## DB ���� �� ���� ����

#### ��Ʈ ��� �ʱ�ȭ

��Ʈ ������ �����ٵ�, ���̵忡���� �н����尡 empty ��� �ϴµ�, ���� ������ �߸��� ������ ������ �ȵȴ�. ������ �ȵȴٴ� �ǹ̴� ArangoDB �� �� root �������� ������ �ȵȴٴ� �ǹ̴�. ����, �� �Ӹ� �ƴ϶� �ܼ�ȭ�鿡���� �α����� �ȵȴ�.

�ϴ�, ��������� ArangoDB �� ���� ���� ������ �� �ֵ��� ���� �ִ�. 

```
service arangodb3 stop
/usr/sbin/arangod --server.authentication false
```

�׸��� ���� ������ �غ���
```
arangosh
```
����� ����� �Է� ���ص� ������ �� �ȴ�. ��� �Ʊ ������ �Ǵµ� ���â�� ���������� ǥ�õȴ�. ����� �����ϸ� ���λ����� ǥ�� �ȴ�. �׸��� ��Ʈ ����� �ٲ���

```
require("@arangodb/users").replace("root", "my-changed-password");
exit
service arangodb3 restart
```

�񋚸��� ��������� ��¶�� ��Ʈ�� ���� ����� ������ �� �Ǿ���. �ܼ� ȭ�鿡���� ������ �� �ȴ�! �����!!!


#### ��� ����




���� �ۼ� ��..........