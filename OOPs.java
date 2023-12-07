package testPackage;

public class OOPs {

    public static void main(String[] args) {

        StringTest();

        // composed interface test
        TestInterface testInterface;

        //new OOPs().new NestedClass().tryMethod();


    }



    public static void ObjectLevelMethod(){

        NestedClass nestedClass = new OOPs().new NestedClass();






    }


    private static void StringTest() {
        String name = "akkhil";
        String nameObject = new String("akkhil");

        String nameMod = "akkhil";

        String nameObjectMod = new String("akkhil");


        String nameModMix = nameMod;
        String nameModMixObject = nameObjectMod;

        System.out.println("1: " + name.equals(nameObject));

        System.out.println("2: " + (name == nameObject));

        System.out.println("3: " + (name == nameMod)); // true

        System.out.println("4: " + (name == nameObjectMod));

        System.out.println("5: " + (nameObject == nameObjectMod));

        System.out.println("6: " + (nameObject.equals(nameObjectMod)));

        System.out.println("7: " + (nameModMix == nameMod));

        System.out.println("8: " + (nameObjectMod == nameModMixObject) + " " + nameObjectMod.equals(nameModMixObject));
    }


    class NestedClass implements  Cloneable{


    }

    interface NestedInterface {

    }

}

