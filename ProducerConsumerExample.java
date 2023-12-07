package testPackage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class ProducerConsumerExample {


    public static void main(String args[]) {
        //BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(10);
        BlockingQueue<File> blockingQueue = new LinkedBlockingQueue<>(10);
        new Thread(new Producer(blockingQueue))
                .start();


        new Thread(new Consumer(blockingQueue))
                .start();


    }

}


class Producer implements Runnable {
    public Producer(BlockingQueue<File> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private BlockingQueue<File> blockingQueue;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            File newFile = new File("/tmp/bq/test" + i + ".txt");
            try {
                newFile.createNewFile();
                FileOutputStream fileOututStream = new FileOutputStream(newFile);
                fileOututStream.write("testing data".getBytes(StandardCharsets.UTF_8));
                blockingQueue.add(newFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" Producer adding .. " + newFile.exists());
            i++;

        }

    }
}


class Consumer implements Runnable {

    private BlockingQueue<File> blockingQueue;

    public Consumer(BlockingQueue<File> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int count=0;
        try {
            while (true) {
                File createdFile = blockingQueue.take();
                count++;
                FileInputStream fileInputStream = new FileInputStream(createdFile);
                int byteRead;
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(createdFile.getAbsolutePath().getBytes());
                while ((byteRead = byteArrayInputStream.read()) != -1) {
                    System.out.print((char) byteRead);
                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
