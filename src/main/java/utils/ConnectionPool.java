package utils;

import dao.database.AbstractDatabaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ConnectionPool {
    private String url;
    private String user;
    private String password;
    private int capacity;
    private static final Queue<Connection> connections= new LinkedList<>();
    private static Logger log = LoggerFactory.getLogger(AbstractDatabaseDAO.class);

    private ConnectionPool() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/resources/META-INF/jdbc.properties"));
        String line = null;
        while(in.hasNext()){
            line = in.next();
            switch (line.substring(0,line.indexOf("="))){
                case "URL":
                    this.url = line.substring(line.indexOf("=")+1);
                    break;
                case "USER":
                    this.user = line.substring(line.indexOf("=")+1);
                    break;
                case "PASSWORD":
                    this.password = line.substring(line.indexOf("=")+1);
                    break;
                case "POOL_CAPACITY":
                    this.capacity = Integer.parseInt(line.substring(line.indexOf("=")+1));
                    break;
                default:
            }
        }
        for (int i = 0; i < capacity; i++) {
            connections.add(createConnection());
        }

    }

    public Connection createConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.info("Error during connection creating");
        }
        return null;
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() throws FileNotFoundException {
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public void putBack(Connection conn){
        if (connections.size()<=capacity){
            connections.add(conn);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Connection getConnection(){
        Connection conn = connections.poll();
        if(conn == null)
            try {
                Thread.sleep(350);
                conn = getConnection();
            } catch (InterruptedException e) {
                log.info("Error during thread sleeping");
        }
        return conn;
    }

    public static void close(){
        Connection con = connections.poll();
        while (con != null) {
            try {
                con.close();
                con = connections.poll();
            } catch (SQLException e) {
                log.info("Error during connection closing");
            }
        }
    }
}