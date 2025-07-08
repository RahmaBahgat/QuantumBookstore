import java.util.*;

public class ManageBookstore {
    private Map<String, Book> inventory;
    public ManageBookstore() {
        this.inventory = new HashMap<>();
    }

    public void listInventory() {
        System.out.println("Welcome to Quantum bookstore! Available Books:");
        for (Book book : inventory.values()) {
            System.out.println("--> ISBN: " + book.getISBN() + ", Title: " + book.getTitle());
        }
    }

    public void addPaperBook(String ISBN, String title, int year, double price, int stock) {
        PaperBook book = new PaperBook(ISBN, title, year, price, stock);
        addBook(book);
    }

    public void addEbook(String ISBN, String title, int year, double price, String fileType) {
        Ebook ebook = new Ebook(ISBN, title, year, price, fileType);
        addBook(ebook);
    }

    public void addShowcaseBook(String ISBN, String title, int year, double price) {
        ShowcaseDemoBook showcaseBook = new ShowcaseDemoBook(ISBN, title, year, price);
        addBook(showcaseBook);
    }


    public void addBook(Book book) {
        this.inventory.put(book.getISBN(), book);
        System.out.println("Book with ISBN:" + book.getISBN() + " added to the inventory");

    }
    public List<Book> removeOutdatedBooks(int maxYears) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Book> removedBooks = new ArrayList<>();
        Iterator<Map.Entry<String, Book>> it = this.inventory.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Book> entry = it.next();
            Book book = entry.getValue();

            int check = currentYear - book.getYear();
            if (check > maxYears) {
                removedBooks.add(book);
                it.remove();
                System.out.println("Book with ISBN:" + book.getISBN() + " removed from the inventory");
            }
        }

        return removedBooks;
    }

    public double buyBook(String ISBN, int quantity, String email, String address) throws Exception {
        Book book = inventory.get(ISBN);
        if (book == null) {
            throw new Exception("Book with ISBN: " + ISBN + " is not found in inventory!");
        }

        if (book instanceof PaperBook) {
            PaperBook paperBook = (PaperBook) book;
            if (paperBook.getStock() < quantity) {
                throw new Exception("Not enough stock for ISBN " + ISBN);
            }

            paperBook.setStock(paperBook.getStock() - quantity);
            ShippingService.shipBook(paperBook, address);
            double totalPrice = paperBook.getPrice() * quantity;
            System.out.println("Purchase completed. Total paid: " + totalPrice);
            return totalPrice;

        } else if (book instanceof Ebook) {
            Ebook ebook = (Ebook) book;
            SendViaEmail.sendBook(ebook, email);
            double totalPrice = ebook.getPrice() * quantity;
            System.out.println("Purchase completed. Total paid: " + totalPrice);
            return totalPrice;

        } else if (book instanceof ShowcaseDemoBook) {
            throw new Exception("ShowcaseBook with ISBN " + ISBN + " is not for sale!");

        } else {
            throw new Exception("Unknown book type!");
        }
    }

}
