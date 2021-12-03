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
//        ht.remove(18);
        String value0 = ht.get(22);
        int[] keySet = ht.keySet();
        System.out.println(value0);
        System.out.println(Arrays.toString(keySet));

    }


}
