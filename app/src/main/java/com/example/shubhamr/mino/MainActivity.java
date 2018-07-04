package com.example.shubhamr.mino;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.shubhamr.mino.HelperClasses.songRetrievingClass;
import com.example.shubhamr.mino.ModelClasses.songModel;
import com.example.shubhamr.mino.RecyclerView.songListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity implements clickListenerInterface {

    @BindView(R.id.listCurrentSongName)TextView listCurrentSongName;
    @BindView(R.id.listCurrentSongSinger)TextView listCurrentSongSinger;
    @BindView(R.id.listCurrentSongYear)TextView listCurrentSongYear;
    @BindView(R.id.songListRecyclerView)RecyclerView songListRecyclerView;
    public songListAdapter songListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        MainActivityPermissionsDispatcher.setRecyclerViewDataWithPermissionCheck(this);

    }

    //Setting RecyclerView adapter and layout
    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void setRecyclerViewData(){

        List<songModel> songList = songRetrievingClass.getSongList(this);
        songListAdapter = new songListAdapter(this);
        songListAdapter.setSongList(songList);
        songListRecyclerView.setAdapter(songListAdapter);
        songListAdapter.setClickListeners(this);
        songListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void itemClicked(View view, int position, CircleImageView sharedImage){

        Intent i = new Intent(this,Player_Activity.class);
        startActivity(i);

    }


    //Permission Dispatcher
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }




}
