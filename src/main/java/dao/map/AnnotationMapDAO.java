package dao.map;

import beans.Annotation;

import java.util.TreeMap;

public class AnnotationMapDAO extends AbstractMapDAO<Annotation> {
    private static final TreeMap<Long, Annotation> map = new TreeMap<>();
    @Override
    protected TreeMap<Long, Annotation> getMap() {
        return map;
    }
}
