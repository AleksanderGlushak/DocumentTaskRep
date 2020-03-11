package readers;

import beans.Attachment;

import java.io.FileNotFoundException;

public class AttachmentCsvFileReader extends AbstractContentScvFileReader {
    private static final String PATTERN = "";
    public AttachmentCsvFileReader(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter, PATTERN);
    }

    public AttachmentCsvFileReader(String filename) throws FileNotFoundException {
        super(filename, PATTERN);
    }

    @Override
    public Attachment readOne(){
        Attachment attachment = new Attachment();
        attachment.setId(super.readLong());
        attachment.setUser(super.readUser());
        attachment.setTitle(super.readText());
        IN.next();
        attachment.setFileSize(super.readLong());
        attachment.setContent(super.readText());
        IN.nextLine();

        return attachment;
    }

    @Override
    public boolean hasNext() {
        return IN.hasNext("");
    }
}