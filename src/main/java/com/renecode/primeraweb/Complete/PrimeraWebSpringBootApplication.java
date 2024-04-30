package com.renecode.primeraweb.Complete;

import com.renecode.primeraweb.Complete.entities.Persona;
import com.renecode.primeraweb.Complete.repository.IPersonaRepository;
import com.renecode.primeraweb.Complete.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
//public class PrimeraWebSpringBootApplication implements CommandLineRunner {
public class PrimeraWebSpringBootApplication {

    // *AHORA QUE ESTAMOS USANDO LA CAPA DE SERVICIO YA NO OCUPAMOS AQUI LA CAPA DE REPOSITORY
//	@Autowired
//	private IPersonaRepository iPersonaRepository;

    @Autowired
    private IPersonaService iPersonaService;

    public static void main(String[] args) {
        SpringApplication.run(PrimeraWebSpringBootApplication.class, args);
    }

    /*
    @Override
    public void run(String... args) throws Exception {
        iPersonaService.crearPersona(new Persona(6L, "Pancho", 62));
        iPersonaService.crearPersona(new Persona(7L, "Emas", 16));
        iPersonaService.crearPersona(new Persona(8L, "Marco", 62));
        iPersonaService.crearPersona(new Persona(9L, "Arlyn", 32));
        iPersonaService.crearPersona(new Persona(10L, "Hestia", 26));

        // Muestra el número de personas
//		System.out.println("Número de personas guadadas en la tabla : " + iPersonaRepository.count());
        System.out.println("Número de personas guadadas en la tabla : " + iPersonaService.contarPersonas());


        //Mostrar la lista de personas
//		List<Persona> listPersonas = iPersonaRepository.findAll();
//		listPersonas.forEach(p -> System.out.println("Nombre : " + p.getNombre()));

        List<Persona> listPersonas = iPersonaService.obtenerTodas();
        listPersonas.forEach(p -> System.out.println("Nombre : " + p.getNombre()));
    }
     */
}
