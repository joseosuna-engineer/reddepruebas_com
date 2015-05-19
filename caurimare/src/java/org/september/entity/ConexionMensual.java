package org.september.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConexionMensual implements Serializable {

    private static final long serialVersionUID = 3365565L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Long Mes;
    Long mesContador;
    Long mesConnexion;

    public ConexionMensual() {
    }

    public ConexionMensual(Long Mes, Long mesContador, Long mesConnexion) {
        this.Mes = Mes;
        this.mesContador = mesContador;
        this.mesConnexion = mesConnexion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMes() {
        return Mes;
    }

    public void setMes(Long Mes) {
        this.Mes = Mes;
    }

    public Long getMesContador() {
        return mesContador;
    }

    public void setMesContador(Long mesContador) {
        this.mesContador = mesContador;
    }

    public Long getMesConnexion() {
        return mesConnexion;
    }

    public void setMesConnexion(Long mesConnexion) {
        this.mesConnexion = mesConnexion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConexionMensual)) {
            return false;
        }
        ConexionMensual other = (ConexionMensual) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.september.entity.ConexionMensual[ id=" + id + " ]";
    }

}
