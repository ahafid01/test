package fr.olaqin.pfd.web.controller;

import fr.olaqin.pfd.api.controller.UtilisateursApi;
import fr.olaqin.pfd.api.model.ApiUtilisateur;
import fr.olaqin.pfd.entity.Utilisateur;
import fr.olaqin.pfd.service.UtilisateurService;
import fr.olaqin.pfd.web.mapper.ApiUtilisateurMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/1.0")
public class UtilisateursRest implements UtilisateursApi {

    private final UtilisateurService utilisateurService;
    private final ApiUtilisateurMapper apiUtilisateurMapper;

    @Override
    public ResponseEntity<ApiUtilisateur> findByEmail(String email) {
        final Utilisateur utilisateur = utilisateurService.findByEmail(email);
        final ApiUtilisateur apiUtilisateur = apiUtilisateurMapper.toApi(utilisateur);
        return ResponseEntity.ok(apiUtilisateur);
    }

    @Override
    public ResponseEntity<ApiUtilisateur> findByUtilisateurId(String utilisateurId) {
        final Utilisateur utilisateur = utilisateurService.get(utilisateurId);
        final ApiUtilisateur apiUtilisateur = apiUtilisateurMapper.toApi(utilisateur);
        return ResponseEntity.ok(apiUtilisateur);
    }

    @Override
    public ResponseEntity<Void> put(String utilisateurId, @Valid ApiUtilisateur apiUtilisateur) {
        final Utilisateur utilisateur = apiUtilisateurMapper.toModel(apiUtilisateur);
        utilisateur.setId(utilisateurId);
        utilisateurService.put(utilisateur);
        return ResponseEntity.noContent().build();
    }

}
