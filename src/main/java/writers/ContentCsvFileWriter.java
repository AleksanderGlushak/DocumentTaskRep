package writers;

import beans.Annotation;
import beans.Attachment;
import beans.Comment;
import beans.Content;
import readers.Delimiters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContentCsvFileWriter {
    private final String filename;
    private final FileWriter fileWriter;

    public void writeAnnotations(List<Annotation> annotations) throws IOException {
        for (Annotation a :
                annotations) {
            fileWriter.write(BeansToWrite.ANNOTATION.toString().toLowerCase()
                    + Delimiters.SEPARATOR
                    + a.toString()
                    + "\n");
        }
    }

    public void writeAttachments(List<Attachment> attachments) throws IOException {
        for (Attachment a :
                attachments) {
            fileWriter.write(BeansToWrite.ATTACHMENT.toString().toLowerCase()
                    + Delimiters.SEPARATOR
                    + a.toString()
                    + "\n");
        }
    }

    public void writeComments(List<Comment> comments) throws IOException {
        for (Comment a :
                comments) {
            fileWriter.write(BeansToWrite.COMMENT.toString().toLowerCase()
                    + Delimiters.SEPARATOR
                    + a.toString()
                    + "\n");
        }
    }

//    public void writeContent(Content c) throws IOException {
//        if (c instanceof Attachment) {
//            fileWriter.append(BeansToWrite.ATTACHMENT.toString().toLowerCase()
//                    + Delimiters.SEPARATOR
//                    + c.toString()
//                    + "\n");
//        } else if (c instanceof Annotation) {
//            fileWriter.append(BeansToWrite.ANNOTATION.toString().toLowerCase()
//                    + Delimiters.SEPARATOR
//                    + c.toString()
//                    + "\n");
//        } else if (c instanceof Comment) {
//            fileWriter.append(BeansToWrite.COMMENT.toString().toLowerCase()
//                    + Delimiters.SEPARATOR
//                    + c.toString()
//                    + "\n");
//        } else {
//            throw new ClassCastException();
//        }
//    }

    public void close() throws IOException {
        this.fileWriter.close();
    }

    public ContentCsvFileWriter(String filename) throws IOException {
        this.filename = filename;
        this.fileWriter = new FileWriter(new File(filename));
    }

    public ContentCsvFileWriter(FileWriter fileWriter) {
        this.filename = null;
        this.fileWriter = fileWriter;
    }

    public String getFilename() {
        return filename;
    }
}
