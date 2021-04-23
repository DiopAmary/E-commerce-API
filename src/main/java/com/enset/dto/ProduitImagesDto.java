package com.enset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitImagesDto implements Serializable {
    private static final long serialVersionUID = -7064027116985081287L;

    private long id;
    private String image;
    private ProduitDto produit;
}
