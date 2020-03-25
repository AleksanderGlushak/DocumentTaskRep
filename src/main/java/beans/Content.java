package beans;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.ManyToAny;
import readers.Delimiters;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Objects;
@MappedSuperclass
public abstract class Content extends Identity {
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(value = "user")
    protected User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id")
    protected Document document;

    @Column
    protected String title;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Content(Document document, User user, String title) {
        this.document = document;
        this.user = user;
        this.title = title;
    }

    public Content(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public Content() {
    }

    protected String frameText(String text){
        return Delimiters.COMPLEX_TEXT_FRAME + text + Delimiters.COMPLEX_TEXT_FRAME;
    }

    @Override
    public String toString(){
        return id + Delimiters.SEPARATOR.toString()
                + user + Delimiters.SEPARATOR.toString()
                + frameText(title) + Delimiters.SEPARATOR.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return id == content.id &&
                Objects.equals(user, content.user) &&
                Objects.equals(title, content.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title);
    }
}
