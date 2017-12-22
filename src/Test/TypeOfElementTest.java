import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Rafał on 2017-12-10.
 */
public class TypeOfElementTest {

    @Test
    public void startAndEndsWithTest(){

        assertTrue(TypeOfElement.punkt.startsAndEndsWith("4)"));
        assertTrue(TypeOfElement.punkt.startsAndEndsWith("9asdas)"));
        assertFalse(TypeOfElement.punkt.startsAndEndsWith("4."));
        assertFalse(TypeOfElement.punkt.startsAndEndsWith("a)"));
        assertTrue(TypeOfElement.ustęp.startsAndEndsWith("1."));
        assertFalse(TypeOfElement.ustęp.startsAndEndsWith("a."));
        assertFalse(TypeOfElement.ustęp.startsAndEndsWith("4)"));
        assertTrue(TypeOfElement.litera.startsAndEndsWith("a)"));
        assertFalse(TypeOfElement.litera.startsAndEndsWith("4)"));
        assertFalse(TypeOfElement.litera.startsAndEndsWith("t."));


    }
}
