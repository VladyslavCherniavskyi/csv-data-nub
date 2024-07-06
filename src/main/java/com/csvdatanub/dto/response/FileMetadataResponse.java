package com.csvdatanub.dto.response;

public record FileMetadataResponse(

        String name,
        Long size,
        String contentType

) {
}
