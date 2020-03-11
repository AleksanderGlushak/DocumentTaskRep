package beans;

import readers.Delimiters;

import java.util.Objects;

public class Annotation extends Content implements Showable{
    private long startPos;
    private long endPos;
    private String text;

    public Annotation(long id, User user, String title, long startPos, long endPos, String text) {
        super(id,user,title);
        this.startPos = startPos;
        this.endPos = endPos;
        this.text = text;
    }

    public Annotation() {
    }

    public long getStartPos() {
        return startPos;
    }

    public void setStartPos(long startPos) {
        this.startPos = startPos;
    }

    public long getEndPos() {
        return endPos;
    }

    public void setEndPos(long endPos) {
        this.endPos = endPos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String show(){
        String text = "Annotation - ";
        System.out.print(text);
        text += Showable.super.show();
        return text;
    }

    @Override
    public String toString() {
        return super.toString()
                + startPos + Delimiters.SEPARATOR.toString()
                + endPos + Delimiters.SEPARATOR.toString()
                + frameText(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Annotation that = (Annotation) o;
        return startPos == that.startPos &&
                endPos == that.endPos &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startPos, endPos, text);
    }
}
