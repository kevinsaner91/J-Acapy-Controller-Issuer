package simplewebapp.beans;

public class ProofExchangeRecord {
	
	private String role;
	private String connection_id;
	private String create_at;
	private String state;
	private String presentationExchangeId;
	
	public ProofExchangeRecord(String role, String connection_id, String create_at, String state,
			String presentationExchangeId) {
		super();
		this.role = role;
		this.connection_id = connection_id;
		this.create_at = create_at;
		this.state = state;
		this.presentationExchangeId = presentationExchangeId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getConnection_id() {
		return connection_id;
	}
	public void setConnection_id(String connection_id) {
		this.connection_id = connection_id;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPresentationExchangeId() {
		return presentationExchangeId;
	}
	public void setPresentationExchangeId(String presentationExchangeId) {
		this.presentationExchangeId = presentationExchangeId;
	}
	
	@Override
	public String toString() {
		return "ProofExchangeRecord [role=" + role + ", connection_id=" + connection_id + ", create_at=" + create_at
				+ ", state=" + state + ", presentationExchangeId=" + presentationExchangeId + "]";
	}

}
