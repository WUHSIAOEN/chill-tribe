package core.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({ "core.exception", "web.*.controller" })
public class SpringMvcConfig implements WebMvcConfigurer {
	
//	因預設首頁須配合ViewResolver使用，ViewResolver 需要刪掉
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setContentType("text/html;charset=UTF-8");
//		registry.viewResolver(viewResolver);
//	}

//	託管靜態資源 - 重新映射靜態資源
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/");
	}

//	託管CommonsMultipartResolver元件 - 檔案上下傳
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSizePerFile(500 * 1024 * 1024);
		resolver.setMaxUploadSize(4L * 1024 * 1024 * 1024);
		return resolver;
	}
	
//	託管/註冊MappingJackson2HttpMessageConverter
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setPrettyPrint(true);
		converters.add(messageConverter);
	}
	
//	預設首頁 - 取代web.xml 的welcome file
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry
			.addViewController("/")
			.setViewName("chilltribe.html");
	}
}
