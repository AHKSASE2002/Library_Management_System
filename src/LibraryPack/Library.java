package LibraryPack;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public abstract class Library {
    protected static ArrayList<Books> books = new ArrayList<>();
    protected static ArrayList<Users> users = new ArrayList<>();
    protected static ArrayList<Librarians> librarians = new ArrayList<>();
    protected static ArrayList<Readers> readers = new ArrayList<>();
    protected static ArrayList<Order> orders = new ArrayList<>();
    protected static ArrayList<Loan> loans = new ArrayList<>();
    
    protected static Users loggedUser;
    public static void setBooks(ArrayList<Books> books) {
        Library.books = books;
    }
    public static void setUsers(ArrayList<Users> users) {
        Library.users = users;
    }
    public static void setLibrarians(ArrayList<Librarians> librarians) {
        Library.librarians = librarians;
    }
    public static void setReaders(ArrayList<Readers> readers) {
        Library.readers = readers;
    }
    public static void setOrders(ArrayList<Order> orders) {
        Library.orders = orders;
    }
    public static void setLoans(ArrayList<Loan> loans) {
        Library.loans = loans;
    }
    public static void setLoggedUser(Users loggedUser) {
        Library.loggedUser = loggedUser;
    }

    public static ArrayList<Books> getBooks() {
        return books;
    }
    public static List<Users> getUsers() {
        return users;
    }
    public static ArrayList<Librarians> getLibrarians() {
        return librarians;
    }
    public static ArrayList<Readers> getReaders() {
        return readers;
    }
    public static ArrayList<Order> getOrders() {
        return orders;
    }
    public static ArrayList<Loan> getLoans() {
        return loans;
    }
    public static Users getLoggedUser() {
        return loggedUser;
    }

    
    public static void addReaders(Readers readers) {

        Library.users.add(readers);
    }
    public static void addLibrarians(Librarians librarians) {
        Library.users.add(librarians);
    }
    public static void orderBook(Users user,Books book) {
        
        Order order = new Order(user, book);
        Library.orders.add(order);
    }
    public static void removeOrder(Users user, Books book) {
        Order ordertoremove = orders.stream()
                .filter(o -> o.getUser().equals(user) && o.getBook().equals(book))
                .findFirst()
                .orElse(null);
        Library.orders.removeIf(order -> order.getUser().equals(user) && order.getBook().equals(book));
        }
    public static List<Order> searchOrders (String search) {
        List<Users> users = searchMembers(search);
        for (Users user : users) {
            return Library.orders.stream()
            .filter(order -> 
                order.getUser().equals(user)
            )
            .collect(Collectors.toList());
        }
        return null;
    }

}
