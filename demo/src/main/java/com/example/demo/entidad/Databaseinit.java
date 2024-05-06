package com.example.demo.entidad;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import com.example.demo.repositorio.ClienteRepository;
import com.example.demo.repositorio.MascotaRepository;
import com.example.demo.repositorio.TratamientoRepository;
import com.example.demo.repositorio.VeterinarioRepository;

import com.example.demo.repositorio.DrogaRepository;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Controller
@Profile("default")
public class Databaseinit implements ApplicationRunner{

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

        for (int i = 1; i <= 100; i++) {
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
        clienteRepository.save(new Cliente("1000000001", "Alejandro", "Suarez", "alejandroesuareza@javeriana.edu.co", "12345671"));
        clienteRepository.save(new Cliente("1000000002", "Hermann", "Hernandez", "hd.hernandez@javeriana.edu.co", "12345672"));
        clienteRepository.save(new Cliente("1000000003", "Nicolas", "Rincon", "ni.rincon@javeriana.edu.co", "12345673"));
        clienteRepository.save(new Cliente("1000000004", "Luis", "Ramirez", "luis@gmail.com", "12345674"));
        clienteRepository.save(new Cliente("1000000005", "Ana", "Lopez", "ana@gmail.com", "12345675"));
        clienteRepository.save(new Cliente("1000000006", "Jose", "Gonzalez", "jose@gmail.com", "12345676"));
        clienteRepository.save(new Cliente("1000000007", "Laura", "Martinez", "laura@gmail.com", "12345677"));
        clienteRepository.save(new Cliente("1000000008", "Carlos", "Hernandez", "carlos@gmail.com", "12345678"));
        clienteRepository.save(new Cliente("1000000009", "Sofia", "Diaz", "sofia@gmail.com", "12345679"));
        clienteRepository.save(new Cliente("1000000010", "Diego", "Torres", "diego@gmail.com", "12345680"));
        clienteRepository.save(new Cliente("1000000011", "Paula", "Gomez", "paula@gmail.com", "12345681"));
        clienteRepository.save(new Cliente("1000000012", "Alejandro", "Alvarez", "alejandro@gmail.com", "12345682"));
        clienteRepository.save(new Cliente("1000000013", "Elena", "Fernandez", "elena@gmail.com", "12345683"));
        clienteRepository.save(new Cliente("1000000014", "Miguel", "Castro", "miguel@gmail.com", "12345684"));
        clienteRepository.save(new Cliente("1000000015", "Carmen", "Rojas", "carmen@gmail.com", "12345685"));
        clienteRepository.save(new Cliente("1000000016", "Fernando", "Ortega", "fernando@gmail.com", "12345686"));
        clienteRepository.save(new Cliente("1000000017", "Gabriela", "Vargas", "gabriela@gmail.com", "12345687"));
        clienteRepository.save(new Cliente("1000000018", "Javier", "Reyes", "javier@gmail.com", "12345688"));
        clienteRepository.save(new Cliente("1000000019", "Rosa", "Molina", "rosa@gmail.com", "12345689"));
        clienteRepository.save(new Cliente("1000000020", "Pablo", "Navarro", "pablo@gmail.com", "12345690"));
        clienteRepository.save(new Cliente("1000000021", "Isabel", "Santos", "isabel@gmail.com", "12345691"));
        clienteRepository.save(new Cliente("1000000022", "Andres", "Cruz", "andres@gmail.com", "12345692"));
        clienteRepository.save(new Cliente("1000000023", "Lucia", "Iglesias", "lucia@gmail.com", "12345693"));
        clienteRepository.save(new Cliente("1000000024", "Daniel", "Fuentes", "daniel@gmail.com", "12345694"));
        clienteRepository.save(new Cliente("1000000025", "Eva", "Herrera", "eva@gmail.com", "12345695"));
        clienteRepository.save(new Cliente("1000000026", "Mario", "Aguilar", "mario@gmail.com", "12345696"));
        clienteRepository.save(new Cliente("1000000027", "Raquel", "Lorenzo", "raquel@gmail.com", "12345697"));
        clienteRepository.save(new Cliente("1000000028", "Alberto", "Gimenez", "alberto@gmail.com", "12345698"));
        clienteRepository.save(new Cliente("1000000029", "Natalia", "Jimenez", "natalia@gmail.com", "12345699"));
        clienteRepository.save(new Cliente("1000000030", "Antonio", "Mendez", "antonio@gmail.com", "12345700"));
        clienteRepository.save(new Cliente("1000000031", "Sara", "Ruiz", "sara@gmail.com", "12345701"));
        clienteRepository.save(new Cliente("1000000032", "Roberto", "Silva", "roberto@gmail.com", "12345702"));
        clienteRepository.save(new Cliente("1000000033", "Ines", "Blanco", "ines@gmail.com", "12345703"));
        clienteRepository.save(new Cliente("1000000034", "Gloria", "Gomez", "gloria@gmail.com", "12345704"));
        clienteRepository.save(new Cliente("1000000035", "Victor", "Leon", "victor@gmail.com", "12345705"));
        clienteRepository.save(new Cliente("1000000036", "Monica", "Navarro", "monica@gmail.com", "12345706"));
        clienteRepository.save(new Cliente("1000000037", "Hugo", "Moreno", "hugo@gmail.com", "12345707"));
        clienteRepository.save(new Cliente("1000000038", "Silvia", "Perez", "silvia@gmail.com", "12345708"));
        clienteRepository.save(new Cliente("1000000039", "Ruben", "Castillo", "ruben@gmail.com", "12345709"));
        clienteRepository.save(new Cliente("1000000040", "Marta", "Gutierrez", "marta@gmail.com", "12345710"));
        clienteRepository.save(new Cliente("1000000041", "Raul", "Diaz", "raul@gmail.com", "12345711"));
        clienteRepository.save(new Cliente("1000000042", "Clara", "Sanchez", "clara@gmail.com", "12345712"));
        clienteRepository.save(new Cliente("1000000043", "Lorenzo", "Fernandez", "lorenzo@gmail.com", "12345713"));
        clienteRepository.save(new Cliente("1000000044", "Alicia", "Morales", "alicia@gmail.com", "12345714"));
        clienteRepository.save(new Cliente("1000000045", "Cristian", "Ortega", "cristian@gmail.com", "12345715"));
        clienteRepository.save(new Cliente("1000000046", "Lidia", "Gonzalez", "lidia@gmail.com", "12345716"));
        clienteRepository.save(new Cliente("1000000047", "Diego", "Martinez", "diego2@gmail.com", "12345717"));
        clienteRepository.save(new Cliente("1000000048", "Sandra", "Hernandez", "sandra@gmail.com", "12345718"));
        clienteRepository.save(new Cliente("1000000049", "Francisco", "Alvarez", "francisco@gmail.com", "12345719"));
        clienteRepository.save(new Cliente("1000000050", "Elena", "Santos", "elena2@gmail.com", "12345720"));
        
        //Asociar cada mascota con un dueño distinto
        for (int i = 1; i <= 100; i++) {

            if (i > 50) {
                Mascota mascota = mascotaRepository.findById((long) i).get();
                int aux = i - 50;
                mascota.setCliente(clienteRepository.findById((long) aux).get());
                mascotaRepository.save(mascota);

            } else {
                Mascota mascota = mascotaRepository.findById((long) i).get();
                mascota.setCliente(clienteRepository.findById((long) i).get());
                mascotaRepository.save(mascota);
            }
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
        for (int i = 1; i <= 100; i++) {
            for (int j = 0; j < 3; j++) {
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
}
