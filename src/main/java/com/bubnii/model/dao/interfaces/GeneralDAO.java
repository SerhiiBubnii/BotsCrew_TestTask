package com.bubnii.model.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface GeneralDAO<T, ID> {

    Optional<T> findByID(ID id);

    List<T> findAll();
}
