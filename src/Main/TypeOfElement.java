import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by Rafał on 2017-12-02.
 */
public enum TypeOfElement {
    dział, Rozdział,artykuł, ustęp, punkt, litera;

    @Override
    public String toString() {   // to string
        TypeOfElement[] tabOfElements = {dział, Rozdział,artykuł, ustęp, punkt, litera};
        String[] tabOfStrings = {"DZIAŁ","Rozdział","Art.","Ustęp","Punkt","Litera"};
        return tabOfStrings[Arrays.asList(tabOfElements).indexOf(this)];
    }

    public TypeOfElement next(){     // metoda zwracajaca nastepny w kolejnosci typ elementu ustawy
        TypeOfElement[] tabOfElements = {dział, Rozdział,artykuł, ustęp, punkt, litera};
            if(Arrays.asList(tabOfElements).indexOf(this) + 1 >= tabOfElements.length) return null;
            return tabOfElements[Arrays.asList(tabOfElements).indexOf(this) + 1];
    }

    public boolean isBigElement(){
        if(this.equals(TypeOfElement.dział) | this.equals(TypeOfElement.Rozdział) | this.equals(TypeOfElement.artykuł)) return true;
        else return false;
    }

    public Pattern theTypeRegexPattern(boolean isCostitution){
        if(isCostitution & this.equals(TypeOfElement.Rozdział)){
            return Pattern.compile("([IVX]{1,5}[A-Z]?)(.*?)" + Pattern.quote("Rozdział"),Pattern.DOTALL);
        }
        switch (this){
            case artykuł: return Pattern.compile("([0-9]{1,3}[a-z]{0,2}[.] )(.*?)" + Pattern.quote("Art. "),Pattern.DOTALL);
            case dział:   return Pattern.compile("([IVX]{1,5}[A-Z]?)(.*?)" + Pattern.quote("DZIAŁ"),Pattern.DOTALL);
            case Rozdział:return Pattern.compile("([0-9]{1,2}[a-z]?)(.*?)" + Pattern.quote("Rozdział"),Pattern.DOTALL);
        }
        return null;
    }

    public String theTypeRegexString(){
        switch (this){
            case ustęp: return "(?m)(^[0-9]{1,2}[a-z]?[.]) ";
            case punkt: return "(?m)(^[0-9]{1,2}[a-z]?[)]) ";
            case litera:return "(?m)(^[a-z][)]) ";
        }
        return " ";
    }
}
