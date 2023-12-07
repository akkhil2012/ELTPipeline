package lld;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
https://leetcode.com/discuss/interview-question/1991514/microsoft-onsite-implement-a-resource-pool

https://leetcode.com/discuss/interview-question/990739/design-connection-pool-multithreaded
 */
public class ImplementResourcePoolExample {


    private static Runnable getRunnable(ConnectionPool connectionPool, String message) {
        return () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Connection conn = connectionPool.getResource();
                    System.out.println(" Thread " + message + " claims Connection " + conn);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

    }


    private static Runnable getRunnableForRelease(ConnectionPool connectionPool, String message) {
        return () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Connection conn = connectionPool.getResource();
                    System.out.println(" Thread " + message + " claims Connection " + conn);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

    }

    public static void main(String args[]) throws InterruptedException {

        ConnectionPool connectionPool = new ConnectionPool();

        new Thread(getRunnable(connectionPool, "First Thread")).start();

        new Thread(getRunnable(connectionPool, "Second Thread")).start();







        /*System.out.println(" Pool size is ---> " + connectionPool.getConnectionPool().size());


        List<Connection> temp = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            new Thread(() -> {
                try {
                    Connection conn = connectionPool.getResource();
                    temp.add(conn);
                    System.out.println(" Connection claimed is " + conn);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();

            Thread.sleep(1000);
        }


        // Thread releasing resource
        for (int i = 0; i < 12; i++) {
            new Thread(() -> {
                try {
                    Connection conn = temp.get(0);
                    temp.remove(0);
                    connectionPool.releaseResource(conn);
                    System.out.println(" Connection resource claimed back is " + conn);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();

            Thread.sleep(1000);
        }*/
    }

}




/*
   Connection
   Set<Connection>


 */

class ConnectionPool {

    Set<Connection> connectionPool;
    int capacity;

    public Set<Connection> getConnectionPool() {
        return connectionPool;
    }


    public ConnectionPool() {
        init();
    }

    public void init() {
        capacity = 10;
        connectionPool = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            connectionPool.add(new Connection());
        }
    }


    public Connection getResource() throws Exception {
        Optional connectionAllocated =
                connectionPool.stream().filter(con -> con.getConnectionStatus().equals(ConnectionStatus.RELEASED)).findFirst();

        if (connectionAllocated.isPresent()) {
            Connection connection = (Connection) connectionAllocated.get();
            connection.setConnectionStatus(ConnectionStatus.BUSY);
            return (Connection) connectionAllocated.get();
        }
        System.out.println(" Pool busy....no connection left");
        throw new Exception();
    }

    public void releaseResource(Connection conn) {
        conn.setConnectionStatus(ConnectionStatus.RELEASED);
        connectionPool.add(conn);
    }

}

class Connection {

    private ConnectionStatus connectionStatus;

    public void setConnectionStatus(ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    public Connection() {
        this.connectionStatus = ConnectionStatus.RELEASED;
    }
}

enum ConnectionStatus {
    RELEASED, BUSY
}
