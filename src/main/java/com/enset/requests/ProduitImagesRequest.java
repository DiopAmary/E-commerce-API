package com.enset.requests;

import com.enset.dto.ProduitDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitImagesRequest {
    private String codeProduit;
}
