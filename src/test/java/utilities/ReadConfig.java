package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig() {
		
		File src = new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis= new FileInputStream(src);
			prop=new Properties();
			prop.load(fis);
			
		}catch (Exception e) {
			System.out.println("Exception is: "+e.getMessage());
		}
	}
	
	public String getApplicationURL() {
		String url = prop.getProperty("baseUrl");
		return url;
	}
	
	public String getUserName() {
		String username = prop.getProperty("usrname");
		return username;
	}
	
	public String getPassword() {
		String password = prop.getProperty("passwrd");
		return password;
	}
}
