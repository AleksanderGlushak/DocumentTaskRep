package services;

import beans.Comment;
import beans.Document;
import dao.CommonDao;
import dao.database.CommonDatabaseDAO;
import dao.map.CommentMapDAO;
import dao.map.DocumentMapDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.TreeMap;

public class CommentService extends AbstractService<Comment> {
    private static final Logger log = LoggerFactory.getLogger(CommonDatabaseDAO.class);
    private CommonDao<Document> docDao;

    public CommentService() {
        this.docDao = new CommonDatabaseDAO<>(Document.class);
        this.dao = new CommonDatabaseDAO<>(Comment.class);
    }


    @Override
    public void delete(Comment comment) {
        Document doc = docDao.getById(comment.getDocument().getId());
        try {
            doc.getComments().remove(comment);
            docDao.update(doc);
            super.delete(comment);
        } catch (NullPointerException e){
            log.error("There was no such comment, as {} !",comment);
        }

    }

    @Override
    public void add(Comment comment) {
        Document doc = docDao.getById(comment.getDocument().getId());
        doc.getComments().add(comment);
        docDao.update(doc);
    }

    @Override
    public void update(Comment comment) {
        super.update(comment);
    }

    @Override
    public Comment getById(Long id) {
        return super.getById(id);
    }

    @Override
    public List<Comment> getAll() {
        return super.getAll();
    }
}
