package services;

import beans.Annotation;
import beans.Attachment;
import beans.Document;
import dao.CommonDao;
import dao.database.CommonDatabaseDAO;
import dao.map.AttachmentMapDAO;
import dao.map.DocumentMapDAO;

import java.util.List;
import java.util.TreeMap;

public class AttachmentService extends AbstractService<Attachment> {
    private CommonDao<Document> docDao;

    public AttachmentService() {
        this.docDao = new CommonDatabaseDAO<>(Document.class);
        this.dao = new CommonDatabaseDAO<>(Attachment.class);
    }


    @Override
    public void delete(Attachment attachment) {
        Document doc = docDao.getById(attachment.getDocument().getId());
        // copy-paste
        doc.getAnnotations().remove(attachment);
        docDao.update(doc);
        super.delete(attachment);
    }

    @Override
    public void add(Attachment attachment) {
        Document doc = docDao.getById(attachment.getDocument().getId());
        doc.getAttachments().add(attachment);
        docDao.update(doc);
    }

    @Override
    public void update(Attachment attachment) {
        super.update(attachment);
    }

    @Override
    public Attachment getById(Long id) {
        return super.getById(id);
    }

    @Override
    public List<Attachment> getAll() {
        return super.getAll();
    }
}