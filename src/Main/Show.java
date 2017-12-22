
import java.io.IOException;
import java.util.List;

/**
 * Created by Rafał on 2017-12-11.
 */
public class Show {
    private String[] args;
    private Act act;
    private String output;


    public Show(String[] args) throws IOException, IllegalArgumentException{
        String[] argss = args[0].split(" ");
        this.args = new String[argss.length-1];
        act = new Act(argss[0]);
        System.arraycopy(argss,1,this.args,0,argss.length-1);  // tablica argumentow bez lokalizacji dokumentu
        this.output = argsParser();
    }

    public void show(){
        System.out.println(output);
    }

    private String argsParser() throws IllegalArgumentException, IndexOutOfBoundsException{
        if(args[0].equals("-s")) return sectionContents();
        if(args[0].equals("-za")) return showRangeOfArts();
        else if(args[0].equals("-a")) return showArt();
        else if(args[args.length-2].equals("-d")) return returnSection().toString();
        else if(args[args.length-2].equals("-r")) return showChapter();
        else throw  new IllegalArgumentException("Niepoprawne argumenty");
    }

    private String showArt() throws IllegalArgumentException {
        String[] badges = onlyBadgesArt();
        int a=1;
        ActElement output = act.listOfArts.getElement(badges[0]);
        String[] types = new String[]{"artykul","ustep","podpunkt","litera"};
        if(output==null) throw new IllegalArgumentException("Nie ma danego elementu: " + types[0]);
        for(int i = a; i< badges.length ; i++){
            output = output.listOfSubElements.getElement(badges[i]);
            if(output == null) throw new IllegalArgumentException("Nie ma danego elementu: " + types[i]);
        }
        return output.toString();
    }

    private String chapterContents(List<ActElement> list){
        String titles="";
        for(ActElement el: list) if(!((BigActElement)el).getTitles().equals("")){
            titles = titles +  "\n     " +((BigActElement)el).getTitles();
        }
        return titles;
    }

    private String sectionContents(){
        String titles="";
        if(act.listOfMainElements.listOfElements.get(0).typ.equals(TypeOfElement.Rozdział)) {
            return chapterContents(act.listOfMainElements.listOfElements);
        }
        else{
            for(ActElement el : act.listOfMainElements.listOfElements){
                titles = titles + "\n" +((BigActElement)el).getTitles() + chapterContents(el.listOfSubElements.listOfElements);
                titles = titles + "\n";
            }
        }
        return titles;
    }

    private String showRangeOfArts()throws IllegalArgumentException{
        String all="";
        int firstIndex =startOfRange(this.args[1]); //artykuly beda wypisywane poczawszy od tego indeksu w liscie
        int lastIndex =endOfRange(this.args[2]);   //artykuly beda wypisywane skonczywszy na tym ineksie listy
        for(int i = firstIndex ; i<= lastIndex ; i++){
            all = all + act.listOfArts.getElement(i) + "\n";
        }
        return all;
    }

    private String[] onlyBadgesArt(){
        String[] allArgs = new String[]{"-a","-u","-p","-l"};
        int howMany =1;
        switch(args[args.length - 2]){
            case "-a" : howMany = 1; break;
            case "-u" : howMany = 2; break;
            case "-p" : howMany = 3; break;
            case "-l" : howMany = 4; break;
        }
        String[] badges = new String[howMany];
        int j=0; //do przeszukiwania tablicy allArgs
        for(int i =0; i< howMany; i++){
            if(allArgs[i].equals(this.args[j])) {
                badges[i] = this.args[j+1];
                j = j+2;
            }
            else badges[i] = "";
        }
        return badges;
    }

    private int startOfRange(String from) throws IllegalArgumentException{
        if(act.listOfArts.getElement(from) != null) return act.listOfArts.indexOf(from); //jezeli jest taki artykul to zwraca jego indeks
        else throw new IllegalArgumentException("Nie ma artykulu danego jako poczatek zakresu, badz jest pominiety \n" +
                         "  ( w przypadku ustawy sa to artykuly od 115 do 129) \n" +
                        "    Podany artykul konca zakresu to:" + from);
    }

    private int endOfRange(String to) throws  IllegalArgumentException{
        if(act.listOfArts.getElement(to) != null) return act.listOfArts.indexOf(to); //jezeli jest taki artykul to zwraca jego indeks
        else throw new IllegalArgumentException("Nie ma artykulu danego jako koniec zakresu, badz jest pominiety \n" +
                "  ( w przypadku ustawy sa to artykuly od 115 do 129) \n " +
                "   Podany artykul konca zakresu to: " + to);
    }

    private ActElement returnSection() throws IllegalArgumentException{
        if(act.listOfMainElements.getElement(0).typ.equals(TypeOfElement.Rozdział)) //sprawdzenie czy sa dzialy
            throw new IllegalArgumentException("W danym dokumencie nie ma dzialow");
        else if(act.listOfMainElements.getElement(args[1]) != null) return act.listOfMainElements.getElement(args[1]);
        else throw new IllegalArgumentException("Nie ma takiego dzialu");
    }

    private String showChapter() throws IllegalArgumentException{
        ActElement elOfAct;
        if(args.length == 4) {
            elOfAct = returnSection();
            if(elOfAct.listOfSubElements.getElement(args[args.length - 1]) != null)
                return elOfAct.listOfSubElements.getElement(args[args.length - 1]).toString();
            else throw new IllegalArgumentException("Nie ma takiego rozdzialu");
        }
        else{
            if(act.listOfMainElements.getElement(0).typ.equals(TypeOfElement.dział)) //sprawdzenie czy sa rozdzialy
                throw new IllegalArgumentException("Zeby wyswietlic dany rozdzial trzeba okreslic w ktorym dziale sie on znajduje");
            else if(act.listOfMainElements.getElement(args[1]) != null) return act.listOfMainElements.getElement(args[1]).toString();
            else throw new IllegalArgumentException("Nie ma takiego rozdzialu");
        }
    }
}
