package com.client.clientui.proxies;

import com.client.clientui.beans.ProductBean;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
/*
 * Classe qui se comporte comme un proxy dont le but est de regrouper les requetes que nous allons passer au microservice-produits
 * C'est juste une classe qui fait le lien avec les microservices extérieurs.
 * */


/**
 * Proxy pour interagir avec le microservice "microservice-produits".
 */
@FeignClient(name = "microservice-produits", url = "localhost:9001", decode404 = false)
@Service
public interface MicroserviceProduitsProxy {

    /**
     * Récupère la liste des produits depuis le microservice "microservice-produits".
     *
     * @return Une liste de ProductBean représentant les produits disponibles.
     */
    @GetMapping(value = "/Produits")
    List<ProductBean> listeDesProduits();


    /**
     * Récupère un produit spécifique depuis le microservice "microservice-produits" en fonction de son identifiant.
     *
     * @param id L'identifiant du produit à récupérer.
     * @return Un objet ProductBean représentant le produit correspondant à l'identifiant donné.
     */
    @GetMapping( value = "/Produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);

}