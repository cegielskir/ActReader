

/**
 * Created by Rafał on 2017-12-09.
 */
public abstract class ActElement {

    public final String badge;
    public final  TypeOfElement typ;
    public ListOfElements listOfSubElements;

    public ActElement(String badge, TypeOfElement typ) {
        this.badge = badge;
        this.typ = typ;
    }
}
