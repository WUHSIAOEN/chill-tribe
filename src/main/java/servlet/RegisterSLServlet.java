package test.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//⽤@WebServlet註冊此Servlet，並做以下設定..
//• 設定2個映射網址:"/registerSL"、"/REGISTER_SL"
//• 設定Servlet名稱:"registerSLServlet"
//• 設定啟動順序:1
//• 設定2個初始化參數:name→你的名字，height→你的⾝⾼
//• 覆寫doGet()，由response物件取得Writer物件，並寫出初始化參數的名稱和值

//@WebServlet(urlPatterns = { "/registerSL", "/REGISTER_SL" }, 
//name = "registerSLServlet", 
//loadOnStartup = 1, 
//initParams = {
//				@WebInitParam(name = "name", value = "Ernie"), 
//				@WebInitParam(name = "height", value = "170") 
//			}
//)

public class RegisterSLServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Writer writer = resp.getWriter();
		ServletConfig config = getServletConfig();
		Enumeration<String> initParamNames = config.getInitParameterNames();
		while (initParamNames.hasMoreElements()) {
			String name = initParamNames.nextElement();
			String value = config.getInitParameter(name);
			writer
			.append(name)
			.append(": ")
			.append(value)
			.append("\n");
		}
	}
}
