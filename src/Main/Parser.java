import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rafał on 2017-12-01.
 */
public class Parser {


    public static String entryParse(String path, boolean wantEntry) throws IOException {
        String doc = "", content = "";
        Scanner scanner = new Scanner(new File(path));
        String newLine = scanner.nextLine();
        while ((scanner.hasNextLine()) & !newLine.startsWith("DZIAŁ") & !newLine.startsWith("Rozdział")) {  //spisywanie wstepu
            doc =  doc + newLine + "\n";
            newLine = scanner.nextLine();
        }
        if (!wantEntry) {
            doc = newLine + "\n";
            while (scanner.hasNextLine()) doc = doc + scanner.nextLine() + "\n";
        }
        //usuniecie kolejnych zbednych elementrow w tekscie
        doc = Pattern.compile("^(©Kancelaria Sejmu)(.*)\n", Pattern.MULTILINE).matcher(doc).replaceAll("");
        doc = Pattern.compile("O\\ns\\nr\\n2\\n3\\np\\nN", Pattern.MULTILINE).matcher(doc).replaceFirst("");
        doc = Pattern.compile("(\\d){4}-(\\d){2}-(\\d){2}\n", Pattern.MULTILINE).matcher(doc).replaceAll("");
        doc = Pattern.compile("(\\S*)[-]\n", Pattern.MULTILINE).matcher(doc).replaceAll("\n" + "$1");
        doc = Pattern.compile("(.*)[(]pominięte[)]\n", Pattern.MULTILINE).matcher(doc).replaceAll("");
        doc = Pattern.compile("(Art. [0-9]{1,3}.)$",Pattern.MULTILINE).matcher(doc).replaceAll("$1"+ " ");
        return doc;
    }

    public static List<ActElement> mainParse(String partOfDocument, TypeOfElement theType,boolean isConstitution) {
            if (theType == null) return null;
            if (theType.isBigElement()) return bigParse(partOfDocument, theType,isConstitution);
            else return littleParse(partOfDocument, theType);
    }

    private static List<ActElement> bigParse(String partOfDocument,TypeOfElement theType,boolean isConstitution) {
        List<ActElement> newListOfEl = new ArrayList<>();
        Pattern pat = theType.theTypeRegexPattern(isConstitution);
        partOfDocument = addNecessaryWords(partOfDocument,theType);
        Matcher matcher = pat.matcher(partOfDocument);
        while (matcher.find()){
            String rightPartOfDoc= fixElement(matcher.group(2),theType);
            newListOfEl.add(new BigActElement(getTitles(rightPartOfDoc,isConstitution,theType),theType.toString(),matcher.group(1),rightPartOfDoc,theType,isConstitution));
        }
        if(newListOfEl.size()<=1) {
            newListOfEl.remove(0);
            newListOfEl.add(new BigActElement("",theType.toString(),"",partOfDocument,theType,isConstitution));
        }
        return newListOfEl;
    }

    private static List<ActElement> littleParse(String partOfDocument, TypeOfElement theType){
        List<ActElement> newListOfEl = new ArrayList<>();
        String[] elements = partOfDocument.split(theType.theTypeRegexString());
        Pattern pat = Pattern.compile(theType.theTypeRegexString());
        Matcher macher = pat.matcher(partOfDocument);
        if(elements.length == 1) {   //gdy nie ma zadnych szukanych elementow, wtedy tworzona jest jednoelementowa lista
            newListOfEl.add(new LittleActElement("",partOfDocument,theType));
            return newListOfEl;
        }else if(theType.equals(TypeOfElement.ustęp)){ //w przypadku ustepow, nie ma "wstepu"
            int i=1;
            while(macher.find()){
                newListOfEl.add(new LittleActElement(macher.group(1),elements[i],theType));
                i++;
            }
        }else{   // w przypadku liter i punktow sa tzw "wstepy"
            int i=1;
            while (macher.find()){
                newListOfEl.add(new LittleActElement(macher.group(1),elements[i],theType));
                i++;
            }
        }
        return newListOfEl;
    }

    //jezeli dany element jest artykulem badz rozdzialem, to moze sie konczyc nastepnym rozpoczynajacym sie rozdzialem, badz dzialem
    private static String fixElement(String partOfDoc,TypeOfElement theType){
        if(theType.equals(TypeOfElement.dział)) return partOfDoc;
        partOfDoc = partOfDoc.split("DZIAŁ")[0];
        partOfDoc = partOfDoc.split("Rozdział")[0];
        return partOfDoc;
    }

    //zeby parser dzialal trzeba w odpowienie miejsca dopisac cos albo usunac
    private static String addNecessaryWords(String partOfDoc,TypeOfElement theType){
            if(theType.equals(TypeOfElement.artykuł)) return partOfDoc + " Art. ";
            if(theType.equals(TypeOfElement.dział)) return partOfDoc.replaceFirst("DZIAŁ","") + " DZIAŁ";
            else return partOfDoc + " Rozdział";
    }

    //metoda, ktora zwraca odpowiednio sformatowanego stringa zawierajacego tytuly
    private static String getTitles(String partOfDoc,boolean isConstitution,TypeOfElement theType){
        if(theType.equals(TypeOfElement.artykuł)) return"";
        if(isConstitution) return getTitlesFromConstitution(partOfDoc);
        else               return getTitlesNotFromConstitution(partOfDoc);
    }

    private static String getTitlesFromConstitution(String partOfDoc){
        Matcher matcher = Pattern.compile("(^[\\p{javaUpperCase}, ]+$)",Pattern.MULTILINE).matcher(partOfDoc);
        String titles="";
        while(matcher.find()){
            titles = titles + "\n   ";
            titles = titles + matcher.group(0);
        }
        return titles;
    }

    private  static  String getTitlesNotFromConstitution(String partOfDoc){
        String titles="",tmp;
        Scanner scanner = new Scanner(partOfDoc);
        tmp = scanner.next();
        while(scanner.hasNextLine() & !tmp.equals("Rozdział") & !tmp.equals("Art.")){
            titles = titles + tmp + scanner.nextLine();
            if(scanner.hasNext()) tmp = scanner.next();
        }
        return titles;
    }
}

