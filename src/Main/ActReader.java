import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
/*
        Program wyswietla okreslony element ustawy
        wszystkie argumenty i numery poszczegolnych elementow ustawy nalezy oddzielac jedna spcją
        Argumenty:



        -a #  - wyswietla argument o okreslonym numerze
        -za # # - wyswietla zakres artykulow







*/

public class ActReader {

    public static void main( String[] args) {
        try {

//              Act act = new Act ("C:\\Users\\Rafał\\Desktop\\CompleteJavaMasterclass\\ActReader\\konstytucja.txt");
//            Matcher matcher = Pattern.compile("^([A-Z ]+)$",Pattern.MULTILINE).matcher(act.content);
//            String titles="";
//            while(matcher.find()){
//                System.out.println("chuj");
//                System.out.println( matcher.group(0) + "\n    ");
//            }

//            String newString ="";
//            String regexString1 ="([0-9]{1,3}[a-z]{0,2}. )(.*?)" + Pattern.quote("Art.");
//            String regexUstep ="(?m)(^[0-9]{1,2}[a-z]?[.] )";
//            String regexPunkt ="(?m)(^[0-9]{1,2}[a-z]?[)] )";
//            String noRegexPunkt ="(?m)(^[0-9]{1,2}[a-z]?[)] )";
//            Pattern patternUstep = Pattern.compile(regexUstep,Pattern.MULTILINE);
//            String regexString2 ="(.*?)" + Pattern.quote("DZIAŁ");
//            String regexString3 ="(.*?)" + Pattern.quote("Rozdział");
//            Pattern patDzial = Pattern.compile(regexString2,Pattern.DOTALL);
//            Pattern patRozdzial = Pattern.compile(regexString3,Pattern.DOTALL);
//            String regexDzial ="([IVX]{1,5}[A-Z]?)(.*?)" + Pattern.quote("DZIAŁ");
//            String regexRozdzial ="([1-9]{1,2})(.*?)" + Pattern.quote("Rozdział");
//            Pattern pat = Pattern.compile(regexUstep,Pattern.DOTALL);
//            Matcher matcher,matcherDzial,matcherRozdzial;
//            //matcher = pat.matcher(act.listOfMainElements.getElement(0).toString());
//            String string = ((LittleActElement)act.listOfArts.getElement(1).listOfSubElements.getElement("2")).content;
////            while(matcher.find()) {
////                System.out.println(matcher.group(2));
////                System.out.println("----------------------------");
////
////            }
//            Pattern patternPunkt = Pattern.compile(regexPunkt);
//            String[] tab = string.split(regexPunkt);
//            String[] tab1 = string.split(noRegexPunkt);
//            Matcher macho = patternPunkt.matcher(string);
//            while(macho.find()){
//                System.out.println(macho.group(1));
//            }
//            for(String stra: tab1) System.out.println(stra + "\n-------------------------------");
//


            Show show = new Show(args);
            show.show();
        }
        catch (IOException ex){
            System.out.println("Blad otwarcia pliku");
        }
//        catch (IndexOutOfBoundsException ex){
//            System.out.println("Niepoprawna liczba argumentow");
//        }
//        catch (IllegalArgumentException ex){
//            System.out.println(ex);
//        }


    }
}
