package com.example.gabri.medicalschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultarActivity extends AppCompatActivity {

    public static final String CONSULTA_ID = "consultaID";
    public static final String  NOME_PACIENTE = "nomePaciente";

    ListView listViewConsultas;

    private DatabaseReference databaseConsultas;

    private FirebaseAuth firebaseAuth;

    List<Consulta> consultaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        //inicializa a autenticacação do firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //caso o usuário não esteja logado a activity sera fechada
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseConsultas = FirebaseDatabase.getInstance().getReference("consultas");

        listViewConsultas = (ListView) findViewById(R.id.lstvConsultas);

        consultaList = new ArrayList<>();



        listViewConsultas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Consulta consulta = consultaList.get(i);

                Intent intent = new Intent(getApplicationContext(), AlterarActivity.class);
                intent.putExtra(CONSULTA_ID, consulta.getConsultaId());
                intent.putExtra(NOME_PACIENTE, consulta.getNomePacinte());

                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseConsultas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                consultaList.clear();
                for(DataSnapshot consultaSnapshot: dataSnapshot.getChildren()){

                    Consulta consulta = consultaSnapshot.getValue(Consulta.class);

                    consultaList.add(consulta);

                }

                ListaConsulta adapter = new ListaConsulta(ConsultarActivity.this, consultaList);
                listViewConsultas.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
