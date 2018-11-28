package sda.sping.test1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sda.sping.test1.dto.Account;
import sda.sping.test1.dto.LoggedUser;
import sda.sping.test1.dto.User;
import sda.sping.test1.repositories.AccountRepository;
import sda.sping.test1.repositories.LoggedUserRepository;
import sda.sping.test1.repositories.UserRepository;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/bank")
public class BankController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoggedUserRepository loggedUserRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/allAccounts")
    public Iterable<Account> getAllAccounts() {

        return accountRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addAccount")
    public void addUser(@RequestBody Account account) {
        accountRepository.save(account);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/amountByCurrency")
    public String getAmountByCurrency(@PathParam("currency") String currency){
        Iterable<Account> accounts = accountRepository.getAccountsByCurrency(currency);
        double total = 0.0;
        for (Account account : accounts) {
            total += account.getAmount();
        }

        return ("Total amount of accounts in " + currency + " is equal to " + String.format("%.2f", total));

    }

    @RequestMapping(method = RequestMethod.POST, path = "/addUser")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/usersByNationality")
    public String getUsersByNationality(@PathParam("nationality") String nationality) {
        Iterable<User> users = userRepository.getAllByNationality(nationality);
        StringBuilder sb = new StringBuilder("List of users with nationality " + nationality + "<br/>\n");
        for (User user : users) {
            sb.append(user.getName() + " - " + user.getNationality() + "<br/>\n");
        }

        return sb.toString();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allUsers")
    public Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/userId")
    public Integer getUserIdByName(@PathParam("name") String name) {

        return userRepository.findIdByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public String loginByUserName(@PathParam("name") String name) {

        Integer userId = userRepository.findIdByName(name);
        if (userId == null || userId == 0) {
            return "Unable to login as " + name;
        }

        User user = userRepository.getUserById(userId);

        Integer numberOfRows = loggedUserRepository.getNuberOfRows();

        loggedUserRepository.save(new LoggedUser(1, user.getId(), user.getAccount().getId(), "user"));
        StringBuilder sb = new StringBuilder(name + " with:" + '\n');
        sb.append("UserId: " + user.getId() + '\n');
        sb.append("AccountId: " + user.getAccount().getId() + '\n');
        sb.append("Status: user" + '\n');
        sb.append("successfully logged in. " + '\n');

        return sb.toString();

    }

    @RequestMapping(method = RequestMethod.GET, path = "/userAmount")
    public String getUserIdByName(@PathParam("account") int accountId) {

        LoggedUser loggedUser = loggedUserRepository.getLoggedUserById(1);

        if (loggedUser.getAccountid() == accountId) {
            Account account = accountRepository.getAccountById(accountId);
            return "Available funds on " + accountId + ":" + account.getAmount();
        }

        return "You are not authorized for account " + accountId;



    }

}
