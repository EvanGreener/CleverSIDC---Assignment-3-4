package demo;

import utils.*;
import adts.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        /*
            HashTable demo
         */
        var ht = new HashTable(13);
        ht.put(18, "18 Str");
        ht.put(41, "41 Str");
        ht.put(22, "22 Str");
        ht.put(44, "44 Str");
        ht.put(19, "19 Str"); // triggers rehash function, prime changes to 23
        ht.remove(18);
        String value0 = ht.get(22);
        int[] keySet = ht.keySet();
        System.out.println(value0);
        System.out.println(Arrays.toString(keySet));

        /*
            Sequence demo
         */
        var sequence = new Sequence<Integer>();
        sequence.addLast(18);
        sequence.addLast(23);
        sequence.addLast(32);
        sequence.set(0, 4);
        int key0 = sequence.get(0);
        int key2 = sequence.get(2);
        int key1 = sequence.remove(1);
        System.out.println(key0);
        System.out.println(key1);
        System.out.println(key2);
    }
}
