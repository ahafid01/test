package fr.olaqin.pfd.service;

import fr.olaqin.pfd.entity.Utilisateur;
import fr.olaqin.pfd.exception.common.NotFoundException;
import fr.olaqin.pfd.repository.UtilisateurRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class UtilisateurServiceTest {

    @Rule
    public ExpectedException thrownException = ExpectedException.none();
    @Mock
    private UtilisateurRepository utilisateurRepository;
    @InjectMocks
    private UtilisateurService utilisateurService;

    @Test
    public void getTest() {
        final Utilisateur utilisateur = mock(Utilisateur.class);
        when(utilisateurRepository.findById(anyString())).thenReturn(Optional.of(utilisateur));
        utilisateurService.get("745987e7-5276-4554-99b3-0dc6c7717897");
        verify(utilisateurRepository, times(1)).findById(eq("745987e7-5276-4554-99b3-0dc6c7717897"));
    }

    @Test(expected = NotFoundException.class)
    public void getTest_NotFound() {
        when(utilisateurRepository.findById(anyString())).thenReturn(Optional.empty());
        utilisateurService.get("UUID_INEXISTANT");
    }

    @Test
    public void findByEmailTest() {
        final String email = "d.bras@olaqin.fr";

        final Utilisateur utilisateur = mock(Utilisateur.class);
        when(utilisateurRepository.findByEmail(eq(email))).thenReturn(Optional.of(utilisateur));

        utilisateurService.findByEmail(email);

        verify(utilisateurRepository, times(1)).findByEmail(eq(email));
    }

    @Test(expected = NotFoundException.class)
    public void findByEmail_NotFoundTest() {
        when(utilisateurRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        utilisateurService.findByEmail("mail.inexistant@olaqin.fr");
    }

    @Test
    public void put_Test() {
        final Utilisateur utilisateur = mock(Utilisateur.class);
        when(utilisateur.getId()).thenReturn("745987e7-5276-4554-99b3-0dc6c7717897");
        when(utilisateurRepository.existsById(anyString())).thenReturn(true);
        utilisateurService.put(utilisateur);
        verify(utilisateurRepository, times(1)).save(eq(utilisateur));
    }

    @Test(expected = NotFoundException.class)
    public void put_NotFoundTest() {
        final Utilisateur utilisateur = mock(Utilisateur.class);
        when(utilisateur.getId()).thenReturn("UUID_INEXISTANT");
        when(utilisateurRepository.existsById(anyString())).thenReturn(false);
        utilisateurService.put(utilisateur);
    }
}