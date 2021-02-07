package com.demo.registration.controller;

import com.demo.registration.db.entity.User;
import com.demo.registration.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.Optional;

@Controller
@RequestMapping(value= "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody
    User addNewUser (@RequestParam String name
            , @RequestParam String email, @RequestParam String address) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User userexist = userRepository.findByEmail(email);
        User n;
        if(userexist == null){
            n = new User();
        }else{
            n = userexist;
        }
        n.setName(name);
        n.setEmail(email);
        n.setAddress(address);
        userRepository.save(n);

        return userRepository.save(n);
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{email}")
    public @ResponseBody User getUserById(@PathVariable("email") String email) {
        // This returns a JSON or XML with the users
        return userRepository.findByEmail(email);
    }
}
