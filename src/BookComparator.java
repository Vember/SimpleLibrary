import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        String name1 = o1.getTitle();
        String name2 = o2.getTitle();
        if (name1.toLowerCase().startsWith("the ")) {
            name1 = name1.substring(4);
        }
        if (name2.toLowerCase().startsWith("the ")) {
            name2 = name2.substring(4);
        }
        return name1.compareToIgnoreCase(name2);
    }
}