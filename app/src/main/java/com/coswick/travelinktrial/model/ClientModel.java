package com.coswick.travelinktrial.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Lucas Albuquerque on 09/03/2018.
 */
@DatabaseTable(tableName = "cliente" )
public class ClientModel implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String nome;

    @DatabaseField
    private int idade;

    @DatabaseField
    private String email;

    @DatabaseField
    private String comentario;

    public ClientModel() {
    }

    public ClientModel(int id, String nome, int idade, String email, String comentario) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
