package Modules;
public class Book {
    private String title;
    private int id;
    private int  authorID;
    private String category;
    private int rate;
    private String dateOfPublish;

    public Book() {}
    public Book(String titleBook, int idBook, int author_ID, String Bookcategory, int bookRate, String getdateOfPublish) {
        super();
        title = titleBook;
        id = idBook;
        authorID = author_ID;
        category = Bookcategory;
        rate = bookRate;
        dateOfPublish = getdateOfPublish;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        authorID = authorID;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int Rate) {
        rate = Rate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdAuthor() {
        return authorID;
    }
    public void setBookAuthor(int  authorID) {
        this.authorID = authorID;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getdateOfPublish() {
        return dateOfPublish;
    }

    public void getdateOfPublish(String getdateOfPublish) {
        this.dateOfPublish = getdateOfPublish;
    }

    @Override
    public String toString() {
        return "Modules.Book [title=" + title + ", id=" + id + ", authorID=" + authorID + ", category="
                + category + ", rate=" + rate + ", bookStringOfPublish=" + dateOfPublish + "]";
    }

}
