package simplewebapp.beans;

public class Creddef {
	
	private String id;
	private String tag;
	private String version;
	
	public Creddef() {};
	
	public Creddef(String id, String tag, String version) {
		super();
		this.id = id;
		this.tag = tag;
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Creddef [id=" + id + ", tag=" + tag + ", version=" + version + "]";
	}
	
	

}
