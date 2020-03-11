package beans;

import org.codehaus.jackson.annotate.JsonProperty;
import readers.Delimiters;

import java.util.Objects;

public abstract class Content {
    protected long id;
    @JsonProperty(value = "user")
    protected User user;
    protected String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Content(long id, User user, String title) {
        this.id = id;
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
