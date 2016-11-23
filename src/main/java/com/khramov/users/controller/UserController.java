package com.khramov.users.controller;

import com.khramov.users.model.User;
import com.khramov.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    
    private UserService personService;
    private int page = 0, pageCount;

    @Autowired(required=true)
    public void setPersonService(@Qualifier("personService") UserService ps){
        this.personService = ps;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new User());
        model.addAttribute("listPersons", this.personService.listPersons(page));
        model.addAttribute("page", page);
        pageCount = this.personService.listPersons().size() / 10 +
                ((this.personService.listPersons().size() % 10 > 0) ? 1 : 0);
        model.addAttribute("pageCount", pageCount);
        return "person";
    }

    //For add and update person both
    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") User p){
        if(p.getId() == 0){
            //new person, add it
            this.personService.addPerson(p);
        }else{
            //existing person, call update
            this.personService.updatePerson(p);
        }

        return "redirect:/";

    }

    //For prev page
    @RequestMapping(value= "/prev", method = RequestMethod.POST)
    public String prevPage(){
        page--;
        if (page < 0) page = 0;
        return "redirect:/";
    }

    //For next page
    @RequestMapping(value= "/next", method = RequestMethod.POST)
    public String nextPage(){
        page++;
        if (page > pageCount)  page--;
        return "redirect:/";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        this.personService.removePerson(id);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
}
