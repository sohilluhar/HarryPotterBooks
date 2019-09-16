package luhar.sohil.wizardbooks.Model;

import java.util.List;

public class Book {
 public  String Name,Image,Category;
 public List<Chapter> Chapters;

    public Book() {
    }

    public Book(String name, String image, String category, List<Chapter> chapters) {
        Name = name;
        Image = image;
        Category = category;
        Chapters = chapters;
    }


}
