package org.example.es2webapp.model.entities;

import jakarta.persistence.*;

//NB: QUESTA CLASSE DEVE STARE NELLO STESSO PACKAGE (O IN UN PACCHETTO SOTTOSTANTE) DI Es2WebApplication!

@Entity
public class Piatto {
    private @Id
    @GeneratedValue Long id;
    private String nome;
    private String descrizione;

    public Piatto(){}
    public Piatto(String nome, String descrizione) {
        super();
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public Long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescrizione() {return descrizione;}
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}
}

