package rc.ac.singidunum.novisad.hrm.payroll_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rc.ac.singidunum.novisad.hrm.payroll_service.entity.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

}
