package com.csvdatanub.service.impl;

import com.csvdatanub.document.CsvDocument;
import com.csvdatanub.repository.CsvDocumentRepository;
import com.csvdatanub.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final CsvDocumentRepository csvDocumentRepository;

    @Override
    public CsvDocument create(CsvDocument csvDocument) {
        return csvDocumentRepository.save(csvDocument);
    }

    @Override
    public List<CsvDocument> createAll(List<CsvDocument> documents) {
        return csvDocumentRepository.saveAll(documents);
    }

    @Override
    public Page<CsvDocument> read(String query, Pageable pageable) {
        return csvDocumentRepository.findByTextSearch(query, pageable);
    }

}
