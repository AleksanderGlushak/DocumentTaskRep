package dao.database;

import beans.Attachment;
import beans.User;
import dao.AttachmentDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AttachmentDatabaseDAO implements AttachmentDAO {
    public void addAttachment(Attachment attachment) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(attachment);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateAttachment(Long attachmentId, Attachment attachment) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(attachment);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Attachment getAttachmentById(Long attachmentId) throws SQLException {
        Session session = null;
        Attachment attachment = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            attachment = (Attachment) session.load(Attachment.class, attachmentId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка \'findById\'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return attachment;
    }

    public Collection getAllAttachments() throws SQLException {
        Session session = null;
        List attachments = new ArrayList<Attachment>();
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            attachments = session.createCriteria(Attachment.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка \'getAll\'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return attachments;
    }

    public void deleteAttachment(Attachment attachment) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(attachment);
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
