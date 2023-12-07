package testPackage;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class CollectionFramework<T> {


    public static void main(String args[]) {


     //Collection col = new LinkedList();
        Collection col = new ArrayDeque();
     col.add(List.of(1,2,3));







     col.forEach(System.out::println);

    }


    public  void compartorExample(){
        Set<Integer> st = Set.of(1, 23, 14, 5);

        // st.forEach(System.out::print);

        System.out.println("---------");
        Set<Integer> treeSet = new TreeSet<>(st);
        //  treeSet.forEach(System.out::println);

        SortedSet<Admin> sortedSet = new TreeSet<>();

        sortedSet.add(new Admin("", 1));
        sortedSet.add(new Admin("akk", 2));
        sortedSet.add(new Admin("gupttta", 3));


        List<Admin> lst = List.of(new Admin("", 1));
        Collections.sort(lst, new Comparator<Admin>() {

            @Override
            public int compare(Admin o1, Admin o2) {
                return o1.getName().length() > o2.getName().length() ? 1 : -1;
            }
        });


        lst.forEach(System.out::println);
    }


}


class Admin {
    public Admin(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    private String name;

    private int score;

}