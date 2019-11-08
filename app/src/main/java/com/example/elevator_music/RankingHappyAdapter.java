package com.example.elevator_music;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

import static com.facebook.share.internal.DeviceShareDialogFragment.TAG;

public class RankingHappyAdapter extends RecyclerView.Adapter<RankingHappyAdapter.ItemViewHolder>{
    ArrayList<String> arrayList;
    Context context;

    public RankingHappyAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String id = arrayList.get(position).substring(arrayList.get(position).lastIndexOf("/")+1);
                String url ="https://img.youtube.com/vi/"+ id+ "/" + "default.jpg";  //유튜브 썸네일 불러오는 방법
                Glide.with(context).load(url).asBitmap()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.lv_rangking);
//                try {
//                    Document doc = Jsoup.connect("https://youtu.be/"+id).get();
//                    doc.select("ytd-video-primary-info-renderer").addClass("style-scope ytd-video-primary-info-renderer");
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView lv_rangking;
        TextView tv_title;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            lv_rangking = itemView.findViewById(R.id.musicAlbum);
            tv_title = itemView.findViewById(R.id.musicTitle);
        }
    }
}

