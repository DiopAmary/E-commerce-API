package com.enset.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProduitDto implements Serializable {
    private static final long serialVersionUID = -4027211697076850818L;

    private long id;
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
    private CategorieDto categorie;
    private FournisseurDto fournisseur;
    private List<ProduitImagesDto> produitImages;
}
