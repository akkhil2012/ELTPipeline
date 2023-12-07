package testPackage;

public class DiamondProblemBasic {


    public static void main(String args[]) {

        ChildImpl child = new ChildImpl();
        child.test();


    }
}


interface FirstInt {
    void test();

    default void defaultTest(){
        System.out.println(" In Default Interface....");
    }
}


interface SecondInt {
    void test();
}

class ChildImpl implements FirstInt, SecondInt {

    @Override
    public void test() {
        defaultTest();
        System.out.println(" In impl class......");
    }
}
