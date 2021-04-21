package com.enset.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name="fournisseurs")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurEntity implements Serializable {
    private static final long serialVersionUID = 7663599929795015301L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String codeFournisseur;

    @Column(nullable = false, length = 50)
    private String nomFournisseur;

    @Column(nullable = false)
    private String descriptionFournisseur;

    @Column(nullable = false, length = 100)
    private String emailFourisseur;

    @Column(nullable = false)
    private String adresseFournisseur;

    @Column(nullable = false, length = 50)
    private String telFournisseur;

    private String photo=null;

    @OneToMany(mappedBy = "fournisseur",fetch = FetchType.EAGER)
    private List<ProduitEntity> produits;
}
