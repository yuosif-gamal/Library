package Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Databaseconfg.DatabaseConfg;
import Modules.Author;

public class AuthorManager {
    public  void addAuthor(Author author) {
        try {
            DatabaseConfg dbConf = new DatabaseConfg();
            Statement state = dbConf.getConnect().createStatement();
            state.executeUpdate("INSERT INTO author(id,name,age) " + "VALUES (" + author.getAuthorId() + ",'"
                    + author.getAuthorName() + "','" + author.getAge() + "');");
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("Failed to add Modules.Author with id = ");
            System.err.println(e.getMessage());
        }
    }

    public  List<Author> getAllAuthors() {
        List<Author> AuthorList = new ArrayList<Author>();
        try {

            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement state = dbConf.getConnect().prepareStatement("SELECT * FROM author");
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Author author = new Author(id , name , age);
                AuthorList.add(author);
            }

        } catch (SQLException e) {
            System.out.println("error");
            System.err.println(e.getMessage());
        }
        return AuthorList;
    }

    public  void getAuthorByID(int searchID) throws SQLException {
        List<Author> AuthorList = new ArrayList<Author>();
        try {
            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement myStmt = dbConf.getConnect().prepareStatement("SELECT * FROM author WHERE id = ?");
            myStmt.setInt(1, searchID);
            ResultSet rs = myStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Author author = new Author(id , name , age);
                AuthorList.add(author);
            }

        } catch (SQLException e) {
            System.out.print("Error");
            System.err.println(e.getMessage());
        }
    }

    public  boolean updateAuthorByID(Author author) {
        try {
            int AuthorID = author.getAuthorId();
            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement preparedStmt = dbConf.getConnect().prepareStatement("update author set" + " id = ? ,"
                    + " name = ?," + "age = ?" + " where id = ?");
            preparedStmt.setInt(1, author.getAuthorId());
            preparedStmt.setString(2, author.getAuthorName());
            preparedStmt.setInt(3, author.getAge());
            preparedStmt.setInt(4, AuthorID);
            preparedStmt.executeUpdate();
            System.out.println("updated");
            return true;

        } catch (SQLException e) {
            System.out.println("not updated");
            System.err.println(e.getMessage());
        }
        return false;

    }
    public  void deleteAuthorById(int Id) {
        try {
            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement myStmt = dbConf.getConnect().prepareStatement("DELETE FROM author WHERE id = ?");
            myStmt.setLong(1, Id);
            myStmt.executeUpdate();
            PreparedStatement myStmt2 = dbConf.getConnect().prepareStatement("DELETE FROM book WHERE Author_id = ?");
            myStmt2.setLong(1, Id);
            myStmt2.executeUpdate();
            System.out.println(" deleted ");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}