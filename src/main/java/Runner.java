import beans.*;
import dao.AbstractDAO;
import dao.database.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Runner {
    private static final String FILE_TO_WRITE = "text.txt";
    private static final String UNKNOWN_TYPE_MESSAGE = "A line of unknown type is found!";

    private static Logger log = Logger.getLogger(String.valueOf(Runner.class));//logback

    public static void main(String[] args) throws IOException, SQLException {
        AbstractDAO attachmentDatabaseDAO = new AttachmentDatabaseDAO();
        AbstractDAO annotationDatabaseDAO = new AnnotationDatabaseDAO();
        AbstractDAO commentDatabaseDAO = new CommentDatabaseDAO();
        AbstractDAO documentDatabaseDAO = new DocumentDatabaseDAO();
        AbstractDAO userDatabaseDAO = new UserDatabaseDAO();

        Document document = new Document("First document", "Document text : coffee is very important thing in our lives");
        documentDatabaseDAO.add(document);

        User user = new User("Artemiy Vladimirovich","Artemiy@gmail.com");
        userDatabaseDAO.add(user);
        Comment comment = new Comment(document,user,"First comment title","First comment content");
        commentDatabaseDAO.add(comment);
        user = new User("Petr Valentinovich","Petr@gmail.com");
        userDatabaseDAO.add(user);
        comment = new Comment(document,user,"Second comment title","Second comment content");
        commentDatabaseDAO.add(comment);
        user = new User("Gennadiy Albertovich","Gennadiy@gmail.com");
        userDatabaseDAO.add(user);
        annotationDatabaseDAO.add(new Annotation(document,user,"First annotation title",5,12,"First annotation text"));
        user = new User("Albert Petrovich","Albert@gmail.com");
        userDatabaseDAO.add(user);
        attachmentDatabaseDAO.add(new Attachment(document,user,"First attachment title","First attachment content",5342));
        print(commentDatabaseDAO.getAll());
        print(userDatabaseDAO.getAll());
        print("After delete :");
        //user = (User) userDatabaseDAO.getById((long) 1);
        userDatabaseDAO.delete(user);
        print(userDatabaseDAO.getAll());
        Comment comment2 = (Comment) commentDatabaseDAO.getById((long) 6);
        user.setName("Changed Name");
        userDatabaseDAO.update(user);
    }

    public static void print(Object text) {
        print(text.toString());
    }

    public static void print(String text) {
        System.out.println(text);//log.info(text);
    }
}
