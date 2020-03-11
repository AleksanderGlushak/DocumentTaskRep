package readers;

import beans.Attachment;

import java.io.FileNotFoundException;

public class AttachmentReader extends AbstractContentFileReader {
    private static final String PATTERN = "";
    public AttachmentReader(String filename, String delimiter) throws FileNotFoundException {
        super(filename, delimiter, PATTERN);
    }

    public AttachmentReader(String filename) throws FileNotFoundException {
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