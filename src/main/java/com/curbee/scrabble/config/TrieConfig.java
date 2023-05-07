package com.curbee.scrabble.config;

import com.curbee.scrabble.model.TrieNode;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Log
@Configuration
public class TrieConfig {

    private final TrieNode root = new TrieNode('\0');
    private int nodeCount = 0;

    @Bean
    public TrieNode trieNode() {
        return this.root;
    }

    // read the file and load the dictionary into a trie on startup
    @PostConstruct
    public void loadDictionary() throws IOException {
        InputStream in = getClass().getResourceAsStream("/scrabble.txt");
        var dictionary = (StreamUtils.copyToString(in, Charset.defaultCharset()).split("\r\n"));
        log.info("Total Word Count: " + dictionary.length);
        var start = System.currentTimeMillis();

        for (var word : dictionary) {
            insert(word);
        }

        log.info("Trie Loaded in: " + (System.currentTimeMillis() - start) + "ms");
        log.info("Trie Node Count: " + nodeCount);

        if (in != null)
            in.close();
    }

    private void insert(String word)
    {
        TrieNode node = root;

        for (char ch: word.toCharArray()) {
            if (node.getChildren()[ch] == null) {
                node.getChildren()[ch] = new TrieNode(ch);
                nodeCount++;
            }
            node = node.getChildren()[ch];
        }

        node.setEndOfWord(true);

    }

}
