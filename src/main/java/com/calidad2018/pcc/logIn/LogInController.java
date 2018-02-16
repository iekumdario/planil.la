package com.calidad2018.pcc.logIn;

import com.calidad2018.pcc.colaboradores.Colaborador;
import com.calidad2018.pcc.usuarios.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(Model model, HttpServletRequest request) {

        model.addAttribute("user", new User());

        try {

            Object flash = request.getSession().getAttribute("flash");

            model.addAttribute("flash", flash);

            request.getSession().removeAttribute("flash");

        } catch (Exception ex) {
            // "flash" session attribute must not exist...do nothing and proceed normally
        }


        return "home/login";

    }




}
