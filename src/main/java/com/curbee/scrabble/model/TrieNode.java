package com.curbee.scrabble.model;

import lombok.Data;

@Data
public class TrieNode {

    /**
        represents the max unicode number as the max size for the array so when the
        words are broken down into nodes the characters are stored. 0-64 are not
        used in this array we only create our nodes from uppercase latin letters
        each node can have a max of 26 characters.
     **/
    private static final int UNICODE_MAX = 91;

    private char data;
    private TrieNode[] children = new TrieNode[UNICODE_MAX];
    private boolean endOfWord;

    public TrieNode(char c) {
        data = c;
    }

}
