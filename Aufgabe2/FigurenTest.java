import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class FigurenTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(3); // 3 seconds max per method tested

    @Test
    public void nurRechteck() {
        Figur[] figs = {
                new Rechteck(3, 4, 10, 3),
                new Rechteck(100, 10, 5, 5)
        };
        double f = Figuren.flaeche(figs);
        assertEquals(55, f, 0.001);
    }

    @Test
    public void nurKreis() {
        Figur[] figs = {
                new Kreis(7, 8, 10),
                new Kreis(-10, 0, 3),
        };
        double f = Figuren.flaeche(figs);
        assertEquals(342.434, f, 0.001);
    }

    @Test
    public void gemischt() {
        Figur[] figs = {
                new Rechteck(3, 4, 10, 3),
                new Kreis(7, 8, 10),
                new Rechteck(100, 10, 5, 5)
        };
        double f = Figuren.flaeche(figs);
        assertEquals(369.159, f, 0.001);
    }

}