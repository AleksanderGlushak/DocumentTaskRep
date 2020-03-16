package dao;

import beans.Attachment;
import beans.User;

import java.sql.SQLException;
import java.util.Collection;

public interface AttachmentDAO {
    public void addAttachment(Attachment attachment) throws SQLException;
    public void updateAttachment(Long attachmentId, Attachment attachment) throws SQLException;
    public Attachment getAttachmentById(Long attachmentId) throws SQLException;
    public Collection getAllAttachments() throws SQLException;
    public void deleteAttachment(Attachment attachment) throws SQLException;
}
