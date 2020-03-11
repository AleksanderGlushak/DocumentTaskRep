package writers;

public enum BeansToWrite {
    COMMENT,
    ANNOTATION,
    ATTACHMENT;

    @Override
    public String toString(){
        return name().toLowerCase();
    }
}
