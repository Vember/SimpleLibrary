public class Book {

    private String title;
    private String author;
    private String reference;
    private int pages;

    public Book(String title, String author, String reference, int pages) {

        this.title = title;
        this.author = author;
        this.reference = reference;
        this.pages = pages;

    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getReference() {
        return this.reference;
    }

    public int getPages() {
        return this.pages;
    }

    @Override

    public String toString() {
        return this.title + " by " + this.author + " - " + this.pages + " pages - REF: " + this.reference;
    }

}
