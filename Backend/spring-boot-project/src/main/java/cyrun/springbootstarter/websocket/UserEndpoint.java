package cyrun.springbootstarter.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
//import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;


@ServerEndpoint("/user")
@Component
public class UserEndpoint {
	
	
	@OnOpen
	public void onOpen(Session session) throws IOException {
		// Get session and WebSocket connection
		session.getBasicRemote().sendText("Connected!");
		
	}

	@OnMessage
	public String onMessage(Session session, String message) throws IOException {
		return message + " (from your server)";
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		// WebSocket connection closes
	}

	@OnError
	public void onError(Throwable throwable) {
		//throwable.printStackTrace();
	}

	/*private static void sendMessage(Session session, String text) {
		try {
			session.getBasicRemote().sendText(text);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}*/

}
