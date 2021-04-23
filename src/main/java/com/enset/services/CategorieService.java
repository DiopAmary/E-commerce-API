package com.enset.services;

import com.enset.dto.CategorieDto;

import java.util.List;

public interface CategorieService {
     CategorieDto createCategorie(CategorieDto categorieDto);
     CategorieDto findCategorieByCodeCategorie(String code) throws Exception;
     CategorieDto updateCategorie(String codeCategorie, CategorieDto categorieDto) throws Exception;
     void deleteCategorie(String codeCategorie) throws Exception;
     List<CategorieDto> getCategories(int page, int size, String search, int status);
}
