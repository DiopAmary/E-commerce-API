package com.enset.responses;

import com.enset.entities.ProduitImagesEntity;

import java.util.Date;
import java.util.List;

public class ProduitResponse {

    private String codeProd;
    private String nomProd;
    private String descriptionProd;
    private boolean available;
    private boolean selected;
    private double prixVente;
    private double prixAchat;
    private double discountPourcentage;
    private double tva;
    private int qteStock;
    private Date createdAt;
    private Date updatedAt;
    private List<ProduitImagesResponse> produitImages;
}
