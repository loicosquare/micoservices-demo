package com.client.clientui.controller;

import com.client.clientui.beans.ProductBean;
import com.client.clientui.proxies.MicroserviceProduitsProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@EnableFeignClients("com.client.clientui")
@RequiredArgsConstructor
@Controller
public class ClientController {

    private final MicroserviceProduitsProxy microserviceProduitsProxy;

    @RequestMapping("/")
    public String accueil(Model model){

        List<ProductBean> produits = microserviceProduitsProxy.listeDesProduits();
        model.addAttribute("produits", produits);
        return "Accueil";
    }

    @RequestMapping("Produits/{id}")
    public String recupererUnProduit(@PathVariable int id,  Model model){
        ProductBean produit = microserviceProduitsProxy.recupererUnProduit(id);
        model.addAttribute("produit", produit);
        return "ficheProduit";
    }
}
