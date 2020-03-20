package dao.database;

import beans.Attachment;

public class AttachmentDatabaseDAO extends AbstractDatabaseDAO<Attachment>{
    public AttachmentDatabaseDAO(){
        super();
        super.type = Attachment.class;
    }
}
