/* OCS - Oracle Consulting Services - Middleware
 *
 * Data de criação 03/12/2018
 * Autor: lucas.dos@oracle.com
 *
 * Projeto: GPA OFDI
 *
 *
 */
package com.ofdi.soap.repository;

import com.ofdi.soap.models.db.OfdiExecutionControlModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfdiExecutionControlRepository extends CrudRepository<OfdiExecutionControlModel, String> {
}
