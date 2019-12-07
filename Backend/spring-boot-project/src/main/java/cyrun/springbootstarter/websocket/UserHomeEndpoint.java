package cyrun.springbootstarter.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import cyrun.springbootstarter.order.OrderService;

@ServerEndpoint("/userhome")
@Component
public class UserHomeEndpoint {

	private static HashMap<String, UserSession> connectedUsers;

	public UserHomeEndpoint() {
		connectedUsers = new HashMap<String, UserSession>();
	}

	@OnOpen
	public void onOpen(Session session) throws IOException {
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		System.out.println("Adding netid");
		// First message sent will be netid
		connectedUsers.put(session.getId(), new UserSession(session, message));
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		// Remove session from the List
		connectedUsers.remove(session.getId());
	}

	@OnError
	public void onError(Throwable throwable) {
	}

/*	@Scheduled(fixedDelay = 1000)
	public void pushUpdatedOrders() throws IOException {
		this.sendMessage();
		
	}*/

	public void sendMessage(String message, String netid) throws IOException {
		
		for (Map.Entry<String, UserSession> connectedUser : connectedUsers.entrySet()) {
			System.out.println("Sending via socket 2");

			if(connectedUser.getValue().getNetid().equals(netid))
			{
				connectedUser.getValue().getSession().getBasicRemote().sendText(message);
			}
		}

	}

}
