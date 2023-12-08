package com.client.clientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

//@EnableFeignClients(basePackageClasses = com.client.clientui.proxies.MicroserviceProduitsProxy.class)
@SpringBootApplication
//@ImportResource({"classpath*:application-context.xml"})
public class ClientUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientUiApplication.class, args);
	}

}
