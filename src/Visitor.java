import java.util.*;

public class Visitor {

    private String firstName;
    private String lastName;
    private ArrayList<Book> booksCheckedOut;

    public Visitor(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.booksCheckedOut = new ArrayList<>();

    }

    public Visitor() {

        this.firstName = null;
        this.lastName = null;
        this.booksCheckedOut = new ArrayList<>();

    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public ArrayList<Book> getBooksCheckedOut() {
        return this.booksCheckedOut;
    }

    public void checkOutBook(Book book) throws CheckOutBookLimitException {
        if (this.booksCheckedOut.size() >= 3) {
            throw new CheckOutBookLimitException(
                this.firstName + " has already checked out the maximum number of books.");
        }
            this.booksCheckedOut.add(book);
    }

    public void returnBook(Book book) throws VisitorDoesNotPossessException {
        if (!this.booksCheckedOut.contains(book)) {
            throw new VisitorDoesNotPossessException(
                this.firstName + " does not have this book!");
        }
            this.booksCheckedOut.remove(book);
    }

    public boolean hasZeroBooks() {
        return this.booksCheckedOut.isEmpty();
    }

    @Override
    public String toString() {
        return this.lastName + ", " + this.firstName;
    }

}
