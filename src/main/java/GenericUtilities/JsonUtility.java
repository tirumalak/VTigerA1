package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String FetchDataFromJsonFile(String key) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser j=new JSONParser();
		Object obj = j.parse(new FileReader("./src/test/resources/Vtigerdata.json"));
		JSONObject jsonobj=(JSONObject)obj;
		String data = jsonobj.get(key).toString();
		return data;
	}

}
