
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;
import model.User;

/**
 *
 * @author ton618
 */
public class UserController {
    
    public void save(User user) {
        String sql = "INSERT INTO Users (userName, "
                + "userPassword) VALUES (?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserPassword());
            
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("erro ao savar usuário " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);      
        }
    }
    
    public User getSelectedUser(String userName) {
        String sql = "SELECT * FROM Users WHERE userName = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            result = statement.executeQuery();
            
            if(result.next()) {
                return new User(
                        result.getInt("userID"),
                        result.getString("userName"),
                        result.getString("userPassword")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao selecionar usuário no banco de dados " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, result);
        }
        
        return null;
    }
    
    public List<Project> getAllFromUser(User user) {
        String sql = "SELECT * FROM Project WHERE projectUser = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
        //project list that will be retorned when the call happen
        List<Project> projects = new ArrayList<>();
        
        try {
            //createing the connection with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //setting projectUser
            statement.setInt(1, user.getUserID());
            
            //executing the query
            result = statement.executeQuery();
            
            while(result.next()) {
                
                //transforming a sql result into a project object
                Project project = new Project(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("createdAt"),
                        result.getDate("updatedAt"),
                        result.getInt("projectUser")
                );
                /*
                Project project = new Project();
                project.setId(result.getInt("id"));
                project.setName(result.getString("name"));
                project.setDescription(result.getString("description"));
                project.setCreatedAt(result.getDate("createdAt"));
                project.setUpdatedAt(result.getDate("updatedAt"));
                */
                
                //adding the recently created Project into the List projects
                projects.add(project);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao retornar projetos " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, result);
        }
        
        //returning the list of Projects
        return projects;
    }
    
}
