package sda.sping.test1.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.sping.test1.dto.User;
import sda.sping.test1.repositories.UserRepository;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/admin")
@Slf4j
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/allUsers")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping(path = "/delete")
    public String deleteUser(@PathParam("username") String username) {
        User userToDelete = userRepository.findByUsername(username);

        if (userToDelete == null) {
            return "No such user " + username + ". Change your request.";
        } else if (userToDelete.getRole().contains("ADMIN")) {
            return "User " + username + " is ADMIN. Deletion is prihibited.";
        }
        userRepository.deleteByUsername(username);
        log.info("user {} was deleted.", username);
        return "User " + username + " must be deleted";
    }
}
