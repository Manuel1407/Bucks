package com.decagon.reward_your_teacher.domain.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, ID> {

    Optional<T> findById(ID id);

    T getRecordById(ID id) throws RuntimeException;

    T saveRecord(T record);

    List<T> saveAllRecord(List<T> records);

    void deleteRecord(T record);

    List<T> getAllRecords();
}
