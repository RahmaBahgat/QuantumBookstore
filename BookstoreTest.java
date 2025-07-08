public class BookstoreTest {
    public static void main(String[] args) {
        // Create the bookstore manager
        ManageBookstore store = new ManageBookstore();

        System.out.println("========== ADDING BOOKS ==========");
        // Add PaperBook
        store.addPaperBook("ER555", "As Long As The Lemon Trees Grow", 2022, 150.0, 5);

        // Add Ebook
        store.addEbook("HU639", "Powerless", 2023, 200, "PDF");

        // Add Showcase/Demo Book
        store.addShowcaseBook("SN789", "Fearful", 2025, 0.0);

        // Add an outdated Book
        store.addPaperBook("TY457", "Matilda", 1988, 100.0, 2);


        System.out.println("\n========== AVAILABLE BOOKS ==========");
        store.listInventory();

        System.out.println("\n========== REMOVING OUTDATED BOOKS (Older than 30 years) ==========");
        store.removeOutdatedBooks(30);

        System.out.println("\n========== AVAILABLE BOOKS AFTER REMOVAL ==========");
        store.listInventory();

        System.out.println("\n========== BUYING PAPERBOOK ==========");
        try {
            double total = store.buyBook("ER555", 2, "rahma@example.com", "123 Haram Street");
            System.out.println("Total paid for PaperBook: L.E." + total);
        } catch (Exception e) {
            System.out.println("Error buying PaperBook: " + e.getMessage());
        }

        System.out.println("\n========== BUYING EBOOK ==========");
        try {
            double total = store.buyBook("HU639", 1, "rahma@example.com", "NONE");
            System.out.println("Total paid for Ebook: L.E." + total);
        } catch (Exception e) {
            System.out.println("Error buying Ebook: " + e.getMessage());
        }

        System.out.println("\n========== TRYING TO BUY SHOWCASE BOOK ==========");
        try {
            store.buyBook("SN789", 1, "rahma@example.com", "456 Tahrir Street");
        } catch (Exception e) {
            System.out.println("Error buying ShowcaseBook: " + e.getMessage());
        }
        
    }
}
