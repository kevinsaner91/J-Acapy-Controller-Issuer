package simplewebapp.beans;

public class Schema {
	
	private String schemaId;
	private String schemaName;
	private String schemaVersion;
	private String attributes;
	
	public Schema() {};
	
	public Schema(String schemaId, String schemaName, String schemaVersion, String attributes) {
		super();
		this.schemaId = schemaId;
		this.schemaName = schemaName;
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

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	@Override
	public String toString() {
		return "Schema [schemaId=" + schemaId + ", schemaVersion=" + schemaVersion + ", attributes=" + attributes + "]";
	}
	
	

}
