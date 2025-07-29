import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Library library = UICommands.declareLibrary();
        Scanner scan = new Scanner(System.in);
        library.setCurrentVisitor(UICommands.declareVisitor(library));
        MenuUI menu = new MenuUI();
        menu.addMenuItem("1", "List books available in the library", () -> UICommands.listBooksIn(library));
        menu.addMenuItem("2", "List books checked out of the library", () -> UICommands.listBooksOut(library));
        menu.addMenuItem("3", "List books " + library.getCurrentVisitor().getFirstName() + " has checked out", () -> UICommands.listVisitorsBooks(library.getCurrentVisitor()));
        menu.addMenuItem("4", "Check a book out of the library", () -> UICommands.checkOutBook(library, library.getCurrentVisitor()));
        menu.addMenuItem("5", "Return a book to the library", () -> UICommands.returnBook(library, library.getCurrentVisitor()));
        menu.addMenuItem("6", "Declare a new Visitor", () -> UICommands.declareVisitor(library));
        menu.addMenuItem("7", "Declare the current Visitor", () -> UICommands.declareCurrentVisitor(library));
        menu.addMenuItem("8", "Exit program", () -> System.exit(0));

        while (true) {
            menu.displayMenu();
            System.out.print("Choose an option 1-" + menu.getHashMap().size() + ": #");
            String input = scan.next();
            boolean valid = false;
            for (int i = 1; i < menu.getHashMap().size() + 1; i++) {
                if (input.equals(String.valueOf(i))) {
                    valid = true;
                }
            }
            if (!valid) {
                System.out.println("Invalid input.");
                continue;
            }
            menu.selectMenuItem(input);
            if (input.equals("7")) {
                menu.addMenuItem("3", "List books " + library.getCurrentVisitor().getFirstName() + " has checked out", () -> UICommands.listVisitorsBooks(library.getCurrentVisitor()));
            }
        }
    }

}
