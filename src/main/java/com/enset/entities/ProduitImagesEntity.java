package com.enset.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="produit_images")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProduitImagesEntity implements Serializable {
    private static final long serialVersionUID = -2664449749421159036L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String image;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private ProduitEntity produit;
}
