import java.io.FileNotFoundException;
import java.util.*;

public class UICommands {

    public static Visitor declareVisitor(Library library) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name (Last, First): ");
        String input = scan.nextLine();
        Visitor visitor = new Visitor();
        try {
            String[] parts = input.split(", ");
            visitor = new Visitor(parts[1], parts[0]);
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return null;
        }
        System.out.println("Welcome to " + library.getName() + ", " + visitor.getFirstName() + "!");
        library.addVisitor(visitor);
        return visitor;

    }

    public static void declareCurrentVisitor(Library library) {

        Scanner scan = new Scanner(System.in);
        listVisitors(library);
        System.out.println();
        System.out.print("Which visitor to declare?: #");
        int input = 0;
        try {
            input = Integer.parseInt(scan.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }
        if (input > (library.getVisitors()).size() || input < 1) {
            System.out.println("Invalid input.");
            return;
        }
        library.setCurrentVisitor(library.getVisitors().get(input - 1));

    }

    public static Library declareLibrary() throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);
        System.out.print("Name the Library (ex \"Webster's Library\"): ");
        String name = scan.nextLine();
        return new Library(name);
    }

    public static void checkOutBook(Library library, Visitor visitor) {

        Scanner scan = new Scanner(System.in);
        listBooksIn(library);
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
        try {
            library.checkBookToVisitor(visitor, library.getBooksIn().get(input - 1));
            System.out.println(visitor.getFirstName() + " has checked out " + visitor.getBooksCheckedOut().getLast().getTitle());
        } catch (CheckOutBookLimitException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void returnBook(Library library, Visitor visitor) {

        Scanner scan = new Scanner(System.in);
        if (visitor.hasZeroBooks()) {
            System.out.println(visitor.getFirstName() + " has no books to return.");
            return;
        }
        System.out.println();
        listVisitorBooksCheckedOut(visitor);
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
        Book returned = visitor.getBooksCheckedOut().get(input - 1);
        try {
            library.returnBookFromVisitor(visitor, visitor.getBooksCheckedOut().get(input - 1));
            System.out.println(visitor.getFirstName() + " has returned " + returned.getTitle() + ".");
        } catch (VisitorDoesNotPossessException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listBooksOut(Library library) {
        if (library.getBooksOut().isEmpty()) {
            System.out.println("There are no books currently checked out.");
            return;
        }
        System.out.println("Books currently checked out of " + library.getName() + ":");
        System.out.println();
        library.getBooksOut().sort(new BookComparator());
        for (int i = 0; i < library.getBooksOut().size(); i++) {
            System.out.println(i + 1 + ": " + library.getBooksOut().get(i));
        }
    }

    public static void listVisitorsBooks(Visitor visitor) {
        listVisitorBooksCheckedOut(visitor);
    }

    public static void listBooksIn(Library library) {
        System.out.println("Books currently in " + library.getName() + ":");
        library.getBooksIn().sort(new BookComparator());
        System.out.println();
        for (int i = 0; i < library.getBooksIn().size(); i++) {
            System.out.println(i + 1 + ": " + library.getBooksIn().get(i));
        }
    }

    public static void listVisitorBooksCheckedOut(Visitor visitor) {

        if (visitor.hasZeroBooks()) {
            System.out.println(visitor.getFirstName() + " has no books checked out.");
            return;
        }
        visitor.getBooksCheckedOut().sort(new BookComparator());
        System.out.println(visitor.getFirstName() + "'s checked out books:");
        System.out.println();
        for (int i = 0; i < visitor.getBooksCheckedOut().size(); i++) {
            System.out.println(i + 1 + ": " + visitor.getBooksCheckedOut().get(i));
        }
    }

    public static void listVisitors(Library library) {
        int i = 1;
        for (Visitor visitor : library.getVisitors()) {
            System.out.println(i + ": " + visitor);
            i++;
        }
    }

}
