package com.enset.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitRequest {

    @NotNull(message = "Ce champ ne doit pas être null")
    private String nomProd;

    private String descriptionProd;

    private boolean available;

    private boolean selected;

    @NotNull(message = "Ce champ ne doit pas être null")
    private double prixVente;

    @NotNull(message = "Ce champ ne doit pas être null")
    private double prixAchat;

    private double discountPourcentage;

    private int qteStock;

    private CategorieRequest categorie;

    private FournisseurRequest fournisseur;
}
