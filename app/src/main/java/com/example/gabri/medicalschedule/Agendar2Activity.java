package com.example.gabri.medicalschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Agendar2Activity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseConsultas;
    private EditText editTextNome;
    private EditText editTextFone;
    private EditText editTextData;
    private Button buttonSalvar;
    private Button buttonVoltar;
    private Spinner spinnerMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar2);

        //inicializa a autenticacação do firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //caso o usuário não esteja logado a activity sera fechada
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseConsultas = FirebaseDatabase.getInstance().getReference("consultas");

        editTextNome = (EditText) findViewById(R.id.txtNome);
        editTextFone = (EditText) findViewById(R.id.txtFone);
        editTextData = (EditText) findViewById(R.id.txtData);
        buttonSalvar = (Button) findViewById(R.id.btnSalvar);
        buttonVoltar = (Button) findViewById(R.id.btnVoltar);
        spinnerMedicos = (Spinner) findViewById(R.id.opcMedicos);

        buttonSalvar.setOnClickListener(this);
        buttonVoltar.setOnClickListener(this);

    }

    public void adicionarConsulta() {
        String nome = editTextNome.getText().toString().trim();
        String fone = editTextFone.getText().toString().trim();
        String data = editTextData.getText().toString().trim();
        String medico = spinnerMedicos.getSelectedItem().toString();

        if ((!TextUtils.isEmpty(nome)) && (!TextUtils.isEmpty(fone)) && (!TextUtils.isEmpty(data))) {

            String id = databaseConsultas.push().getKey();

            Consulta consulta = new Consulta(id, nome, fone, data, medico);

            databaseConsultas.child(id).setValue(consulta);

            Toast.makeText(this, "Consulta Agendada. Em breve entraremos em contato...", Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(this, MainActivity.class));

        } else {
            Toast.makeText(this, "Preencha todos os Campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSalvar) {
            adicionarConsulta();
        }

        if (view == buttonVoltar) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
