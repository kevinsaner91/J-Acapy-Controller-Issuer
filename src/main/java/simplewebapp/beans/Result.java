package simplewebapp.beans;

import com.owlike.genson.annotation.JsonProperty;

public class Result {

	private String request_id;
	private String state;
	private String routing_state;
	private String my_did;
	private String connection_id;
	private String invitation_key;
	private String their_label;
	private String their_did;
	private String initiator;
	private String updated_at;
	private String invitation_mode;
	private String accept;
	private String created_at;
	
	public Result() {
		
	}

	public Result(@JsonProperty("request_id") String request_id, @JsonProperty("state") String state,
			 @JsonProperty("routing_state") String routing_state,
			@JsonProperty("my_did") String my_did, @JsonProperty("connection_id") String connection_id,
			@JsonProperty("invitation_key") String invitation_key, @JsonProperty("their_label") String their_label,
			@JsonProperty("their_did") String their_did, @JsonProperty("initiator") String initiator,
			@JsonProperty("updated_at") String updated_at, @JsonProperty("invitation_mode") String invitation_mode,
			@JsonProperty("accept") String accept, @JsonProperty("created_at") String created_at) {
		this.request_id = request_id;
		this.state = state;
		this.setRouting_state(routing_state);
		this.my_did = my_did;
		this.connection_id = connection_id;
		this.invitation_key = invitation_key;
		this.their_label = their_label;
		this.their_did = their_did;
		this.initiator = initiator;
		this.updated_at = updated_at;
		this.invitation_mode = invitation_mode;
		this.accept = accept;
		this.created_at = created_at;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMy_did() {
		return my_did;
	}

	public void setMy_did(String my_did) {
		this.my_did = my_did;
	}

	public String getConnection_id() {
		return connection_id;
	}

	public void setConnection_id(String connection_id) {
		this.connection_id = connection_id;
	}

	public String getInvitation_key() {
		return invitation_key;
	}

	public void setInvitation_key(String invitation_key) {
		this.invitation_key = invitation_key;
	}

	public String getTheir_label() {
		return their_label;
	}

	public void setTheir_label(String their_label) {
		this.their_label = their_label;
	}

	public String getTheir_did() {
		return their_did;
	}

	public void setTheir_did(String their_did) {
		this.their_did = their_did;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getInvitation_mode() {
		return invitation_mode;
	}

	public void setInvitation_mode(String invitation_mode) {
		this.invitation_mode = invitation_mode;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getRouting_state() {
		return routing_state;
	}

	public void setRouting_state(String routing_state) {
		this.routing_state = routing_state;
	}
}

