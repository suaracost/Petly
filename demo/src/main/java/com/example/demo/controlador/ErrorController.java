package com.example.demo.controlador;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    //Metodo para manejar la excepcion NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public String error(Model model, NotFoundException e){

        model.addAttribute("id", e.getId());

        return "paginaErrorMascota";
    }

    //Metodo para manejar la excepcion CedulaNotFoundException
    @ExceptionHandler(CedulaNotFoundException.class)
    public String errorLogin(Model model, CedulaNotFoundException e){

        model.addAttribute("cedula", e.getCedula());

        return "paginaErrorCliente";
    }

    //Metodo para manejar la excepcion IdClienteNotFoundException
    @ExceptionHandler(IdClienteNotFoundException.class)
    public String errorCliente(Model model, IdClienteNotFoundException e){

        model.addAttribute("id", e.getid());

        return "paginaErrorClienteId";
    }
    
}
