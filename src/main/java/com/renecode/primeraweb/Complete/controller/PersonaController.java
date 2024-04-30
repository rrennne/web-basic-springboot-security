package com.renecode.primeraweb.Complete.controller;

import com.renecode.primeraweb.Complete.entities.Persona;
import com.renecode.primeraweb.Complete.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private IPersonaService iPersonaService;

    // La clase Model lse utiliza para transferir objetos del Controller a la VISTA.
    @GetMapping
    public String listarPersonas(Model model){
        List<Persona> personas = iPersonaService.obtenerTodas();
        model.addAttribute("personasLista", personas); // | Key-Valor |

        return  "listarPersonas";
    }

    @GetMapping("/nuevaPersona")
    public String mostrarFormularioNuevaPersona(Model model) {
        model.addAttribute("persona", new Persona());
        model.addAttribute("accion", "/personas/nuevaPersona");
        return "formularioNuevaPersona";
    }

    @PostMapping("/nuevaPersona")
    public String guardarNuevaPersona(@ModelAttribute Persona persona) {
        iPersonaService.crearPersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPersona(@PathVariable Long id,@ModelAttribute Persona persona,Model model) {
        model.addAttribute("persona", persona);
        model.addAttribute("accion", "/personas/editar/"+id);
        return "formularioNuevaPersona";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable Long id, @ModelAttribute Persona persona){
        iPersonaService.actualizarPersona(id, persona);
        return "redirect:/personas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id){
        iPersonaService.eliminarPersona(id);
        return "redirect:/personas";
    }
}
