package testPackage;

public class ClonableExample {
    public static void main(String args[]) throws CloneNotSupportedException {


        Student original  = new Student();
        Address address = new Address();
        original.setAddress(address);

        address.setName("originalName");

        Student cloned = (Student)original.clone();

        System.out.println("original:  " + original.getAddress().getName());
        System.out.println("cloned:  " + cloned.getAddress().getName());

        original.getAddress().setName("modified Name");


        System.out.println("original:  " + original.getAddress().getName());
        System.out.println("cloned-->:  " + cloned.getAddress().getName());




    }
}

 class Student implements  Cloneable{

    private Address address;

     public Address getAddress() {
         return address;
     }

     public void setAddress(Address address) {
         this.address = address;
     }

     @Override
     protected Object clone() throws CloneNotSupportedException {
         Student clonedStudent  = (Student) super.clone();
         clonedStudent.setAddress((Address) address.clone());
         return clonedStudent;
     }
 }


 class Address implements  Cloneable{
    String name;
    int marks;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     @Override
     public Object clone() throws CloneNotSupportedException {
         return super.clone();  // Assuming that Address is not mutable
     }

     @Override
     public String toString() {
         return "Address{" +
                 "name='" + name + '\'' +
                 ", marks=" + marks +
                 '}';
     }
 }