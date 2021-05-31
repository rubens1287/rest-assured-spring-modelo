package br.com.demo.spring.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder @AllArgsConstructor
public class Topico {

    private int id;
    private String titulo;
    private String mensagem;
    private String nomeAutor;
    private String emailUsuario;
    private String nomeCurso;
    private String dataCriacao;
}

