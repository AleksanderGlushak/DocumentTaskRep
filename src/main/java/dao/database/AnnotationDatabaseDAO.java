package dao.database;

import beans.Annotation;

public class AnnotationDatabaseDAO extends AbstractDatabaseDAO<Annotation> {
    public AnnotationDatabaseDAO(){
        super();
        super.type = Annotation.class;
    }

}
