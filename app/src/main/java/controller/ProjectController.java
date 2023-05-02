
package controller;
import model.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import util.ConnectionFactory;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author ton618
 */
public class ProjectController {
    
    public void save(Project project) {
        String sql = "INSERT INTO Project ("
                + "name,"
                + "description,"
                + "createdAt,"
                + "updatedAt,"
                + "projectUser) VALUES (?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //createing the connection with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //setting the values to the sql statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getProjectUser());
            
            //executing the query
            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o projeto " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update(Project project) {
        String sql = "UPDATE Project SET "
                + "name = ?,"
                + "description = ?,"
                + "createdAt = ?,"
                + "updatedAt = ?,"
                + "projectUser = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //createing the connection with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //setting the values to the sql statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getProjectUser());
            statement.setInt(6, project.getId());
            
            //executing the query
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o projeto " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void delete(int idProject) {
        String sql = "DELETE FROM Project WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //createing the connection with the database
            connection = ConnectionFactory.getConnection();
            
            //preparing the sql query
            statement = connection.prepareStatement(sql);
            
            //setting the values to the sql statement
            statement.setInt(1, idProject);
            
            //executing the query
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao apagar projeto " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    /*public List<Project> getAll() {
        String sql = "SELECT * FROM Project";
        
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
                
                //Project project = new Project();
                //project.setId(result.getInt("id"));
                //project.setName(result.getString("name"));
                //project.setDescription(result.getString("description"));
                //project.setCreatedAt(result.getDate("createdAt"));
                //project.setUpdatedAt(result.getDate("updatedAt"));
                
                
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
    }*/
    
    public Project getProjectFromDB(int projectID) {

        String sql = "SELECT * FROM Project WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectID);
            result = statement.executeQuery();

            Project project = null;
            
            if(result.next()) {
                project = new Project(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("createdAt"),
                        result.getDate("updatedAt"),
                        result.getInt("projectUser")
                );
            }
            
            return project;

        } catch (Exception e) {
            throw new RuntimeException("erro ao obter projeto " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, result);
        }
    }
    
    
}
