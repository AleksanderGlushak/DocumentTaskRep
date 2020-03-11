package readers;

import beans.Annotation;

import java.io.FileNotFoundException;

public class AnnotationReader extends AbstractContentFileReader {
    private static final String PATTERN = "\\d*,\\d*,\".*\",";

    public AnnotationReader(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter, PATTERN);
    }

    public AnnotationReader(String filename) throws FileNotFoundException {
        super(filename, PATTERN);
    }

    @Override
    public Annotation readOne(){
        Annotation annotation = new Annotation();
        annotation.setId(super.readLong());
        annotation.setUser(super.readUser());
        annotation.setTitle(super.readText());
        IN.next();
        annotation.setStartPos(super.readLong());
        annotation.setEndPos(super.readLong());
        annotation.setText(super.readText());
        IN.nextLine();

        return annotation;
    }

    @Override
    public boolean hasNext() {
        return IN.hasNext("");
    }
}
