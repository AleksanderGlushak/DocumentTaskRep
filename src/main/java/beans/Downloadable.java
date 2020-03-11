package beans;

public interface Downloadable {
    static final String ENDING = " is downloaded";
    default void download(){
        System.out.println(this.toString() + ENDING);
    }
}
