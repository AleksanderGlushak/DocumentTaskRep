package dao.map;

import beans.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DocumentMapDAO extends AbstractMapDAO<Document> {
    private static final TreeMap<Long, Document> map = new TreeMap<>();
    @Override
    protected TreeMap<Long, Document> getMap() {
        return map;
    }
    @Override
    public Document getById(Long id) {
        Document d;
        d = super.getById(id);
        for (Map.Entry<Long, Annotation> e:
                new AnnotationMapDAO().getMap().entrySet()){
            if(e.getValue().getId() == id)
                d.addAnnotation(e.getValue());
        }
        for (Map.Entry<Long, Attachment> e:
                new AttachmentMapDAO().getMap().entrySet()){
            if(e.getValue().getId() == id)
                d.addAttachment(e.getValue());
        }
        for (Map.Entry<Long, Comment> e:
                new CommentMapDAO().getMap().entrySet()){
            if(e.getValue().getId() == id)
                d.addComment(e.getValue());
        }
        return d;
    }

    @Override
    public List<Document> getAll() {
        List<Document> docs = super.getAll();
        Document temp;
        for (Document d :
                docs) {
            temp = getById(d.getId());
            d.setAnnotations(temp.getAnnotations());
            d.setAttachments(temp.getAttachments());
            d.setComments(temp.getComments());
        }
        return docs;
    }
}
