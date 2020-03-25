package dao.map;

import beans.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DocumentMapDAO extends AbstractMapDAO<Document> {
    public DocumentMapDAO(TreeMap<Long, Document> map) {
        super(map);
    }
}
