package com.enset.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategorieResponse  implements Serializable {
    private String codeCategorie;
    private String libelle;
    private String descriptionCategorie;
    private List<ProduitResponse> produits;
}
