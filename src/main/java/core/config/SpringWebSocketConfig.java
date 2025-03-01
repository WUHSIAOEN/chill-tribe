package core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import core.websocket.ChatWebSocketHandler;

//待確認那些功能要使用再改
//web 部屬描述檔也還沒加
public class SpringWebSocketConfig implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/member/chat"); // 帶修改聊天室頁面
	}
	
	@Bean
	 public WebSocketHandler webSocketHandler() {
	  return new ChatWebSocketHandler();
	 }
	

}
