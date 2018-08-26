package spring.data.redis.cacheable;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import spring.data.redis.cacheable.core.entity.Person;
import spring.data.redis.cacheable.core.repository.PersonRepository;

import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
@EnableCaching
public class CacheableApplication implements CommandLineRunner {

	private final PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(CacheableApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Person person01 = new Person("eddy", 1981, Arrays.asList("developer","tester"));
		ObjectMapper oMapper = new ObjectMapper();
		Map map = oMapper.convertValue(person01, Map.class);
		personRepository.getPersonStorage().put("N3004180826", map);
	}
}
