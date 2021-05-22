package com.enset.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.enset.dto.RatingReviewDto;
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

    private String codeCategorie;
    private String codeFournisseur;
}
