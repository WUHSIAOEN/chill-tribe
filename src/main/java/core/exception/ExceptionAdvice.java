package core.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	
private static final Logger logger = LogManager.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public String handlerExceptionString(Exception e) {
		logger.error(e.getMessage(), e);
//		錯誤畫面待刻
		return "/WEB-INF/chilltribe";
	}
	
}
