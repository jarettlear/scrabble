package com.curbee.scrabble.service;

import com.curbee.scrabble.model.TrieNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log
@Service
@RequiredArgsConstructor
public class AutocompleteService {

    private final TrieNode root;

    public List<String> getAutocomplete(String word) {
        return autocomplete(word.toUpperCase());
    }

    /**
     * breaks down the prefix word just like the dictionary load and recursively
     * builds up to 10 words from the trie, the word is added once the isEndOfWord
     * in the node is true.
     */
    private List<String> autocomplete(String prefix) {
        var node = root;
        var res = new ArrayList<String>();

        for (var ch: prefix.toCharArray()) {
            node = node.getChildren()[ch];
            if (node == null)
                return new ArrayList<>();
        }

        helper(node, res, prefix.substring(0, prefix.length()-1));
        return res;
    }

    private void helper(TrieNode node, List<String> res, String prefix) {
        if (node == null | res.size() == 10)
            return;

        if (node.isEndOfWord())
            res.add(prefix + node.getData());

        for (TrieNode child : node.getChildren())
            helper(child, res, prefix + node.getData());
    }

}
