package com.calidad2018.pcc.home;

import com.calidad2018.pcc.colaboradores.Colaborador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @RequestMapping("/")
       public String home(Model model) {

        Colaborador colaborador = new Colaborador();

        colaborador.setId("123");

        colaborador.setName("Esteban");


        model.addAttribute("colaborador",colaborador);


        return "home/index";
    }




}
