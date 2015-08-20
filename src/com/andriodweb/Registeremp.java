package com.andriodweb;



import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
//Path: http://localhost/<appln-folder-name>/registeremp
@Path("/registeremp")
public class Registeremp {
	// HTTP Get Method
	@GET 
	// Path: http://localhost/<appln-folder-name>/registeremp/doregisteremp
	@Path("/doregisteremp")  
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON) 
	// Query parameters are parameters: http://localhost/<appln-folder-name>/registeremp/doregisteremp?name=pqrs&username=abc&password=xyz&pass=fdgh
	public String doLogin(@QueryParam("name") String uname, @QueryParam("username") String name, @QueryParam("password") String pwd, @QueryParam("pass") String pass){
		String response = "";
		System.out.println("Inside doLogin "+uname+"  "+pass);
		int retCode = registeremp(name, uname, pwd,pass);
		if(retCode> 0){
			response =Utitlity.constructJSON("register",true, retCode);
			//response = Utitlity.constructJSON("register",true,retCod);
		}else if(retCode == -1){
			response = Utitlity.constructJSON("register",false, "You are already registered");
		}else if(retCode == -2){
			response = Utitlity.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
		}else if(retCode == -3){
			response = Utitlity.constructJSON("register",false, "Error occured");
		}
		return response;
				
	}
	
	public int registeremp(String name, String uname, String pwd,String pass){
		
		int result = 3;
		int id=-1;
		if(Utitlity.isNotNull(uname) && Utitlity.isNotNull(pwd)){
			try {
				id=DBConnection.insertemp(uname, name, pwd,pass);
				
				//id=(int)DBConnection.insertemp(name, uname, pwd);
				if(id>0){
					System.out.println("RegisterUSer if"+id);
					result = 0;
					
				}
			}
			catch(SQLException sqle){
				System.out.println("RegisterUSer catch sqle");
				//When Primary key violation occurs that means user is already registered
				if(sqle.getErrorCode() == 1062){
					result = 1;
					id=-1;
				} 
				//When special characters are used in name,username or password
				else if(sqle.getErrorCode() == 1064){
					System.out.println(sqle.getErrorCode());
					result = 2;
					id=-2;
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
				id=-3;
			}
		}else{
			System.out.println("Inside checkCredentials else");
			result = 3;
			id=-3;
		}
			
		return id;
	}
	
	
}
