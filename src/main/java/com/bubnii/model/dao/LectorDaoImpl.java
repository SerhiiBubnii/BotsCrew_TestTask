package com.bubnii.model.dao;

import com.bubnii.model.dao.interfaces.LectorDao;
import com.bubnii.model.entity.Lector;
import com.bubnii.model.session.EntityManagerFactoryUtil;

import java.util.List;
import java.util.Optional;

public class LectorDaoImpl implements LectorDao {

    public Optional<Lector> findByID(Integer id) {
        Lector lector = EntityManagerFactoryUtil.getEntityManager().find(Lector.class, id);
        return lector != null ? Optional.of(lector) : Optional.empty();
    }

    public List<Lector> findAll() {
        return EntityManagerFactoryUtil
                .getEntityManager()
                .createQuery("SELECT e FROM Lector e ")
                .getResultList();
    }

    @Override
    public List<Lector> findAllBySubstring(String substring) {
        return EntityManagerFactoryUtil
                .getEntityManager()
                .createQuery("SELECT e FROM Lector e WHERE e.firstName LIKE :str  OR e.lastName LIKE :str")
                .setParameter("str", "%" + substring + "%").getResultList();
    }
}
