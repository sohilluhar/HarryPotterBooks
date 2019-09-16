package luhar.sohil.wizardbooks.Interface;

import java.util.List;

import luhar.sohil.wizardbooks.Model.Book;

public interface sBookLoadDone {

    void onBooksLoadDoneListener(List<Book> books);
}
