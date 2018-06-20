package com.example.gabri.medicalschedule;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlterarActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseConsultas;
    private TextView textViewNomePaciente;
    private EditText editTextNome;
    private EditText editTextFone;
    private EditText editTextData;
    private Button buttonAlterar;
    private Button buttonDeletar;
    private Spinner spinnerMedicos;
    private String consultaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        databaseConsultas = FirebaseDatabase.getInstance().getReference("consultas");

        //inicializa a autenticacação do firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //caso o usuário não esteja logado a activity sera fechada
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        textViewNomePaciente = (TextView) findViewById(R.id.txtNomePaciente);
        editTextNome = (EditText) findViewById(R.id.txtNome);
        editTextFone = (EditText) findViewById(R.id.txtFone);
        editTextData = (EditText) findViewById(R.id.txtData);
        buttonAlterar = (Button) findViewById(R.id.btnAlterar);
        buttonDeletar = (Button) findViewById(R.id.btnDeletar);
        spinnerMedicos = (Spinner) findViewById(R.id.opcMedicos);

        buttonAlterar.setOnClickListener(this);
        buttonDeletar.setOnClickListener(this);

        Intent intent = getIntent();
        String idConsulta = intent.getStringExtra(ConsultarActivity.CONSULTA_ID);
        String nomePaciente = intent.getStringExtra(ConsultarActivity.NOME_PACIENTE);


        textViewNomePaciente.setText("Alterar consulta do Paciente " + nomePaciente);
        consultaId = idConsulta;
    }

    public boolean alterarConsulta(String id, String nome, String fone, String data, String medico) {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("consultas").child(id);


        Consulta consulta = new Consulta(id, nome, fone, data, medico);

        databaseReference.setValue(consulta);

        Toast.makeText(this, "Consulta alterada com sucesso", Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == buttonAlterar) {


            String nome = editTextNome.getText().toString().trim();
            String fone = editTextFone.getText().toString().trim();
            String data = editTextData.getText().toString().trim();
            String medico = spinnerMedicos.getSelectedItem().toString();

            if ((!TextUtils.isEmpty(nome)) && (!TextUtils.isEmpty(fone)) && (!TextUtils.isEmpty(data))) {
                alterarConsulta(consultaId, nome, fone, data, medico);
                Toast.makeText(this, "Consulta alterada com sucesso", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast.makeText(this, "Preencha todos os Campos", Toast.LENGTH_SHORT).show();
            }
        }

        if (view == buttonDeletar) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("consultas").child(consultaId);
            databaseReference.removeValue();

            Toast.makeText(this, "Consulta apagada com sucesso", Toast.LENGTH_LONG).show();

            finish();

        }
    }


}

