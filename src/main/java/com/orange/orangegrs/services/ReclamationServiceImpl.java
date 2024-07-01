package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Reclamation;
import com.orange.orangegrs.repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ReclamationServiceImpl implements ReclamationService{


    @Autowired
    ReclamationRepository reclamationRepository;

    @Override
    public Reclamation addNewReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> listOfReclamation() {
        return reclamationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAllSelected(List<Integer> listSelected) {
        for (int i : listSelected){
            this.reclamationRepository.deleteByReclamationId(i);
        }
    }
}
