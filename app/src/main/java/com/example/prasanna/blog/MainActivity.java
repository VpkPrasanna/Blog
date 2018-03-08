package com.example.prasanna.blog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText name,pass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name  = (EditText)findViewById(R.id.Username);
        pass = (EditText)findViewById(R.id.Password);

        Button signin = (Button)findViewById(R.id.SignIn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(formValid()){
                    perform_sign_in();
                }

            }
        });
        Button register = (Button)findViewById(R.id.Register);
          register.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if(formValid()){
                      perform_register();

                  }

              }
          });
          progressDialog = new ProgressDialog(this);
          progressDialog.setIndeterminate(true);
          progressDialog.setMessage("please wait");


    }
    private Boolean formValid(){
        if(name.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"User name cannot be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pass.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    private void perform_sign_in(){
        showProgressDialog(true);
        ApiManager.getApiInterface().login(new AunthicationRequest(name.getText().toString().trim(),pass.getText().toString().trim()))
                .enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        showProgressDialog(false);
                      if(response.isSuccessful()){
                          navigateToArticleListActivity();
                      }
                      else{
                          try {
                              String errorMessage  = response.errorBody().string();
                              try{
                                  ErrorResponse errorResponse = new Gson().fromJson(errorMessage,ErrorResponse.class);
                                  showAlert("signedIn failed",errorResponse.getError());
                              }catch (JsonSyntaxException jsonException){
                                   jsonException.printStackTrace();
                                   showAlert("signedIn failled","something went wrong");
                              }
                          } catch (IOException e) {
                              e.printStackTrace();
                              showAlert("signedIn failled","something went wrong");
                          }

                      }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        showProgressDialog(true);
                        showAlert("signedIn failled","something went wrong");

                    }
                });

    }
    private void perform_register(){
        showProgressDialog(true);
        ApiManager.getApiInterface().registration(new AunthicationRequest(name.getText().toString().trim(),pass.getText().toString().trim()))
                .enqueue(new Callback<MessageResponse>() {
                    @Override
                    public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                        showProgressDialog(false);
                        if(response.isSuccessful()){
                            showAlert("welcome",response.body().getMessage());
                        }
                        else{
                            try {
                                String errorMessage  = response.errorBody().string();
                                try{
                                    ErrorResponse errorResponse = new Gson().fromJson(errorMessage,ErrorResponse.class);
                                    showAlert("registration failed",errorResponse.getError());
                                }catch (JsonSyntaxException jsonException){
                                    jsonException.printStackTrace();
                                    showAlert("registration failled","something went wrong");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                showAlert("registration failled","something went wrong");
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<MessageResponse> call, Throwable t) {
                        showProgressDialog(true);
                        showAlert("registration failled","something went wrong");

                    }
                });

    }


    private void showProgressDialog(Boolean shouldShould){
        if(shouldShould){
            progressDialog.show();
        }
        else{
            progressDialog.dismiss();
        }

    }
    private void showAlert(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              dialogInterface.dismiss();
            }
        });
        builder.show();
    }

class signInTask extends AsyncTask<String,Void,Boolean> {
        String mockUser = "test";
        String mockPass = "Password";


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showProgressDialog(true);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        showProgressDialog(false);

        if(aBoolean){
            showAlert("Welcome","you have sucessfully signedin");
        }
        else{
           showAlert("failed","Enter name/password correctly");
        }
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        String name = strings[0];
        String pass = strings[1];
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return name.contentEquals(mockUser) && pass.contentEquals(mockPass);
    }
  }
  private void navigateToArticleListActivity(){
      Intent intent = new Intent(MainActivity.this,ArticleListActivity.class);
      startActivity(intent);
  }
}
