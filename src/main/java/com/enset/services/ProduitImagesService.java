package com.enset.services;

import com.enset.dto.ProduitDto;
import com.enset.dto.ProduitImagesDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProduitImagesService {
    public ProduitImagesDto createProduitImage(MultipartFile image, ProduitDto produitDto);
    public ProduitImagesDto updateProduitImage(ProduitImagesDto produitImageDto, MultipartFile image, ProduitDto produitDto);
    public void deleteProduitImage(long id) throws Exception;
    public List<ProduitImagesDto> findProduitImageByProduit(String codeProduit) throws Exception;
}
