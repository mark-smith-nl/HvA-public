package hva.ads.college.week08;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
class Author {

    private String lastName;

    private String initials;

    private LocalDate dateOfBirth;

    public Author(String lastName, String initials, LocalDate dateOfBirth) {
        this.lastName = lastName;
        this.initials = initials;
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInitials() {
        return initials;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(lastName, author.lastName) &&
                Objects.equals(initials, author.initials) &&
                Objects.equals(dateOfBirth, author.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, initials, dateOfBirth);
    }

    /**
     * Static method
     */
    public static int compareByInitials2(Author a1, Author a2) {
        int result = a1.initials.compareTo(a2.getInitials());
        if (result == 0) result = a1.getLastName().compareTo(a2.getLastName());
        if (result == 0) result = a1.getDateOfBirth().compareTo(a2.getDateOfBirth());
        return result;
    }

    /**
     * Instance method
     */
    public int compareByInitials3(Author a2) {
        int result = initials.compareTo(a2.getInitials());
        if (result == 0) result = lastName.compareTo(a2.lastName);
        if (result == 0) result = dateOfBirth.compareTo(a2.dateOfBirth);
        return result;
    }

    /**
     * Static inner class with easy access to <b>private</b> instance fields
     */
    public static class ComparatorByInitials implements Comparator<Author> {
        @Override
        public int compare(Author a1, Author a2) {
            int result = a1.initials.compareTo(a2.getInitials());
            if (result == 0) result = a1.lastName.compareTo(a2.lastName);
            if (result == 0) result = a1.dateOfBirth.compareTo(a2.dateOfBirth);
            return result;
        }
    }

    @Override
    public String toString() {
        return "Author{" + "lastName='" + lastName + '\'' + ", initials='" + initials + '\'' + ", dateOfBirth=" + dateOfBirth + '}';
    }
}

class Book {

    private long isbn;

    private String title;

    private int numPages;

    private int yearOfIssue;

    private List<Author> authors;

    private List<Section> chapters;

    public Book(long isbn, String title, int numPages, int yearOfIssue, Author... authors) {
        this.isbn = isbn;
        this.title = title;
        this.numPages = numPages;
        this.yearOfIssue = yearOfIssue;
        this.authors = new ArrayList<>(Arrays.asList(authors));
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Author... authors) {
        this.authors = new ArrayList<>(Arrays.asList(authors));
    }

    public void addAuthors(Author... authors) {
        if (this.authors == null) this.authors = new ArrayList<>();
        Collections.addAll(this.authors, authors);
    }


    public boolean authoredByEWD() {
        return authors.contains(new Author("Dijkstra", "E.W.", LocalDate.of(1930, 5, 11)));
    }

    public List<Section> getChapters() {
        return chapters;
    }

    public void setChapters(Section... chapters) {
        this.chapters = new ArrayList<>(Arrays.asList(chapters));
    }

    public void addChapters(Section... chapters) {
        if (this.chapters == null) this.chapters = new ArrayList<>();
        Collections.addAll(this.chapters, chapters);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", numPages=" + numPages +
                ", yearOfIssue=" + yearOfIssue +
                '}';
    }
}

class Section {

    private String title;

    private int pageNum;

    private int numLines;

    private List<Section> subSections;

    public Section(String title, int pageNum, int numLines, Section... subSections) {
        this.title = title;
        this.pageNum = pageNum;
        this.numLines = numLines;
        this.subSections = Arrays.asList(subSections);
    }

    public String getTitle() {
        return title;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getNumLines() {
        return numLines;
    }

    public List<Section> getSubSections() {
        return subSections;
    }
}

class BookRegister {
    private Set<Book> books;

    public BookRegister(Book... books) {
        this.books = new HashSet<>(Arrays.asList(books));
    }

    public Set<Book> findBooks(Predicate<Book> filter) {
        Set<Book> filteredBooks = new HashSet<>();
        for (Book book : books) if (filter.test(book)) filteredBooks.add(book);
        return filteredBooks;
    }

    public Set<Book> getBooks() {
        return books;
    }
}

class ObjectWrapper<T> {

    private T value;

    public ObjectWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public T setValue(T value) {
        this.value = value;
        return value;
    }

}

class Main {

    public static void main(String[] args) {

        Book dutchBook = new Book(9789023463825L, "Nooit meer ontdekt en gezien in Indonesië", 50, 1950,
                new Author("Hermans", "W.F.", LocalDate.of(1927, 9, 1)),
                new Author("Mulish", "H.", LocalDate.of(1927, 7, 29)),
                new Author("Reve", "G.", LocalDate.of(1923, 12, 14)),
                new Author("Vestdijk", "S.", LocalDate.of(1898, 10, 17)),
                new Author("Haasse", "H.", LocalDate.of(1918, 2, 2))
        );

        dutchBook.getAuthors().forEach(System.out::println); // Note println is an instance method
        System.out.println("-".repeat(40));

        // Example 1: use object instance that compares two instances of authors
        dutchBook.getAuthors().sort(new Author.ComparatorByInitials());
        dutchBook.getAuthors().forEach(System.out::println);
        System.out.println("-".repeat(40));

        // Example 2: use static method that compares two instances of authors
        dutchBook.getAuthors().sort(Author::compareByInitials2);
        dutchBook.getAuthors().forEach(System.out::println);
        System.out.println("-".repeat(40));

        // Example 3: use instance (Author) method that compares this with other instance of type author
        dutchBook.getAuthors().sort(Author::compareByInitials3);
        dutchBook.getAuthors().forEach(System.out::println);
        System.out.println("-".repeat(40));

        // Example 4: Create on the fly an instance of an anonymous class with behaviour: lambda. Use getters!
        dutchBook.getAuthors().sort((a1, a2) -> {
            int result = a1.getInitials().compareTo(a2.getInitials());
            if (result == 0) result = a1.getLastName().compareTo(a2.getLastName());
            if (result == 0) result = a1.getDateOfBirth().compareTo(a2.getDateOfBirth());
            return -result; // Reversed order for demo
        });
        dutchBook.getAuthors().forEach(System.out::println);
        System.out.println("-".repeat(40));

       Book EWDBookOne = new Book(8789023463825L, "Introduction to polymorphic emptiness", 50, 1950,
                new Author("Dijkstra", "E.W.", LocalDate.of(1930, 5, 11)),
                new Author("Chadwick", "J", LocalDate.of(1891, 10, 20))
        );

        Book EWDBookTwo = new Book(7789023463825L, "Introduction to graphs", 1, 2017,
                new Author("Dijkstra", "E.W.", LocalDate.of(1930, 5, 11))
        );

        Book deAvonden = new Book(6789023463825L, "De avonden", 50, 1950,
                new Author("Reve", "G.", LocalDate.of(1923, 12, 14))
        );

        BookRegister bookRegister = new BookRegister(dutchBook, EWDBookOne, EWDBookTwo, deAvonden);

        Set<Book> ewdBooks = bookRegister.findBooks(Book::authoredByEWD);// Instance method
        ewdBooks.forEach(System.out::println);
        System.out.println("-".repeat(40));

        Set<Book> reeveBooks = bookRegister.findBooks(b -> b.getAuthors().contains(new Author("Reve", "G.", LocalDate.of(1923, 12, 14)))); // Lambda
        reeveBooks.forEach(System.out::println);
        System.out.println("-".repeat(40));

        //   int index = 0;
        //   bookRegister.getBooks().forEach(b -> System.out.printf("%d %s", ++index, b)); // Does not work not final
        System.out.println("*".repeat(40));
        ObjectWrapper<Integer> index = new ObjectWrapper<>(0);
        bookRegister.getBooks().forEach(b -> System.out.printf("%d %s\n", index.setValue(index.getValue() + 1), b));
    }
}
