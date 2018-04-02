package io.labs.data.mgmt;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.labs.data.mgmt.category.controller.CategoryRestController;
import io.labs.data.mgmt.main.controller.DocumentRestController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LabsDataMgmtServiceApplicationTests {
	
	@Autowired
	private CategoryRestController categoryRestController;
	
	@Autowired
	private DocumentRestController documentRestController;

	@Test
	public void contextLoads() {
		assertThat(categoryRestController).isNotNull();
		assertThat(documentRestController).isNotNull();
	}

}
