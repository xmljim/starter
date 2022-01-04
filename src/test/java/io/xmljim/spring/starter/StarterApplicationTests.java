package io.xmljim.spring.starter;

import io.xmljim.spring.starter.domain.Person;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StarterApplicationTests {


	@Autowired
	TestRestTemplate template;


	@Test
	@DisplayName("Add a person")
	@Order(1)
	void testAdd() {
		Person person = new Person();
		person.setFirstName("Jim");
		person.setLastName("Earley");
		person.setAge(53);

		HttpEntity<Person> entity = new HttpEntity<>(person);
		ResponseEntity<Person> response = template.exchange("/persons", HttpMethod.POST, entity, Person.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		Assertions.assertTrue(response.getBody().getId() > 0);
	}

	@Test
	@DisplayName("Find person by id")
	@Order(2)
	void testFindById() {
		Person person = new Person();
		person.setFirstName("Jim");
		person.setLastName("Earley");
		person.setAge(53);
		person.setId(1);
		HttpEntity<Person> entity = new HttpEntity<>(person);
		ResponseEntity<Person> response = template.exchange("/persons", HttpMethod.POST, entity, Person.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		Assertions.assertTrue(response.getBody().getId() > 0);
		int id = response.getBody().getId();
		Person p = template.getForObject("/persons/{id}", Person.class, id);
		Assertions.assertNotNull(p);
	}
}
