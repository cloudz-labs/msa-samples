package io.dtlabs.registration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mailing.to")
public class MailListConfiguration {

	private List<String> list;

	public MailListConfiguration() {
		this.list = new ArrayList<String>();
	}

	public List<String> getList() {
		return this.list;
	}

	public String[] getArray() {
		return (String[]) this.list.toArray(new String[list.size()]);
	}
}
