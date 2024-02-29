package com.example.demo.controlador;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NotFoundException.class)
    public String error(Model model, NotFoundException e){

        model.addAttribute("id", e.getId());

        return "paginaErrorMascota";
    }

    @ExceptionHandler(CedulaNotFoundException.class)
    public String errorLogin(Model model, CedulaNotFoundException e){

        model.addAttribute("cedula", e.getCedula());

        return "paginaErrorCliente";
    }

    @ExceptionHandler(IdClienteNotFoundException.class)
    public String errorCliente(Model model, IdClienteNotFoundException e){

        model.addAttribute("id", e.getid());

        return "paginaErrorClienteId";
    }
    
}
