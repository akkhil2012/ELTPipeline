package testPackage;

public class DiamondProblem {


    public static void main(String args[]) {



        child3 child3 = new child3();
        child3.test();

    }
}


interface ParentInterface {
    void test();
}


class child1 implements ParentInterface {

    @Override
    public void test() {
        System.out.println("Under child1 ");
    }
}


class child2 implements ParentInterface {

    @Override
    public void test() {
        System.out.println("Under child2 ");
    }
}


class child3 extends child2 implements ParentInterface {

    public void testDiamond() {
        ParentInterface parentInterface = new child2();
        parentInterface.test();
    }

    @Override
    public void test() {
        super.test();
        System.out.println("Under Child3............. ");
    }
}


