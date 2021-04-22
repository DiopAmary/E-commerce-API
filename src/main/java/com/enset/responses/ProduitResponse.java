package com.enset.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private CategorieResponse categorie;
    private FournisseurResponse fournisseur;
    private List<ProduitImagesResponse> produitImages;
}
