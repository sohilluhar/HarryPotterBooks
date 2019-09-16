package luhar.sohil.wizardbooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import luhar.sohil.wizardbooks.Adapter.MyChapterAdapter;
import luhar.sohil.wizardbooks.Model.Book;

public class BooksActivity extends AppCompatActivity {

    RecyclerView recycler_chapter;
    TextView txt_chaptername;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        txt_chaptername=(TextView)findViewById(R.id.txtChapterName);
        recycler_chapter=(RecyclerView)findViewById(R.id.recyclerChapter);
        recycler_chapter.setHasFixedSize(true);


        android.support.v7.widget.Toolbar toolbar =(android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(Common.boookSelected.Name);

        linearLayoutManager=new LinearLayoutManager(this);
        recycler_chapter.setLayoutManager(linearLayoutManager);
        recycler_chapter.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));

        //setIcon

        toolbar.setNavigationIcon(R.drawable.arrowleft);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fetchChapter(Common.boookSelected);
    }

    private void fetchChapter(Book boookSelected) {

        Common.chapterList=boookSelected.Chapters;



        recycler_chapter.setAdapter(new MyChapterAdapter(this,boookSelected.Chapters));


        txt_chaptername.setText(new StringBuilder("CHAPTERS (").append(boookSelected.Chapters.size())
        .append(")"));
    }
}
