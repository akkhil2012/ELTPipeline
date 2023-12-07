package testPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionExample {

    public static void main(String args[]) {

            new ExceptionExample().testException();

        System.out.println(" passed exception ");

    }



    public void testException(){

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/tmp/teasd.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line;
         while(true){
             try {
                 if (!((line=reader.readLine())!=null)) break;
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }

         }

    }
}
