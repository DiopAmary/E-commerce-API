package com.enset.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FournisseurResponse {
    private String codeFournisseur;
    private String nomFournissuer;
    private String descriptionFournisseur;
    private String emailFourisseur;
    private String adresseFournisseur;
    private String telFournisseur;
    private String photo;
    private List<ProduitResponse> produits;
}
