package com.orange.orangegrs.repositories;

import com.orange.orangegrs.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
    List<Reclamation> findAll();


    int deleteByReclamationId(int reclamationId);
}
