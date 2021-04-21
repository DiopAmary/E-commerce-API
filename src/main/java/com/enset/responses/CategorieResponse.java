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
public class CategorieResponse {
    private String codeCategorie;
    private String libelle;
    private String descriptionCategorie;
    private List<ProduitResponse> produits;
}
