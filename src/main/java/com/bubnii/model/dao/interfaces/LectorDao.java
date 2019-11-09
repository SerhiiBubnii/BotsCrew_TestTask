package com.bubnii.model.dao.interfaces;

import com.bubnii.model.entity.Lector;

import java.util.List;

public interface LectorDao extends GeneralDAO<Lector, Integer> {
    List<Lector> findAllBySubstring(String substring);
}
