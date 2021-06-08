package br.com.demo.spring.modelo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder @AllArgsConstructor
public class Curso {
    private String nome;
    private String categoria;
}
