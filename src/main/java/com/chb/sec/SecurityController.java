package com.chb.sec;

import com.chb.entities.Coach;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/SecurityController")
@Controller
public class SecurityController extends HttpServlet {

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "logout")
    public String logout(){
        return "redirect:/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request){
        if(request.isUserInRole("SUPERADMIN"))
            return "redirect:/tabClient";
        else if(request.isUserInRole("USER"))
            return "redirect:/listClientsDuCoach";
        else
            return "403";
    }

    @GetMapping(value = "/403")
    public String notAccess(){
        return "403";
    }
}
