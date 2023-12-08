package com.mcommandes.web.controller;


import com.mcommandes.dao.CommandesDao;
import com.mcommandes.model.Commande;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CommandeController {


    private final CommandesDao commandesDao;

    /**
     * Ajoute une nouvelle commande.
     *
     * @param commande La commande à ajouter.
     * @return ResponseEntity contenant la nouvelle commande ajoutée avec le statut HTTP 201 (Créé).
     * @throws ImpossibleAjouterCommandeException Si l'ajout de la commande est impossible.
     */
    @PostMapping (value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

        Commande nouvelleCommande = commandesDao.save(commande);

        if(nouvelleCommande == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }


    /**
     * Récupère une commande en fonction de son identifiant.
     *
     * @param id L'identifiant de la commande à récupérer.
     * @return Une option contenant la commande correspondant à l'identifiant, si elle existe.
     * @throws CommandeNotFoundException Si aucune commande correspondant à l'identifiant n'est trouvée.
     */
    @GetMapping(value = "/commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id){

        Optional<Commande> commande = commandesDao.findById(id);

        if(!commande.isPresent()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        return commande;
    }
}
