package simplewebapp.beans;

import java.util.List;

import com.owlike.genson.annotation.JsonProperty;

public class Invitation {
	
	private String type;
	private String id;
	private List<String> recipientKeys;
	private String serviceEndpoint;
	
	public Invitation() {
		
	}
	
	public Invitation(@JsonProperty("@type") String type, @JsonProperty("@id") String id,@JsonProperty("recipientKeys") List<String> recipientKeys, @JsonProperty("serviceEndpoint") String serviceEndpoint) {
		this.type = type;
		this.id = id;
		this.recipientKeys = recipientKeys;
		this.serviceEndpoint = serviceEndpoint;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getRecipientKeys() {
		return recipientKeys;
	}

	public void setRecipientKeys(List<String> recipientKeys) {
		this.recipientKeys = recipientKeys;
	}

	public String getServiceEndpoint() {
		return serviceEndpoint;
	}

	public void setServiceEndpoint(String serviceEndpoint) {
		this.serviceEndpoint = serviceEndpoint;
	}
	
	
	

}
