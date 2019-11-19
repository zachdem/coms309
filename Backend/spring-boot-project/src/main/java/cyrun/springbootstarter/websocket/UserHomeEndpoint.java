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

	private static OrderService orderService;

	@Autowired
	public void setUserService(OrderService orderServ) {
		orderService = orderServ;
	}

	private static HashMap<Session, String> connectedUsers;

	public UserHomeEndpoint() {
		connectedUsers = new HashMap<Session, String>();
	}

	@OnOpen
	public void onOpen(Session session) throws IOException {
		session.getBasicRemote().sendText("Connected");
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		System.out.println("Adding netid");
		// First message sent will be netid
		session.getBasicRemote().sendText("Adding Entry");
		connectedUsers.put(session, message);
		this.sendMessage();
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		// Remove session from the List
		connectedUsers.remove(session);
	}

	@OnError
	public void onError(Throwable throwable) {
	}

	@Scheduled(fixedDelay = 10000)
	public void pushUpdatedOrders() throws IOException {
		this.sendMessage();
		
	}

	public void sendMessage() throws IOException {

		for (Map.Entry<Session, String> connectedUser : connectedUsers.entrySet()) {
			Gson g = new Gson();
			String userOrders = g.toJson(orderService.getUserOrders(connectedUser.getValue()));
			connectedUser.getKey().getBasicRemote().sendText(userOrders);
		}

	}

}
