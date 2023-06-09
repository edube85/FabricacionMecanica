package com.edusoft.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edusoft.entity.Proyecto;
import com.edusoft.entity.Taller;
import com.edusoft.models.service.IEstadoService;
import com.edusoft.models.service.IProyectoService;
import com.edusoft.models.service.ITallerService;
import com.edusoft.models.service.IUploadFileService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@SessionAttributes("proyecto")
public class ProyectoController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IProyectoService proyectoService;

    @Autowired
    private ITallerService tallerService;

    @Autowired
    private IUploadFileService uploadFileService;

    @Autowired
    private IEstadoService estadoService;

    private final static String UPLOADS_FOLDER = "C:/fileDownloadExample/";

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = {"/listar", "/"}, method = RequestMethod.GET)
    public String añadirProyecto(Model model, RedirectAttributes flash, SessionStatus status, Locale locale) {

        model.addAttribute("proyecto", proyectoService.findAll());
        model.addAttribute("estado", estadoService.findAll());

        return "listar";

    }

    @RequestMapping(value = "/solicitudes/form")
    public String crear(Map<String, Object> model, Locale locale, Model modelo) throws IOException {

        String dirName = "C:\\fileDownloadExample\\Solicitudes";

        File fileName = new File(dirName);
        String[] fileList = fileName.list();

        model.put("carpetas", fileList);

        modelo.addAttribute("taller", tallerService.findAll());
        modelo.addAttribute("estado", estadoService.findAll());
        Proyecto proyecto = new Proyecto();
        model.put("proyecto", proyecto);
        model.put("titulo", messageSource.getMessage("text.solicitud.form.titulo", null, locale));
        return "solicitudes/form";
    }

    @RequestMapping(value = "/solicitudes/form", method = RequestMethod.POST)
    public String guardar(@ModelAttribute("taller") Taller taller, @Valid Proyecto proyecto, BindingResult result,
            @RequestParam("fileOf") MultipartFile oferta, @RequestParam("fileAlb") MultipartFile albaran,
            @RequestParam("filePed") MultipartFile pedido, @RequestParam("fileSscc") MultipartFile sscc,
            RedirectAttributes flash, SessionStatus status, Locale locale,Map<String, Object> model) {

        if (result.hasErrors()) {
            flash.addFlashAttribute("titulo", messageSource.getMessage("text.solicitud.form.titulo", null, locale));
                    model.put("titulo", messageSource.getMessage("text.solicitud.form.titulo", null, locale));

            return "solicitudes/form";
        }

        try {
            if (!oferta.isEmpty()) {
                String originalFilename = oferta.getOriginalFilename();
                proyecto.setOfertaPdf(originalFilename);
                proyecto.setOferta(getFileNameWithoutExtension(originalFilename));
                flash.addFlashAttribute("ofertas",
                        messageSource.getMessage("text.solicitud.flash.archivo.subir.success", null, locale) + " ' "
                        + originalFilename + " ' ");
            }

            if (!albaran.isEmpty()) {
                String originalFilenameAlb = albaran.getOriginalFilename();
                proyecto.setAlbaranPdf(originalFilenameAlb);
                proyecto.setAlbaran(getFileNameWithoutExtension(originalFilenameAlb));
                flash.addFlashAttribute("info",
                        messageSource.getMessage("text.solicitud.flash.archivo.subir.success", null, locale) + " ' "
                        + originalFilenameAlb + " ' ");
            }

            if (!pedido.isEmpty()) {
                String originalFilenamePed = pedido.getOriginalFilename();
                proyecto.setPedidoPdf(originalFilenamePed);
                proyecto.setPedido(getFileNameWithoutExtension(originalFilenamePed));
                flash.addFlashAttribute("pedidos",
                        messageSource.getMessage("text.solicitud.flash.archivo.subir.success", null, locale) + " ' "
                        + originalFilenamePed + " ' ");
            }

            if (!sscc.isEmpty()) {
                String originalFilenameSscc = sscc.getOriginalFilename();
                proyecto.setSsccPdf(originalFilenameSscc);
                proyecto.setSscc(getFileNameWithoutExtension(originalFilenameSscc));
                flash.addFlashAttribute("ssccs",
                        messageSource.getMessage("text.solicitud.flash.archivo.subir.success", null, locale) + " ' "
                        + originalFilenameSscc + " ' ");
            }

            calcularDemora(proyecto);

            String mensajeFlash = (proyecto.getId() != null)
                    ? messageSource.getMessage("text.solicitud.flash.editar.success", null, locale)
                    : messageSource.getMessage("text.solicitud.flash.crear.success", null, locale);

            proyectoService.save(proyecto);

            status.setComplete();
            flash.addFlashAttribute("success", mensajeFlash);

            return "redirect:/";
        } catch (Exception e) {
            flash.addFlashAttribute("info", messageSource.getMessage("text.solicitud.flash.subirarchivo.error", null, locale));
            Long proyectoId = proyecto.getId();
            return (proyectoId != null) ? "redirect:/solicitudes/form/" + proyectoId : "redirect:/solicitudes/form/";
        }
    }

    @RequestMapping(value = "/solicitudes/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash, Locale locale, Model modelo) {

        Proyecto proyecto = null;
        String dirName = "C:\\fileDownloadExample\\Solicitudes";
        File fileName = new File(dirName);
        String[] fileList = fileName.list();

        if (id > 0) {

            proyecto = proyectoService.findOne(id);

            if (proyecto == null) {

                flash.addFlashAttribute("error", messageSource.getMessage("text.solicitud.flash.db.error", null, locale));

                return "redirect:/listar";
            }

        } else {

            flash.addFlashAttribute("error", messageSource.getMessage("text.solicitud.flash.id.success", null, locale));
            return "redirect:/listar";

        }
        
        

        model.put("carpetas", fileList);
        modelo.addAttribute("taller", tallerService.findAll());
        modelo.addAttribute("estado", estadoService.findAll());
        model.put("proyecto", proyecto);
        model.put("titulo", messageSource.getMessage("text.solicitud.form.titulo.editar", null, locale));

        return "solicitudes/form";
    }

    @RequestMapping(value = "/eliminarproy/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash, Locale locale) {

        if (id > 0) {

            proyectoService.delete(id);
            flash.addFlashAttribute("success", messageSource.getMessage("text.solicitud.flash.eliminar.success", null, locale));

        }

        return "redirect:/listar";

    }

    @RequestMapping(value = "/eliminarTodo")
    public String eliminarTodo(RedirectAttributes flash, Locale locale) {

        proyectoService.borrarTodo();

        flash.addFlashAttribute("success", messageSource.getMessage("text.solicitud.flash.eliminarTodo.success", null, locale));

        return "redirect:/listar";
    }

    private void deleteExistingFile(String filename) {
        if (StringUtils.hasText(filename)) {
            uploadFileService.delete(filename);
        }
    }

  

    private String getFileNameWithoutExtension(String filename) {
        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex != -1) {
            return filename.substring(0, extensionIndex);
        }
        return filename;
    }

    private void calcularDemora(Proyecto proyecto) {
        long milisecondsByDay = 86400000;

        if (proyecto.getFechaRec() != null && proyecto.getPlazo() != null) {
            long fechaRecMilis = proyecto.getFechaRec().getTime();
            long plazoMilis = proyecto.getPlazo().getTime();
            long diferenciaMilis = fechaRecMilis - plazoMilis;
            int demora = (int) (diferenciaMilis / milisecondsByDay);
            proyecto.setDemora(demora);
        }
    }

}
