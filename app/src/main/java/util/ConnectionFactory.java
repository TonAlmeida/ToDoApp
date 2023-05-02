
package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ton618
 */
public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/ToDoApp";
    public static final String USER = "root";
    public static final String PASS = "";
    
    public static Connection getConnection() throws SQLException {
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch(ClassNotFoundException | SQLException e) {
            throw new SQLException("Erro ao conectar com o banco de dados " + e.getMessage(), e);
        } 
    }
    
    public static void closeConnection(Connection connection) {
        try{
            if(connection != null) {
                connection.close();
            }
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao fechar a conexão" + ex.getMessage(), ex);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try{
            if(connection != null) {
                connection.close();
            }
            
            if(statement != null) {
                statement.close();
            }
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao fechar a conexão" + ex.getMessage(), ex);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement,
            ResultSet result) {
        try{
            if(connection != null) {
                connection.close();
            }
            
            if(statement != null) {
                statement.close();
            }
            
            if(result != null) {
                result.close();
            }
            
        }catch(SQLException ex){
            throw new RuntimeException("Erro ao fechar a conexão" + ex.getMessage(), ex);
        }
    }
    
}
