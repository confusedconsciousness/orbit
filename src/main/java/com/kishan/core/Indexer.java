package com.kishan.core;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class Indexer {

    StanfordCoreNLP pipeline;
    public Indexer() {
        setup();
    }

    private void setup() {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        this.pipeline = new StanfordCoreNLP(props);
    }
    public static Set<String> STOP_WORDS = Set.of("a", "an", "and", "are", "as", "at", "be", "but", "by",
            "for", "if", "in", "into", "is", "it",
            "no", "not", "of", "on", "or", "s", "such",
            "t", "that", "the", "their", "then", "there", "these",
            "they", "this", "to", "was", "will", "with");


    public void generateInvertedIndex(Document document) {
        List<String> tokens = tokenize(document.getBody());
        log.info(tokens.toString());
    }

    public static void main(String[] args) throws IOException {
        // TO TEST THE WORKFLOW
        Indexer indexer = new Indexer();
        // the following data will be provided by the crawler
        String url = "/dummy.txt";
        InputStream inputStream = Indexer.class.getResourceAsStream(url);
        Document document = Document.builder()
                .id(UUID.randomUUID().toString())
                .url(url)
                .body(indexer.readFromInputStream(inputStream))
                .build();

        indexer.generateInvertedIndex(document);
    }

    /**
     * Reads input from an InputStream and returns it as a string.
     *
     * @param inputStream The InputStream to read from.
     * @return The contents of the InputStream as a string.
     * @throws IOException If an I/O error occurs while reading the InputStream.
     */
    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }


    private List<String> tokenize(String corpus) {
        Annotation annotation = new Annotation(corpus);
        pipeline.annotate(annotation);

        // Extract tokens and lemmata from the annotation
        List<CoreLabel> tokens = annotation.get(CoreAnnotations.TokensAnnotation.class);

        // Build a list to store the results
        List<String> tokenized = new ArrayList<>();

        // Append the lemmatized tokens to the list
        for (CoreLabel token : tokens) {
            String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
            tokenized.add(lemma);
        }

        // remove the stop words from the tokenized words
        return tokenized.stream().filter(token -> !STOP_WORDS.contains(token)).collect(Collectors.toList());
    }

    private void preprocess(String corpus) {
        List<String> tokenized = tokenize(corpus);
    }


}
