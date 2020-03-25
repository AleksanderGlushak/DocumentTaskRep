package dao.map;

import beans.Attachment;

import java.util.TreeMap;

public class AttachmentMapDAO extends AbstractMapDAO<Attachment>{
    public AttachmentMapDAO(TreeMap<Long, Attachment> map) {
        super(map);
    }
}
