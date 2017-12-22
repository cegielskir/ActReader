
/**
 * Created by Rafał on 2017-12-01.
 */
public class BigActElement extends ActElement {


    private  String name;
    private String content;
    private String titles;
    private boolean isConstitution;


    public BigActElement(String titles,String name, String badge, String content, TypeOfElement typ,boolean isConstitution) {
        super(badge,typ);
        this.name= name;
        this.isConstitution = isConstitution;
        this.titles = titles;
        this.content = content;
        if(!typ.next().equals(TypeOfElement.artykuł)) this.listOfSubElements = new ListOfElements(Parser.mainParse(content,typ.next(),isConstitution));
    }

    public String getTitles(){
        if(titles.equals("")) return "";
        return this.name + " " +this.badge+ " " + this.titles;
    }

    @Override
    public String toString(){
        return this.name + " " + this.badge +  this.content;
    }
}
