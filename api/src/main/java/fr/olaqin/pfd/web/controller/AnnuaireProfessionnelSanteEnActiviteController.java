package fr.olaqin.pfd.web.controller;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Deprecated
@ApiIgnore
@RestController
@RequestMapping("api/annuaire")
public class AnnuaireProfessionnelSanteEnActiviteController {

    @GetMapping(value = "/asipFile", produces = "application/zip")
    @Profile("dev")
    public void getFile(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"PS_LibreAcces_202001311337.zip\"");
        response.setContentType("application/zip");

        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

        ZipInputStream zip = new ZipInputStream(new ClassPathResource("static/PS_LibreAcces_202001311337.zip").getInputStream());
        ZipEntry ze;
        while ((ze = zip.getNextEntry()) != null) {
            ZipEntry zipEntry = new ZipEntry(ze.getName());
            zipOutputStream.putNextEntry(zipEntry);
            IOUtils.copy(zip, zipOutputStream);
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
    }

}
