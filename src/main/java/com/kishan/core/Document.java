package com.kishan.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {

    private String id;
    private String url;
    private String body;

    private Map<String, Integer> termFrequency;

    public Document(String id, String body) {
        buildTermFrequency();
    }

    private void buildTermFrequency() {

    }
}
