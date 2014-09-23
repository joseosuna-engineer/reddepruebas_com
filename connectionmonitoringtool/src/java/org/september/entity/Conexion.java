package org.september.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Conexion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Long hoyContador;
    @Temporal(TemporalType.TIMESTAMP)
    Date hoyFecha;
    Long hoyConnexion;

    Long ayerContador;
    @Temporal(TemporalType.TIMESTAMP)
    Date ayerFecha;
    Long ayerConnexion;

    Long mesContador;
    @Temporal(TemporalType.TIMESTAMP)
    Date mesFecha;
    Long mesConnexion;

    Long yearContador;
    @Temporal(TemporalType.TIMESTAMP)
    Date yearFecha;
    Long yearConnexion;

    public Conexion() {
    }

    public Conexion(Long id, Long hoyContador, Date hoyFecha, Long hoyConnexion, Long ayerContador, Date ayerFecha, Long ayerConnexion, Long mesContador, Date mesFecha, Long mesConnexion, Long yearContador, Date yearFecha, Long yearConnexion) {
        this.id = id;
        this.hoyContador = hoyContador;
        this.hoyFecha = hoyFecha;
        this.hoyConnexion = hoyConnexion;
        this.ayerContador = ayerContador;
        this.ayerFecha = ayerFecha;
        this.ayerConnexion = ayerConnexion;
        this.mesContador = mesContador;
        this.mesFecha = mesFecha;
        this.mesConnexion = mesConnexion;
        this.yearContador = yearContador;
        this.yearFecha = yearFecha;
        this.yearConnexion = yearConnexion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHoyContador() {
        return hoyContador;
    }

    public void setHoyContador(Long hoyContador) {
        this.hoyContador = hoyContador;
    }

    public Date getHoyFecha() {
        return hoyFecha;
    }

    public void setHoyFecha(Date hoyFecha) {
        this.hoyFecha = hoyFecha;
    }

    public Long getHoyConnexion() {
        return hoyConnexion;
    }

    public void setHoyConnexion(Long hoyConnexion) {
        this.hoyConnexion = hoyConnexion;
    }

    public Long getAyerContador() {
        return ayerContador;
    }

    public void setAyerContador(Long ayerContador) {
        this.ayerContador = ayerContador;
    }

    public Date getAyerFecha() {
        return ayerFecha;
    }

    public void setAyerFecha(Date ayerFecha) {
        this.ayerFecha = ayerFecha;
    }

    public Long getAyerConnexion() {
        return ayerConnexion;
    }

    public void setAyerConnexion(Long ayerConnexion) {
        this.ayerConnexion = ayerConnexion;
    }

    public Long getMesContador() {
        return mesContador;
    }

    public void setMesContador(Long mesContador) {
        this.mesContador = mesContador;
    }

    public Date getMesFecha() {
        return mesFecha;
    }

    public void setMesFecha(Date mesFecha) {
        this.mesFecha = mesFecha;
    }

    public Long getMesConnexion() {
        return mesConnexion;
    }

    public void setMesConnexion(Long mesConnexion) {
        this.mesConnexion = mesConnexion;
    }

    public Long getYearContador() {
        return yearContador;
    }

    public void setYearContador(Long yearContador) {
        this.yearContador = yearContador;
    }

    public Date getYearFecha() {
        return yearFecha;
    }

    public void setYearFecha(Date yearFecha) {
        this.yearFecha = yearFecha;
    }

    public Long getYearConnexion() {
        return yearConnexion;
    }

    public void setYearConnexion(Long yearConnexion) {
        this.yearConnexion = yearConnexion;
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
        if (!(object instanceof Conexion)) {
            return false;
        }
        Conexion other = (Conexion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.september.entity.Conexion[ id=" + id + " ]";
    }

}
