package com.ofdi.soap.repository;

import com.ofdi.soap.models.OfdiExecutionControlModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfdiExecutionControlRepository extends CrudRepository<OfdiExecutionControlModel, String> {
}
