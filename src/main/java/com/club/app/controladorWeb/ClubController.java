package com.club.app.controladorWeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.club.app.repository.AsociacionRepositorio;
import com.club.app.repository.ClubRepositorio;
import com.club.app.repository.CompeticionRepositorio;
import com.club.app.repository.EntrenadorRepositorio;
import com.club.app.tables.Asociacion;
import com.club.app.tables.Club;
import com.club.app.tables.Competicion;
import com.club.app.tables.Entrenador;

@Controller
public class ClubController {
    
    @Autowired
    private ClubRepositorio clubRepositorio;
    
    @Autowired
    private EntrenadorRepositorio entrenadorRepositorio;
    
    @Autowired
    private AsociacionRepositorio asociacionRepositorio;
    
    @Autowired
    private CompeticionRepositorio competicionRepositorio;
    
    @GetMapping({"/verClub"})
    public String listarClub(Model model) {
        List<Club> listaClubes = clubRepositorio.findAll();
        model.addAttribute("listaClubes", listaClubes);
        return "verClub";
    }
    
    @GetMapping("/verClub/formClub")
    public String mostrarFormulario(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorRepositorio.findEntrenadoresSinClub());
        
        List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
        model.addAttribute("listaAsociacion", listaAsociacion);
        
        List<Competicion> listaCompeticion = competicionRepositorio.findAll();
        model.addAttribute("listaCompeticion", listaCompeticion);
        
        return "formClub";
    }
    
    @PostMapping("/guardarClub")
    public String guardarClub(Club club) {
        clubRepositorio.save(club);
        
        Entrenador entrenador = club.getEntrenador();
        if (entrenador != null) {
            entrenador.setClub(club);
            entrenadorRepositorio.save(entrenador);
        }
        
        return "redirect:/verClub";
    }
    
    @GetMapping("/club/editar/{id}")
    public String modificarClub(@PathVariable("id") Integer id, Model model) {
        Club club = clubRepositorio.findById(id).get();
        model.addAttribute("club", club);
        
        List<Entrenador> listaEntrenador = entrenadorRepositorio.findAll();
        model.addAttribute("listaEntrenador", listaEntrenador);
        
        List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
        model.addAttribute("listaAsociacion", listaAsociacion);
        
        List<Competicion> listaCompeticion = competicionRepositorio.findAll();
        model.addAttribute("listaCompeticion", listaCompeticion);
        
        return "formClub";
    }
    
    @GetMapping("/club/eliminar/{id}")
    public String eliminarClub(@PathVariable("id") Integer id, Model model) {
        clubRepositorio.deleteById(id);
        return "redirect:/verClub";
    }
}