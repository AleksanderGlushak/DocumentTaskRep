package beans;

public interface Showable {
    static final String ENDING = " is shown";
    default String show(){
        String text = this.toString() + ENDING;
        System.out.println(text);
        return text;
    }
}
