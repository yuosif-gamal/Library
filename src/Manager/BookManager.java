package Manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import Databaseconfg.DatabaseConfg;
import Modules.Book;

import java.sql.*;

public class BookManager {

    public List<Book> allBooks() {
        List<Book> bookList = new ArrayList<Book>();
        try {
            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement state = dbConf.getConnect().prepareStatement("SELECT * FROM book");
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int AuthorID = rs.getInt("Author_id");
                String category = rs.getString("category");
                int rate = rs.getInt("rate");
                String datePublish = rs.getString("dateofpublish");
                Book book = new Book(title, id, AuthorID, category, rate , datePublish);
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return bookList;
    }

    public List<Book> getAllBooks() {

        return allBooks();
    }

    public List<Book> getAllBooksSortedByName() {
        List<Book> bookListSorted = new ArrayList<Book>();
        bookListSorted = allBooks();
        bookListSorted.sort(Comparator.comparing(Book::getTitle));
        return bookListSorted;
    }

    public boolean updateBook(Book book) {
        try {
            int ID = book.getId();
            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement preparedStmt = dbConf.getConnect()
                    .prepareStatement("update book set" + " id = ? ," + " title = ?," + " dateofpublish = ? ,"
                            + " rate = ? ," + "category = ? ," + "Author_id = ?" + " where id = ?");
            preparedStmt.setInt(1, book.getId());
            preparedStmt.setString(2, book.getTitle());
            preparedStmt.setString(3,  book.getdateOfPublish());
            preparedStmt.setInt(4, book.getRate());
            preparedStmt.setString(5, book.getCategory());
            preparedStmt.setInt(6, book.getAuthorID());
            preparedStmt.setInt(7, ID);
            preparedStmt.executeUpdate();

            System.out.println("updated");

        } catch (SQLException e) {
            System.out.println("not updated");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean addBook(Book book) {
        try {
            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement preparedStmt = dbConf.getConnect().prepareStatement(
                    "INSERT INTO book (id , title, dateofpublish,rate,category,Author_id) VALUES ( ?,?,?,?,?,?)");


            preparedStmt.setInt(1, book.getId());
            preparedStmt.setString(2, book.getTitle());
            preparedStmt.setString(3,book.getdateOfPublish());
            preparedStmt.setInt(4, book.getRate());
            preparedStmt.setString(5, book.getCategory());
            preparedStmt.setInt(6, book.getAuthorID());
            preparedStmt.executeUpdate();
            allBooks().add(book);
        } catch (SQLException e) {
            System.out.println("Error");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void deleteBookById(int Id) {
        try {
            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement myStmt = dbConf.getConnect().prepareStatement("DELETE FROM book WHERE id = ?");
            myStmt.setInt(1, Id);
            myStmt.executeUpdate();
            System.out.println(" deleted ");
        } catch (SQLException e) {
            System.out.println("Not deleted ");
            System.err.println(e.getMessage());
        }
    }

    public List<Book> searchBookById(int searchID) {
        List<Book> myBook = new ArrayList<Book>();
        for (Book b : allBooks()) {
            if (b.getId() == searchID) {
                myBook.add(b);
                return myBook;
            }
        }
        return null;
    }

    public List<Book> searchBookBytitle(String bookTitle) {
        List<Book> myBook = new ArrayList<Book>();
        for (Book b : allBooks()) {
            if (b.getTitle().equals(bookTitle)) {
                myBook.add(b);
            }
        }
        return myBook;
    }

    public List<Book> getAllBookSBySpecificCategory(String bookCategory){
        List<Book> sepicificCategoryBooks = new ArrayList<Book>();

        for (Book b : allBooks()) {
            if (b.getCategory().equals(bookCategory)) {
                sepicificCategoryBooks.add(b);
            }
        }
        return sepicificCategoryBooks;
    }

    public List<Book> getAllBooksBySpecificAuthorID(int Authorid){
        List<Book> sepicificAuthorBooks = new ArrayList<Book>();
        for (Book b : allBooks()) {
            if (b.getAuthorID() == Authorid) {
                sepicificAuthorBooks.add(b);
            }
        }
        return sepicificAuthorBooks;
    }
}