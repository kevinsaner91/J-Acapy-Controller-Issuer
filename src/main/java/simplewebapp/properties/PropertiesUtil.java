package simplewebapp.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {

	private String faberAgentURL;
	private String scheme;

	private void setProperties() {
		Properties prop = new Properties();
		
		try {
			InputStream input = new FileInputStream("/usr/local/tomcat/webapps/config.properties");
			prop.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		faberAgentURL = prop.getProperty("faberAgentURL", "127.0.0.1:9021");
		scheme = prop.getProperty("scheme", "http");
		
		System.out.println(faberAgentURL);
	}

	public String getFaberAgentURL() {
		setProperties();
		return faberAgentURL;
	}
	
	public String getScheme() {
		setProperties();
		return scheme;
	}


}
