package com.club.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.club.app.tables.Asociacion;

public interface AsociacionRepositorio extends JpaRepository<Asociacion, Integer> {
}