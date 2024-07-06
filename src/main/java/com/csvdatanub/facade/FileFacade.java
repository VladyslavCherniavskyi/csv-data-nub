package com.csvdatanub.facade;

import com.csvdatanub.dto.response.CsvDocumentResponse;
import com.csvdatanub.dto.response.FileMetadataResponse;
import com.csvdatanub.mapper.FileMapper;
import com.csvdatanub.parser.FileParser;
import com.csvdatanub.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Component
@Transactional
@RequiredArgsConstructor
public class FileFacade {

    private final DocumentService documentService;
    private final FileParser fileParser;
    private final FileMapper fileMapper;


    public FileMetadataResponse upload(MultipartFile file) {
        var documents = fileParser.parse(file);
        documentService.createAll(documents);
        return fileMapper.mapTo(file);
    }

    public Page<CsvDocumentResponse> search(String query, Pageable pageable) {
        return documentService.read(query, pageable)
                .map(fileMapper::mapTo);
    }

}
