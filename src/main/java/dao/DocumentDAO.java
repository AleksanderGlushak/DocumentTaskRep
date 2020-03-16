package dao;

import beans.Annotation;
import beans.Document;
import beans.User;

import java.sql.SQLException;
import java.util.Collection;

public interface DocumentDAO {
    public void addDocument(Document document) throws SQLException;
    public void updateDocument(Long documentId, Document document) throws SQLException;
    public Document getDocumentById(Long documentId) throws SQLException;
    public Collection getAllDocuments() throws SQLException;
    public void deleteDocument(Document document) throws SQLException;
}
