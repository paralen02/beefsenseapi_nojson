package com.example.beefsenseapi.dtos;
import jakarta.persistence.*;
import com.example.beefsenseapi.entities.*;
import java.time.*;

public class ModelosDTO {

    private int idModelos;
    private String version;
    private Boolean operativo;

    public int getIdModelos() {
        return idModelos;
    }

    public void setIdModelos(int idModelos) {
        this.idModelos = idModelos;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getOperativo() {
        return operativo;
    }

    public void setOperativo(Boolean operativo) {
        this.operativo = operativo;
    }
}
