package com.student.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.student.dao.StudentDao;
import com.student.model.Student;

@Service
public class StudentDaoImpl implements StudentDao{
	
	private Connection connection;
	Student s=null;
     public StudentDaoImpl() {
        try {
			this.connection = DriverManager.getConnection("jdbc:postgresql://172.29.10.225:5432/postgres", "myusername", "mypassword");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@Override
	public int saveStudent(Student s){
		int id=0;
		String sql = "INSERT INTO student (id,first_name, last_name, dob, branch) VALUES (?,?,?,?,?)";
		try {
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, s.getId());
		statement.setString(2, s.getFirst_name());
		statement.setString(3, s.getLast_name());
		statement.setTimestamp(4, s.getDob());
		statement.setString(5, s.getBranch());
		
        int affectedRows=statement.executeUpdate();
        if (affectedRows==0) {
        	throw new SQLException("Insertion of student failed");	
        }
        
             id= s.getId();
        
		
	}
		catch(Exception e)
		{
			System.out.println("PostgreSQL driver not found");
			e.printStackTrace();
		}
		return id;
	}

	@Override
	
	public List<Student> getAllStudent() {
		List<Student> students = new ArrayList<>();

        String query = "SELECT * FROM student";
        try
        {
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            Timestamp dob = resultSet.getTimestamp("dob");
            String branch = resultSet.getString("branch");

            Student student = new Student(id, first_name, last_name, dob, branch);
            students.add(student);
        }
        }
        catch(Exception e)
        {
        	System.out.println("PostgreSQL driver not found");
			e.printStackTrace();
        }
        
        return students;
    }
//	UUID uuid = UUID.randomUUID();
//    String randomUUIDString = uuid.toString();

	@Override
	public Student getStudentById(int id) {
		Student student = null;
		String query="SELECT * FROM student WHERE id=?";
		try
		{
	        PreparedStatement preparedStatement=connection.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	        	student=new Student();
	            student.setId(resultSet.getInt("id"));
	            student.setFirst_name(resultSet.getString("first_name"));
	            student.setLast_name(resultSet.getString("last_name"));
	            student.setDob(resultSet.getTimestamp("dob"));
	            student.setBranch(resultSet.getString("branch"));
	        }
		}
		 catch(Exception e)
        {
        	System.out.println("PostgreSQL driver not found");
			e.printStackTrace();
        }
		return student;
		
	}

	@Override
	public String deleteStudent(int id) {
		String result="";
        String delete = "DELETE FROM student WHERE id= ?";
        try {
        	PreparedStatement preparedStatement = connection.prepareStatement(delete);
        	preparedStatement.setInt(1,id);
        	int affectedRows = preparedStatement.executeUpdate();
        
        	if(affectedRows==0)
        	{
        		result= "Deleting student failed, no such row exists";
        	}
        	else
        	{
        		result= "Student with id: "+id+" deleted successfully";
        	}
        	}
        catch(Exception e)
        {
        	System.out.println("PostgreSQL driver not found");
			e.printStackTrace();
        }
        return result;
	}

	@Override
	public int updateStudent(int id, Student s) {
		Student student =null;
		String query="UPDATE student SET first_name=?, last_name=?, dob=?, branch=? WHERE id=?";
		try
		{
			PreparedStatement ps=connection.prepareStatement(query);
			
			ps.setString(1,s.getFirst_name());
			ps.setString(2,s.getLast_name());
			ps.setTimestamp(3,s.getDob());
			ps.setString(4,s.getBranch());
			ps.setInt(5, id);
			
			int affectedRows=ps.executeUpdate();
			
			if(affectedRows==0)
				throw new SQLException("Update query of student has failed");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return id;
	}

}
