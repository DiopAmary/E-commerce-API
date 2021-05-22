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
    private String nomFournisseur;
    private String descriptionFournisseur;
    private String emailFournisseur;
    private String adresseFournisseur;
    private String telFournisseur;
    private List<ProduitDto> produits;
}
