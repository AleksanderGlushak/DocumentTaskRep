package readers;

import beans.Annotation;

import java.io.FileNotFoundException;

public class AnnotationCsvFileReader extends AbstractContentScvFileReader {
    private static final String PATTERN = "\\d*,\\d*,\".*\",";

    public AnnotationCsvFileReader(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter, PATTERN);
    }

    public AnnotationCsvFileReader(String filename) throws FileNotFoundException {
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
