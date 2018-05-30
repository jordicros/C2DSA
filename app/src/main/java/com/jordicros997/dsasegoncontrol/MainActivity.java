package com.jordicros997.dsasegoncontrol;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void resultat(String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        builder.setMessage(result).setTitle("Register");
        Dialog dialeg = builder.create();
        dialeg.show();

    }
    public void sendUsername(View view){
       GithubAPI caller = Singleton.getInstance().getService();
        EditText ed = (EditText) this.findViewById(R.id.editTextUsername);
        Call<User> call = caller.user(ed.getText().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Singleton.getInstance().setUser(response.body());
                    Intent intent = new Intent(MainActivity.this, FollowersActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
                else
                {
                   resultat("An error has occurred during the request");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                resultat("An error has occurred during the request");
            }
        });
    }
}
