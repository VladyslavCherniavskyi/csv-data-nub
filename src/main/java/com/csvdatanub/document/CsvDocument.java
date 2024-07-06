package com.csvdatanub.document;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "csv_documents")
public class CsvDocument {

    @Id
    private String id;

    @TextIndexed
    private String data;

}
