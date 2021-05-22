package com.enset.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProduitResponse implements Serializable{

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
    @JsonIgnore
    private CategorieResponse categorie;
    @JsonIgnore
    private FournisseurResponse fournisseur;
    private List<ProduitImagesResponse> produitImages;
}
