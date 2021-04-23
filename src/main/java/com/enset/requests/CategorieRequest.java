package com.enset.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieRequest {
    @NotNull(message = "Ce champ ne doit pas être null")
    private String libelle;
    private String descriptionCategorie;
}