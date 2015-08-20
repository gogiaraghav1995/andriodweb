package com.andriodweb;





	import java.sql.SQLException;

	import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
	//Path: http://localhost/<appln-folder-name>/bookroom
	@Path("/bookroom")
	public class bookroom {
		// HTTP Get Method
		@GET 
		// Path: http://localhost/<appln-folder-name>/bookroom/doregisterbook
		@Path("/doregisterbook")  
		// Produces JSON as response
		@Produces(MediaType.APPLICATION_JSON)
		
		//http://localhost:8080/andriodweb/bookroom/doregisterbook?num=101&eid=23145&date=2015-06-14 19:15:36&time=19:15:33
		// Query parameters are parameters: http://localhost/<appln-folder-name>/bookroom/do?num=101&eid=abc&date=2015-06-14 19:15:36&time=19:15:33&reason=helkdsbfd
		public String doLogin(@QueryParam("num") int num, @QueryParam("eid") int eid, @QueryParam("date") String date,@QueryParam("time") String time,@QueryParam("reason") String reason){
			String response = "";
		
			System.out.println("Inside doLogin 1"+num+"  "+eid+"123 "+ date+"dfvr"+time);
			
			
			
			int retCode=-1;
			try {
				retCode = bookroomdb(num,eid,date,time,reason);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(retCode> 0){
				response =Utitlity.constructJSON("register",true, retCode);
				//response = Utitlity.constructJSON("register",true,retCod);
			}else if(retCode == -1){
				response = Utitlity.constructJSON("register",false, "ALl ready booked");
			}else if(retCode == -2){
				response = Utitlity.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
			}else if(retCode == -3){
				response = Utitlity.constructJSON("register",false, "Error occured");
			}else if(retCode == -4){
				response = Utitlity.constructJSON("register",false, "Error occured Room not found");
			}
			return response;
					
		}
		
		public int bookroomdb(int num, int eid,String date, String time, String reason) throws SQLException
		{
			System.out.println("Inside check1Credentials");
			int result = 3;
			int id=-1;{
							try {
					id=DBConnection.insertbook(num,eid,date,time,reason);
					
						if(id>0){
						System.out.println("Registerbook if"+id);
						result = 0;
						
					}
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Inside checkCredentials catch e ");
					result = 3;
					id=-3;
				}
			}//else{
				//System.out.println("Inside checkCredentials else");
				//result = 3;
				//id=-3;
			//}
				
			return id;
		}
		
		
	}


