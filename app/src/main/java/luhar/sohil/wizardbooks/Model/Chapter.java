package luhar.sohil.wizardbooks.Model;

import java.util.List;

public class Chapter {
   public String Name;
    public List<String> Links;

    public Chapter() {
    }

    public Chapter(String name, List<String> links) {
        Name = name;
        Links = links;
    }
}
