package simplewebapp.beans;

import com.owlike.genson.annotation.JsonProperty;

public class Connection {
	
	private String connection_id;	
	private Invitation invitation;
	private String invitation_url;
	
	public Connection() {
		
	}
	
	public Connection(@JsonProperty("connection_id") String connection_id, @JsonProperty("invitation") Invitation invitation, @JsonProperty("invitation_url") String invitation_url) {
		this.connection_id = connection_id;
		this.invitation = invitation;
		this.invitation_url = invitation_url;
	}
	
	public String getConnection_id() {
		return connection_id;
	}

	public void setConnection_id(String connection_id) {
		this.connection_id = connection_id;
	}
	
	public Invitation getInvitation() {
		return invitation;
	}
	
	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}

	public String getInvitation_url() {
		return invitation_url;
	}

	public void setInvitation_url(String invitation_url) {
		this.invitation_url = invitation_url;
	}
	
}

