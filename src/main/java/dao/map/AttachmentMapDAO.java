package dao.map;

import beans.Attachment;

import java.util.TreeMap;

public class AttachmentMapDAO extends AbstractMapDAO<Attachment>{
    private static final TreeMap<Long, Attachment> map = new TreeMap<>();
    @Override
    protected TreeMap<Long, Attachment> getMap() {
        return map;
    }
}
