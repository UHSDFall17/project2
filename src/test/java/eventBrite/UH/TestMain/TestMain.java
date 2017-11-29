package eventBrite.UH.TestMain;

import eventBrite.UH.EventApp.Main;
import eventBrite.UH.EventTools.EventInputScanner;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMain {

    @Test
    public void testMainAsGuestAndSearch() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain1"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainLogin() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain2"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainCreateUser() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain3"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainLogin2() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain7"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainCreateEventAsAUser() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain8"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainUpdateEventAsAUser() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain9"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainDeleteEventAsAUser() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain10"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainCreateEventAsAGuest() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain11"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainUpdateEventAsAGuest() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain12"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainDeleteEventAsAGuest() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain13"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainInvalidWelcomeInput() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain4"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainWrongWelcomeInput() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain5"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }

    @Test
    public void testMainAsGuestAndInvalidInput() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("./src/test/testInputs/testsMain/testMain6"));
        EventInputScanner.setScanner(scanner);
        Main.main(null);

    }
}
