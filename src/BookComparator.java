import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    @Override
    public int compare(Book one, Book two) {
        String name1 = one.getTitle();
        String name2 = two.getTitle();
        if (name1.toLowerCase().startsWith("the ")) {
            name1 = name1.substring(4);
        }
        if (name2.toLowerCase().startsWith("the ")) {
            name2 = name2.substring(4);
        }
        return name1.compareToIgnoreCase(name2);
    }
}