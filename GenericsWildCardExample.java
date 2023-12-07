package testPackage;

import java.util.ArrayList;
import java.util.List;

public class GenericsWildCardExample {

    public static void main(String args[]) {

        Printer<Dog> p = new Printer<>();
        p.addItem(new Dog());


        Printer<Cat> c = new Printer<>();
        c.addItem(new Cat());
    }
}


class Printer<E extends  Animal>{

    List<E> list = new ArrayList<>();
    public void addItem(E item){
        list.add(item);
    }
}



class Animal{

}


class Dog extends  Animal{

}

class Cat extends  Animal{

}

