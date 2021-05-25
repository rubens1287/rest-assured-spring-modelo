package br.com.demo.spring.modelo;

import java.util.ArrayList;
import java.util.List;


public class Topico {

    private int id;
    private String titulo;
    private  String mensagem;
    private  String dataCriacao;

    public class Topicos{
        List<Topico> topico = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}

