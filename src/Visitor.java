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

    public void listBooksCheckedOut() {

        if (this.hasZeroBooks()) {
            System.out.println(this.firstName + " has no books checked out.");
            return;
        }
        this.booksCheckedOut.sort(new BookComparator());
        System.out.println(this.getFirstName() + "'s checked out books:");
        System.out.println();
        for (int i = 0; i < this.booksCheckedOut.size(); i++) {
                System.out.println(i + 1 + ": " + this.booksCheckedOut.get(i));
        }
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

    public boolean hasBookCheckedOut(Book book) {
        for (int i = 0; i < booksCheckedOut.size(); i++) {
            if (this.booksCheckedOut.get(i) == book) {
                return true;
            }
        }
        return false;
    }

    public void checkOutBook(Book book) {
        if (booksCheckedOut.size() < 3) {
            booksCheckedOut.add(book);
        }
    }

    public void returnBook(Book book) {
        for (int i = 0; i < this.booksCheckedOut.size(); i++) {
            if (this.booksCheckedOut.get(i) == book) {
                booksCheckedOut.remove(book);
                return;
            }
        }
    }

    public boolean hasMaxBooks() {
        return booksCheckedOut.size() == 3;
    }

    public boolean hasZeroBooks() {
        return booksCheckedOut.isEmpty();
    }

}
