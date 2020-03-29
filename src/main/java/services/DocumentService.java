package services;

import beans.*;
import dao.database.CommonDatabaseDAO;
import dao.map.DocumentMapDAO;

import java.util.List;
import java.util.TreeMap;

public class DocumentService extends AbstractService<Document>{
    public DocumentService() {
        this.dao = new CommonDatabaseDAO<>(Document.class);
    }

    public void addUsersIfAbsent(Document document){
        CommonDatabaseDAO<User> userDao = new CommonDatabaseDAO<>(User.class);
        for (Annotation a :
                document.getAnnotations()) {
            if(userDao.getById(a.getUser().getId())==null)
                userDao.add(a.getUser());
        }
        for (Attachment a :
                document.getAttachments()) {
            if(userDao.getById(a.getUser().getId())==null)
                userDao.add(a.getUser());
        }
        for (Comment a :
                document.getComments()) {
            if(userDao.getById(a.getUser().getId())==null)
                userDao.add(a.getUser());
        }
    }
    
    @Override
    public void delete(Document document){
        super.delete(document);
    }
    @Override
    public void add(Document document){
        super.add(document);
    }
    @Override
    public void update(Document document){
        super.update(document);
    }
    @Override
    public Document getById(Long id){
        return super.getById(id);
    }
    @Override
    public List<Document> getAll(){
        return super.getAll();
    }
}
