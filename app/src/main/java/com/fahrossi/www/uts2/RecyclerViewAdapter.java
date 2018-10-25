package com.fahrossi.www.uts2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Lagu> mData;

    public RecyclerViewAdapter(Context mContext, List<Lagu> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_view_lagu,parent,false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(MyViewHolder holder, final int position){

//        holder.tv_lagu_ID.setText(""+mData.get(position).getID());
        holder.tv_lagu_JUDUL.setText(mData.get(position).getJudul());
        holder.tv_lagu_NAMABAND.setText(mData.get(position).getNama_band());
//        holder.tv_lagu_GENRE.setText(mData.get(position).getGenre());
//        holder.karakter_img_thumbnail.setImageResource(mData.get(position).getThumbnail());
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(mContext,Karakter_Activity.class);
//
//
//                //lempar data ke karakter activity
//                intent.putExtra("Nama",mData.get(position).getName());
//                intent.putExtra("Biodata",mData.get(position).getBiodata());
//                intent.putExtra("Description",mData.get(position).getDescription());
//                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
//                //start activity
//                mContext.startActivity(intent);
//            }
//        });
    }

    public int getItemCount(){
        return mData.size();
    }

    public  static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tv_lagu_ID;
        TextView tv_lagu_JUDUL;
        TextView tv_lagu_NAMABAND;
        TextView tv_lagu_GENRE;
        CardView cardView;

        public  MyViewHolder(View itemView) {
            super(itemView);


//            tv_lagu_ID = (TextView) itemView.findViewById(R.id.lagu_id) ;
            tv_lagu_JUDUL = (TextView) itemView.findViewById(R.id.lagu_judul) ;
            tv_lagu_NAMABAND = (TextView) itemView.findViewById(R.id.lagu_nama_band) ;
//            tv_lagu_GENRE = (TextView) itemView.findViewById(R.id.lagu_genre) ;
//            karakter_img_thumbnail = (ImageView) itemView.findViewById(R.id.karakter_img) ;
            cardView = (CardView) itemView.findViewById(R.id.cardview_id) ;
        }
    }

}
