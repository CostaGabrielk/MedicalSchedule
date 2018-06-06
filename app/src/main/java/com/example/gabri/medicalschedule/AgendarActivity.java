package com.example.gabri.medicalschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgendarActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private EditText editTextNome;
    private EditText editTextFone;
    private EditText editTextData;
    private Button buttonSalvar;
    private Button buttonSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        //inicializa a autenticacação do firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //caso o usuário não esteja logado a activity sera fechada
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextNome = (EditText) findViewById(R.id.txtNome);
        editTextFone = (EditText) findViewById(R.id.txtFone);
        editTextData = (EditText) findViewById(R.id.txtData);
        buttonSalvar = (Button) findViewById(R.id.btnSalvar);
        buttonSair = (Button) findViewById(R.id.btnSair);

        buttonSalvar.setOnClickListener(this);
        buttonSair.setOnClickListener(this);
    }

    private void agendarConsulta() {
        String nome = editTextNome.getText().toString().trim();
        String fone = editTextFone.getText().toString().trim();
        String data = editTextData.getText().toString().trim();

        DadosUsuario dadosUsuario = new DadosUsuario(nome, fone, data);


        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(dadosUsuario);

        Toast.makeText(this, "Consulta Agendada. Em breve entraremos em contato...", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onClick(View view) {

        if (view == buttonSalvar) {
            agendarConsulta();
        }

        if (view == buttonSair) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
