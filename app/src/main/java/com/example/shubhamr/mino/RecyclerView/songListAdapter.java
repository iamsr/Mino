package com.example.shubhamr.mino.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shubhamr.mino.ModelClasses.songModel;
import com.example.shubhamr.mino.R;
import com.example.shubhamr.mino.clickListenerInterface;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class songListAdapter extends RecyclerView.Adapter<songListAdapter.songViewHolder>{

    private List<songModel> songList;
    private Context context;
    private clickListenerInterface clickListeners = null;

    public songListAdapter(Context context){
        this.context=context;
    }

    public class songViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CircleImageView songImage;
        public TextView songName;
        public TextView songSinger;

        public songViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            songImage = (CircleImageView) view.findViewById(R.id.listSongImage);
            songName = (TextView) view.findViewById(R.id.listSongName);
            songSinger = (TextView) view.findViewById(R.id.listSongSinger);
        }

        @Override
        public void onClick(View view){
            if(clickListeners!=null){
                clickListeners.itemClicked(view,getAdapterPosition(),songImage);
            }


        }

    }


    public void setClickListeners(clickListenerInterface clickListener){
        this.clickListeners =clickListener;
    }

    @Override
    public songViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_list_recyclerview_layout, parent, false);
        return new songViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull songViewHolder holder, int position) {

        songModel song = songList.get(position);
        holder.songName.setText(song.getSongName());
        holder.songSinger.setText(song.getSongArtist());
        if(song.getImagePath()==null||song.getImagePath().equals("")){
            holder.songImage.setImageResource(R.color.colorPrimary);
        }else{
        Uri songImageUri = Uri.parse(song.getImagePath());
        holder.songImage.setImageURI(songImageUri);
    }
    }

    @Override
    public int getItemCount(){
        return songList.size();
    }

    public void setSongList(List<songModel> songList) {
        this.songList = songList;
    }

    public List<songModel> getSongList() {
        return songList;
    }



}
