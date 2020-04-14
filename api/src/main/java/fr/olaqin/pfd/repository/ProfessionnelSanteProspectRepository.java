package fr.olaqin.pfd.repository;

import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ProfessionnelSanteProspectRepository extends CrudRepository<ProfessionnelSanteProspect, String> {

}
