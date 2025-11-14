package com.club.app.controladorWeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.club.app.repository.ClubRepositorio;
import com.club.app.repository.EntrenadorRepositorio;
import com.club.app.tables.Club;
import com.club.app.tables.Entrenador;

@Controller
public class EntrenadorController {
    
    @Autowired
    private EntrenadorRepositorio entrenadorRepositorio;
    
    @Autowired
    private ClubRepositorio clubRepositorio;
    
    // ======= VER LISTA DE ENTRENADORES =======
    @GetMapping("/verEntrenador")
    public String listarEntrenadores(Model model) {
        List<Entrenador> listaEntrenadores = entrenadorRepositorio.findAll();
        model.addAttribute("listaEntrenadores", listaEntrenadores);
        return "verEntrenador";
    }
    
    // ======= MOSTRAR FORMULARIO =======
    @GetMapping("/verEntrenador/formEntrenador")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        List<Club> listaClubes = clubRepositorio.findAll();
        model.addAttribute("listaClubes", listaClubes);
        return "formEntrenador";
    }
    
    // ======= GUARDAR ENTRENADOR =======
    @PostMapping("/guardarEntrenador")
    public String guardarEntrenador(Entrenador entrenador) {
        // Si el formulario no asignó un club, evitar error de referencia nula
        if (entrenador.getClub() != null && entrenador.getClub().getId() == null) {
            entrenador.setClub(null);
        }

        entrenadorRepositorio.save(entrenador);
        return "redirect:/verEntrenador";
    }
    
    // ======= EDITAR ENTRENADOR =======
    @GetMapping("/entrenador/editar/{id}")
    public String modificarEntrenador(@PathVariable("id") Integer id, Model model) {
        Entrenador entrenador = entrenadorRepositorio.findById(id).orElse(null);
        if (entrenador != null) {
            model.addAttribute("entrenador", entrenador);
            List<Club> listaClubes = clubRepositorio.findAll(); // Agregamos la lista para el select
            model.addAttribute("listaClubes", listaClubes);
            return "formEntrenador";
        } else {
            return "redirect:/verEntrenador";
        }
    }
    
    // ======= ELIMINAR ENTRENADOR =======
    @GetMapping("/entrenador/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable("id") Integer id) {
        entrenadorRepositorio.deleteById(id);
        return "redirect:/verEntrenador";
    }
}
