package com.decagon.swisspay.infrastructure.persistence.daoImpl;

import com.decagon.swisspay.domain.dao.TransactionDao;
import com.decagon.swisspay.domain.entities.StudentEntity;
import com.decagon.swisspay.domain.entities.TeacherEntity;
import com.decagon.swisspay.domain.entities.transact.TransactionEntity;
import com.decagon.swisspay.infrastructure.persistence.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionDaoImpl extends CrudDaoImpl<TransactionEntity, Long> implements TransactionDao {
    private final TransactionRepository transactionRepository;

    protected TransactionDaoImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<TransactionEntity> findTransactionEntitiesByStudentOrderByCreatedAtDesc(Pageable pageable, StudentEntity student) {
        return transactionRepository.findTransactionEntitiesByStudentOrderByCreatedAtDesc(pageable,student);
    }

    @Override
    public Page<TransactionEntity> findTransactionEntitiesByTeacherOrderByCreatedAtDesc(Pageable pageable, TeacherEntity teacher) {
        return transactionRepository.findTransactionEntitiesByTeacherOrderByCreatedAtDesc(pageable,teacher);
    }
}
