package fr.olaqin.pfd.web.controller;

import fr.olaqin.pfd.api.controller.ProfessionnelsSanteEnActiviteApi;
import fr.olaqin.pfd.api.model.ApiAnnuaireProfessionnelSanteEnActivitePage;
import fr.olaqin.pfd.entity.AnnuaireProfessionnelSanteEnActivite;
import fr.olaqin.pfd.service.AnnuaireProfessionnelSanteEnActiviteService;
import fr.olaqin.pfd.web.mapper.ApiAnnuaireProfessionnelSanteEnActivitePageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/1.0")
public class ProfessionnelsSanteEnActiviteRest implements ProfessionnelsSanteEnActiviteApi {

    private final AnnuaireProfessionnelSanteEnActiviteService annuaireService;
    private final ApiAnnuaireProfessionnelSanteEnActivitePageMapper apiAnnuaireProfessionnelSanteEnActivitePageMapper;

    @Override
    public ResponseEntity<ApiAnnuaireProfessionnelSanteEnActivitePage> getAnnuaire(Integer page, Integer size) {
        final Page<AnnuaireProfessionnelSanteEnActivite> resultPage = annuaireService.getAll(page, size);
        return ResponseEntity.ok(apiAnnuaireProfessionnelSanteEnActivitePageMapper.toApi(resultPage));
    }
}
