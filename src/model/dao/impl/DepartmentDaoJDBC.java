package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}	
	
	@Override
	public void Insert(Department obj) {
		PreparedStatement st = null;
           try {
			 st = conn.prepareStatement("INSERT INTO Department (name) values (?)",Statement.RETURN_GENERATED_KEYS);
						
			st.setString(1,obj.getName());
			
			int RowsAffected = st.executeUpdate();
			
			if(RowsAffected >0)
			{	ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);					
				}		DB.closeResultSet(rs);		
			}else
			{
				throw new DbException("Application error! Inserted not execute!");
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
        try {
			 st = conn.prepareStatement("UPDATE Department set name=? where id=?");
						
			st.setString(1,obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
			DB.closeStatement(st);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
        try {
			 st = conn.prepareStatement("DELETE from Department WHERE id=?");
					
			st.setInt(1,id);
			
			st.executeUpdate();
			
			DB.closeStatement(st);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Department findById(Integer id) {
		
		PreparedStatement st = null;
        try {
			 st = conn.prepareStatement("select id, name from Department where id=?");
						
			st.setInt(1, id);				
			ResultSet rs = st.executeQuery();	
			
			while(rs.next()) {
				Department obj = instantiateDepartment(rs);						
				return obj;		
			}	
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		
        try {
			 st = conn.prepareStatement("select id, name from Department");
			
			ResultSet rs = st.executeQuery();
			List<Department> list = new ArrayList<>();			
			
			while(rs.next()) {
				Department obj = instantiateDepartment(rs);
				list.add(obj);				
			}
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			return list;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();		
		dep.setId(rs.getInt("id"));
		dep.setName(rs.getString("name"));		
		return dep;
	}
	
}
