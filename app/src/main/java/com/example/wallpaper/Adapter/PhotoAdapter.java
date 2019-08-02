package com.example.wallpaper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallpaper.Models.Photo;
import com.example.wallpaper.R;
import com.example.wallpaper.Utils.GlideApp;
import com.example.wallpaper.Utils.SquareImage;

import java.util.List;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>

 {

     private Context context;
     private List<Photo> photos;

     public  PhotoAdapter( Context context, List<Photo> photos){
         this.context =context;
         this.photos =photos;
     }
     @NonNull
     @Override
     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View itemView = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.item_photo, parent, false);
                  return new ViewHolder(itemView);
     }

     @Override
     public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Photo photo = photos.get(position);
         holder.username.setText(photo.getUser().getUsername());

             GlideApp
            .with(context)
            .load(photo.getUrl().getRegular())
            .placeholder(R.drawable.placeholder)
            .override(600, 600)
            .into(holder.photo);

             GlideApp
                     .with(context)
                     .load(photo.getUser().getProfileImages().getSmall())
                     .into(holder.userAvatar);

     }

     @Override
     public int getItemCount() {
         return photos.size();
     }

     public  class ViewHolder extends RecyclerView.ViewHolder{

         @BindView(R.id.item_photo_user_avatar)
         CircleImageView userAvatar;

         @BindView(R.id.item_username)
         TextView username;

         @BindView(R.id.item_photo_photo)
         SquareImage photo;

         public ViewHolder(View itemView){
             super(itemView);
             ButterKnife.bind(this, itemView);

         }

     }

}
