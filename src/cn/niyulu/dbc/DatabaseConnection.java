package cn.niyulu.dbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
	private Connection conn ;
	
	public DatabaseConnection() {
		try {
			Properties pro = new Properties();
	        //加载配置文件
	        pro.load(DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties"));
			Class.forName(pro.getProperty("dbdriver"));
			this.conn = DriverManager.getConnection(pro.getProperty("dburl"),pro.getProperty("dbuser"),pro.getProperty("dbpwd"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConn(){
		return this.conn;
	}
	public void close() {
		if(this.conn!=null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
