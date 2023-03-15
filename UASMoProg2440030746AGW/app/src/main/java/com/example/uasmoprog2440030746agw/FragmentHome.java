package com.example.uasmoprog2440030746agw;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uasmoprog2440030746agw.Movie;
import com.example.uasmoprog2440030746agw.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=7ee13e46207077c401c19a5c9f8142fd";

    ArrayList<Movie> movieList;
    RecyclerView recyclerView;
    AppCompatActivity appCompatActivity;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appCompatActivity.setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        recyclerView = appCompatActivity.findViewById(R.id.recyclerView);

        GetData getData = new GetData();
        getData.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public class GetData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    int data = inputStreamReader.read();
                    while (data != -1) {
                        current += (char) data;
                        data = inputStreamReader.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    Movie movie = new Movie();
                    movie.setMovieName(jsonObject1.getString("title"));
                    movie.setMovieImg(jsonObject1.getString("poster_path"));

                    movieList.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(movieList);
        }
    }

    private void PutDataIntoRecyclerView(ArrayList<Movie> movieList){
        AdapterMovieList adapterMovieList = new AdapterMovieList(context, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        recyclerView.setAdapter(adapterMovieList);
    }
}