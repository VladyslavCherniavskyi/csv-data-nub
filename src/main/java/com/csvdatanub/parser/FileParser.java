package com.csvdatanub.parser;

import com.csvdatanub.document.CsvDocument;
import com.csvdatanub.exeption.FileParserException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Component
public class FileParser {

    public List<CsvDocument> parse(MultipartFile file) {
        try (var inputStream = file.getInputStream()) {
            MappingIterator<Map<String, Object>> mappingIterator = new CsvMapper()
                    .readerFor(Map.class)
                    .with(CsvSchema.emptySchema().withHeader())
                    .readValues(inputStream);
            Iterable<Map<String, Object>> iterable = () -> mappingIterator;
            return StreamSupport.stream(iterable.spliterator(), false)
                    .map(data -> {
                        var csvDocument = new CsvDocument();
                        csvDocument.setData(data.toString());
                        return csvDocument;
                    })
                    .toList();
        } catch (IOException e) {
            throw new FileParserException("Failed to upload file: " + e.getMessage());
        }
    }

}
