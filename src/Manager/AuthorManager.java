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
    final  Scanner input = new Scanner(System.in);

    public  void addAuthor() {
        try {
            DatabaseConfg dbConf = new DatabaseConfg();
            Statement state = dbConf.getConnect().createStatement();
            System.out.println("please enter AuthorID, AuthorName , Author age: ");
            int id = input.nextInt();
            String AuthorName = input.next();
            int age = input.nextInt();
            Author author = new Author(id, AuthorName, age);
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
                String id = rs.getString("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.print("id : " + id + "\n");
                System.out.print("Name : " + name + "\n");
                System.out.print("Age: " + age + "\n");

            }

        } catch (SQLException e) {
            System.out.println("error");
            System.err.println(e.getMessage());
        }
        return AuthorList;
    }

    public  void getAuthorByID(int searchID) throws SQLException {
        try {
            DatabaseConfg dbConf = new DatabaseConfg();
            PreparedStatement myStmt = dbConf.getConnect().prepareStatement("SELECT * FROM author WHERE ID = ?");
            myStmt.setInt(1, searchID);
            ResultSet rs = myStmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.print("id : " + id + "\n");
                System.out.print("Name : " + name + "\n");
                System.out.print("Age: " + age + "\n");
            }

        } catch (SQLException e) {
            System.out.print("Error");
            System.err.println(e.getMessage());
        }
    }

    public  boolean updateAuthorByID(int AuthorID) {
        try {
            DatabaseConfg dbConf = new DatabaseConfg();

            System.out.println("please enter ID , Name , Age : ");
            int id = input.nextInt();
            String name = input.next();
            int age = input.nextInt();
            Author Author = new Author(id, name, age);
            PreparedStatement preparedStmt = dbConf.getConnect().prepareStatement("update author set" + " id = ? ,"
                    + " name = ?," + "age = ?" + " where ID = ?");
            preparedStmt.setInt(1, Author.getAuthorId());
            preparedStmt.setString(2, Author.getAuthorName());
            preparedStmt.setInt(3, Author.getAge());
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
            PreparedStatement myStmt = dbConf.getConnect().prepareStatement("DELETE FROM author WHERE ID = ?");
            myStmt.setLong(1, Id);
            myStmt.executeUpdate();
            PreparedStatement myStmt2 = dbConf.getConnect().prepareStatement("DELETE FROM myBook WHERE AuthorID = ?");
            myStmt2.setLong(1, Id);
            myStmt2.executeUpdate();
            System.out.println(" deleted ");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}