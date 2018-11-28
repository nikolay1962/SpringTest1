package sda.sping.test1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sda.sping.test1.Utils.UserUtils;
import sda.sping.test1.dto.AllowedRoles;
import sda.sping.test1.dto.User;
import sda.sping.test1.repositories.AllowedRolesRepository;
import sda.sping.test1.repositories.UserRepository;

@RequestMapping(path = "/")
@RestController
public class BasicController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUtils userUtils;

    @Autowired
    AllowedRolesRepository rolesRepository;

    @GetMapping
    public String hello(){
        return "Welcome!";
    }

    @GetMapping(path = "/protected")
    public String helloFromProtected(){
        return "Here you are safe!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/avgAge")
    public int getAvgAge() {

        Iterable<User> users = userRepository.findAll();
        int totalAge = 0;
        int numberOfUsers = 0;

        for (User user : users) {
            numberOfUsers++;
            totalAge += userUtils.age(user.getDateOfBirth());
        }

        if (numberOfUsers > 0) {
            return totalAge / numberOfUsers;
        }
        return 0;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/registration")
    public String showRegistration() {

        return "Here should be a registration form...";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/registration")
    public String addUser(@RequestBody User user) {
        String role = user.getRole();
        AllowedRoles allowedRoles = rolesRepository.getAllowedRolesByRole(role);
        if ( allowedRoles != null) {
            userRepository.save(user);
            return "User " + user.getUsername() + " was successfully added to User list.";
        }
        return "User " + user.getUsername() + " was NOT added to User list. Wrong role:" + role;
    }
}
