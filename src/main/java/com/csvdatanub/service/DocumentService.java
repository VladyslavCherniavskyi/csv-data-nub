package com.csvdatanub.service;

import com.csvdatanub.document.CsvDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocumentService {
    CsvDocument create(CsvDocument csvDocument);

    List<CsvDocument> createAll(List<CsvDocument> documents);

    Page<CsvDocument> read(String query, Pageable pageable);

}
