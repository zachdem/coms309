package cyrun.springbootstarter.websocket;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import cyrun.springbootstarter.order.OrderService;

@Controller
public class WebSocketHandler extends AbstractWebSocketHandler {
	
	
	private static OrderService orderService;

	@Autowired
	public void setUserService(OrderService orderServ){
	    orderService = orderServ;
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
	    System.out.println("New Text Message Received");
	    session.sendMessage(message);
	    /*SimpleMessageConverter converter = new SimpleMessageConverter();
	    converter.createMessageForString(orderService.getUserOrders("test_netid").toString(), session)
	    TextMessage mess = new TextMessage();
	    orderService.getUserOrders("test_netid").toString().to;

	    session.sendMessage();
	    orderService.getUserOrders("test_netid");*/

	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
	    System.out.println("New Binary Message Received");
	    session.sendMessage(message);
	}
	

}
