package reivax.norac.interviewprep.webapp;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonDecode {

	public static List<Entry> decodeBook(String filePath) throws org.json.simple.parser.ParseException, IOException{
		FileReader reader = new FileReader(filePath);
		JSONParser jsonParser = new JSONParser();
		JSONArray obj = (JSONArray) jsonParser.parse(reader);
		
		List<Entry> book = new ArrayList<Entry>();

		for(int i=0; i<obj.size(); i++){
			book.add(decodeOneRecord((JSONObject)obj.get(i)));
		}
		return book;
	}
	private static Entry decodeOneRecord(JSONObject o){
		Entry obj = new Entry();
		obj.setQuestion(((String)o.get("question")).trim());
		if(o.get("timeout") != null){
			obj.setTimeout(Long.parseLong(((String)o.get("timeout")).trim()));
		}
		return obj;
	}
}
