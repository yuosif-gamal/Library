package Modules;

public class Author {
    private int authorId;
    private String authorName;
    private int age;
    public Author() {
    }
    public Author(int authorID, String authorName, int age) {
        super();
        this.authorId = authorID;
        this.authorName = authorName;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }


    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorID) {
        this.authorId = authorID;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String toString() {
        return "Author[authorId=" + authorId + ", authorName=" + authorName + ", age=" + age + "]";
    }

}
