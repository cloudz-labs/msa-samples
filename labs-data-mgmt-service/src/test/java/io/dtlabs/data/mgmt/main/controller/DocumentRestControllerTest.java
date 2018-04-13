package io.dtlabs.data.mgmt.main.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import io.dtlabs.data.mgmt.category.vo.Category;
import io.dtlabs.data.mgmt.main.vo.Document;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DocumentRestControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
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
	public void getDocumentsShouldReturnMakeAndModel() {
		ResponseEntity<List> entity =
				restTemplate.getForEntity("/v1/docs", List.class, (Object) null);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().size()).isNotZero();
	}

	@Test
	public void addDocumentShouldReturnMakeAndModel() throws Exception {
		MockMultipartFile file = new MockMultipartFile("files", "filename.txt", "text/plain", "some text".getBytes());
		
		webAppContextSetup(this.wac).build()
			.perform(fileUpload("/v1/docs")
					.file(file)
					.param("title", "test document")
					.param("contents", "test contents")
					.param("links[0].title", "link sample")
					.param("links[0].path", "http://dtlabs.skcc.com")
					.param("attachments[0].title", "text sample"))
			.andExpect(status().isOk());
	}

	@Test
	public void searchDocumentsShouldReturnMakeAndModel() {
		ResponseEntity<List> entity =
				restTemplate.getForEntity("/v1/docs/search?q={query}", List.class, document.getTitle());
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().size()).isNotZero();
	}

	@Test
	public void getDocumentShouldReturnMakeAndModel() {
		ResponseEntity<Document> entity =
				restTemplate.getForEntity("/v1/docs/{id}", Document.class, document.getId());
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getId()).isEqualTo(document.getId());
	}

	@Test
	public void modifyDocumentShouldReturnMakeAndModel() throws Exception {
		MockMultipartFile file = new MockMultipartFile("files", "filename.txt", "text/plain", "some text".getBytes());
		
		webAppContextSetup(this.wac).build()
			.perform(fileUpload("/v1/docs/" + document.getId())
					.file(file)
					.param("id", document.getId())
					.param("title", "test document")
					.param("contents", "test contents")
					.param("links[0].title", "link sample")
					.param("links[0].path", "http://dtlabs.skcc.com")
					.param("attachments[0].title", "text sample"))
			.andExpect(status().isOk());
	}

	@Test
	public void removeDocumentShouldReturnMakeAndModel() {
		restTemplate.delete("/v1/docs/{id}", document.getId());
	}

	@Test
	public void downloadShouldReturnMakeAndModel() throws Exception {
		webAppContextSetup(this.wac).build()
				.perform(get("/v1/docs/" + document.getId() + "/attachments/" + "filename.txt"))
			.andExpect(status().isOk())
			.andExpect(content().string("some text"));
	}
	
}
