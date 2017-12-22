

import java.util.HashMap;
import java.util.List;

/**
 * Created by Rafał on 2017-12-09.
 */
public class ListOfElements  {

    public final List<ActElement> listOfElements;
    private HashMap<String, ActElement> elements = new HashMap<>();

    public ListOfElements(List<ActElement> listOfElements) {
        this.listOfElements = listOfElements;
        try{
            addAllToHashMap();
        }catch (NullPointerException ex) {
            listOfElements = null;
        }
    }

    public ActElement getElement(int index){
        return listOfElements.get(index);
    }

    private void addAllToHashMap() throws NullPointerException{
        for(ActElement el : listOfElements){
            elements.put(withoutSign(el.badge),el);
        }
    }

    public ActElement getElement(String key){
        if(elements.containsKey(key)) return elements.get(key);
        else return null;
    }

    private String withoutSign(String str){
        if(str.equals("")) return str;
        if(str.endsWith(" ")) str = str.substring(0,str.length()-1);
        if(!Character.isLetterOrDigit(str.charAt(str.length()-1))) return str.substring(0,str.length()-1);
        else return str;
    }

    public int indexOf(String str){
        return listOfElements.indexOf(elements.get(str));
    }
}
