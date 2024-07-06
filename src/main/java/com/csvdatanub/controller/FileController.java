package com.csvdatanub.controller;

import com.csvdatanub.dto.response.FileMetadataResponse;
import com.csvdatanub.facade.FileFacade;
import com.csvdatanub.validation.annotation.File;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Validated
public class FileController {

    private final FileFacade fileFacade;

    @PostMapping("/upload")
    public ResponseEntity<FileMetadataResponse> upload(@RequestParam("file") @File MultipartFile file) {
        return new ResponseEntity<>(fileFacade.upload(file), HttpStatus.CREATED);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestParam("query") String query, @PageableDefault Pageable pageable) {
        return new ResponseEntity<>(fileFacade.search(query, pageable), HttpStatus.OK);
    }

}
