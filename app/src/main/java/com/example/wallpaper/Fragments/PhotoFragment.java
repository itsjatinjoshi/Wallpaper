package com.example.wallpaper.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaper.Adapter.PhotoAdapter;
import com.example.wallpaper.Models.Photo;
import com.example.wallpaper.R;
import com.example.wallpaper.Webservices.ApiInterface;
import com.example.wallpaper.Webservices.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoFragment extends Fragment {

    private final String TAG = PhotoFragment.class.getSimpleName();
    @BindView(R.id.fragment_photo_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.fragment_photo_recycleview)
    RecyclerView recyclerView;

    private PhotoAdapter photoAdapter;
    private List<Photo> photos = new ArrayList<>();

    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_photo, container, false);

       unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        photoAdapter = new PhotoAdapter(getActivity(), photos);
        recyclerView.setAdapter(photoAdapter);

        showProgessBar(true);
        getPhoto();


        return view;
    }

    private void getPhoto(){
        ApiInterface apiInterface= ServiceGenerator.createService(ApiInterface.class);
        Call<List<Photo>> call=apiInterface.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    photos.addAll(response.body());
                    photoAdapter.notifyDataSetChanged();
                }
                else {
                    Log.e(TAG, "fail" + response.message());
                }
                showProgessBar(false);
            }



            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

                Log.e(TAG, "fail" + t.getMessage());
                showProgessBar(false);

            }
        });
         }

         private  void showProgessBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else{
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        }
         }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
