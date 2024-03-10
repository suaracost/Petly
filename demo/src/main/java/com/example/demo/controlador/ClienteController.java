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



@RequestMapping("veterinario/clientes")
@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //http://localhost:8090/veterinario/clientes/all
    @GetMapping("/all")
    public String showAllClients(Model model) {
        model.addAttribute("clientes", clienteService.SearchAll());
        return "mostrarTodosClientes"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/veterinario/clientes/find/1
    @GetMapping("/find/{id}")
    public String showClient(Model model, @PathVariable("id") Long identificacion) {
        Cliente cliente = clienteService.SearchById(identificacion);

        if(cliente!=null){
            model.addAttribute("cliente", clienteService.SearchById(identificacion));
        } else{
            throw new IdClienteNotFoundException(identificacion);
        }

        return "mostrarCliente"; //Esto retornara al HTML que se debe mostrar
    }

    //http://localhost:8090/veterinario/clientes/add
    @GetMapping("/add")
    public String mostrarFormularioCreaString(Model model) {

        Cliente cliente = new Cliente("", "", "", "", "");

        model.addAttribute("cliente", cliente);

        return "registroCliente";
    }

    //http://localhost:8090/veterinario/clientes/agregar
    @PostMapping("/agregar")
    public String agregarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteService.add(cliente);
        return "redirect:/veterinario/clientes/all";
    }

    //http://localhost:8090/veterinario/clientes/delete/1
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable("id") Long identificacion) {
        clienteService.deleteById(identificacion);
        return "redirect:/veterinario/clientes/all";
    }
    
    //http://localhost:8090/veterinario/clientes/update/1
    @GetMapping("/update/{id}")
    public String mostrarFormularioUpdate(@PathVariable("id") Long identificacion, Model model) {
        model.addAttribute("cliente", clienteService.SearchById(identificacion));
        return "actualizarCliente";
    }

    //http://localhost:8090/veterinario/clientes/update/1
    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable("id") int identificacion, @ModelAttribute("cliente") Cliente cliente) {
        clienteService.update(cliente);
        return "redirect:/veterinario/clientes/all";
    }
    
    
}
