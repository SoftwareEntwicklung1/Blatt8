import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MonitoringTest {

    private PrintStream origOut = System.out;
    private ByteArrayOutputStream outContent;

    @Before
    public void captureOut() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreOut() {
        System.setOut(origOut);
    }

    @Test
    public void testDeltaTrigger1() {
        Monitor m = new Monitor();
        m.addTrigger(new DeltaTrigger(10000, 10));

        m.addAlert(new TextAlert("%t ms: Value changed to %v"));

        m.report(new Dataset(0, 50));
        m.report(new Dataset(1000, 51));
        m.report(new Dataset(2000, 52));
        m.report(new Dataset(3000, 53));
        m.report(new Dataset(4000, 59));
        m.report(new Dataset(5000, 61)); // DeltaTrigger feuert Alarm

        assertEquals("5000 ms: Value changed to 61.00\n", outContent.toString());
    }

    @Test
    public void testDeltaTrigger2() {
        Monitor m = new Monitor();
        m.addTrigger(new DeltaTrigger(3000, 10));

        m.addAlert(new TextAlert("%t ms: Value changed to %v"));

        m.report(new Dataset(0, 50));
        m.report(new Dataset(1000, 51));
        m.report(new Dataset(2000, 52));
        m.report(new Dataset(3000, 53));
        m.report(new Dataset(4000, 59));

        assertEquals("", outContent.toString());
    }

    @Test
    public void testMultipleTriggers() {
        Monitor m = new Monitor();
        m.addTrigger(new AboveTrigger(100));
        m.addTrigger(new DeltaTrigger(5000, 10));

        m.addAlert(new TextAlert("%t ms: Value changed to %v"));

        m.report(new Dataset(0, 99));
        m.report(new Dataset(1000, 100));
        m.report(new Dataset(2000, 101));
        m.report(new Dataset(3000, 90));

        assertEquals("2000 ms: Value changed to 101.00\n" +
                "3000 ms: Value changed to 90.00\n", outContent.toString());
    }

    @Test
    public void testMultipleAlerts() {
        Monitor m = new Monitor();
        m.addTrigger(new AboveTrigger(9000));

        m.addAlert(new EmailAlert("hans@example.com", "ALERT!"));
        m.addAlert(new TextAlert("%t ms: Value changed to %v"));

        m.report(new Dataset(0, 99));
        m.report(new Dataset(1000, 100));
        m.report(new Dataset(2000, 9001));
        m.report(new Dataset(3000, 10000));

        assertEquals("hans@example.com\n" +
                "ALERT!\n" +
                "2000 ms: Value changed to 9001.00\n" +
                "hans@example.com\n" +
                "ALERT!\n" +
                "3000 ms: Value changed to 10000.00\n", outContent.toString());
    }

    @Test
    public void beispielAufgabenblatt() {
        ////example)
        Monitor m = new Monitor();
        m.addTrigger(new AboveTrigger(39));
        m.addTrigger(new DeltaTrigger(4000, 1));
        m.addAlert(new TextAlert("%t ms: Value changed to %v"));

        m.report(new Dataset(0, 38.4));
        m.report(new Dataset(1000, 38.5));
        m.report(new Dataset(2000, 38.5));
        m.report(new Dataset(3000, 38.6));
        m.report(new Dataset(4000, 39.1)); // AboveTrigger(39) feuert
        m.report(new Dataset(5000, 38.9));
        m.report(new Dataset(6000, 38.5));
        m.report(new Dataset(7000, 38.0)); // DeltaTrigger(4000, 1) feuert
        m.addAlert(new EmailAlert("hans@example.com", "ALERT!"));
        m.report(new Dataset(8000, 38.0)); // DeltaTrigger feuert erneut
        m.report(new Dataset(9000, 38.0));

        ////endexample)
        assertEquals("4000 ms: Value changed to 39.10\n" +
                "7000 ms: Value changed to 38.00\n" +
                "8000 ms: Value changed to 38.00\n" +
                "hans@example.com\n" +
                "ALERT!\n", outContent.toString());
    }

}
