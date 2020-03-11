package readers;

public enum Delimiters {
    SEPARATOR(","),
    COMPLEX_TEXT_FRAME("\"");

    private String value;

    Delimiters(String value){
        setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
