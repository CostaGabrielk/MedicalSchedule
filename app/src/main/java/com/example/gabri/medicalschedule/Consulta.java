package com.example.gabri.medicalschedule;

/**
 * Created by Gabri on 06/06/2018.
 */

public class Consultas {

    String consultaId;
    String nomePacinte;
    String fonePaciente;
    String dataConsulta;

    public Consultas(){

    }

    public Consultas(String consultaId, String nomePacinte, String fonePaciente, String dataConsulta) {
        this.consultaId = consultaId;
        this.nomePacinte = nomePacinte;
        this.fonePaciente = fonePaciente;
        this.dataConsulta = dataConsulta;
    }

    public String getConsultaId() {
        return consultaId;
    }

    public String getNomePacinte() {
        return nomePacinte;
    }

    public String getFonePaciente() {
        return fonePaciente;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }
}
