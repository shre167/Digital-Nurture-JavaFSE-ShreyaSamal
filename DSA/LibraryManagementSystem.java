import java.util.Arrays;

class Book {
    private final String bookId;
    private final String title;
    private final String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("Book{id=%s, title=%s, author=%s}", bookId, title, author);
    }
}

public class LibraryManagementSystem {
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book binarySearch(Book[] books, String title) {
        int low = 0;
        int high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int compare = books[mid].getTitle().compareToIgnoreCase(title);
            if (compare == 0) {
                return books[mid];
            }
            if (compare < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = new Book[] {
            new Book("B001", "Algorithms", "Robert Sedgewick"),
            new Book("B002", "Clean Code", "Robert C. Martin"),
            new Book("B003", "Design Patterns", "Erich Gamma"),
            new Book("B004", "Effective Java", "Joshua Bloch")
        };

        System.out.println("Linear search for 'Clean Code':");
        System.out.println(linearSearch(books, "Clean Code"));

        Arrays.sort(books, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
        System.out.println("\nBinary search for 'Design Patterns':");
        System.out.println(binarySearch(books, "Design Patterns"));
    }
}
