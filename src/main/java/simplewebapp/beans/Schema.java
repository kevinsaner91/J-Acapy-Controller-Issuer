package simplewebapp.beans;

public class Schema {
	
	private String schemaId;
	private String schemaVersion;
	private String attributes;
	
	public Schema() {};
	
	public Schema(String schemaId, String schemaVersion, String attributes) {
		super();
		this.schemaId = schemaId;
		this.schemaVersion = schemaVersion;
		this.attributes = attributes;
	}

	public String getSchemaId() {
		return schemaId;
	}

	public void setSchemaId(String schemaId) {
		this.schemaId = schemaId;
	}

	public String getSchemaVersion() {
		return schemaVersion;
	}

	public void setSchemaVersion(String schemaVersion) {
		this.schemaVersion = schemaVersion;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "Schema [schemaId=" + schemaId + ", schemaVersion=" + schemaVersion + ", attributes=" + attributes + "]";
	}
	
	

}
