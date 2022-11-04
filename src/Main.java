
import Manager.AuthorManager;
import Manager.BookManager;
import Modules.Author;
import Modules.Book;

public class Main {

    public static void main(String[] args)  {
        BookManager bookManager = new BookManager();
        AuthorManager authorManager = new AuthorManager();
        authorManager.addAuthor();

    }

}

