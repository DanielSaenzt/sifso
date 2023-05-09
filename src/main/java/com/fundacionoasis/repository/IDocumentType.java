package com.fundacionoasis.repository;

import com.fundacionoasis.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentType extends JpaRepository<DocumentType, Integer> {
}
