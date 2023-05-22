import FileHandling.*;
import GUI.*;
import javafx.application.Application;

public class Main extends Library_Managment_System   {   
    public static void main(String[] args) throws Exception {
        
        String BookData = "src\\Data\\Books.txt"; 
        String ReaderData = "src\\Data\\ReadersData.txt";
        String LibrarianData = "src\\Data\\LibrariansData.txt";
        String OrderData = "src\\Data\\Orders.txt";
        String LoanData = "src\\Data\\Loans.txt";
        ReadBooksFromFile.readBooks(BookData);
        ReadReadersFromFile.readReaders(ReaderData);
        ReadLibrariansFromFile.readLibrarians(LibrarianData);
        ReadOrdersFromFile.readOrders(OrderData);
        ReadLoansFromFile.readLoans(LoanData);
        Application.launch(args);
    }
}
