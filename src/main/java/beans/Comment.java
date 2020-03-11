package beans;

import readers.Delimiters;

import java.util.Objects;

public class Comment extends Content implements Showable{
    private String text;

    public Comment(long id, User user, String title, String text) {
        super(id,user,title);
        this.text = text;
    }

    public Comment() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String show(){
        String text = "Comment - ";
        System.out.print(text);
        text += Showable.super.show();
        return text;
    }

    @Override
    public String toString() {
        return super.toString()
                + frameText(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }
}
