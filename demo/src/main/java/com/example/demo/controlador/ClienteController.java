package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entidad.Cliente;
import com.example.demo.servicio.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RequestMapping("/clientes")
@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //http://localhost:8090/clientes/all
    @GetMapping("/all")
    public String showAllClients(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
        return "mostrarTodosClientes"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/clientes/find/1
    @GetMapping("/find/{id}")
    public String showClient(Model model, @PathVariable("id") int identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        if(cliente!=null){
            model.addAttribute("cliente", clienteService.SearchById(identificacion));
        } else{
            throw new IdClienteNotFoundException(identificacion);
        }

        return "mostrarCliente"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/clientes/find/1
    @GetMapping("/perfil/{id}")
    public String mostrarPerfil(Model model, @PathVariable("id") int identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        if(cliente!=null){
            model.addAttribute("cliente", clienteService.SearchById(identificacion));
        } else{
            throw new NotFoundException(identificacion);
        }

        return "perfilCliente"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/clientes/add
    @GetMapping("/add")
    public String mostrarFormularioCreaString(Model model) {

        Cliente cliente = new Cliente( 0, "", "", "", "", "", null);

        model.addAttribute("cliente", cliente);

        return "registroCliente";
    }

    //Metodo que recibe el POST del formulario para agregar cliente de (/clientes/add)
    @PostMapping("/agregar")
    public String agregarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.add(cliente);
        return "redirect:/clientes/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") int identificacion) {
        clienteService.deleteById(identificacion);
        return "redirect:/clientes/all";
    }
    
    @GetMapping("/update/{id}")
    public String mostrarFormularioUpdate(@PathVariable("id") int identificacion, Model model) {
        model.addAttribute("cliente", clienteService.SearchById(identificacion));
        return "actualizarCliente";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable("id") int identificacion, @ModelAttribute("cliente") Cliente cliente) {
        clienteService.update(cliente);
        return "redirect:/clientes/all";
    }
    
    
}
