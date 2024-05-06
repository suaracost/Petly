package com.example.demo.entidad;

import com.example.demo.repositorio.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Profile("test")
public class DatabaseinitTest implements ApplicationRunner{

    @Autowired
    MascotaRepository mascotaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Autowired
    DrogaRepository drogaRepository;

    @Autowired
    TratamientoRepository tratamientoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Creación de mascotas
        String[] nombresPerros = {
            "Max", "Bella", "Charlie", "Lucy", "Cooper", "Daisy", "Buddy", "Luna", "Rocky", "Lola",
            "Bailey", "Sadie", "Toby", "Molly", "Bear", "Maggie", "Duke", "Sophie", "Jake", "Chloe",
            "Teddy", "Zoe", "Jack", "Roxy", "Riley", "Penny", "Oliver", "Ruby", "Murphy", "Gracie",
            "Harley", "Coco", "Lucky", "Sasha", "Oscar", "Zoey", "Sam", "Rosie", "Tucker", "Mia",
            "Zeus", "Annie", "Marley", "Princess", "Gus", "Sandy", "Leo", "Pepper", "Winston", "Apolo"
        };

        String[] razasPerros = {
            "Labrador Retriever", "Golden Retriever", "German Shepherd", "Beagle", "Bulldog", 
            "Poodle", "Boxer", "Siberian Husky", "Dachshund", "Shih Tzu", 
            "Yorkshire Terrier", "Rottweiler", "Australian Shepherd", "Border Collie", "Pug",
            "Chihuahua", "Doberman Pinscher", "Great Dane", "Shetland Sheepdog", "Boston Terrier",
            "Cocker Spaniel", "Maltese", "Pomeranian", "Saint Bernard", "Cavalier King Charles Spaniel"
        };
        String[] enfermedadesPerros = {
            "Parvovirus", "Moquillo Canino", "Leptospirosis", "Tos de las perreras", "Gastroenteritis",
            "Obesidad", "Diabetes", "Artritis", "Alergias cutáneas", "Otitis", 
            "Enfermedad del corazón", "Cáncer", "Enfermedad periodontal", "Insuficiencia renal", "Hepatitis",
            "Luxación de rótula", "Diarrea", "Cistitis", "Dermatitis", "Epilepsia",
            "Hipotiroidismo", "Hipertiroidismo", "Displasia de cadera", "Cálculos urinarios", "Hipertensión"
        };
        String[] enlaces = {
            "https://static.fundacion-affinity.org/cdn/farfuture/PVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE/mtime:1643275542/sites/default/files/los-10-sonidos-principales-del-perro.jpg",
            "https://ichef.bbci.co.uk/ace/ws/800/cpsprodpb/15665/production/_107435678_perro1.jpg",
            "https://img.freepik.com/fotos-premium/perrito-lindo-pequeno-posando-alegre-aislado-gris_155003-42660.jpg",
            "https://media.istockphoto.com/id/636475496/es/foto/retrato-de-cachorro-marr%C3%B3n-con-fondo-bokeh.jpg?s=612x612&w=0&k=20&c=xVLp2lHN7AhVyTMBXWTf1yfRyJovXT1R0hUwk98Riw8=",
            "https://cdn.pixabay.com/photo/2019/02/06/15/18/puppy-3979350_640.jpg",
            "https://www.ngenespanol.com/wp-content/uploads/2023/12/descubren-que-los-humanos-influimos-en-el-color-de-ojos-de-los-perros-770x431.jpg"
        };
        int numFoto = 0;

        for (int i = 1; i <= 10; i++) {
            String nombre = nombresPerros[(int) (Math.random() * 50) ];
            String raza = razasPerros[(int) (Math.random() * 25)];
            int edad = (int) (Math.random() * 15);
            float peso = (float) (Math.round(((Math.random() * 20) + 5) * 10) / 10.0);
            String enfermedad = enfermedadesPerros[(int) (Math.random() * 25)];
            String foto = enlaces[numFoto];
            
            numFoto++;
            if (numFoto == 6) {
                numFoto = 0;
            }
        
            String estado = "Disponible";
            if (i % 10 == 0) {
                estado = "Inactivo";
            }
        
            mascotaRepository.save(new Mascota(nombre, raza, edad, peso, enfermedad, foto, estado));
        }
        
        //Creacion de clientes
        clienteRepository.save(new Cliente("1000000001", "Pedro", "Duran", "pedro@gmail.com", "12345671"));
        clienteRepository.save(new Cliente("1000000002", "Juan", "Sanchez", "juan@gmail.com", "12345672"));
        clienteRepository.save(new Cliente("1000000003", "Maria", "Perez", "maria@gmail.com", "12345673"));
        clienteRepository.save(new Cliente("1000000004", "Luis", "Ramirez", "luis@gmail.com", "12345674"));
        clienteRepository.save(new Cliente("1000000005", "Ana", "Lopez", "ana@gmail.com", "12345675"));
        clienteRepository.save(new Cliente("1000000006", "Jose", "Gonzalez", "jose@gmail.com", "12345676"));
        clienteRepository.save(new Cliente("1000000007", "Laura", "Martinez", "laura@gmail.com", "12345677"));
        clienteRepository.save(new Cliente("1000000008", "Carlos", "Hernandez", "carlos@gmail.com", "12345678"));
        clienteRepository.save(new Cliente("1000000009", "Sofia", "Diaz", "sofia@gmail.com", "12345679"));
        clienteRepository.save(new Cliente("1000000010", "Diego", "Torres", "diego@gmail.com", "12345680"));
        
        //Asociar cada mascota con un dueño distinto
        for (int i = 1; i <= 10; i++) {
            Mascota mascota = mascotaRepository.findById((long) i).get();
            mascota.setCliente(clienteRepository.findById((long) i).get());
            mascotaRepository.save(mascota);
        } 
        
        //Creacion de veterinarios y el admin
        veterinarioRepository.save(new Veterinario("1000000000", "Administrador", "Petly", "Admin", "", "Administrador", 0, "Admin"));
        veterinarioRepository.save(new Veterinario("1000000051", "Alexander", "García", "Petly123", "https://universidadeuropea.com/resources/media/images/medicina-veterinaria-1200x630.original.jpg", "General", 0, "Disponible"));
        veterinarioRepository.save(new Veterinario("1000000052", "Isabella", "Martínez", "Petly123", "https://bensvet.com.br/wp-content/uploads/2020/03/blog-5-passos-para-se-tornar-o-melhor-m%C3%A9dico-veterin%C3%A1rio.jpg", "Quirurgico", 0, "Disponible"));
        veterinarioRepository.save(new Veterinario("1000000053", "Sebastián", "Rojas", "Petly123", "https://cdn979857.fac.mil.co/sites/default/files/2022-05/caman.jpg", "Intensivista", 0, "Disponible"));
        veterinarioRepository.save(new Veterinario("1000000054", "Valentina", "López", "Petly123", "https://papelmatic.com/wp-content/uploads/2019/09/papelmatic-higiene-profesional-limpieza-desinfeccion-clinicas-veterinarias.jpg", "Medicina Interna", 0, "Disponible"));
        veterinarioRepository.save(new Veterinario("1000000055", "Sofia", "Herrera", "Petly123", "https://t1.ea.ltmcdn.com/es/posts/3/6/3/cuando_llevar_a_mi_cachorro_al_veterinario_por_primera_vez_23363_600_square.jpg", "Dermatologo", 0, "Disponible"));
        veterinarioRepository.save(new Veterinario("1000000056", "Mateo", "González", "Petly123", "https://media.mercola.com/ImageServer/Public/2016/July/hipotiroidismo-en-perros.jpg", "Quirurgico", 0, "Disponible"));
        veterinarioRepository.save(new Veterinario("1000000057", "Natalia", "Silva", "Petly123", "https://www.himalayacentroamericana.com/sites/default/files/consultaveterinaria.jpg", "Medicina Interna", 0, "Disponible"));        
        veterinarioRepository.save(new Veterinario("1000000058", "Leonardo", "Pérez", "Petly123", "https://eduka.occidente.co/wp-content/uploads/2022/04/Donde-estudiar-Medicina-Veterinaria.jpg", "Odontologo", 0, "Inactivo"));

        //Leer las drogas del archivo excel y guardarlos en la base de datos
        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream("MEDICAMENTOS_VETERINARIA.xlsx"); 
            
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet("MEDICAMENTOS BD FINAL");
            for(int rowIndex =1; rowIndex <= sheet.getLastRowNum(); rowIndex++){
                Row row = sheet.getRow(rowIndex);
                if(row != null){
                    Droga drug = new Droga();
                    drug.setNombre(row.getCell(0).getStringCellValue());
                    drug.setPrecioVenta((float) row.getCell(1).getNumericCellValue());
                    drug.setPrecioCompra((float) row.getCell(2).getNumericCellValue());
                    drug.setUnidadesDisponibles((int) row.getCell(3).getNumericCellValue());
                    drug.setUnidadesVendidas((int) row.getCell(4).getNumericCellValue());
                    drogaRepository.save(drug);
                }
            }

            if(workbook != null){
                workbook.close();
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Creacion de tratamientos
        for (int i = 1; i <= 10; i++) {
            int idVeterinario = (int) (Math.random() * 8) + 2;
            int idDroga = (int) (Math.random() * 523) + 1;
            int mes = (int) (Math.random() * 12) + 1;
            int dia = (int) (Math.random() * 30) + 1;
            String fecha = dia + "-" + mes + "-2024"; // Cambia la fecha según sea necesario
            Tratamiento tratamiento = new Tratamiento();
            tratamiento.setmascota(mascotaRepository.findById((long) i).get());
            tratamiento.setveterinario(veterinarioRepository.findById((long) idVeterinario).get());

            //Sumarle un numero de atención al veterinario
            Veterinario vet = veterinarioRepository.findById((long) idVeterinario).get();
            vet.setNumAtenciones(vet.getNumAtenciones() + 1);
            veterinarioRepository.save(vet);

            //Crear tratamientos con drogas que tengan unidades disponibles
            boolean flag = false;
            while(flag == false){
                if(drogaRepository.findById((long) idDroga).get().getUnidadesDisponibles() != 0) {
                    tratamiento.setdroga(drogaRepository.findById((long) idDroga).get());
                    flag = true;
                }
                else
                    idDroga = (int) (Math.random() * 523) + 1;
            }

            //Asignar la fecha de hoy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            LocalDate fechaLocalDate = LocalDate.parse(fecha, formatter);
            tratamiento.setFecha(fechaLocalDate);

            tratamientoRepository.save(tratamiento);

            //Despues que se agrega la droga se hace la modificación en la base de datos
            Droga droga = drogaRepository.findById((long) idDroga).get();
            droga.setUnidadesDisponibles(droga.getUnidadesDisponibles() - 1);
            droga.setUnidadesVendidas(droga.getUnidadesVendidas() + 1);
            drogaRepository.save(droga);
        }   
    }
}
