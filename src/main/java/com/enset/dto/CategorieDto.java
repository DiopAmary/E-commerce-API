package com.enset.dto;

import com.enset.responses.ProduitResponse;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategorieDto implements Serializable {
    private static final long serialVersionUID = -8508128707640271169L;

    private long id;
    private String codeCategorie;
    private String libelle;
    private String descriptionCategorie;
    private List<ProduitDto> produits;
}
