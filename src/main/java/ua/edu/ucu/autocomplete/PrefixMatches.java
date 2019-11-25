package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Collections;
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
        if (pref.length()<2||k<0){
            return null;
        }
        Iterable<String> old = trie.wordsWithPrefix(pref);
        List<String> newlist = new ArrayList();
        for (String str: old) {
            if(str.length()>=pref.length()){
                newlist.add(str);
            }
        }
        Collections.sort(newlist, Comparator.comparing(String::length));;

        int i = 0;
        int cur_len = 0;
        List<String> out = new ArrayList();
        for (String strr : newlist) {
            if (cur_len != strr.length()) {
                cur_len = strr.length();
                i++;
                if (i == k + 1) {
                    break;
                }

            }
            out.add(strr);
        }
        return out;
    }

    public int size() {
        return trie.size();
    }
}
