package com.csvdatanub.repository;

import com.csvdatanub.document.CsvDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvDocumentRepository extends MongoRepository<CsvDocument, String> {

    @Query("{ '$text': { '$search': ?0 } }")
    Page<CsvDocument> findByTextSearch(String text, Pageable pageable);

}
