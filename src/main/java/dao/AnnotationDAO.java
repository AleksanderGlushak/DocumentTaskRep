package dao;

import beans.Annotation;
import beans.Attachment;
import beans.User;

import java.sql.SQLException;
import java.util.Collection;

public interface AnnotationDAO {
    public void addAnnotation(Annotation annotation) throws SQLException;
    public void updateAnnotation(Long annotationId, Annotation annotation) throws SQLException;
    public Annotation getAnnotationById(Long annotationId) throws SQLException;
    public Collection getAllAnnotations() throws SQLException;
    public void deleteAnnotation(Annotation annotation) throws SQLException;
}
