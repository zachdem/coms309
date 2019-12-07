package cyrun.springbootstarter.websocket;
import javax.websocket.Session;


public class UserSession {
	
	private Session session;
	private String netid;
	
	public UserSession(Session session, String netid) {
		this.session = session;
		this.netid = netid;
	}
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public String getNetid() {
		return netid;
	}
	public void setNetid(String netid) {
		this.netid = netid;
	}
	
	

}
