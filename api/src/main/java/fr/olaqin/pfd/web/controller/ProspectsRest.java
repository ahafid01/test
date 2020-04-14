package fr.olaqin.pfd.web.controller;

import fr.olaqin.pfd.api.controller.ProspectsApi;
import fr.olaqin.pfd.api.model.ApiProfessionnelSanteProspect;
import fr.olaqin.pfd.api.model.ApiProfessionnelSanteProspectForm;
import fr.olaqin.pfd.entity.ProfessionnelSanteProspect;
import fr.olaqin.pfd.service.ProfessionnelSanteProspectService;
import fr.olaqin.pfd.web.mapper.ApiProfessionnelSanteProspectFormMapper;
import fr.olaqin.pfd.web.mapper.ApiProfessionnelSanteProspectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/1.0")
public class ProspectsRest implements ProspectsApi {

    private final ProfessionnelSanteProspectService professionnelSanteProspectService;
    private final ApiProfessionnelSanteProspectMapper apiProfessionnelSanteProspectMapper;
    private final ApiProfessionnelSanteProspectFormMapper apiProfessionnelSanteProspectFormMapper;

    @Override
    public ResponseEntity<ApiProfessionnelSanteProspect> findByProspectId(String prospectId) {
        final ProfessionnelSanteProspect professionnelSanteProspect = professionnelSanteProspectService.get(prospectId);
        final ApiProfessionnelSanteProspect apiProfessionnelSanteProspect = apiProfessionnelSanteProspectMapper.toApi(professionnelSanteProspect);
        return ResponseEntity.ok(apiProfessionnelSanteProspect);
    }

    @Override
    public ResponseEntity<List<ApiProfessionnelSanteProspect>> getAll() {
        final List<ProfessionnelSanteProspect> prospects = professionnelSanteProspectService.getAll();
        final List<ApiProfessionnelSanteProspect> apiProspects = apiProfessionnelSanteProspectMapper.toApi(prospects);
        return ResponseEntity.ok(apiProspects);
    }

    @Override
    public ResponseEntity<ApiProfessionnelSanteProspect> save(@Valid ApiProfessionnelSanteProspectForm apiProfessionnelSanteProspectForm) {
        final ProfessionnelSanteProspect professionnelSanteProspect = apiProfessionnelSanteProspectFormMapper.toModel(apiProfessionnelSanteProspectForm);
        professionnelSanteProspectService.checkAndSave(professionnelSanteProspect);

        final ApiProfessionnelSanteProspect apiProfessionnelSanteProspect = apiProfessionnelSanteProspectMapper.toApi(professionnelSanteProspect);

        final String uid = professionnelSanteProspect.getId();
        final URI location = ServletUriComponentsBuilder.fromPath("/prospects/{prospectId}").buildAndExpand(uid).toUri();

        return ResponseEntity.created(location).body(apiProfessionnelSanteProspect);
    }
}
