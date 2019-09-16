package luhar.sohil.wizardbooks;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

import dmax.dialog.SpotsDialog;
import luhar.sohil.wizardbooks.Adapter.MyChapterAdapter;
import luhar.sohil.wizardbooks.Adapter.MyViewPagerAdapter;
import luhar.sohil.wizardbooks.Model.Chapter;

public class ViewBookActivity extends AppCompatActivity {

    View back,next;
    TextView txtchaptername;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        txtchaptername=(TextView)findViewById(R.id.txtChapterName);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        back=(View)findViewById(R.id.chapter_back);
        next=(View)findViewById(R.id.chapter_next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.chapterIndex==0)
                    Toast.makeText(ViewBookActivity.this, "You are Reading first chapter", Toast.LENGTH_SHORT).show();
                else 
                {
                    Common.chapterIndex--;

                    Common.chapterSelected=Common.chapterList.get(Common.chapterIndex);
                    fetchLinks(Common.chapterList.get(Common.chapterIndex));
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.chapterIndex==Common.chapterList.size()-1)
                    Toast.makeText(ViewBookActivity.this, "You are Reading last chapter", Toast.LENGTH_SHORT).show();
                else
                {
                    Common.chapterIndex++;
                    Common.chapterSelected=Common.chapterList.get(Common.chapterIndex);
                    fetchLinks(Common.chapterList.get(Common.chapterIndex));

                }
            }

        });

        fetchLinks(Common.chapterSelected);
    }

    private void fetchLinks(Chapter chapter) {



        if (chapter.Links!=null){
            if (chapter.Links.size()>0){
                MyViewPagerAdapter adapter=new MyViewPagerAdapter(getBaseContext(),chapter.Links);
                viewPager.setAdapter(adapter);

                txtchaptername.setText(Common.formatText(Common.chapterSelected.Name));



                //Animation

                BookFlipPageTransformer bookFlipPageTransformer = new BookFlipPageTransformer();
                bookFlipPageTransformer.setScaleAmountPercent(10f);
                viewPager.setPageTransformer(true,bookFlipPageTransformer);

            }
        }
        else {

            Toast.makeText(ViewBookActivity.this, "No image here", Toast.LENGTH_SHORT).show();

        }
    }
}
