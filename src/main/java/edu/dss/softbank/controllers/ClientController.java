package edu.dss.softbank.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import edu.dss.softbank.domain.Client;
import edu.dss.softbank.domain.User;
import edu.dss.softbank.domain.enumerations.AccountType;
import edu.dss.softbank.service.ClientService;
import edu.dss.softbank.service.UserService;

@Controller
public class ClientController {
    private ClientService clientService;
    private UserService userService;

    public ClientController(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }

    @GetMapping("/clients")
    public String users(Model model) {
        List<Client> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        return "clients";
    }

    // handler method to handle user registration form request
    @GetMapping("/client/new")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        Client client = new Client();
        model.addAttribute("client", client);

        // add the list of users in the system (to populate a <select> in the view)
        model.addAttribute("options", userService.findAllUsers());
        model.addAttribute("allAccountTypes", AccountType.values());

        return "client"; // will show view /templates/client.html to users
    }

    // handler method to handle user registration form submit request
    @PostMapping("/client/new/save")
    public String registration(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model) {
        User u = userService.findUserById(client.getIduser()); // this is coming from "client.html" --> <select class="form-control" id="user" name="user">
        // check errors
        if(result.hasErrors()){
            model.addAttribute("client", client);
            return "/client/new";
        }
        // Attach user to client
        client.setUser(u);
        clientService.saveClient(client);
        return "redirect:/clients?success";
    }

}
