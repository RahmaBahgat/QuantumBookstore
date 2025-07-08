public class SendViaEmail {
    public static void sendBook(Ebook book, String email) {
        System.out.println("Sending book " + book.getTitle() + " to " + email);
    }
}
