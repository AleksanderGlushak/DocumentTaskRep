package services;

import beans.Annotation;
import beans.Document;
import dao.database.CommonDatabaseDAO;

import java.util.List;

public class AnnotationService extends AbstractService<Annotation> {
    private CommonDatabaseDAO<Document> docDao;

    public AnnotationService() {
        this.docDao = new CommonDatabaseDAO<>(Document.class);
        this.dao = new CommonDatabaseDAO<>(Annotation.class);
    }


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
