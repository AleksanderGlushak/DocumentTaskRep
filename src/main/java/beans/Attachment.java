package beans;

import readers.Delimiters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
@Entity
@Table(name = "attachments")
public class Attachment extends Content implements Comparable<Attachment>, Downloadable{
    @Column
    private String content;
    @Column
    private long fileSize;

    public Attachment(Document document, User user, String title, String content, long fileSize) {
        super(document,user,title);
        this.content = content;
        this.fileSize = fileSize;
    }

    public Attachment() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return super.toString()
                + fileSize + Delimiters.SEPARATOR.toString()
                + frameText(content);
    }

    @Override
    public void download(){
        System.out.print("Attachment ");
        Downloadable.super.download();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Attachment that = (Attachment) o;
        return fileSize == that.fileSize &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), content, fileSize);
    }

    @Override
    public int compareTo(Attachment attachment) {
        return (int) (attachment.fileSize - this.fileSize);
    }
}
