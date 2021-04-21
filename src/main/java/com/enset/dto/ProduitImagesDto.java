package com.enset.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProduitImagesDto implements Serializable {
    private static final long serialVersionUID = -7064027116985081287L;

    private long id;
    private String image;
}
