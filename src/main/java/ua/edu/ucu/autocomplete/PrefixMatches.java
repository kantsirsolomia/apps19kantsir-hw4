package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        if (strings.length > 2) {
            for (String s : strings) {
                for (String el : Pattern.compile("\\s+").split(s)) {
                    trie.add(new Tuple(el, el.length()));
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2) {
            throw new IllegalArgumentException();
        }
        List<String> words = new ArrayList<>();
        Iterable<String> trieWords = trie.wordsWithPrefix(pref);
        if (k == 1) {
            words.add(pref);
        } else {
            for (String w : trieWords) {
                words.add(w);
            }
            words.sort(Comparator.comparingInt(String::length));
            int cut = Math.min(words.size(), k + 1);
            words = words.subList(0, cut);
        }
        return words;
    }

    public int size() {
        return trie.size();
    }
}
