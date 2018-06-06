package com.example.gabri.medicalschedule;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonLogar;
    private TextView textViewCadastrar;

    private ProgressDialog Loading;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Loading = new ProgressDialog(this);

        firebaseAuth = firebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.txtEmail);
        editTextSenha = (EditText) findViewById(R.id.txtSenha);
        buttonLogar = (Button) findViewById(R.id.btnLogar);
        textViewCadastrar = (TextView) findViewById(R.id.txtCadastrar);

        buttonLogar.setOnClickListener(this);
        textViewCadastrar.setOnClickListener(this);
    }

    private void loginUsuario(){
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        //Verifica se os campos não estão vazios
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Por favor, digite o seu e-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(senha)) {
            Toast.makeText(this, "Por favor, digite a sua senha", Toast.LENGTH_SHORT).show();
        }

        Loading.setMessage("Entrando...");
        Loading.show();

        firebaseAuth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Loading.dismiss();
                        if (task.isSuccessful()){
                            //chama a tela inicial
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view==buttonLogar){
            loginUsuario();
        }
        if (view==textViewCadastrar){
            finish();
            startActivity(new Intent(this, CadastrarActivity.class));
        }
    }
}
