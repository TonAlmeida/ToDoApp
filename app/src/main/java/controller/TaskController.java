
package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Task;
import java.util.List;
import util.ConnectionFactory;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ton618
 */
public class TaskController {
    
    public void save(Task task) {
        String sql = "INSERT INTO Tasks (myProject,"
                + "name,"
                + "description,"
                + "compleated,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //estabilishing the connection with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //setting the values of the statement
            statement.setInt(1, task.getMyproject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleated());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            
            //executing the query
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao slavar tarefa " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update(Task task) {
        
        String sql = "UPDATE Tasks SET name = ?,"
                + "description = ?,"
                + "compleated = ?,"
                + "notes = ?,"
                + "deadline = ?,"
                + "createdAt = ?,"
                + "updatedAt = ?,"
                + "myProject = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //estabilishing the connection with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //setting the values of the statement
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setBoolean(3, task.isCompleated());
            statement.setString(4, task.getNotes());
            statement.setDate(5, new Date(task.getDeadline().getTime()));
            statement.setDate(6, new Date(task.getCreatedAt().getTime()));
            statement.setDate(7, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(8, task.getMyproject());
            statement.setInt(9, task.getId());
            
            //executing the query
            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tarefa" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    /*public void update(Task task) {
        String sql = "UPDATE Tasks SET name =" + task.getName()
                + ", description =" + task.getDescription()
                + ", compleated =" + task.isCompleated()
                + ", notes =" + task.getNotes()
                + ", deadline =" + task.getDeadline()
                + ", createdAt =" + task.getCreatedAt()
                + ", updatedAt =" + task.getUpdatedAt()
                + ", myProject =" + task.getMyproject()
                + "WHERE id =" + task.getId();
        
        System.out.println(sql);
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //estabilishing the connection with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //executing the statement
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("erro ao atualizar tarefa!" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }*/
    
    public void delete(int taskID) {
        String sql = "DELETE FROM Tasks WHERE ID = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //creating the connectin with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //setting the values of the statement
            statement.setInt(1, taskID);
            
            //executing the query
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar tarefa! " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Task> getAll(int projectID) { 
        
        String sql = "SELECT * FROM Tasks WHERE myProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        //creating the ResultSet to set the result
        ResultSet result = null;
        
        //tasks list that will be retorned when the call happen
        List<Task> tasks = new ArrayList<>();
        
        try {
            //creating the connection with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //setting the values of the statement
            statement.setInt(1, projectID);
            
            //executing the query and attributing the returned sql to ResultSet
            result = statement.executeQuery();
            
            //while existing values to be running in the ResultSet this will happen
            while(result.next()) {
                //transforming a sql result into a object Task
                Task task = new Task();
                task.setId(result.getInt("id"));
                task.setMyproject(result.getInt("myProject"));
                task.setName(result.getString("name"));
                task.setDescription(result.getString("description"));
                task.setCompleated(result.getBoolean("compleated"));
                task.setNotes(result.getString("notes"));
                task.setDeadline(result.getDate("deadline"));
                task.setCreatedAt(result.getDate("createdAt"));
                task.setUpdatedAt(result.getDate("updatedAt"));
                
                //adding the recently created Task into the List tasks
                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao retornar lista " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, result);
        }
        
        //returning the list of Tasks
        return tasks;
    }
    
}
