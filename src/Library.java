import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private String name;
    private ArrayList<Book> booksIn;
    private ArrayList<Book> booksOut;
    private ArrayList<Visitor> visitors;
    public Visitor currentVisitor;

    public Library(String name) throws FileNotFoundException {

        File myFile = new File("C:\\Users\\patri\\Documents\\JavaProjects\\LibraryBetter\\books");
        Scanner scan = new Scanner(myFile);
        this.name = name;
        this.booksIn = new ArrayList<>();
        this.booksOut = new ArrayList<>();
        this.visitors = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(">");
            booksIn.add(new Book(parts[0], parts[1], parts[2], Integer.valueOf(parts[3])));
        }
    }

    public ArrayList<Visitor> getVisitors() {
        return this.visitors;
    }

    public void addVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

    public Visitor getCurrentVisitor() {
        return this.currentVisitor;
    }

    public void listVisitors() {
        for (int i = 0; i < this.visitors.size(); i++) {
            System.out.println((i + 1) + ": " + this.visitors.get(i).getLastName() + ", " + this.visitors.get(i).getFirstName());
        }
    }

    public void setCurrentVisitor(Visitor visitor) {
        this.currentVisitor = visitor;
    }

    public String getName() {
        return this.name;
    }

    public void listBooks() {
        System.out.println("Books currently in " + this.name);
        booksIn.sort(new BookComparator());
        System.out.println();
        for (int i = 0; i < booksIn.size(); i++) {
            System.out.println(i + 1 + ": " + booksIn.get(i));
        }
    }

    public void listBooksOut() {
        if (booksOut.isEmpty()) {
            System.out.println("There are no books currently checked out.");
            return;
        }
        System.out.println("Books currently checked out of " + this.name);
        System.out.println();
        booksOut.sort(new BookComparator());
        for (int i = 0; i < booksOut.size(); i++) {
            System.out.println(i + 1 + ": " + booksOut.get(i));
        }
    }

    public ArrayList<Book> getBooksIn() {

        return this.booksIn;
    }

    public void checkBookToVisitor(Visitor visitor, Book book) {

        if (visitor.hasMaxBooks()) {
            System.out.println(visitor.getFirstName() + " has already checked out the maximum number of books.");
            return;
        }
        if (!(booksIn.contains(book))) {
            System.out.println(book.getTitle() + " has already been checked out.");
            return;
        }
        visitor.checkOutBook(book);
        this.booksIn.remove(book);
        this.booksOut.add(book);

    }

    public void returnBookFromVisitor(Visitor visitor, Book book) {

        if (!(visitor.hasBookCheckedOut(book))) {
            System.out.println(visitor.getFirstName() + " does not have this book checked out.");
            return;
        }
        if (!(booksOut.contains(book))) {
            System.out.println(book.getTitle() + " has not been checked out.");
            return;
        }
        visitor.returnBook(book);
        this.booksIn.add(book);
        this.booksOut.remove(book);

    }

}
