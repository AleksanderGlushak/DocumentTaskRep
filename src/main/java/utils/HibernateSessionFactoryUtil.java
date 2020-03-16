package utils;

import beans.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Content.class);
                configuration.addAnnotatedClass(Comment.class);
                configuration.addAnnotatedClass(Annotation.class);
                configuration.addAnnotatedClass(Attachment.class);
                configuration.addAnnotatedClass(Document.class);
                //StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory();

            } catch (Exception e) {
                e.printStackTrace();//System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
