package dao.database;

import beans.Annotation;
import beans.Attachment;
import dao.AnnotationDAO;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationDatabaseDAO implements AnnotationDAO {
    public void addAnnotation(Annotation annotation) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(annotation);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateAnnotation(Long annotationId, Annotation annotation) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(annotation);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Annotation getAnnotationById(Long annotationId) throws SQLException {
        Session session = null;
        Annotation annotation = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            annotation = (Annotation) session.load(Annotation.class, annotationId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка \'findById\'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return annotation;
    }

    public Collection getAllAnnotations() throws SQLException {
        Session session = null;
        List annotations = new ArrayList<Annotation>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            annotations = session.createCriteria(Annotation.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка \'getAll\'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return annotations;
    }

    public void deleteAnnotation(Annotation annotation) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(annotation);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при удалении", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
