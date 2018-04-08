정리안된 글... 다음 주말에 정리 예정;



# ArangoDB Getting Started with Spring

## Overview
Spring Framework에서 Arango DB 연동을 위한 샘플 테스트 코드를 작성한다. 

1. Graph DB 개념
2. ArangoDB 설치
3. Arango Web UI
4. 테스트 데이터 입력
5. Spring Data ArangoDB - SELECT
6. Spring Data ArangoDB - INSERT


## Data model

#### 일반적인 Property Graph Model
점과 선으로 모든 것을 표현하겠다는 것! 
Vertices, Edges, Properties 가 있다. 
선위에 임의의 속성을 부여할 수 있는 것이 Properties

참고 자료
https://www.youtube.com/watch?v=NH6WoJHN4UA

Node  는 Noun 이 되고, 명사
Relationship 은 Verb 가 된다. 동사
Properties 는 adverb 가 된다.  부사??

#### GraphDB 의 컨셉

- Vertex(Node) : 속성을 가지는 엔터티
- Arc(Edge) : 노드 사이의 선(연결 link), 방향성(가리키는 방향)을 가질 수 있음.
- Property : Node나 Edge에 할당된 값, 이름과 값으로 구성됨

**A Vertex represents a "thing"**
예를 들어 영화, 사람이 Vertex 가 된다. 
예를 들어서 엽기적인그녀 라는 영화가 있고, 전지현 이라는 사람이 있다. 

**Edges are labeled relationships**
Edges have direction. 
Edge 는 관계를 나타내며 방향이 있다. (양방향일수도 있다. 양방향은 조금 이따 고민하자..)


#### ArangoDB 에서의 Data Model
**Document** 는 VelocyPack 라고 불리는 바이너리 포맷으로 저장되는데, JSON 과 거의 유사하다.  **Document** 는 **Collection** 안에 그룹화 된다. 만약, RDBMS에 익숙하다면, 테이블과 **Document** 를 비교하는 것이 적합할 것이다. 전통적인 RDMBS 와의 차이는, 테이블에는 정의된 컬럼에 데이터를 넣는다. 스키마라고도 부른다.  **ArangoDB** 에는 스키마가 없기 때문에 **Document** 를 정의할 필요는 없다.  모든 **Document** 는 완전히 다른 구조를 가질 수 있으며, 단일 **Collection**에서 다른 **Document**와 함께 저장될 수 있다.  **Collection**에는 두가지 타입이 있는데, **Document Collection** 과 **Edge Collection** 이다. **Edge Collection** 에는 **Document**도 저장하지만, **Document** 사이의 관계를 만드는 두가지 특별한 속성, **_from**, **_to** 이 포함되어 있다. 


## 처음 설치

#### CentOS 6.X
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

#### DB 생성 및 계정 생성

루트 계정이 있을텐데, 가이드에서는 패스워드가 empty 라고 하는데, 뭔가 설정이 잘못된 것인지 접속이 안된다. 접속이 안된다는 의미는 ArangoDB 쉘 에 root 계정으로 접속이 안된다는 의미다. 물론, 쉘 뿐만 아니라 웹UI화면에서도 로그인이 안된다.

일단, 편법이지만 ArangoDB 를 권한 없이 접속할 수 있도록 띄울수 있다. 

```
service arangodb3 stop
#arangod.conf 파일에서 authentication 을 false 로 설정하자.
/usr/sbin/arangod 
--server.authentication false
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

어쨋든 루트의 계정 비번이 변경이 잘 되었다. 이제 변경된 Root 비번으로 콘솔 화면에서도 접속이 잘 된다! 가즈아!!!


참고로, 필자는 CentOS 외에도 도커 이미지로 설치를 진행하였다. 도커는 처음 컨테이너를 띄우는 과정은 매우 간단하지만, 도커에 대해서 잘 모르기 떄문에 아직은 어렵다. 도커를 잘 아는 개발자라면 도커가 편할 것이다. 



## 테스트 데이터 입력

#### 웹UI 에서 데이터 입력해보기
Collection 은 단순하게 테이블과 유사하다고 생각하면 된다. Type 은 Document 와 Edge 가 있다. Document 는 JSON 형태의 데이터를 저장하는 NO-SQL Collection 이다. Collection 이 생성이 되었다. 이제 쿼리를 작성해보자. 좌측 쿼리 메뉴를 클릭하고 아래와 같이 작성한 다음에, Explain 버튼을 클릭하자.  참고로 AQL 구문이라는 arangoDB에서만 사용할 수 있는 쿼리문이다. coffee 라는 콜렉션을 만들고 아래와 같이 AQL 구문으로 데이터를 입력해보자. 
```
INSERT {name: "모카", price: 1200}
INTO coffee
```
Coffee Collection 에 보면 아래와 같이 데이터가 생긴 것을 확인할 수 있을 것이다.
01.png

AQL 구문에 대한 가이드는 레퍼런스를 참고하자. 필자는 AQL 구문이 매우 어색하기 때문에 나중에 공부하기로 했다.

#### 영화배우, 영화, 영상 관계를 만들어보자.
테스트 데이터로 영화배우와 영화라는 컨셉으로 만들어봤다. Graph DB 를 간단하게 공부하기에는 좋다는 판단이다. 

위에서 설명한 Graph 개념을 한번 더 생각해보자. 

- Vertex(Node) : 속성을 가지는 엔터티
- Arc(Edge) : 노드 사이의 선(연결 link), 방향성(가리키는 방향)을 가질 수 있음.
- Property : Node나 Edge에 할당된 값, 이름과 값으로 구성됨

영화배우 또는 영화, 영상 등은 Node 가 된다. ArangoDB 에서는 Document 타입의 콜렉션이 되는 것이다. 
그리고 "영화배우가 어떤 영화에 출연했다" 라는 관계를 나타내는 것이 바로 Arc, Edge 라고 하는데 ArangoDB 에서는 Edge 타입의 콜렉션이 된다.  actors, movies 라는 document 타입의 콜렉션을 생성한다. 그리고 actedin 이라는 edge 를 생성한다. 

자 그림은 아래와 같다. 

(방향성 그림 추가)


actors Document에 송강호 와 임시완 이라는 데이터를 만들고, movies 에는 변호인이라는 데이터를 만든다. 그 다음에 송강호, 임시완을 변호인를 가리키도록 edge 에 데이터를 넣는다. 추가로 김태희 라는 actor 데이터와 그랑프리 라는 영화 데이터를 추가로 넣고 연결해 보자. 이런식으로 영화 배우와 영화, 그리고 추가로 각 영화들의 영상 데이터를 입력해서 넣어봤다. 

데이터를 입력하는 방법에는 여러가지가 있다.   

- AQL 구문으로 입력
- WebUI 에서 플러스(+) 버튼으로 직접 입력
- JSON 파일 업로드
- 쉘(Arango쉘) 에서 입력, 서버 접속해서 콘솔에서 입력하는 것이다.
- HTTP Rest 전송으로 입력
- Spring Data 프로그래밍 구현
등등... 
내가 모르는 어떤 방법이 또 있을수도 있다. ArangoDB 를 공부한지 몇일 안되었기 때문에 잘 모른다. 

Graph.png 참고..


## Spring Data ArangoDB - Select
Spring에서 ArangoDB 를 연동한다.

#### 기본 설정
- intellij
- Spring Boot 2.0
- Gradle 4.X

참고 링크
[https://www.arangodb.com/tutorials/spring-data/part1-getting-started/](https://www.arangodb.com/tutorials/spring-data/part1-getting-started/)
[https://github.com/arangodb/spring-data#getting-started](https://github.com/arangodb/spring-data#getting-started)


#### 디펜던시
먼저 Gradle 디펜던시를 추가한다. 

```
compile('com.arangodb:arangodb-spring-data:2.0.3')
```
시간을 내서 Spring Data 프로젝트를 꼭 확인해보자. 
http://projects.spring.io/spring-data/


#### Config 설정

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
arangodb.host=119.205.***.***  
arangodb.port=8529  
arangodb.user=root  
arangodb.password=****
```

필자는 아래와 같이 작성하였다. 

참고로, property 파일에 작성하는게 더 깔끔하지만, 필자는 테스트 소스라서 아래와 같이 간단하게 연동한다. 

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

#### Entity 와  Repository 선언
Spring 에서 먼저 @Document 어노테이션이 선언된 Actors 클래스를 만들자. 

```java
@Document("actors")  
public class Actors {  
  
    @Id  
  private String id;  
  
 private String name;  
  
 public String getName() {  
        return name;  
  }  
  
    @Relations(edges = Actedin.class)  
    private Collection<Movies> movies;  
  
 public Collection<Movies> getMovies() {  
        return movies;  
  }  
}
```

```java
@Edge("actedin")  
public class Actedin {  
  
    @From  
  private Actors actor;  
  
  @To  
  private Movies movies;  
  
 public Actedin(final Actors actors, final Movies movies){  
        super();  
 this.actor = actors;  
 this.movies = movies;  
  }  
}
```

```java 
@Document("movies")  
public class Movies {  
    private String name;  
  
 public String getName() {  
        return name;  
  }  
}
```

arangodb-spring-data 에서는 ArangoRepository 를 제공한다. 해당 Repository 는 PagingAndSortingRepository 와 QueryByExampleExecutor 를 상속받는다. 다들 아시겠지만, PagingAndSortingRepository 는 CrudRepository 를 상속받는다. 

> ArangoRepository 를 사용하면 CRUD 를 심플하게 구현할 수 있다.

```java
import com.arangodb.springframework.repository.ArangoRepository;  
  
import java.util.Optional;  
  
public interface ActorsRepository extends ArangoRepository<Actors> {  
    Optional<Actors> findByName(String name);  
  
  Iterable<Actors> findByMoviesName(String name);  
  
}
```
- **findByName** 는 이름으로 조회한다. 
- **findByMoviesName** 는 Edge 를 참고하여 영화 리스트를 조회한다.

findBy**** 등으로 선언하여 조회할 수 있고 Order , Between 등 도 구현할 수 있다. Spring Data 를 다들 잘 안다는 가정하에 이정도로 설명하고 패스한다.

아래와 같이 Between 구문은 아래와 같이 구현하면 된다. (참고용)
```java  
public interface CoffeeRepository extends ArangoRepository<Coffee> {    
    
    Iterable<Coffee> findByChildsAgeBetween(int lowerBound, int upperBound);    
 }  
```

#### 리스트 조회 샘플 테스트

모든 영화 배우를 조회해보자. Spring Data Arangodb 에서 제공하는 ArangoRepository는 PagingAndSortingRepository 를 상속받기 때문에, paging 기능도 제공할 것이다. 

```
@GetMapping("/actors")  
public Page<Actors> list(Pageable pageable) {  
  
    Page<Actors> actors = actorsRepository.findAll(pageable);  
 return actors;  
}
```

> http://localhost:8080/actors?page=0&size=5

파라미터로 page는 해당 페이지, size 는 페이지의 사이즈 값을 넘겨주면 된다. 리턴 값은 Page<T> 로 구현하면 된다. 

참고로, ActorsRepository 를 선언하지 않았더라도 아래와 같이 사용할 수도 있다. 
```java
@GetMapping("/actors")  
public Page<Actors> list(Pageable pageable) {  
  
 Page<Actors> actors = (new SimpleArangoRepository(arangoOperations, Actors.class).findAll(pageable)); return actors;}
```

이번에는 특정 영화에 출연했던 배우를 모두 조회해보자. findByMoviesName 메소드를 활용한다. 

Repository 에 아래와 같이 메소드를 선언이 되어있다.
```java
Iterable<Actors> findByMoviesName(String name);
```

```java
@GetMapping("/actors")  
public Collection<Actors> listActorsByMovie(@RequestParam(name="movie") String movieName) {  
  
    return StreamSupport.stream(actorsRepository.findByMoviesName(movieName).spliterator(), true)  
            .collect(Collectors.toList());  
  
}
```



반대로 특정 배우가 출연했던 영화를 모두 조회해보자. 임시완배우가 출연한 영화는 아래와 같다. 
```java
@GetMapping("/movies")  
public Collection<Movies> listByActedin(@RequestParam(name="name") String name) {  
    return actorsRepository.findByName("임시완").get().getMovies();  
}
```
간단하게 다양한 조건에서 테스트를 해봤다. 근데, 뭔가 아쉽다. 관계형 조회를 조금 더 복잡하게 해보자.

임시완이 출연한 영화들의 영상들을 조회하고 싶다면, 어떻게 할까?  아래와 같은 구조이다.

***배우(임시완) ---> 영화 <--- 영상***

일단 영상 클래스(Document)와 영상과영화를 연결해주는 Edge 를 선언한다.

```java
@Document("videos")  
public class Videos {  
    private String name;  
  
 public String getName() {  
        return name;  
  }  
  
    @Relations(edges = VideoGroup.class)  //중요!! 영화,영상 의 Edge 연결한다.
    private Collection<Movies> movies;  
}
```

```java
@Edge("videoGroup")  
public class VideoGroup {  
    @Id  
  private String id;  
  
  @From  
  private Videos videos;  
  
  @To  
  private Movies movies;  
  
 public VideoGroup(final Videos videos, final Movies movies){  
        super();  
 this.videos = videos;  
 this.movies = movies;  
  }  
}
```

```java
public interface VideosRepository extends ArangoRepository<Videos> {  
    Iterable<Videos> findByMoviesName(String name); //영화 이름으로 영상을 찾는다.
}
```

검색 방법은 일단 임시완이 출연한 영화를 먼저 검색하고, 영화를 기준으로 해당 영화에 포함되어있는 영상을 찾으면 될까?? 맞는 방법인지는 모르겠지만, 이렇게 해보자. 

참고로 Stream 이 중복으로 들어가서 깔끔하지가 않지만, 대충 만들면 이렇게 된다. 

```java
Collection<Videos> videos = new ArrayList<>();  
  
actorsRepository.findByName(actorName).get().getMovies().stream().forEach(ar -> {  
    StreamSupport.stream(videosRepository.findByMoviesName(ar.getName()).spliterator(), true).forEach(az -> {  
            videos.add(az);  
  });  
});  
return videos;
```

## Spring Data ArangoDB - Insert, Update















## 참고
이런저런 참고 사항을 나름 객관적인 관점에서 정리한다. 
(주관적인 의견이 추가되기 시작하며 글이 엉망이 될것이다...)

#### 업계에서 사용하는 Graph DB 
현재 가장 많이 사용중인 GraphDB는 Neo4j 이고 ArangoDB, OrientDB 등 다양한 솔루션이 유료 또는 오픈소스로 사용 중이다. 근데, 이런 GraphDB 들 간에 사용하는 용어가 조금씩 다르다. 또한, 기능도 완전히 같지는 않다. 참고로 이런 이유로 인해서 미들웨어 단에 적용할 수 있는 인터페이스를 제공해주는 라이브러리도 있다. 이런 인터페이스 들은, GraphDB 를 바꿔야할 상황이 생길 경우에 적합할 것이다. 
https://github.com/tinkerpop/blueprints

참고로 그래프DB 랭킹은 2018년 4월 기준으로 아래와 같다.  db-engines.com 이라는 사이트를 참고하였다. 참고로 Neo4j가 압도적인 1등이다. 

1. [Neo4j](https://db-engines.com/en/system/Neo4j)
2. [Microsoft Azure Cosmos DB](https://db-engines.com/en/system/Microsoft+Azure+Cosmos+DB)
3. [Datastax Enterprise](https://db-engines.com/en/system/Datastax+Enterprise)
4. [OrientDB](https://db-engines.com/en/system/OrientDB)
5. [ArangoDB](https://db-engines.com/en/system/ArangoDB)
6. [Virtuoso](https://db-engines.com/en/system/Virtuoso)
7. [Giraph](https://db-engines.com/en/system/Giraph)
8. [Amazon Neptune](https://db-engines.com/en/system/Amazon+Neptune)
9. [AllegroGraph](https://db-engines.com/en/system/AllegroGraph)
10.[Stardog](https://db-engines.com/en/system/Stardog) 

참고로 제일 많이 사용하는 Neo4j 와 Giraph 두 솔루션만 Only Graph Model 이고 나머지는 Multi-Model 이다. 

#### 카카오의 S2Graph
카카오는 자체 오픈소스 Graph DB 를 구축하여 운영중이다. 
-   S2Graph  [공식 문서](https://steamshon.gitbooks.io/s2graph-book/content/)


#### Spring 에서의 GraphDB 
문서 작성 중..




## 참고문서


