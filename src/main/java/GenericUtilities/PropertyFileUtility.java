package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	public String fetchDataFromPropertyFile(String key) throws IOException
	{
		//fetch the data from property file
		FileInputStream fis=new FileInputStream("./src/test/resources/VTigerCRM.properties");
		Properties p=new Properties();
		p.load(fis);
		String data = p.getProperty(key);
		return data;
	}
	
	public void writeBackDataToPropretyFile(String key,String value) throws IOException
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/VTigerCRM.properties");
		Properties p=new Properties();
		p.load(fis);
		p.put(key, value);
		FileOutputStream fos=new FileOutputStream("./src/test/resources/VTigerCRM.properties");
		p.store(fos, "Property file updated");
	}

}
