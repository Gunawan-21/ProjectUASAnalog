package project.uas.analog;

import java.sql.*;
import javax.swing.*;

public class DataMahasiswa {
	
	public static Connection ConnectDB()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection
					("jdbc:sqlite:D:\\aplikasi\\ProjectUasAnalog\\dbDataMhs.db");
					JOptionPane.showMessageDialog(null, "Menghubungkan....");
					return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Connection Error");
			return null;
		}
	}
}