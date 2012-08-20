package org.arquillian.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class PlsDaoImpl implements PlsDao {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void addOrganization(String name, Integer type) {

		try {
			Connection c = dataSource.getConnection();
			
			PreparedStatement s = c.prepareStatement("INSERT INTO ORGANIZATION (org_name, org_type) VALUES (?, ?);");
			s.setString(1, name);
			s.setInt(2, type);
			s.executeUpdate();
			
			s.close();
			c.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}