package com.enset.services;

import com.enset.dto.FournisseurDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FournisseurService {
    public FournisseurDto createFournisseur (FournisseurDto fournisseurDto);
    public FournisseurDto getFournisseurByCode(String codeFournisseur) throws Exception;
    public FournisseurDto UpdateFournisseur(String codeFournisseur, FournisseurDto fournisseurDto) throws Exception;
    public void deleteFournisseur(String codeFournisseur) throws Exception;
    public List<FournisseurDto> getFournisseurs(int page, int size);
}
