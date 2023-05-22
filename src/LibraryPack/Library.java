package LibraryPack;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import FileHandling.*;
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

    public static void AddBook(Books book) {
        Library.books.add(book);
        WriteOutputToBookFile.writeToFile();
    }
    public static void addReaders(Readers readers) {

        Library.users.add(readers);
        WriteOutputToReaderFile.writeToFile();
    }
    public static void addLibrarians(Librarians librarians) {
        Library.users.add(librarians);
    }
    public static void removeBook(Books book) {
        Library.books.remove(book);
        RemoveBooksFromFile.removeBookFromFile("Project\\src\\Data\\Books.txt", book.getTitle());
    }

    public static void removeUser(Users user) {
        Library.users.remove(user);
        if (user instanceof Readers) {
            RemoveReadersFromFile.removereaderFromFile("Project\\src\\Data\\ReadersData.txt", user.getUsername());
        }
        else if (user instanceof Librarians) {
            RemoveLibrariansFromFile.removeLibrarianFromFile("Project\\src\\Data\\LibrariansData.txt", user.getUsername());
        }
            
    }
        
    public static List<Books> searchBooks(String search) {
        return Library.books.stream()
                .filter(book -> 
                    book.getTitle().equalsIgnoreCase(search) ||
                    book.getAuthor().equalsIgnoreCase(search) ||
                    book.getGenre().equalsIgnoreCase(search) ||
                    String.valueOf(book.getISBN()).equalsIgnoreCase(search)
                )
                .collect(java.util.stream.Collectors.toList());
    }

   
    

    
    
    
    

    public static void orderBook(Users user,Books book) {
        
        Order order = new Order(user, book);
        Library.orders.add(order);
        WriteOutputToOrderFile.writeToFile();
    }
    public static void removeOrder(Users user, Books book) {
        Order ordertoremove = orders.stream()
                .filter(o -> o.getUser().equals(user) && o.getBook().equals(book))
                .findFirst()
                .orElse(null);
        Library.orders.removeIf(order -> order.getUser().equals(user) && order.getBook().equals(book));
        
        RemoveOrdersFromFile.removeOrderFromFile("Project\\src\\Data\\Orders.txt", ordertoremove);
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
