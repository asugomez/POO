package atelier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClassA {
    private List<Integer> listeInt;
    public static void main(String [] args){
        List<Integer> liste= new ArrayList<>();
        liste.add(3);
        liste.add(4);
        Iterator<Integer> iter= liste.iterator();
        int elem= iter.next();
        System.out.println(elem);
    }
}
