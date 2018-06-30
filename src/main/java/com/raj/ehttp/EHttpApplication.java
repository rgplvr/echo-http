package com.raj.ehttp;

import java.io.File;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raj.ehttp.api.CreateEcho;

import com.raj.ehttp.filters.CatchAllFilter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

@Component
public class EHttpApplication extends Application<EHttpConfiguration> {

	public static void main(final String[] args) throws Exception {

		(new EHttpApplication()).run("server", "config.yml");
	}

	@Override
	public String getName() {
		return "echo-http";
	}

	@Override
	public void initialize(final Bootstrap<EHttpConfiguration> bootstrap) {

	}

	@Override
	public void run(final EHttpConfiguration configuration, final Environment environment) {

		File file = new File("uriconfigs");
		if (!new File("uriconfigs").exists())
			file.mkdir();
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.raj");
		context.getBeanFactory().registerSingleton("mapper", new ObjectMapper());
		context.getBeanFactory().registerSingleton("config", configuration);
		context.refresh();
		environment.jersey().register(context.getBean(CreateEcho.class));

		environment.servlets().addFilter("catchall", (Filter) context.getBean(CatchAllFilter.class))
				.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

	}

}
