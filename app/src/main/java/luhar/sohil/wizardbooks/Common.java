package luhar.sohil.wizardbooks;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import luhar.sohil.wizardbooks.Model.Book;
import luhar.sohil.wizardbooks.Model.Chapter;

public class Common {
    public static List<Book> bookList=new ArrayList<>();
    public static Book boookSelected;
    public static List<Chapter> chapterList;
    public static Chapter chapterSelected;
    public static int chapterIndex=-1;

    public static String formatText(String name) {

        StringBuilder temp=new StringBuilder(name);
        char c=temp.charAt(0);
        int i=0;
        while(c!=':'){
            temp.setCharAt(i,' ');
            i++;
            c=temp.charAt(i);

        }

        String newname=temp.substring(i+1,temp.length());

        StringBuilder finalResult=new StringBuilder(
                newname.length()>20?newname.substring(0,20)+"...":newname);

        Log.d("String chapter name",newname);
        return finalResult.toString();
    }
}
