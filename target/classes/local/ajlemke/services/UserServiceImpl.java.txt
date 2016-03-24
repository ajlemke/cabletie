/**
 * 
 */
package local.ajlemke.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import local.ajlemke.models.User;

/**
 * @author AJ Lemke
 *
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static Connection conn = null;
    private static PreparedStatement stmt = null;

    public User create(User user) {
        
        String sql = "INSERT INTO users("
                + "firstname, "
                + "lastname, "
                + "username, "
                + "email, "
                + "address, "
                + "city, "
                + "state, "
                + "country, "
                + "locale, "
                + "password) "
                + "VALUES("
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?) RETURNING id;";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://jupiter.system:5432/api","java_api_user", "pg@dmin");
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getCity());
            stmt.setString(7, user.getState());
            stmt.setString(8, user.getCountry());
            stmt.setString(9, user.getLocale());
            stmt.setString(10, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            user.setId(rs.getLong("id"));
            return user;
        } catch ( Exception e ) {
            System.out.println(e);
            return user;
        }        
        
    }

    public User read(long id) {
        
        String sql = "SELECT * FROM users WHERE id = ?;";
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://jupiter.system:5432/api","java_api_user", "pg@dmin");
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            User user = new User(
                rs.getLong("id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("country"),
                rs.getString("locale"),
                rs.getString("password") 
            );
            rs.close();
            stmt.close();
            conn.close();
            return user;

        } catch ( Exception e ) {
            System.out.println(e);
            return null;
        }
        
    }
    
    public void update(User user) {

        String sql = "UPDATE users"
                + "SET firstname = ?,"
                + "    lastname = ?,"
                + "    username = ?, "
                + "    email = ?, "
                + "    address = ?, "
                + "    city = ?, "
                + "    state = ?, "
                + "    country = ?, "
                + "    locale = ?, "
                + "    password = ?"
                + "WHERE id = ?;";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://jupiter.system:5432/api","java_api_user", "pg@dmin");
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getCity());
            stmt.setString(7, user.getState());
            stmt.setString(8, user.getCountry());
            stmt.setString(9, user.getLocale());
            stmt.setString(10, user.getPassword());
            stmt.setLong(11, user.getId());
            stmt.executeQuery();
        } catch ( Exception e ) {
            System.out.println(e);
        }        
        
    }

    public void delete(long id) {
        String sql = "DELETE FROM users WHERE id = ?;";
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://jupiter.system:5432/api","java_api_user", "pg@dmin");
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeQuery();
            stmt.close();
            conn.close();
        } catch ( Exception e ) {
            System.out.println(e);
        }
        
    }        

    public List<User> list() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users ORDER BY id asc;";
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://jupiter.system:5432/api","java_api_user", "pg@dmin");
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while( rs.next() ) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getString("locale"),
                        rs.getString("password") 
                    )
                );
            }
            rs.close();
            stmt.close();
            conn.close();
            return users;

        } catch ( Exception e ) {
            System.out.println(e);
            return null;
        }        
    }

    public List<User> list(String term) {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://jupiter.system:5432/api","java_api_user", "pg@dmin");
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, term);
            ResultSet rs = stmt.executeQuery();

            while( rs.next() ) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("country"),
                        rs.getString("locale"),
                        rs.getString("password") 
                    )
                );
            }
            rs.close();
            stmt.close();
            conn.close();
            return users;

        } catch ( Exception e ) {
            System.out.println(e);
            return null;
        }        
    }
    
    public boolean doesExist(User user) {
        int count = list(user.getEmail()).size();
        if (count > 0) {
            return true;    
        } else {
            return false;
        }
        
    }
    
   
    
}
