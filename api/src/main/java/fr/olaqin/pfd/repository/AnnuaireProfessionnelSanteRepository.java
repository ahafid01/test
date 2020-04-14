package fr.olaqin.pfd.repository;

import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@EnableScan
@EnableScanCount
public interface AnnuaireProfessionnelSanteRepository extends PagingAndSortingRepository<AnnuaireProfessionnelSanteEnActivite, String> {

    List<AnnuaireProfessionnelSanteEnActivite> findAllByNomExerciceSearchAndPrenomExerciceSearch(String nom, String prenon);
    AnnuaireProfessionnelSanteEnActivite findByNomExerciceSearch(String nom);
    AnnuaireProfessionnelSanteEnActivite findByNomExercice(String nom);
}
