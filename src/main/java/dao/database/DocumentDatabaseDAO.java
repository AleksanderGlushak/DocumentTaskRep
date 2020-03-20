package dao.database;

import beans.Document;

public class DocumentDatabaseDAO extends AbstractDatabaseDAO<Document> {
    public DocumentDatabaseDAO(){
        super();
        super.type = Document.class;
    }
}
