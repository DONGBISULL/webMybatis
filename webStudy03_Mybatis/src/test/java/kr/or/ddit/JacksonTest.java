package kr.or.ddit;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	/*@Test //
	public void test() throws JsonProcessingException {
		Map<String, Object> target = new HashMap<>();
		target.put("prop1" , "텍스트");
		target.put("prop2" , 34);
		target.put("prop3" , true);
		target.put("prop4" , null);
		target.put("prop4" , new String[] {"value","value2"});
		target.put("prop5" , Collections.singletonMap("innerProp", "내부맵데이터"));
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(target) ;// write 자바객체를 json으로 
				//read json --> 자바 객체로 바꾸기 
		
		System.out.println(json);
		Map<String , Object> destMap = mapper.readValue(json, Map.class);
		System.out.println(destMap);
	
	}*/
	
	@Test
	public void bundleTest() throws JsonProcessingException {
		//Serializable 
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.servlet05.message");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(bundle);
		System.out.println(json);
		
		
	}
	
	
	

}
