import Manager.BookManager;
import Modules.Book;

public class Main {

    public static void main(String[] args)  {
        BookManager bookManager = new BookManager();
        for (Book b : bookManager.getAllBooks())
        {
            System.out.println(b.toString());
        }

    }

}

