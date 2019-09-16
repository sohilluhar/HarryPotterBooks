package luhar.sohil.wizardbooks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.master.glideimageview.GlideImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import luhar.sohil.wizardbooks.Adapter.MyBookAdapter;
import luhar.sohil.wizardbooks.Adapter.MySlideAdapter;
import luhar.sohil.wizardbooks.Interface.sBannerLoadDone;
import luhar.sohil.wizardbooks.Interface.sBookLoadDone;
import luhar.sohil.wizardbooks.Model.Baner;
import luhar.sohil.wizardbooks.Model.Book;
import luhar.sohil.wizardbooks.Services.PicassoLoadingService;
import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity implements sBannerLoadDone, sBookLoadDone {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference banner,books;

    RecyclerView recycler_Book;
    TextView txt_name;
    Slider slider;
    SwipeRefreshLayout swipeRefreshLayout;
    sBannerLoadDone  bannerListener;
    sBookLoadDone  bookListener;
    android.app.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseDatabase=FirebaseDatabase.getInstance();
        banner=firebaseDatabase.getReference("Banners");
        books=firebaseDatabase.getReference("Book");

        bannerListener =this;
        bookListener=this;

        slider=(Slider) findViewById(R.id.sliderBanner);
        Slider.init(new PicassoLoadingService());
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipelayout);


        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBanner();
                loadBook();
            }
        });


        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadBanner();
                loadBook();
            }
        });


        recycler_Book=(RecyclerView)findViewById(R.id.recycler_book);
        recycler_Book.setHasFixedSize(true);
        recycler_Book.setLayoutManager(new GridLayoutManager(this,2));

        txt_name=(TextView)findViewById(R.id.txtBookName);





    }

    private void loadBanner() {

        banner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> bannerList=new ArrayList<>();

                for(DataSnapshot bannerSnapshot:dataSnapshot.getChildren())
                {
                    String img=bannerSnapshot.getValue(String.class);
                    bannerList.add(img);
                    Log.d("value:  ",img);
                }

                bannerListener.onBannerLoadDoneListener(bannerList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadBook() {/*
      alertDialog=new SpotsDialog.Builder()
              .setContext(this)
              .setCancelable(false)
              .setMessage("Plese Wait....")
              .build();
      if(!swipeRefreshLayout.isRefreshing())
            alertDialog.show();*/

        books.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Book> booksList=new ArrayList<>();

                for(DataSnapshot bookSnapshot:dataSnapshot.getChildren())
                {
                    Book book=bookSnapshot.getValue(Book.class);
                    booksList.add(book);
                    //Log.d("value:  ",);
                }

                bookListener.onBooksLoadDoneListener(booksList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onBannerLoadDoneListener(List<String> banners) {

        for (String i:banners){

        }
        slider.setAdapter( new MySlideAdapter(banners));
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBooksLoadDoneListener(List<Book> bookList) {
            Common.bookList=bookList;

            recycler_Book.setAdapter( new MyBookAdapter(getBaseContext(),bookList));

            txt_name.setText(new StringBuilder("Wizard Books (")
                                .append(bookList.size()).append(")")
            );

          /*  if (!swipeRefreshLayout.isRefreshing()){
                alertDialog.dismiss();
            }*/

    }
}
