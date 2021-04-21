package com.enset.dto;

import com.enset.responses.ProduitResponse;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class FournisseurDto implements Serializable {
    private static final long serialVersionUID = 2850818402711697076L;

    private long id;
    private String codeFournisseur;
    private String nomFournissuer;
    private String descriptionFournisseur;
    private String emailFourisseur;
    private String adresseFournisseur;
    private String telFournisseur;
    private String photo;
    private List<ProduitDto> produits;
}
