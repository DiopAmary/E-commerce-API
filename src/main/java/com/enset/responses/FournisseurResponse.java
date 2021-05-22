package com.enset.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FournisseurResponse implements Serializable {
    private String codeFournisseur;
    private String nomFournisseur;
    private String descriptionFournisseur;
    private String emailFournisseur;
    private String adresseFournisseur;
    private String telFournisseur;
    private List<ProduitResponse> produits;
}
