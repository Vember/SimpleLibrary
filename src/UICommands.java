import java.io.FileNotFoundException;
import java.util.*;

public class UICommands {

    public static Visitor declareVisitor(Library library) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name (Last, First): ");
        String input = scan.nextLine();
        String[] parts = input.split(", ");
        Visitor visitor = new Visitor(parts[1], parts[0]);
        System.out.println("Welcome to " + library.getName() + ", " + visitor.getFirstName() + "!");
        return visitor;

    }

    public static Library declareLibrary() throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        System.out.print("Name the Library (ex \"Webster's Library\"): ");
        String name = scan.nextLine();
        return new Library(name);
    }

    public static void checkOutBook(Library library, Visitor visitor) {

        Scanner scan = new Scanner(System.in);
        library.listBooks();
        System.out.println();
        System.out.print("Which book would you like to check out?: #");
        int input = 0;
        try {
            input = Integer.parseInt(scan.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }
        if (input > (library.getBooksIn()).size() || input < 1) {
            System.out.println("Invalid input.");
            return;
        }
        if (!visitor.hasMaxBooks()) {
            System.out.println(visitor.getFirstName() + " has checked out " + library.getBooksIn().get(input - 1).getTitle() + ".");
        }
        library.checkBookToVisitor(visitor, library.getBooksIn().get(input - 1));

    }

    public static void returnBook(Library library, Visitor visitor) {

        Scanner scan = new Scanner(System.in);
        if (visitor.hasZeroBooks()) {
            System.out.println(visitor.getFirstName() + " has no books to return.");
            return;
        }
        System.out.println();
        visitor.listBooksCheckedOut();
        System.out.println();
        System.out.print("Which book would you like to return?: #");
        int input = 0;
        try {
            input = Integer.parseInt(scan.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }
        if (input > (visitor.getBooksCheckedOut().size()) || input < 1) {
            System.out.println("Invalid input.");
            return;
        }
        System.out.println(visitor.getFirstName() + " has returned " + visitor.getBooksCheckedOut().get(input - 1).getTitle() + ".");
        library.returnBookFromVisitor(visitor, visitor.getBooksCheckedOut().get(input - 1));
    }

    public static void listBooksIn(Library library) {
        library.listBooks();
    }

    public static void listBooksOut(Library library) {
        library.listBooksOut();
    }

    public static void listVisitorsBooks(Visitor visitor) {
        visitor.listBooksCheckedOut();
    }

}
