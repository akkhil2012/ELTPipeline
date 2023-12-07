package testPackage;

public class FinalizeExample {

    public static void main(String args[]){

            new FinalizeExample().testClassType();
    }

    public  void testClassType(){

        TestInt childClassInstance = new ChildClass();
        System.out.println("--->  "+ childClassInstance.getClass() +" " + (childClassInstance instanceof TestInt) +
                " : " + (childClassInstance instanceof ChildClass) );
    }

}


class ChildClass implements  TestInt{

}

interface TestInt{

}
