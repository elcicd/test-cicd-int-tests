package stationdemo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {
	
	private String id;
	private String name;
	private boolean hdEnabled;
	private String callSign;
	
	public String getId() {
		return id;
	}
    
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getHdEnabled() {
		return hdEnabled;
	}
    
	public void setHdEnabled(boolean name) {
		this.hdEnabled = hdEnabled;
	}
	
	public String getCallSign() {
		return callSign;
	}
    
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}
}