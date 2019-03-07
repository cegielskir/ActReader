import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
            System.out.println(Arrays.toString(args));
            Show show = new Show(args);
            show.show();
        }
        catch (IOException ex){
            System.out.println("Blad otwarcia pliku");
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println("Niepoprawna liczba argumentow");
       }
        catch (IllegalArgumentException ex){
           System.out.println(ex);
        }


    }
}
