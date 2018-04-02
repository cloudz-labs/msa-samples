package io.labs.data.mgmt.category.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.labs.data.mgmt.category.vo.Category;
import io.labs.data.mgmt.main.vo.Document;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CategoryRestControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	private Category category;
	
	private Document document;
	
	@Before
	public void before() {
		category = new Category();
		category.setId("1");
		category.setName("Category1");
		category.setOrder(1);
		mongoTemplate.save(category);
		
		document = new Document();
		document.setId("1");
		document.setTitle("test document");
		document.setContents("test document content");
		document.setCategories(Arrays.asList("1"));
		mongoTemplate.save(document);
	}
	
	@Test
	public void getCategoriesShouldReturnMakeAndModel() {
		ResponseEntity<List> entity =
				restTemplate.getForEntity("/v1/categories", List.class, (Object) null);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().size()).isNotZero();
	}
	
	@Test
	public void addCategoryShouldReturnMakeAndModel() {
		ResponseEntity<List> entity =
				restTemplate.postForEntity("/v1/categories", category, null);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void getCategoryShouldReturnMakeAndModel() {
		ResponseEntity<Category> entity =
				restTemplate.getForEntity("/v1/categories/{id}", Category.class, category.getId());
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getId()).isEqualTo(category.getId());
	}
	
	@Test
	public void getDocumentsShouldReturnMakeAndModel() {
		ResponseEntity<List> entity =
				restTemplate.getForEntity("/v1/categories/{id}/docs", List.class, category.getId());
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().size()).isNotZero();
	}
	
	@Test
	public void getCategoryDocumentsShouldReturnMakeAndModel() {
		ResponseEntity<List> entity =
				restTemplate.getForEntity("/v1/categories/docs", List.class, (Object) null);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().size()).isNotZero();
	}
	
}
