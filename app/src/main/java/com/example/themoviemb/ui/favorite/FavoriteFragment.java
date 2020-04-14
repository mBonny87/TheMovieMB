package com.example.themoviemb.ui.favorite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themoviemb.R;
import com.example.themoviemb.adapters.MoviesAdapter;
import com.example.themoviemb.data.models.Result;
import com.example.themoviemb.interface_movie.IWebServer;
import com.example.themoviemb.networks.WebService;

import java.util.List;

public class FavoriteFragment extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    private FavoriteViewModel dashboardViewModel;
    private RecyclerView rvFavorite;
    private RecyclerView.Adapter adapterFavorite;
    private RecyclerView.LayoutManager layoutManagerFavorite;
    private WebService webService;
    private TextView textView;
    private IWebServer webServerListener = new IWebServer() {
        @Override
        public void onMoviesFetched(boolean success, Result result, int errorCode, String errorMessage) {
            Log.d("TAGGGGG", result.getResult().get(1).getTitle());
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        webService = WebService.getInstance();

      loadTodos();

        rvFavorite=root.findViewById(R.id.rvMovies);
        layoutManagerFavorite=new GridLayoutManager(getContext(),2);
        adapterFavorite=new MoviesAdapter(fetchedData);
        rvFavorite.setAdapter(adapterFavorite);

        return root;
    }
    private void loadTodos() {

        webService.getMovies(webServerListener);
    }
}
