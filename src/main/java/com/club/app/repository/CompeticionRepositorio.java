package com.club.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.club.app.tables.Competicion;

public interface CompeticionRepositorio extends JpaRepository<Competicion, Integer> {
}