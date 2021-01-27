package co.edu.unipiloto.rest_service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private ListView list;
    private ArrayList<okhttp3.Response> titles = new ArrayList<okhttp3.Response>();
    private ArrayAdapter arrayAdapter;
    EditText id_post;
    public int idpost_q = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id_post = findViewById(R.id.idpost);


        //ArrayAdapter
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        list = findViewById(R.id.list);


        list.setAdapter(arrayAdapter);

        getPosts();

    }

        public void getPostid (View view){

        String idpost_s = id_post.getText().toString();
        idpost_q = Integer.parseInt(idpost_s);
        getPosts();




    }


    private void getPosts(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call<Post> call = postService.getPost(idpost_q);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                titles.add(response.raw());
                 arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });



    }
}


