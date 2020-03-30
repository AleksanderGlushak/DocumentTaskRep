package services;

import beans.Annotation;
import beans.Document;
import dao.CommonDao;
import dao.database.CommonDatabaseDAO;
import dao.map.AnnotationMapDAO;
import dao.map.DocumentMapDAO;

import java.util.List;
import java.util.TreeMap;

public class AnnotationService extends AbstractService<Annotation> {
    // you must not have parent dao in child dao class.
    //you should configure hibernate to remove it from Document when deleting Annotation
    private CommonDao<Document> docDao;

    public AnnotationService() {
        this.docDao = new CommonDatabaseDAO<>(Document.class);
        this.dao = new CommonDatabaseDAO<>(Annotation.class);
    }


    // service methods should have logging
    @Override
    public void delete(Annotation annotation) {
        Document doc = docDao.getById(annotation.getDocument().getId());
        doc.getAnnotations().remove(annotation);
        docDao.update(doc);
        super.delete(annotation);
    }

    @Override
    public void add(Annotation annotation) {
        Document doc = docDao.getById(annotation.getDocument().getId());
        doc.getAnnotations().add(annotation);
        docDao.update(doc);
    }

    @Override
    public void update(Annotation annotation) {
        super.update(annotation);
    }

    @Override
    public Annotation getById(Long id) {
        return super.getById(id);
    }

    @Override
    public List<Annotation> getAll() {
        return super.getAll();
    }
}
