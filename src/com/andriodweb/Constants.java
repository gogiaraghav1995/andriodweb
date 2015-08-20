package com.andriodweb;

//Change these parameters according to your DB
public class Constants {
	public static String dbClass = "com.mysql.jdbc.Driver";
	private static String dbName= "jktapp";
	public static String dbUrl = "jdbc:mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT//"+dbName;
	public static String dbUser = "adminxy6ukG3";
	public static String dbPwd = "x97zH3hvs8il";
}
