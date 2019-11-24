package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        if (strings.length <= 2){
            return 0;
        }
        else{
            int counter = 0;
            for (String str: strings) {
                String[] strArr =  str.split("\\s+");
                for(String el: strArr){
                    int len = el.length();
                    trie.add(new Tuple (el, len));
                    counter++;
                }
            }
            return counter;
        }
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
        ArrayList<String> words = new ArrayList<>();
        Iterable<String> trieWords = trie.wordsWithPrefix(pref);

        if (pref.length() >= 2) {
            for (String w : trieWords) {
                words.add(w);
                }

            }

        words.sort(Comparator.comparingInt(String::length));
        int cut = Math.min(words.size(), k+1);


        return words.subList(0, cut);
    }

    public int size() {
        return trie.size();
    }
}
