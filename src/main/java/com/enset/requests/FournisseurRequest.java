package com.enset.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurRequest {
    @NotBlank(message = "Ce champ ne doit pas être null")
    @Size(min = 3, message = "Ce champ devrait au moins avoir 3 caracteres")
    private String nomFournisseur;

    private String descriptionFournisseur;

    @NotNull(message = "Ce champ ne doit pas être null")
    @Email(message = "Vous devez respecter le format email")
    private String emailFournisseur;

    @NotNull(message = "Ce champ ne doit pas être null")
    private String adresseFournisseur;

    @NotNull(message = "Ce champ ne doit pas être null")
    private String telFournisseur;
}
