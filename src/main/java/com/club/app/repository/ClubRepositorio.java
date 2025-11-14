package com.club.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.club.app.tables.Club;

public interface ClubRepositorio extends JpaRepository<Club, Integer> {
}