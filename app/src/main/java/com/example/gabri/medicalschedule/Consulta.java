package com.example.gabri.medicalschedule;

/**
 * Created by Gabri on 06/06/2018.
 */

public class Consulta {

    String consultaId;
    String nomePacinte;
    String fonePaciente;
    String dataConsulta;
    String medicoSelecionado;

    public Consulta(){

    }

    public Consulta(String consultaId, String nomePacinte, String fonePaciente, String dataConsulta, String medicoSelecionado) {
        this.consultaId = consultaId;
        this.nomePacinte = nomePacinte;
        this.fonePaciente = fonePaciente;
        this.dataConsulta = dataConsulta;
        this.medicoSelecionado = medicoSelecionado;
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

    public String getMedicoSelecionado() {
        return medicoSelecionado;
    }
}
