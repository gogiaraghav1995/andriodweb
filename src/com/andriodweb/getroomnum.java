package com.andriodweb;





	import java.sql.SQLException;
import java.util.ArrayList;

	import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
	//Path: http://localhost/<appln-folder-name>/checkroom
	@Path("/getroomnum")
	public class getroomnum {
		// HTTP Get Method
		@GET 
		// Path: http://localhost/<appln-folder-name>/getroomnum/donum
		@Path("/donum")  
		// Produces JSON as response
		@Produces(MediaType.APPLICATION_JSON)
		
		//http://localhost:8080/andriodweb/checkroom/docheck?num=101&date=2015-06-14 19:15:36&time=19:15:33
		// Query parameters are parameters: http://localhost/<appln-folder-name>/bookroom/do?num=101&date=2015-06-14 19:15:36&time=19:15:33
		public String doLogin(){
			String response = "";
			ArrayList<String> num =DBConnection.getroom();
			JSONObject obj = new JSONObject();
			try {
				
				int i=num.size();
				obj.put("num",i-1);
				for(int j=0;j<i;j++)
				{
					obj.put(""+j,num.get(j));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
			}
			
			
			return obj.toString();
					
		}}
		
		

		


