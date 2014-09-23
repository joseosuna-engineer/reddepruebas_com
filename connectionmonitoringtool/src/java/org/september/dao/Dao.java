package org.september.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.datanucleus.api.jpa.NucleusJPAHelper;
import org.september.entity.Conexion;
import org.september.service.EMFService;

public enum Dao {

    INSTANCE;

    public List<Conexion> getConexions() {
        EntityManager em = EMFService.get().createEntityManager();
        Query q = em.createQuery("select m from Conexion m");
        List<Conexion> conexions = q.getResultList();
        return conexions;
    }

    public void add(Long id, Long hoyContador, Date hoyFecha, Long hoyConnexion,
            Long ayerContador, Date ayerFecha, Long ayerConnexion,
            Long mesContador, Date mesFecha, Long mesConnexion,
            Long yearContador, Date yearFecha, Long yearConnexion) {

        EntityManager em = EMFService.get().createEntityManager();
        Conexion conexion = new Conexion(id, hoyContador, hoyFecha,
                hoyConnexion, ayerContador, ayerFecha, ayerConnexion,
                mesContador, mesFecha, mesConnexion, yearContador, yearFecha,
                yearConnexion);
        em.persist(conexion);
        em.close();
    }

    public void update(Conexion conexion) {
        EntityManager em = NucleusJPAHelper.getEntityManager(conexion);
        em.merge(conexion);
        em.close();
    }

}
