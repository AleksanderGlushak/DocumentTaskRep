package utils;

import beans.*;
import dao.database.CommonDatabaseDAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static Logger log = LoggerFactory.getLogger(CommonDatabaseDAO.class);//logback
    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().addFile("META-INF/hibernate.cfg.xml").configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Content.class);
                configuration.addAnnotatedClass(Comment.class);
                configuration.addAnnotatedClass(Identity.class);
                configuration.addAnnotatedClass(Annotation.class);
                configuration.addAnnotatedClass(Attachment.class);
                configuration.addAnnotatedClass(Document.class);

                //StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                log.info("Hibernate session factory util error happened" + e);
            }
        }
        return sessionFactory;
    }
}
