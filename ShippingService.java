public class ShippingService {
    public static void shipBook(PaperBook book, String address) {
        System.out.println("Shipping " + book.getTitle() + " to " + address);
    }

}
