package org.example.es2webapp.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//NB: QUESTA CLASSE DEVE STARE NELLO STESSO PACKAGE (O IN UN PACCHETTO SOTTOSTANTE) DI Es2WebApplication!

//@Data //in automatico crea getter e setter per tutte le proprietà
@Entity //indica che questa classe è un entità del DB.
public class Ristorante {
    private @Id
    @GeneratedValue Long id; //indica che questa variabile è un campo dell'entità Ristorante ed è anche la chiave primaria.
    private String nome;
    private String descrizione;
    private String ubicazione;

    @ManyToMany
    @JoinTable(
            name = "ristorante_piatto", //nome della tabella di join
            joinColumns = @JoinColumn(name = "ristorante_id"), //colonna di join per Ristorante
            inverseJoinColumns = @JoinColumn(name = "piatto_id") //colonna di join per Piatto
    )
    private List<Piatto> piatti = new ArrayList<>();

    public Ristorante(){}
    public Ristorante(String nome, String descrizione, String ubicazione) {
        super();
        this.nome = nome;
        this.descrizione = descrizione;
        this.ubicazione = ubicazione;
    }

    public Long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescrizione() {return descrizione;}
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public String getUbicazione() {return ubicazione;}
    public void setUbicazione(String ubicazione) {this.ubicazione = ubicazione;}

    public List<Piatto> getPiatti() {return piatti;}
    public void setPiatti(List<Piatto> piatti) {this.piatti = piatti;}

    public void addPiatto(Piatto piatto) { this.piatti.add(piatto); }
    public void removePiatto(Piatto piatto) { this.piatti.remove(piatto); }
}
