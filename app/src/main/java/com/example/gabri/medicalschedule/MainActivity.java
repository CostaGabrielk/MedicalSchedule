package com.example.gabri.medicalschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSair;
    private FirebaseAuth firebaseAuth;
    private Button buttonAgendar;
    private Button buttonConsultar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        buttonSair = (Button) findViewById(R.id.btnSair);
        buttonAgendar = (Button) findViewById(R.id.btnAgendar);
        buttonConsultar = (Button) findViewById(R.id.btnConsultar);



        buttonSair.setOnClickListener(this);
        buttonAgendar.setOnClickListener(this);
        buttonConsultar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == buttonSair) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        if (view == buttonAgendar) {
            finish();
            startActivity(new Intent(this, Agendar2Activity.class));

        }

        if (view == buttonConsultar) {
            finish();
            startActivity(new Intent(this, ConsultarActivity.class));

        }


    }
}
