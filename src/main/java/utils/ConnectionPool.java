package utils;

import dao.database.CommonDatabaseDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class ConnectionPool {
    private String url;
    private String user;
    private String password;
    private int capacity;
    private static final Queue<Connection> connections= new LinkedList<>();
    private static Logger log = LoggerFactory.getLogger(CommonDatabaseDAO.class);
    private static volatile ConnectionPool instance = null;

    //Properties
    private ConnectionPool() {
        log.info("Connection pool initializing");
        Properties properties = new Properties();
        try {
            // bad path - won't work from executable JAR or WAR
            properties.load(new FileInputStream(new File("src/resources/META-INF/jdbc.properties")));
            this.url = properties.getProperty("URL");
            this.user = properties.getProperty("USER");
            this.password = properties.getProperty("PASSWORD");
            this.capacity = Integer.parseInt(properties.getProperty("POOL_CAPACITY"));
            for (int i = 0; i < capacity; i++) {
                connections.add(createConnection());
            }
        } catch (IOException e) {
            log.error("I/O exception during CP initialization");
        }
        // is it success if previously you had IOException?
        log.info("Connection pool initialized successfully");
    }

    private Connection createConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // you should log down the exception details or throw it up
            log.error("Error during connection creating");
        }
        return null;
    }

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public void putBack(Connection conn){

        if (connections.size()<=capacity){
            connections.add(conn);
        } else {
            throw new IndexOutOfBoundsException(); //another runtime exc (own exc)
        }
    }

    public Connection getConnection() throws TimeoutException {
        Connection conn = connections.poll();
        if(conn == null){
            conn = getConnection(1);
        }
        return conn;
    }

    private Connection getConnection(int n) throws TimeoutException {
        if(n++==5)
            throw new TimeoutException();
        else{
            Connection conn = connections.poll();//restrict
            if(conn == null)
                try {
                    Thread.sleep(350);
                    conn = getConnection(n);
                } catch (InterruptedException e) {
                    log.info("Error during thread sleeping");
                }
            return conn;
        }

    }

    // method is not used
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