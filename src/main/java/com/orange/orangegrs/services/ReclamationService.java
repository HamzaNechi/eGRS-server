package com.orange.orangegrs.services;

import com.orange.orangegrs.entities.Reclamation;

import java.util.List;

public interface ReclamationService {
    Reclamation addNewReclamation(Reclamation reclamation);

    List<Reclamation> listOfReclamation();


    void deleteAllSelected(List<Integer> listSelected);
}
