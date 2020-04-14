package fr.olaqin.pfd.service;

import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfd.repository.AnnuaireProfessionnelSanteRepository;
import fr.olaqin.pfd.service.validator.ValidatorPageDemandee;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AnnuaireProfessionnelSanteEnActiviteService {

    private final AnnuaireProfessionnelSanteRepository annuaireRepository;

    public Page<AnnuaireProfessionnelSanteEnActivite> getAll(Integer currentPage, Integer size) {
        final PageRequest pageable = PageRequest
                .of(currentPage, size);
        final Page<AnnuaireProfessionnelSanteEnActivite> page = annuaireRepository.findAll(pageable);

        new ValidatorPageDemandee()
                .setCurrentPage(currentPage)
                .setSize(size)
                .isSatisfiedBy(page);

        return page;
    }
}