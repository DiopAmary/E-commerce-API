package com.enset.services;


import com.enset.dto.ProduitDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ProduitService {
     ProduitDto createProduit (ProduitDto produitDto);
     ProduitDto getProduitByCodeProd(String codeProd) throws Exception;
     ProduitDto updateProduit(String codeProd, ProduitDto produitDto, List<MultipartFile> photos) throws Exception;
     void deleteProduit(String codeProd) throws Exception;
     ProduitDto getProduitById(long id);
     List<ProduitDto> getProduits(int page, int size, String search, int status);
     List<ProduitDto> getProduits(int page, int size);
}
