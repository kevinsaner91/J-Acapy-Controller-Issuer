package simplewebapp.beans;

public class Credential {
	


	private String referent;
	
	private String attr1Key;
	private String attr2Key;
	private String attr3Key;
	private String attr4Key;
	private String attr5Key;
	
	private String attr1Value;
	private String attr2Value;
	private String attr3Value;
	private String attr4Value;
	private String attr5Value;	
	
	public Credential() {};
	
	public Credential(String referent, String attr1Key, String attr2Key, String attr3Key, String attr4Key, String attr5Key,
			String attr1Value, String attr2Value, String attr3Value, String attr4Value, String attr5Value) {
		super();
		this.setReferent(referent);
		this.attr1Key = attr1Key;
		this.attr2Key = attr2Key;
		this.attr3Key = attr3Key;
		this.attr4Key = attr4Key;
		this.attr5Key = attr5Key;
		this.attr1Value = attr1Value;
		this.attr2Value = attr2Value;
		this.attr3Value = attr3Value;
		this.attr4Value = attr4Value;
		this.attr5Value = attr5Value;
	}
	
	public String getAttr1Key() {
		return attr1Key;
	}
	public void setAttr1Key(String attr1Key) {
		this.attr1Key = attr1Key;
	}
	public String getAttr2Key() {
		return attr2Key;
	}
	public void setAttr2Key(String attr2Key) {
		this.attr2Key = attr2Key;
	}
	public String getAttr3Key() {
		return attr3Key;
	}
	public void setAttr3Key(String attr3Key) {
		this.attr3Key = attr3Key;
	}
	public String getAttr4Key() {
		return attr4Key;
	}
	public void setAttr4Key(String attr4Key) {
		this.attr4Key = attr4Key;
	}
	public String getAttr5Key() {
		return attr5Key;
	}
	public void setAttr5Key(String attr5Key) {
		this.attr5Key = attr5Key;
	}
	public String getAttr1Value() {
		return attr1Value;
	}
	public void setAttr1Value(String attr1Value) {
		this.attr1Value = attr1Value;
	}
	public String getAttr2Value() {
		return attr2Value;
	}
	public void setAttr2Value(String attr2Value) {
		this.attr2Value = attr2Value;
	}
	public String getAttr3Value() {
		return attr3Value;
	}
	public void setAttr3Value(String attr3Value) {
		this.attr3Value = attr3Value;
	}
	public String getAttr4Value() {
		return attr4Value;
	}
	public void setAttr4Value(String attr4Value) {
		this.attr4Value = attr4Value;
	}
	public String getAttr5Value() {
		return attr5Value;
	}
	public void setAttr5Value(String attr5Value) {
		this.attr5Value = attr5Value;
	}

	public String getReferent() {
		return referent;
	}

	public void setReferent(String referent) {
		this.referent = referent;
	}
	
	@Override
	public String toString() {
		return "Credential [referent=" + referent + ", attr1Key=" + attr1Key + ", attr2Key=" + attr2Key + ", attr3Key="
				+ attr3Key + ", attr4Key=" + attr4Key + ", attr5Key=" + attr5Key + ", attr1Value=" + attr1Value
				+ ", attr2Value=" + attr2Value + ", attr3Value=" + attr3Value + ", attr4Value=" + attr4Value
				+ ", attr5Value=" + attr5Value + "]";
	}
}
