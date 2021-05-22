package com.enset.responses;

import com.enset.dto.ProduitDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProduitImagesResponse {
    private long id;
    private String image;
    @JsonIgnore
    private ProduitDto produitDto;
}
