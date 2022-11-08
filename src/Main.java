
import Manager.AuthorManager;
import Manager.BookManager;
import Modules.Author;


public class Main {

    public static void main(String[] args)  {
        AuthorManager authorManager = new AuthorManager();
        Author author = new Author(9 , "yousifGGamal", 222 );
        authorManager.addAuthor(author);
    }

}

