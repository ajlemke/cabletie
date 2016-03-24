/**
 * 
 */
package local.ajlemke.services;

import java.util.List;

import local.ajlemke.models.User;

/**
 * @author AJ Lemke
 *
 */
public interface UserService {
    
    User create(User user);
    User read(long id);
    void update(User user);
    void delete(long id);

    static List<User> list() {
        // TODO Auto-generated method stub
        return null;
    }
    List<User> list( String term );
    
    boolean doesExist(User user);
   
    
}
