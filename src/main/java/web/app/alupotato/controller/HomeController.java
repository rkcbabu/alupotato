/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.app.alupotato.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.app.alupotato.entity.Setting;
import web.app.alupotato.service.GenericService;

@Controller
public class HomeController {

    @Autowired
    GenericService<Setting, Long> settingService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexGet(Model model) {
        model.addAttribute("setting", new Setting());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String indexPost(@ModelAttribute("setting") Setting setting, Model model, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("setting", setting);
            return "index";
        }
        settingService.create(setting);
        session.setAttribute("flashMessage", "Setting is created.");
        return "redirect:/";
    }

    @RequestMapping(value = "/flashMessage", method = RequestMethod.GET)
    public String flashMessage(Model model, HttpSession session) {
        if (session.getAttribute("flashMessage") != null) {
            model.addAttribute("flashMessage", session.getAttribute("flashMessage"));
            session.setAttribute("flashMessage", null);
        }
        return "includes/flashMessage";
    }

}
