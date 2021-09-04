package crudApp.controllers;

import crudApp.models.User;
import crudApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final UserService userService;
    @Autowired
    public PeopleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", userService.getAllUsers());
        return "user/index";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id")int id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("user") User user){
        return "user/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user/new";
        }
        userService.add(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                               @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        userService.update(userService.getById(id));
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/people";
    }
}
