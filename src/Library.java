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

    public void setCurrentVisitor(Visitor visitor) {
        this.currentVisitor = visitor;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Book> getBooksIn() {
        return this.booksIn;
    }

    public ArrayList<Book> getBooksOut() {
        return this.booksOut;
    }

    public void checkBookToVisitor(Visitor visitor, Book book) throws CheckOutBookLimitException {

        visitor.checkOutBook(book);
        this.booksIn.remove(book);
        this.booksOut.add(book);

    }

    public void returnBookFromVisitor(Visitor visitor, Book book) throws VisitorDoesNotPossessException {

        visitor.returnBook(book);
        this.booksIn.add(book);
        this.booksOut.remove(book);

    }

}
