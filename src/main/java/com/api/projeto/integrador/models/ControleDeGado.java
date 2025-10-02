package com.api.projeto.integrador.models;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMPERATURA")
public class ControleDeGado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name = "data")
    private String numeroProdutor;

    @Column(name = "hora")
    private String cpfProdutor;

    @Column(name = "temperatura")
    private String numeroPropriedade;

    @Column(name = "umidade")
    private String numeroIdentificacao;

    // Construtor vazio
    public ControleDeGado() {
    }

    // Construtor com campos
    public ControleDeGado(String numeroProdutor, String numeroIdentificacao,
            String cpfProdutor) {
        this.numeroProdutor = numeroProdutor;
        this.cpfProdutor = cpfProdutor;
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroProdutor() {
        return numeroProdutor;
    }

    public void setNumeroProdutor(String numeroProdutor) {
        this.numeroProdutor = numeroProdutor;
    }

    public String getCpfProdutor() {
        return cpfProdutor;
    }

    public void setCpfProdutor(String cpfProdutor) {
        this.cpfProdutor = cpfProdutor;
    }

    public double getNumeroPropriedade() {
        try {
            return Double.parseDouble(this.numeroPropriedade);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public void setNumeroPropriedade(String numeroPropriedade) {
        this.numeroPropriedade = numeroPropriedade;
    }

    public double getNumeroIdentificacao() {
        try {
            return Double.parseDouble(this.numeroIdentificacao);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public void setNumeroIdentificacao(String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return id + "," + numeroProdutor + "," + cpfProdutor + "," +
                numeroPropriedade;
    }

}
