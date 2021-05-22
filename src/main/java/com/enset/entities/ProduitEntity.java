package com.enset.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "produits")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProduitEntity implements Serializable {

    private static final long serialVersionUID = 1382774530834385657L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String codeProd;

    @Column(nullable = false, length = 50)
    private String nomProd;

    @Column(nullable = false)
    private String descriptionProd;

    private boolean available;

    private boolean selected;

    @Column(nullable = false)
    private double prixVente;

    @Column(nullable = false)
    private double prixAchat;

    @Column(nullable = false)
    private double discountPourcentage;

    @Column(nullable = false)
    private double tva;

    @Column(nullable = false)
    private int qteStock;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProduitImagesEntity> produitImages = new ArrayList<ProduitImagesEntity>();
    

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private CategorieEntity categorie;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private FournisseurEntity fournisseur;
    
    @OneToMany(mappedBy = "produit")
    private Set<RatingReviewEntity> ratingReview= new HashSet<RatingReviewEntity>();
    
    
    @OneToMany(mappedBy = "produit")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LigneCommandeEntity> ligneCommandeEntity = new ArrayList<LigneCommandeEntity>();
    
    @PrePersist
    public void setCreatedAt() {
        this.createdAt= new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void seUpdatedAt() {
        this.updatedAt= new Date();
    }
}
