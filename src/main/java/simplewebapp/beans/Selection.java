package simplewebapp.beans;

public class Selection {
	
	private String connection;
	private String schema;
	private String creddef;
	
	public Selection() {}
	
	public Selection(String connection, String schema, String creddef) {
		super();
		this.connection = connection;
		this.schema = schema;
		this.creddef = creddef;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getCreddef() {
		return creddef;
	}

	public void setCreddef(String creddef) {
		this.creddef = creddef;
	}
	
	
	
	

}
