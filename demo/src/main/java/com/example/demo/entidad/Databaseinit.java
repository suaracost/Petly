package com.example.demo.entidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.MascotaRepository;

@Controller
public class Databaseinit implements ApplicationRunner{

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mascotaRepository.save(new Mascota("Rex", "Bulldog", 2, 15, "Gastritis", "https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg", "Disponible"));
        mascotaRepository.save(new Mascota("Scooby", "Gran Danes", 4, 25, "Diarrea", "https://ichef.bbci.co.uk/ace/ws/800/cpsprodpb/15665/production/_107435678_perro1.jpg", "Disponible"));
        mascotaRepository.save(new Mascota("Pluto", "Labrador", 5, 30, "Gripe", "https://img.freepik.com/fotos-premium/perrito-lindo-pequeno-posando-alegre-aislado-gris_155003-42660.jpg", "Disponible"));
        mascotaRepository.save(new Mascota("Max", "Beagle", 6, 18, "Healthy", "https://media.istockphoto.com/id/636475496/es/foto/retrato-de-cachorro-marr%C3%B3n-con-fondo-bokeh.jpg?s=612x612&w=0&k=20&c=xVLp2lHN7AhVyTMBXWTf1yfRyJovXT1R0hUwk98Riw8=", "Disponible"));
        mascotaRepository.save(new Mascota("Charlie", "Poodle", 8, 12, "Healthy", "https://cdn.pixabay.com/photo/2019/02/06/15/18/puppy-3979350_640.jpg", "Disponible"));
        mascotaRepository.save(new Mascota("Firulais", "Pastor Aleman", 3, 20, "Moquillo", "https://www.ngenespanol.com/wp-content/uploads/2023/12/descubren-que-los-humanos-influimos-en-el-color-de-ojos-de-los-perros-770x431.jpg", "Disponible"));
        mascotaRepository.save(new Mascota("Rex", "Bulldog", 2, 15, "Gastritis", "https://img.freepik.com/fotos-premium/perrito-lindo-pequeno-posando-alegre-aislado-gris_155003-42660.jpg", "Disponible"));
        mascotaRepository.save(new Mascota("Scooby", "Gran Danes", 4, 25, "Diarrea", "https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg", "Disponible"));

        clienteRepository.save(new Cliente("1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671"));
        clienteRepository.save(new Cliente("1000000002", "Juan", "Sanchez", "juan@gmail.com", "12345672"));
        clienteRepository.save(new Cliente("1000000003", "Maria", "Perez", "maria@gmail.com", "12345673"));
        clienteRepository.save(new Cliente("1000000004", "Luis", "Ramirez", "luis@gmail.com", "12345674"));
        clienteRepository.save(new Cliente("1000000005", "Ana", "Lopez", "ana@gmail.com", "12345675"));
        clienteRepository.save(new Cliente("1000000006", "Jose", "Gonzalez", "jose@gmail.com", "12345676"));

        //Asociar cada mascota con un dueño distinto
        Mascota mascota1 = mascotaRepository.findById(1L).get();
        mascota1.setCliente(clienteRepository.findById(1L).get());
        mascotaRepository.save(mascota1);

        Mascota mascota2 = mascotaRepository.findById(2L).get();
        mascota2.setCliente(clienteRepository.findById(2L).get());
        mascotaRepository.save(mascota2);

        Mascota mascota3 = mascotaRepository.findById(3L).get();
        mascota3.setCliente(clienteRepository.findById(3L).get());
        mascotaRepository.save(mascota3);

        Mascota mascota4 = mascotaRepository.findById(4L).get();
        mascota4.setCliente(clienteRepository.findById(4L).get());
        mascotaRepository.save(mascota4);

        Mascota mascota5 = mascotaRepository.findById(5L).get();
        mascota5.setCliente(clienteRepository.findById(5L).get());
        mascotaRepository.save(mascota5);

        Mascota mascota6 = mascotaRepository.findById(6L).get();
        mascota6.setCliente(clienteRepository.findById(6L).get());
        mascotaRepository.save(mascota6);

        Mascota mascota7 = mascotaRepository.findById(7L).get();
        mascota7.setCliente(clienteRepository.findById(1L).get());
        mascotaRepository.save(mascota7);

        Mascota mascota8 = mascotaRepository.findById(8L).get();
        mascota8.setCliente(clienteRepository.findById(2L).get());
        mascotaRepository.save(mascota8);
    }
    
}
