package testPackage;

import java.util.ArrayList;
import java.util.List;

public class GenericsExample {

    public static void main(String args[]) {

        Generics<String> generics = new Generics<>();

        generics.addItem("akkhil");
        System.out.println(" item we retreive is " + generics.getItem());


        Generics<Person> persons = new Generics<>();

        persons.addItem(new Person("testUser"));
        System.out.println(" item we retreive is : 2 " + persons.getItem());

    }
}


class Generics<T>{

    private List<T> list = (List<T>) new ArrayList<>();

    public void addItem(T item){
        list.add(item);
    }

    public T getItem(){
        return list.get(0);
    }

}


class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
