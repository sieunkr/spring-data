package spring.jedis.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		JedisPool pool = new JedisPool(jedisPoolConfig, "119.205.221.42", 6379, 1000, "1234");

		Jedis jedis = pool.getResource();

		/*
		//1.기본 데이터
		jedis.set("key_test01", "1");
		System.out.println(jedis.get("test01"));

		//2.Hash 데이터
        // 키 = "key_member:key_n3140"
        jedis.hset("key_member:key_n3140", "name", "sieun");
        jedis.hset("key_member:key_n3140", "team", "portalteam");
        Map<String, String> stringMap = jedis.hgetAll("key_member:key_n3140");
        System.out.println(stringMap.get("name"));
        System.out.println(stringMap.get("team"));
		*/

		//3.Set 데이터
        // 키 = "key_food:key_test02"
		jedis.sadd("key_food:key_test02", "apple");
		jedis.sadd("key_food:key_test02", "orange");
		jedis.sadd("key_food:key_test02", "banana");
		jedis.sadd("key_food:key_test02", "orange"); //중복 테스트
		Set<String> foods = jedis.smembers("key_food:key_test02");
		foods.forEach(a -> System.out.println(new String(a.getBytes(), StandardCharsets.UTF_8)));

        
		//4.List 데이터

        //5.Soreted Set 데이터

		pool.close();


	}
}
