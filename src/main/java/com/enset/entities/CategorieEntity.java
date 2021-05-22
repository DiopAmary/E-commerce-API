package com.enset.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name="categories")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategorieEntity implements Serializable {
    private static final long serialVersionUID = 1263599929795015301L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String codeCategorie;

    @Column(nullable = false, length = 50)
    private String libelle;

    @Column(nullable = false)
    private String descriptionCategorie;

    @OneToMany(mappedBy = "categorie",fetch = FetchType.EAGER)
    private List<ProduitEntity> produits;

    public CategorieEntity(long id, String codeCategorie, String libelle, String description){
        this.id = id;
        this.codeCategorie = codeCategorie;
        this.libelle = libelle;
        this.descriptionCategorie = description;
    }
}
