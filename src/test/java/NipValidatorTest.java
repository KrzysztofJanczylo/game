import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NipValidatorTest {


    @Test
    public void nipPoprawny() {
        //given
        List<Long> nipy = new ArrayList<Long>();
        nipy.add(7575820010L);
        nipy.add(1185061160L);
        nipy.add(1547921397L);
        nipy.add(7016613111L);
        nipy.add(1165195035L);
        //when

        for (Long aLong : nipy) {
            boolean valid = NipValidator.valid(aLong);
            assertTrue(valid);
        }
        //then

    }

    @Test
    public void nipNiePoprawny() {
        //given
        List<Long> nipy = new ArrayList<Long>();
        nipy.add(1575820010L);
        nipy.add(2185061160L);
        nipy.add(1447921397L);
        nipy.add(7018813111L);
        nipy.add(1165105035L);
        //when

        for (Long aLong : nipy) {
            boolean valid = NipValidator.valid(aLong);
            assertFalse(valid);
        }
        //then

    }

}