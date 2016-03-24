/**
 * 
 */
package local.ajlemke.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import local.ajlemke.models.User;
import local.ajlemke.services.UserService;

/**
 * @author AJ Lemke
 *
 */
@RestController
public class UserController {

    /**
     * @return returns a list of Users
     * 
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<User> list() {
        List<User> users = UserService.list();
        return users;
    }

    /**
     * @return void
     *  
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = {"application/json", "application/XML"})
    public void create() {
        
    }

    /**
     * @return returns a single Users
     * 
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User read() {
        return null;
    }
    
    /**
     * @return void
     * 
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, consumes = {"application/json", "application/XML"})
    public void update() {
        
    }
    
    /**
     * @return void
     * 
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete() {
        
    }
    
    /**
     * @return void
     * 
     */
    @RequestMapping(value = "/user", method = RequestMethod.OPTIONS, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public void options() {
        
    }
    
    
    
    
}
