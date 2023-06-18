package com.example.pushnotificationmainapp;

import static com.google.firebase.messaging.FirebaseMessaging.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pushnotificationmainapp.Retrofit.ApiInterface;
import com.example.pushnotificationmainapp.Retrofit.RetrofitClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
DatabaseReference reference;
Button notificationPush;
TextView textView;
    String token="";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase  = FirebaseDatabase.getInstance("https://pushnotificationcustomer-d6f9e-default-rtdb.firebaseio.com/");

        reference = firebaseDatabase.getReference("Demo/");
        notificationPush = findViewById(R.id.notificationPush);

        textView = findViewById(R.id.textView);

        notificationPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                retriveChild();
            }
        });

    }
    public void retriveChild(){
        String uid = "Tushar@123";
            reference.child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    token = snapshot.child("token").getValue().toString();
                    textView.setText(token);
                    callApi(token);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

    }
    
    public void callApi(String token){
        ProgressDialog pd  = new ProgressDialog(this);
        pd.setMessage("Please wait...");
        pd.show();
        ApiInterface apiInterface = new RetrofitClient().getRetrofit().create(ApiInterface.class);
        RequestBody.Notification notification = new RequestBody.Notification("This is push notification","Firebase");
        RequestBody requestBody = new RequestBody(token,notification);
        Call<ResponseClass>call =apiInterface.getMessage("key=AAAA_2Nifxs:APA91bEJtY4-ldfOAsfNcAqRAlC5Flm14vVJZOL9ZZDvDMVLFUM6a9-9BcuIuPuLMIsvk2ExYC35ZHED0IXVFG-ScLP1lDNQOwrJvky2iMeJ9lqVFFnrqwnHRedvANdkDa-kyM1GfZ0L",requestBody);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Your notification has been send!!!", Toast.LENGTH_SHORT).show();
                   pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                 pd.dismiss();
            }
        });
    }
}