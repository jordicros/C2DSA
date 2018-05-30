package com.jordicros997.dsasegoncontrol;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersActivity extends AppCompatActivity {
    public Activity activity = this;
    public void resultat(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        builder.setMessage(result).setTitle("Register");
        Dialog dialeg = builder.create();
        dialeg.show();

    }
    public List<LlistaFollowers> fromUsersToAdapter(List<User> followers){
        List<LlistaFollowers> adapted = new ArrayList<>();
        for(int i=0;i<followers.size();i++){
            adapted.add(new LlistaFollowers(followers.get(i).login,followers.get(i).avatar_url));
        }
        return adapted;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        final ProgressBar progrBar = (ProgressBar) findViewById(R.id.progressBar);
        progrBar.setVisibility(View.VISIBLE);
        User u = Singleton.getInstance().getUser();
        ImageView profil_pic = findViewById(R.id.imageView);
        Picasso.get().load(u.avatar_url).into(profil_pic);
        TextView profil_followers = (TextView) findViewById(R.id.textView3);
        profil_followers.setText("Followers: "+u.followers);
        TextView profil_repos = (TextView) findViewById(R.id.textView2);
        profil_repos.setText("Repositories: "+u.public_repos);
        GithubAPI caller = Singleton.getInstance().getService();
        EditText ed = (EditText) this.findViewById(R.id.textView);
        Call<List<User>> call = caller.llistaFollowers(u.login);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()) {
                    List<User> follows = response.body();
                    List<LlistaFollowers> adaptedFollowers= fromUsersToAdapter(follows);
                    Adapter adp = new Adapter(activity,adaptedFollowers);
                    ListView llista = activity.findViewById(R.id.list_followers);
                    llista.setAdapter(adp);
                    progrBar.setVisibility(View.GONE);
                }
                else{
                    progrBar.setVisibility(View.GONE);
                    resultat("Failed");}
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progrBar.setVisibility(View.GONE);
                resultat("Fail");
            }
        });
    }
}
