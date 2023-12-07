package testPackage;

import java.util.Objects;

public class EqualsHashCodeExample {

    public static void main(String args[]) {

        User first = new User("akkhil",1);
       // User Second = new User("akkhil",1);
        User Second = first;

        for(int i=0;i<5;i++){
            System.out.println(" object " + first + " hashcode " + first.hashCode());
            System.out.println(" object " + Second + " hashcode " + Second.hashCode());
            System.out.println(" default impl " +  first.equals(Second));
        }

    }
}


class User {

    private String name;

    public User(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    private int marks;
}
