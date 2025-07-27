import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        Library library = UICommands.declareLibrary();
        Visitor visitor = UICommands.declareVisitor(library);
        MenuUI menu = new MenuUI();
        menu.addMenuItem("1", "List books available in library", () -> UICommands.listBooksIn(library));
        menu.addMenuItem("2", "List books checked out of library", () -> UICommands.listBooksOut(library));
        menu.addMenuItem("3", "List books " + visitor.getFirstName() + " has checked out", () -> UICommands.listVisitorsBooks(visitor));
        menu.addMenuItem("4", "Check a book out of the library", () -> UICommands.checkOutBook(library, visitor));
        menu.addMenuItem("5", "Return a book to the library", () -> UICommands.returnBook(library, visitor));
        menu.addMenuItem("6", "Exit program", () -> System.exit(0));

        while (true) {
            menu.displayMenu();
            System.out.print("Choose an option 1-6: #");
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
        }
    }
}
