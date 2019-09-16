package luhar.sohil.wizardbooks.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.crypto.Cipher;

import luhar.sohil.wizardbooks.Common;
import luhar.sohil.wizardbooks.Interface.iRecyecleritemClickListener;
import luhar.sohil.wizardbooks.Model.Chapter;
import luhar.sohil.wizardbooks.R;
import luhar.sohil.wizardbooks.ViewBookActivity;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.MyViewHolder> {

    Context context;
    List<Chapter> chaptersList;
    LayoutInflater inflater;

    public MyChapterAdapter(Context context, List<Chapter> chapters) {
        this.context = context;
        this.chaptersList = chapters;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=inflater.inflate(R.layout.chapter_item,viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textchapternumb.setText(chaptersList.get(i).Name);

        myViewHolder.setRecyecleritemClickListener(new iRecyecleritemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Common.chapterSelected=chaptersList.get(pos);
                Common.chapterIndex=pos;
                context.startActivity(new Intent(context,ViewBookActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return chaptersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textchapternumb;
        iRecyecleritemClickListener recyecleritemClickListener;

        public void setRecyecleritemClickListener(iRecyecleritemClickListener recyecleritemClickListener) {
            this.recyecleritemClickListener = recyecleritemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textchapternumb=(TextView)itemView.findViewById(R.id.txt_chapter_num);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyecleritemClickListener.onClick(v,getAdapterPosition());
        }
    }
}
