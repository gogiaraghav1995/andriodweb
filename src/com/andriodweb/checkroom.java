package com.andriodweb;





	import java.sql.SQLException;

	import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
	//Path: http://localhost/<appln-folder-name>/checkroom
	@Path("/checkroom")
	public class checkroom {
		// HTTP Get Method
		@GET 
		// Path: http://localhost/<appln-folder-name>/checkroom/docheck
		@Path("/docheck")  
		// Produces JSON as response
		@Produces(MediaType.APPLICATION_JSON)
		
		//http://localhost:8080/andriodweb/checkroom/docheck?num=101&date=2015-06-14 19:15:36&time=19:15:33
		// Query parameters are parameters: http://localhost/<appln-folder-name>/bookroom/do?num=101&date=2015-06-14 19:15:36&time=19:15:33
		public String doLogin(@QueryParam("num") int num, @QueryParam("date") String date,@QueryParam("time") String time){
			String response = "";
			System.out.println("Inside doLogin 1"+num+"  "+"123 "+ date+"dfvr"+time);
			
			
			
			int retCode=-1;
			try {
				retCode = checkroomdb(num,date,time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(retCode> 0){
				String name=getname(retCode);
				response =Utitlity.constructJSON1("BOOKED",name);
				//response = Utitlity.constructJSON("register",true,retCod);
			}else if(retCode == -1){
				response = Utitlity.constructJSON1("Availabe");
			}else if(retCode == -2){
				response = Utitlity.constructJSON1("ERROR");
			}else if(retCode == -3){
				response = Utitlity.constructJSON1("ERROR");
			}
			return response;
					
		}
		
		private String getname(int eid) {
			// TODO Auto-generated method stub
			String name=DBConnection.getempname(eid);
			return name ;
		}

		public int checkroomdb(int num,String date, String time) throws SQLException
		{
			System.out.println("Inside check1Credentials");
			int result = 3;
			int id=-1;{
							try {
					id=DBConnection.checkroom(num,date,time);
					
					
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Inside checkCredentials catch e ");
					result = 3;
					id=-3;
				}
			}
				
			return id;
		}
		
		
	}


