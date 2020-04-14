package fr.olaqin.pfd.web;

import fr.olaqin.pfd.api.model.ApiUtilisateur;
import fr.olaqin.pfd.entity.Utilisateur;
import fr.olaqin.pfd.exception.ErrorCode;
import fr.olaqin.pfd.exception.common.NotFoundException;
import fr.olaqin.pfd.service.UtilisateurService;
import fr.olaqin.pfd.web.controller.UtilisateursRest;
import fr.olaqin.pfd.web.mapper.ApiUtilisateurMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UtilisateursRest.class)
class UtilisateursRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurService utilisateurService;

    @MockBean
    private ApiUtilisateurMapper apiUtilisateurMapper;

    @Value("classpath:controller/utilisateur.json")
    private Resource resourceApiUtilisateur;

    @Test
    public void findByEmailTest() throws Exception {

        final Utilisateur utilisateur = mock(Utilisateur.class);
        when(utilisateur.getEmail()).thenReturn("d.bras@olaqin.fr");
        when(utilisateurService.findByEmail(utilisateur.getEmail())).thenReturn(utilisateur);

        final ApiUtilisateur apiUtilisateur = getApiUtilisateur();
        when(apiUtilisateurMapper.toApi(utilisateur)).thenReturn(apiUtilisateur);


        mockMvc.perform(
                get("/api/1.0/utilisateurs/?email=".concat(utilisateur.getEmail()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$").isMap())

                .andExpect(jsonPath("$.utilisateurId").exists())
                .andExpect(jsonPath("$.utilisateurId").isString())
                .andExpect(jsonPath("$.utilisateurId", Matchers.is(apiUtilisateur.getId())))

                .andExpect(jsonPath("$.nom").isString())
                .andExpect(jsonPath("$.nom", Matchers.is(apiUtilisateur.getNom())))

                .andExpect(jsonPath("$.prenom").isString())
                .andExpect(jsonPath("$.prenom", Matchers.is(apiUtilisateur.getPrenom())))

                .andExpect(jsonPath("$.dateNaissance").isString())
                .andExpect(jsonPath("$.dateNaissance", Matchers.is(String.valueOf(apiUtilisateur.getDateNaissance()))))

                .andExpect(jsonPath("$.email").isString())
                .andExpect(jsonPath("$.email", Matchers.is(apiUtilisateur.getEmail())));

        verify(utilisateurService, times(1))
                .findByEmail(eq(utilisateur.getEmail()));
    }

    @Test
    public void findByEmail_NotFoundTest() throws Exception {

        doThrow(
                new NotFoundException(
                        ErrorCode.NOTFOUNDEXCEPTION.CODE_ERREUR_UTILISATEUR_AVEC_EMAIL,
                        ErrorCode.NOTFOUNDEXCEPTION.MESSAGE_ERREUR_UTILISATEUR_AVEC_EMAIL,
                        new Object[]{"mail.inexistant@olaqin.fr"}
                )
        ).when(utilisateurService).findByEmail("mail.inexistant@olaqin.fr");

        mockMvc.perform(
                get("/api/1.0/utilisateurs/?email=mail.inexistant@olaqin.fr")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());

        verify(utilisateurService, times(1))
                .findByEmail(eq("mail.inexistant@olaqin.fr"));
    }

    @Test
    public void lireTest() throws Exception {
        final Utilisateur utilisateur = mock(Utilisateur.class);
        when(utilisateur.getId()).thenReturn("745987e7-5276-4554-99b3-0dc6c7717897");
        when(utilisateurService.get(utilisateur.getId())).thenReturn(utilisateur);

        final ApiUtilisateur apiUtilisateur = getApiUtilisateur();
        when(apiUtilisateurMapper.toApi(utilisateur)).thenReturn(apiUtilisateur);


        mockMvc.perform(
                get("/api/1.0/utilisateurs/745987e7-5276-4554-99b3-0dc6c7717897")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$").isMap())

                .andExpect(jsonPath("$.utilisateurId").exists())
                .andExpect(jsonPath("$.utilisateurId").isString())
                .andExpect(jsonPath("$.utilisateurId", Matchers.is(apiUtilisateur.getId())))

                .andExpect(jsonPath("$.nom").isString())
                .andExpect(jsonPath("$.nom", Matchers.is(apiUtilisateur.getNom())))

                .andExpect(jsonPath("$.prenom").isString())
                .andExpect(jsonPath("$.prenom", Matchers.is(apiUtilisateur.getPrenom())))

                .andExpect(jsonPath("$.dateNaissance").isString())
                .andExpect(jsonPath("$.dateNaissance", Matchers.is(String.valueOf(apiUtilisateur.getDateNaissance()))))

                .andExpect(jsonPath("$.email").isString())
                .andExpect(jsonPath("$.email", Matchers.is(apiUtilisateur.getEmail())));

        verify(utilisateurService, times(1))
                .get(eq(utilisateur.getId()));
    }

    @Test
    public void lire_NotFoundTest() throws Exception {

        doThrow(new NotFoundException(
                ErrorCode.NOTFOUNDEXCEPTION.CODE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT,
                ErrorCode.NOTFOUNDEXCEPTION.MESSAGE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT,
                new Object[]{"UUID_INEXISTANT"}
        )).when(utilisateurService)
                .get("UUID_INEXISTANT");

        mockMvc.perform(
                get("/api/1.0/utilisateurs/UUID_INEXISTANT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());

        verify(utilisateurService, times(1))
                .get(eq("UUID_INEXISTANT"));
    }

    @Test
    public void modifierTest() throws Exception {
        final Utilisateur utilisateur = mock(Utilisateur.class);

        when(utilisateur.getId()).thenReturn("745987e7-5276-4554-99b3-0dc6c7717897");
        doNothing().when(utilisateurService).put(utilisateur);
        when(apiUtilisateurMapper.toModel(any(ApiUtilisateur.class))).thenReturn(utilisateur);

        final File file = resourceApiUtilisateur.getFile();
        final String body = new String(Files.readAllBytes(file.toPath()));
        mockMvc.perform(
                put("/api/1.0/utilisateurs/745987e7-5276-4554-99b3-0dc6c7717897")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(status().isNoContent());

        verify(utilisateurService, times(1))
                .put(eq(utilisateur));
    }

    @Test
    public void modifier_NotFoundTest() throws Exception {
        final Utilisateur utilisateur = mock(Utilisateur.class);
        when(utilisateur.getId()).thenReturn("UUID_INEXISTANT");
        doThrow(new NotFoundException(
                ErrorCode.NOTFOUNDEXCEPTION.CODE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT,
                ErrorCode.NOTFOUNDEXCEPTION.MESSAGE_ERREUR_UTILISATEUR_AVEC_IDENTIFIANT,
                new Object[]{"UUID_INEXISTANT"}
        )).when(utilisateurService)
                .put(utilisateur);

        when(apiUtilisateurMapper.toModel(any(ApiUtilisateur.class))).thenReturn(utilisateur);

        final File file = resourceApiUtilisateur.getFile();
        final String body = new String(Files.readAllBytes(file.toPath()));
        mockMvc.perform(
                put("/api/1.0/utilisateurs/UUID_INEXISTANT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(body)
        )
                .andExpect(status().isNotFound());

        verify(utilisateurService, times(1))
                .put(eq(utilisateur));
    }

    @Test
    public void modifier_BadRequestTest() throws Exception {

        mockMvc.perform(
                put("/api/1.0/utilisateurs/UUID_INEXISTANT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{BAD_REQUEST}")
        )
                .andExpect(status().isBadRequest());
    }

    private ApiUtilisateur getApiUtilisateur() {
        return new ApiUtilisateur()
                .id("745987e7-5276-4554-99b3-0dc6c7717897")
                .nom("BRAS")
                .prenom("David")
                .dateNaissance(LocalDate.of(1980, 01, 01))
                .email("d.bras@olaqin.fr");
    }
}