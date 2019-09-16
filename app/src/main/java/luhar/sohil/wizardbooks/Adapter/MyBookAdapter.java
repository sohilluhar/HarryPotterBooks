package luhar.sohil.wizardbooks.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import luhar.sohil.wizardbooks.BooksActivity;
import luhar.sohil.wizardbooks.Common;
import luhar.sohil.wizardbooks.Interface.iRecyecleritemClickListener;
import luhar.sohil.wizardbooks.Model.Book;
import luhar.sohil.wizardbooks.Model.Chapter;
import luhar.sohil.wizardbooks.R;

public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.MyViewHolder> {

    Context context;
    List<Book> bookList;
    LayoutInflater layoutInflater;


    public MyBookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=layoutInflater.inflate(R.layout.book_item,viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Picasso.get().load(bookList.get(i).Image).into(myViewHolder.book_img);
        myViewHolder.book_name.setText(bookList.get(i).Name);
        //Log.d("Deashfg",bookList.get(i).Image+"name  "+bookList.get(i).Name);

        myViewHolder.setRecyecleritemClickListener(new iRecyecleritemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                //save comic list
                Common.boookSelected=bookList.get(pos);

                context.startActivity(new Intent(context,BooksActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView book_name;
        ImageView book_img;

        iRecyecleritemClickListener  recyecleritemClickListener;

        public void setRecyecleritemClickListener(iRecyecleritemClickListener recyecleritemClickListener) {
            this.recyecleritemClickListener = recyecleritemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            book_img=(ImageView)itemView.findViewById(R.id.book_img);
            book_name=(TextView) itemView.findViewById(R.id.bookname);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyecleritemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
