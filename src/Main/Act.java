
import java.io.IOException;
import java.util.Scanner;


/**
 * Created by Rafał on 2017-12-01.
 */
public class Act {

    private  String title;
    public  String content;
    public  ListOfElements listOfMainElements;
    public  ListOfElements listOfArts;

    public Act(String path)throws IOException{
        this.title = Parser.entryParse( path, true);
        this.content = Parser.entryParse( path, false);
        this.listOfMainElements =  new ListOfElements(Parser.mainParse(content,whatsTheBiggestType(),isConstitution()));
        this.listOfArts = new ListOfElements(Parser.mainParse(content,TypeOfElement.artykuł,isConstitution()));
    }

    private TypeOfElement whatsTheBiggestType(){
        Scanner sc = new Scanner(this.content);
        if(sc.next().equals("DZIAŁ")) return TypeOfElement.dział;
        return TypeOfElement.Rozdział;
    }

    public boolean isConstitution(){
        if(this.title.startsWith("KONSTYTUCJA")) return true;
        else return false;
    }
}
