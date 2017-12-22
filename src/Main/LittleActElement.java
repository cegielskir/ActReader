/**
 * Created by Rafał on 2017-12-08.
 */
public class LittleActElement extends ActElement{

    private String content;

    public LittleActElement(String badge, String content, TypeOfElement type) {
        super(badge,type);
        this.content = content;
        this.listOfSubElements = new ListOfElements(Parser.mainParse(content,type.next(),false));

    }

    @Override
    public String toString(){
        return this.badge + " " + content;
    }
}
