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
import org.springframework.stereotype.Component;

import cyrun.springbootstarter.order.OrderService;


@ServerEndpoint("/userhome")
@Component
public class UserHomeEndpoint {
	
	@Autowired
	OrderService orderService;
	
	private HashMap<Session, String> connectedUsers;

		
	public UserHomeEndpoint()
	{
		connectedUsers = new HashMap<Session, String>();
	}
	
	@OnOpen
	public void onOpen(Session session) throws IOException {
		session.getBasicRemote().sendText("Connected");
	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {
		//First message sent will be netid
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
	
	
	/*@Scheduled(fixedDelay = 300)
	public void pushUpdatedOrders()
	{
		
	}*/


	/*private void sendUpdatedOrders()
	{

	}*/
	
	public void sendMessage() {
		try {
			
			for(Map.Entry<Session, String> connectedUser : connectedUsers.entrySet())
			{
				System.out.println(orderService.getUserOrders("test_netid"));
				//connectedUser.getKey().getBasicRemote().sendText(orderService.getUserOrders(connectedUser.getValue()).toString());
				connectedUser.getKey().getBasicRemote().sendText("blah");
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
