package com.club.app.controladorWeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.club.app.repository.ClubRepositorio;
import com.club.app.repository.JugadorRepositorio;
import com.club.app.tables.Club;
import com.club.app.tables.Jugador;

@Controller
public class JugadorController {
    
    @Autowired
    private JugadorRepositorio jugadorRepositorio;
    
    @Autowired
    private ClubRepositorio clubRepositorio;
    
    @GetMapping({"/verJugador"})
    public String listarJugadores(Model model) {
        List<Jugador> listaJugadores = jugadorRepositorio.findAll();
        model.addAttribute("listaJugadores", listaJugadores);
        return "verJugador";
    }
    
    @GetMapping("/verJugador/formJugador")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        List<Club> listaClubes = clubRepositorio.findAll();
        model.addAttribute("listaClubes", listaClubes);
        return "formJugador";
    }
    
    @PostMapping("/guardarJugador")
    public String guardarJugador(Jugador jugador) {
        jugadorRepositorio.save(jugador);
        return "redirect:/verJugador";
    }
    
    @GetMapping("/jugador/editar/{id}")
    public String modificarJugador(@PathVariable("id") Integer id, Model model) {
        Jugador jugador = jugadorRepositorio.findById(id).orElse(new Jugador());
        model.addAttribute("jugador", jugador);
        List<Club> listaClubes = clubRepositorio.findAll(); // corregido el nombre
        model.addAttribute("listaClubes", listaClubes);
        return "formJugador";
    }
    
    @GetMapping("/jugador/eliminar/{id}")
    public String eliminarJugador(@PathVariable("id") Integer id, Model model) {
        jugadorRepositorio.deleteById(id);
        return "redirect:/verJugador";
    }
}
