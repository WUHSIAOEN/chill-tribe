//package core.config;
//
//import javax.servlet.Filter;
//import javax.servlet.MultipartConfigElement;
//import javax.servlet.ServletRegistration.Dynamic;
//
//import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class<?>[] { SpringConfig.class };
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return new Class<?>[] { SpringMvcConfig.class };
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		return new String[] { "/" };
//	}
//
//	// 取代原本web.xml 註冊filter 的寫法
//	// 他也把設定request / response 編碼的設定，取代成filter 方法中的設定
//	@Override
//	protected Filter[] getServletFilters() {
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceRequestEncoding(true);
//	    characterEncodingFilter.setForceResponseEncoding(true);
//
//		OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();
//		return new Filter[] { characterEncodingFilter, openSessionInViewFilter };
//	}
//
//	@Override
//	protected void customizeRegistration(Dynamic registration) {
//		registration.setMultipartConfig(new MultipartConfigElement(""));
//	}
//
//}
