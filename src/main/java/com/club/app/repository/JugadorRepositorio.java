package com.club.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.club.app.tables.Jugador;

public interface JugadorRepositorio extends JpaRepository<Jugador, Integer> {
}