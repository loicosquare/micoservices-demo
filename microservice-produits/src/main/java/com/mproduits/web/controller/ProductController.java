package com.mproduits.web.controller;

import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;
import com.mproduits.web.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    //@Autowired
    private final ProductDao productDao;

    /**
     * Récupère la liste de tous les produits disponibles.
     *
     * @return Une liste contenant tous les produits disponibles à la vente.
     * @throws ProductNotFoundException Si aucun produit n'est disponible.
     */
    @GetMapping(value = "/Produits")
    public List<Product> listeDesProduits(){
        try {
            List<Product> products = productDao.findAll();
            LOGGER.debug(" Liste des produites retrouvés : {}", products);
            if(products.isEmpty()) throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");
            return products;
        }catch (Exception ex){
            LOGGER.error("Une erreur est survenue : {}", ex.getMessage());
        }
        return Collections.emptyList();
    }

    /**
     * Récupère un produit en fonction de son identifiant.
     *
     * @param id L'identifiant du produit à récupérer.
     * @return Une option contenant le produit correspondant à l'identifiant, s'il existe.
     * @throws ProductNotFoundException Si aucun produit correspondant à l'identifiant n'est trouvé.
     */
    @GetMapping( value = "/Produits/{id}")
    public Optional<Product> recupererUnProduit(@PathVariable int id) {

        Optional<Product> product = productDao.findById(id);
        if(!product.isPresent())  throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");
        return product;
    }
}

