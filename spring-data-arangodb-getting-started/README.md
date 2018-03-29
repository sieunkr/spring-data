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

## 데이터 입력

#### 웹콘솔에서 데이터 입력해보기
Collection 은 단순하게 테이블과 유사하다고 생각하면 된다. Type 은 Document 와 Edge 가 있다. Document 는 JSON 형태의 데이터를 저장하는 NO-SQL Collection 이다. Collection 이 생성이 되었다. 이제 쿼리를 작성해보자. 좌측 쿼리 메뉴를 클릭하고 아래와 같이 작성한 다음에, Explain 버튼을 클릭하자.  참고로 AQL 구문이라는 arangoDB에서만 사용할 수 있는 쿼리문이다. 
```
INSERT {name: "모카", price: 1200}
INTO coffee
```
Coffee Collection 에 보면 아래와 같이 데이터가 생긴 것을 확인할 수 있다. 
01.png

AQL 구문에 대한 가이드는 레퍼런스를 참고하자. 필자는 arangoDB 를 아직은 깊게 볼 생각이 없기 때문에 AQL 구문은 아직 보고 싶지 않다. 

#### Document vs Edge
그렇다면 두 타입의 차이는 무엇인가? 

글 작성 중...

## Spring Getting Started 

참고 링크
[https://www.arangodb.com/tutorials/spring-data/part1-getting-started/](https://www.arangodb.com/tutorials/spring-data/part1-getting-started/)
[https://github.com/arangodb/spring-data#getting-started](https://github.com/arangodb/spring-data#getting-started)

구축 환경은 아래와 같다. 

- intellij
- Spring Boot 2.0
- Gradle 4.X

먼저 Gradle 디펜던시 추가한다. 

```
compile('com.arangodb:arangodb-spring-data:2.0.3')
```

Property 와 Config 설정을 추가 한다. 

```java
import com.arangodb.ArangoDB;  
import com.arangodb.springframework.annotation.EnableArangoRepositories;  
import com.arangodb.springframework.config.AbstractArangoConfiguration;  
import org.springframework.context.annotation.Configuration;  
  
@Configuration  
@EnableArangoRepositories(basePackages = { "spring.data.arangodb" })  
public class ArangoConfiguration extends AbstractArangoConfiguration {  
  
    @Override  
  public ArangoDB.Builder arango() {  
        return new ArangoDB.Builder();  
  }  
  
    @Override  
  public String database() {  
        // Name of the database to be used  
  return "Cafe";  
  }  
}
```

```java
arangodb.host=119.205.221.42  
arangodb.port=8529  
arangodb.user=root  
arangodb.password=****
```

#### Spring 에서 데이터 조회
Spring 에서 데이터를 입력하기 전에 아까 콘솔화면에서 이미 입력한 데이터를 조회해보자.  먼저 @Document 어노테이션이 선언된 Coffee 클래스를 만들자. 
```java
@Document  
public class Coffee {  
  
    private String name;  
  
 private Integer price;  
  
 public Coffee(String name, Integer price){  
        this.name = name;  
 this.price = price;  
  }  
  
    public String getName() {  
        return name;  
  }  
  
    public Integer getPrice() {  
        return price;  
  }  
}
```

컨피그 설정은 아래와 같이 작성한다. 참고로, property 파일에 작성하는게 더 깔끔할 것이다. 필자는 테스트 소스라서 아래와 같이 간단하게 연동한다. 
```java
@Configuration  
@EnableArangoRepositories(basePackages = { "spring.data.arangodb.entity" })  
public class ArangoConfiguration extends AbstractArangoConfiguration {  
  
    @Override  
  public ArangoDB.Builder arango() {  
        ArangoDB.Builder arango = new ArangoDB.Builder()  
                .host("***.205.***.42")  
                .port(8529)  
                .user("root")  
                .password("$$$$$$$");  
		 return arango;  
  
  }  
  
	@Override  
	public String database() {  
        // Name of the database to be used  
	return "Cafe";  
  }  
}

```
arangodb-spring-data 에서는 ArangoRepository 를 제공해주는데, 해당 Repository 는 PagingAndSortingRepository 와 QueryByExampleExecutor 를 상속받는다. PagingAndSortingRepository  는 CrudRepository 를 상속받는다. 대충 감이 오겠지만, 해당 Repository 를 사용하면 왠만한 CRUD 는 구현이 가능할 것 같다. 

```java
public interface CoffeeRepository extends ArangoRepository<Coffee> {  
  
    Iterable<Coffee> findByChildsAgeBetween(int lowerBound, int upperBound);  
  
}
```
간단한 RestController 를 만들고, Document 데이터 리스트를 리턴해보자. 
```java
@RestController  
public class HomeController {  
  
    @Autowired  
  private CoffeeRepository coffeeRepository;  
  
  @GetMapping("/coffees")  
    public List<Coffee> list(){  
  
        List<Coffee> coffees = StreamSupport.stream(coffeeRepository.findAll().spliterator(), true)  
                .collect(Collectors.toList());  
  
 return coffees;  
  }
```

 #### Spring 에서 데이터 입력

```java
//샘플소스
//Post 메소드이고, RequestBody 또는 Param 이 필요하지만, 테스트로 구현하는 것이이 아래와 같이 빠르게 구현, 데이터가 잘 들어가는지만 확인
@PostMapping("/coffees")  
public void save(){  
  
    final Coffee coffee = new Coffee("라떼", 1100);  
  coffeeRepository.save(coffee);  
  }
```

자, 정상적으로 입력된 것을 확인할 수 있다. 

findOne 메소드를 활용해서 특정 데이터를 조회할수도 있다. 
```java
coffeeRepository.findOne(Example.of(coffee)).get()
```

아래와 같이 Repository 에 만들수도 있는데, 조금더 깔끔한거 같다.
```java
public interface CoffeeRepository extends ArangoRepository<Coffee> {
	//생략...
	Iterable<Character> findByName(String name);  
	Iterable<Character> findByPrice(Integer price);
```



문서 작성 중

[https://github.com/arangodb/spring-data-demo#create-a-repository](https://github.com/arangodb/spring-data-demo#create-a-repository)
