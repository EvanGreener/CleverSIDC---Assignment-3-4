package demo;

import cleversidc.CleverSIDC;
import utils.*;
import adts.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

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

        /*
            Create sample CleverSIDC object
         */
        Scanner sc=new Scanner(System.in);
        try
        {
            sc = new Scanner(new FileInputStream("NASTA_test_file1.txt"));
            while(sc.hasNextLine()) {
            	 
    	        System.out.println(sc.nextLine());
    	      }
    	 
           
        }
        catch (FileNotFoundException f)
        {
        	 System.out.println("Cannot open file");
             System.exit(0);
        }
        
        CleverSIDC cs=new CleverSIDC();
        Sequence<Entry> entries = new Sequence<>();

        // Get size of sequence to get threshold
        int size=0;
        while(sc.hasNext())
        {
            String thisLine= sc.nextLine();
            int ID=Integer.parseInt(thisLine);
            size++;
            entries.addLast(new Entry(ID, "No info yet"));
        }

        // Set threshold to be +100 the actual size
        cs.setSIDCThreshold(size + 50);
        Position<Entry> p = entries.getHead().getNextPosition();
        while (p.getElement() != null){
            int keyP = p.getElement().getKey();
            String valueP = p.getElement().getValue();
            cs.add(keyP, valueP);
        }
        System.out.println("DATA ADDED");

        /*
            Demo of CleverSIDC operations
         */


    }
}
