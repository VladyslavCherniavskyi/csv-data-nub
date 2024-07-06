package com.csvdatanub.mapper;

import com.csvdatanub.document.CsvDocument;
import com.csvdatanub.dto.response.CsvDocumentResponse;
import com.csvdatanub.dto.response.FileMetadataResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.web.multipart.MultipartFile;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FileMapper {

    @Mapping(target = "name", source = "originalFilename")
    FileMetadataResponse mapTo(MultipartFile file);

    CsvDocumentResponse mapTo(CsvDocument document);

}
