import beans.*;


import dao.AbstractDAO;
import dao.database.*;
import dao.jdbc.UserJdbcDAO;
import dao.map.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConnectionPool;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private static final String FILE_TO_WRITE = "text.txt";
    private static final String UNKNOWN_TYPE_MESSAGE = "A line of unknown type is found!";

    private static Logger log = LoggerFactory.getLogger(Runner.class);//logback

    public static void main(String[] args) throws IOException, SQLException {
        AbstractDAO attachmentDAO = null;
        AbstractDAO annotationDAO = null;
        AbstractDAO commentDAO = null;
        AbstractDAO documentDAO = null;
        AbstractDAO userDAO = null;

        String choice;
        Scanner in = new Scanner(System.in);
        choice = in.next();
        if (choice.equals("memory")){
            attachmentDAO = new AttachmentMapDAO();
            annotationDAO = new AnnotationMapDAO();
            commentDAO = new CommentMapDAO();
            documentDAO = new DocumentMapDAO();
            userDAO = new UserMapDAO();
        } else if(choice.equals("database")){
            attachmentDAO = new AttachmentDatabaseDAO();
            annotationDAO = new AnnotationDatabaseDAO();
            commentDAO = new CommentDatabaseDAO();
            documentDAO = new DocumentDatabaseDAO();
            userDAO = new UserDatabaseDAO();

        } else {
            return;
        }

        Document document = new Document("First document", "Document text : coffee is very important thing in our lives");
        documentDAO.add(document);
        User user = new User("Artemiy Vladimirovich","Artemiy@gmail.com");
        userDAO.add(user);
        Comment comment = new Comment(document,user,"First comment title","First comment content");
        commentDAO.add(comment);
        user = new User("Petr Valentinovich","Petr@gmail.com");
        userDAO.add(user);
        comment = new Comment(document,user,"Second comment title","Second comment content");
        commentDAO.add(comment);
        user = new User("Gennadiy Albertovich","Gennadiy@gmail.com");
        userDAO.add(user);
        annotationDAO.add(new Annotation(document,user,"First annotation title",5,12,"First annotation text"));
        user = new User("Albert Petrovich","Albert@gmail.com");
        userDAO.add(user);
        attachmentDAO.add(new Attachment(document,user,"First attachment title","First attachment content",5342));
        print("Comments : ");
        print(commentDAO.getAll());
        print("Users : ");
        print(userDAO.getAll());
        print("Users after delete :");
        user = (User) userDAO.getById((long) 1);
        userDAO.delete(user);
        print(userDAO.getAll());
        Comment comment2 = (Comment) commentDAO.getById((long) 6);
        user = (User) userDAO.getById((long) 2);
        user.setName("Changed Name");
        print("Users after name updating :");
        userDAO.update(user);
        print(userDAO.getAll());


        print("User one by one : ");
        User u;
        u = (User) userDAO.readNext();
        while(u != null){
            print(u);
            u = (User) userDAO.readNext();
        }

        if (choice.equals("database")) {
            int n = 50;

            AbstractDAO<User> poolDAO = new UserJdbcDAO();
            ExecutorService es = Executors.newFixedThreadPool(n);

            long start = System.currentTimeMillis();

            for (int i = 0; i < n; i++) {
                es.submit(new Runnable() {
                    @Override
                    public void run() {
                        poolDAO.add(new User("Test@mail.com", "Test Name"));
                    }
                });
            }
            es.shutdown();
            while (!es.isTerminated()) ;
            print(System.currentTimeMillis() - start);

            ConnectionPool.close();
        }
    }

    private static void print(Object o){
        log.info(o.toString());
    }

    private static void print(String text){
        log.info(text);
    }
}
