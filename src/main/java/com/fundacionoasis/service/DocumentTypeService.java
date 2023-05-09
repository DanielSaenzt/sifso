package com.fundacionoasis.service;

import com.fundacionoasis.entity.BloodType;
import com.fundacionoasis.entity.DocumentType;
import com.fundacionoasis.repository.IBloodTypeRepository;
import com.fundacionoasis.repository.IDocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeService {
    @Autowired
    private IDocumentType iDocumentType;

    public List<DocumentType> findAll(){
        return iDocumentType.findAll();
    }

    public DocumentType save(DocumentType documentType){
        return iDocumentType.save(documentType);
    }

    public void deleteById(Integer id){
        iDocumentType.deleteById(id);
    }


    public Optional<DocumentType> findById(Integer id){
        return iDocumentType.findById(id);
    }
}
