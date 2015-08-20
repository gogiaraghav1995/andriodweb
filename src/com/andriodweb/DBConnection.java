package com.andriodweb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnection {
	
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}
    
	public static boolean checkLogin(String uname, String pwd) throws Exception {
		boolean isUserAvailable = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = " SELECT * FROM `jktapp`.`login` WHERE loginid ="+uname +" and pass =" + "'" + pwd + "'";
					
			
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
				isUserAvailable = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isUserAvailable;
	}
	
	public static boolean insertUser(String name, String uname, String pwd) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into user(name, username, password) values('"+name+ "',"+"'"
					+ uname + "','" + pwd + "')";
			//System.out.println(query);
			int records = stmt.executeUpdate(query);
			//System.out.println(records);
			//When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}
	  public static int insertemp(String name, String dest, String cinfo,String pass) throws SQLException, Exception {
	        //boolean insertStatus = false;
	        Connection dbConn = null;
	        int i = -1;
	        try {
	            try {
	                dbConn = DBConnection.createConnection();
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	           
	            Statement stmt = dbConn.createStatement();
	            String query = "SELECT  `idemp` FROM `jktapp`.`employee` LIMIT 1000";
	            System.out.println(query);
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	              i=Integer.parseInt(rs.getString(1)); 
	              
	            }
	            i++;
	            System.out.print(i);
	            String query1 = "INSERT INTO `employee` (`idemp`, `empname`, `empdest`, `continfo`) VALUES ('" + i + "','"+name+ "',"+"'"
	                    +dest+ "','" + cinfo + "')";
	           
	            //System.out.println(query);
	            int records = stmt.executeUpdate(query1);
	            //System.out.println(records);
	            //INSERT INTO `jktapp`.`login` (`loginid`) VALUES (3);
	            PreparedStatement updateemp = dbConn.prepareStatement("INSERT INTO `jktapp`.`login` VALUES (?,?,?);");
      	      updateemp.setInt(1,i);
      	      updateemp.setString(2,pass);
      	      updateemp.setInt(3,1);
      	     
      	      System.out.print("done1");
      	      updateemp.executeUpdate();
      	      System.out.print("done");
	            //When record is successfully inserted
	            if (records > 0) {
	                //insertStatus = true;
	            }
	            else
	            	{
	            	i=-1;
	            	}
	        } catch (SQLException sqle) {
	            //sqle.printStackTrace();
	            throw sqle;
	        } catch (Exception e) {
	            //e.printStackTrace();
	            // TODO Auto-generated catch block
	            if (dbConn != null) {
	                dbConn.close();
	            }
	            throw e;
	        } finally {
	            if (dbConn != null) {
	                dbConn.close();
	            }
	        }
	        return i;
	        
	        
	    }

	public static int insertbook(int num, int eid, String date, String time, String reason) {
		
		 Connection dbConn = null;
	        int i = -1;
	        try {
	            try {
	                dbConn = DBConnection.createConnection();
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	           
	            Statement stmt = dbConn.createStatement();
	            String query = "SELECT * FROM `jktapp`.`book` ORDER BY `idbook` ASC, `time` ASC LIMIT 1000;";
	            System.out.println(query);
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	              i=Integer.parseInt(rs.getString(1)); 
	              
	            }
	            i++;
	            System.out.print(i);
	            
	            System.out.print("donezzz");
	            PreparedStatement updateemp = dbConn.prepareStatement("INSERT INTO `jktapp`.`book` (`idbook`, `num`, `time`, `date`, `empid`,`reason`) VALUES (?,?,?,?,?,?);");
	            	      updateemp.setInt(1,i);
	            	      updateemp.setInt(2,num);
	            	      updateemp.setInt(5,eid);
	            	      updateemp.setString(3, time);
	            	      updateemp.setString(4, date);
	            	      updateemp.setString(6, reason);
	            	      System.out.print("done1");
	            	      
	            	      int records=updateemp.executeUpdate();
	            	      System.out.print("done");
	            //System.out.println(query);
	            //int records = stmt.executeUpdate(query1);
	            //System.out.println(records);
	            //When record is successfully inserted
	            if (records > 0) {
	                //insertStatus = true;
	            }
	            else
	            	{
	            	i=-1;
	            	}
	        } 
	        catch (SQLException sqle) {
	        	i=-4;
	            //sqle.printStackTrace();
	            try {
					throw sqle;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } 
	        catch (Exception e) {
	            //e.printStackTrace();
	            // TODO Auto-generated catch block
	            if (dbConn != null) {
	                try {
						dbConn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	            throw e;
	        } finally {
	            if (dbConn != null) {
	                try {
						dbConn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	        return i;
	        
	        
	    }
	public static int checkroom(int num, String date, String time) {
		//SELECT  `idbook`,  `num`,  `time`,  `date` FROM `jktapp`.`book` WHERE `num` = 101 AND `date`='2015' AND `time`='19';
		//SELECT  `idbook`,  `num`,  `time`,  `date` FROM `jktapp`.`book` WHERE `num` = "+num+" AND `date`='"+date+"' AND `time`='"+time+"';
		 Connection dbConn = null;
	        int i = -1;
	        try {
	        	System.out.print("checkroom datbaseconncetion");
	            try {
	                dbConn = DBConnection.createConnection();
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	           
	            Statement stmt = dbConn.createStatement();
	            String query = "SELECT  `idbook`,  `num`,  `time`,  `date`,  `empid` FROM `jktapp`.`book` WHERE `num` = "+num+" AND `date`='"+date+"' AND `time`='"+time+"';";
	            System.out.println(query);
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	            	i=rs.getInt(5);
	            	System.out.println("empid"+i);
	              System.out.print(rs.getString(2)); 
	              
	            }
	            
	           
	            
	            System.out.print("donezzz"+i);
	            
	            
	           
	        } 
	    
	        catch (Exception e) {
	            
	            // TODO Auto-generated catch block
	            if (dbConn != null) {
	                try {
						dbConn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	           
	        } finally {
	            if (dbConn != null) {
	                try {
						dbConn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	        return i;
	        
	        
	    }
	public static void main(String args[])
	{
		//checkroom(101,"2015","21:00");
		getempname(1);
	}

	public static String getempname(int eid) {
		// TODO Auto-generated method stub
		Connection dbConn = null;
		String name="";
        //int i = -1;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM `jktapp`.`employee` WHERE `idemp` = "+eid+"";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              name=rs.getString(2); 
              System.out.println(name);
            }
          //  i++;
          //  System.out.print(i);
		
	}
        catch(Exception e){}
		return name;
        
	}
	public static ArrayList<String> getroom()
	{
		ArrayList<String> room = new ArrayList<String>();
		Connection dbConn = null;
		String name="";
        //int i = -1;
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM `jktapp`.`room`";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
              name=rs.getString(1); 
              System.out.println(name);
              room.add(name);
            }
          //  i++;
          //  System.out.print(i);
		
	}
        catch(Exception e){}
        
		return room;
		
	}
	
}

