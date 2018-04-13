package io.dtlabs.apigateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@Primary
@EnableAutoConfiguration
@EnableSwagger2
public class SwaggerConfiguration implements SwaggerResourcesProvider {

	@Bean
	public UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
	}

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		resources.add(swaggerResource("dtlabs-registration-service", "/registration-service/v2/api-docs", "2.0"));
		resources.add(swaggerResource("dtlabs-data-mgmt-service", "/data-mgmt-service/v2/api-docs", "2.0"));
		resources.add(swaggerResource("dtlabs-user-oauth-service", "/user-oauth-service/v2/api-docs", "2.0"));
		return resources;
	}
	
	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
	
}
