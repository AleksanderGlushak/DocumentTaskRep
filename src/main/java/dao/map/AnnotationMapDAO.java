package dao.map;

import beans.Annotation;

import java.util.TreeMap;

public class AnnotationMapDAO extends AbstractMapDAO<Annotation> {
    public AnnotationMapDAO(TreeMap<Long, Annotation> map) {
        super(map);
    }
}
