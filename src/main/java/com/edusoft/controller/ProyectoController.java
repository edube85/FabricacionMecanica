package com.edusoft.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
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
import com.edusoft.models.dao.IProyectoDao;
import com.edusoft.models.service.IEstadoService;
import com.edusoft.models.service.IProyectoService;
import com.edusoft.models.service.ITallerService;
import com.edusoft.models.service.IUploadFileService;




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
	@RequestMapping(value = { "/listar", "/" }, method = RequestMethod.GET)
	public String añadirProyecto(Model model, RedirectAttributes flash, SessionStatus status, Locale locale) {

		model.addAttribute("proyecto", proyectoService.findAll());
		model.addAttribute("estado", estadoService.findAll());
		
		
		
		/*
		 * for (int i = 0; i < proyectoService.demora().toArray().length; i++) {
		 * System.out.println(proyectoService.demora().toString()); ((Map<String,
		 * Object>) model).put("demora", messageSource.getMessage("text.login.demora",
		 * null, locale) + " " + proyectoService.demora()); }
		 * 
		 * int x = (int) (new Date().getTime()/1000)-1; int j = (int) (new
		 * Date().getTime()/1000); for ( x = (int) (new Date().getTime()/1000)-1 ; x <
		 * j; x++) { System.out.println(x); }
		 */
		
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
	    Proyecto proyecto= new Proyecto();
		model.put("proyecto", proyecto);
		model.put("titulo", messageSource.getMessage("text.solicitud.form.titulo", null, locale));
		return "solicitudes/form";
	}
	
	
	
	@RequestMapping(value = "/solicitudes/form", method = RequestMethod.POST)
	public String guardar( Taller taller,@Valid Proyecto proyecto,  BindingResult result, Model model,
			@RequestParam("fileOf") MultipartFile oferta,@RequestParam("fileAlb") MultipartFile albaran,
			@RequestParam("filePed") MultipartFile pedido, 
			@RequestParam("fileSscc") MultipartFile sscc,
			RedirectAttributes flash, SessionStatus status, Locale locale) {
		    
		
		String uniqueFilename = null;
		String uniqueFilenameAlb = null;
		String uniqueFilenamePed = null;
		String uniqueFilenameSscc = null;
		model.addAttribute("taller", tallerService.findAll());	
		
		try {
			
		  if (result.hasErrors()) { model.addAttribute("titulo",
		  messageSource.getMessage("text.solicitud.form.titulo", null, locale)); return
		  "solicitudes/form"; }
		  
		  if (proyecto.getPrecio() == null) {
			  proyecto.setPrecio((double) 0);
		  }
		  
		  
         
		  
		  if (!oferta.isEmpty()) {
				if (proyecto.getId() != null && proyecto.getId() > 0 && proyecto.getOfertaPdf() != null 
						&& proyecto.getOfertaPdf().length() > 0) {
					uploadFileService.delete(proyecto.getOfertaPdf());

				}
				
				try {
				 
					 uniqueFilename = uploadFileService.copy(oferta,"Solicitudes/" + proyecto.getSolicitud());
					
					} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("ofertas",
						messageSource.getMessage("text.solicitud.flash.archivo.subir.success", null, locale) + " ' "
								+ uniqueFilename + " ' ");
				proyecto.setOfertaPdf(uniqueFilename);
				proyecto.setOferta(proyecto.getOfertaPdf().substring(0, proyecto.getOfertaPdf().lastIndexOf('.')));
			}
		  
		  
		  
		  if (!albaran.isEmpty()) {
				if (proyecto.getId() != null && proyecto.getId() > 0 && proyecto.getAlbaranPdf() != null 
						 && proyecto.getAlbaranPdf().length() > 0 ) {
					uploadFileService.delete(proyecto.getAlbaranPdf());
					
	               
				}
				
				try {
				    Files.createDirectories(Paths.get(UPLOADS_FOLDER.concat("Albaranes/"+proyecto.getNombreTaller())));

					uniqueFilenameAlb = uploadFileService.copy(albaran, "Albaranes/"+proyecto.getNombreTaller());
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info",
						messageSource.getMessage("text.solicitud.flash.archivo.subir.success", null, locale) + " ' "
								+ uniqueFilenameAlb + " ' ");
				proyecto.setAlbaranPdf(uniqueFilenameAlb);
				proyecto.setAlbaran(proyecto.getAlbaranPdf().substring(0, proyecto.getAlbaranPdf().lastIndexOf('.')));

			}
		  
		  
		  
		  if (!pedido.isEmpty()) {
				if (proyecto.getId() != null && proyecto.getId() > 0 && proyecto.getPedidoPdf() != null 
						 && proyecto.getPedidoPdf().length() > 0 ) {
					uploadFileService.delete(proyecto.getPedidoPdf());

				}
				
				try {

					uniqueFilenamePed = uploadFileService.copy(pedido, "Solicitudes/"+ proyecto.getSolicitud());
	
					flash.addFlashAttribute("pedidos",
						messageSource.getMessage("text.solicitud.flash.archivo.subir.success", null, locale) + " ' "
								+ uniqueFilenamePed + " ' ");
					
					} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
				proyecto.setPedidoPdf(uniqueFilenamePed);
				proyecto.setPedido(proyecto.getPedidoPdf().substring(0, proyecto.getPedidoPdf().lastIndexOf('.')));

			}
		  
			 
		  
		  if (!sscc.isEmpty()) {
			  
				if (proyecto.getId() != null && proyecto.getId() > 0 && proyecto.getSsccPdf() != null && proyecto.getSsccPdf().length() > 0 ) {
						 					
					uploadFileService.delete(proyecto.getSsccPdf());

				}
				
				try {
					
					uniqueFilenameSscc = uploadFileService.copy(sscc, "Solicitudes/"+ proyecto.getSolicitud());
	
					} catch (IOException e) {
						
					e.printStackTrace();
				}
				
				flash.addFlashAttribute("ssccs",
						messageSource.getMessage("text.solicitud.flash.archivo.subir.success", null, locale) + " ' " + uniqueFilenameSscc + " ' ");
								
				proyecto.setSsccPdf(uniqueFilenameSscc);
				proyecto.setSscc(proyecto.getSsccPdf().substring(0, proyecto.getSsccPdf().lastIndexOf('.')));

			}
		  
 
		  int milisecondsByDay = 86400000;
		  
	    if (proyecto.getFechaRec() == null) {
	    	
	    	proyecto.getDemora();
	    		    	
	    }else {
	    	
	    	if(proyecto.getFechaRec().getTime() < proyecto.getPlazo().getTime()) {
	    		
	    	   int demora = (int) ((proyecto.getFechaRec().getTime()-proyecto.getPlazo().getTime() )/milisecondsByDay);
	    	   proyecto.setDemora((int) (demora));
	    	   
	    	}else {
	    		
	    		int demora =  (int) ((proyecto.getFechaRec().getTime()-proyecto.getPlazo().getTime() )/milisecondsByDay);
		    	proyecto.setDemora((int) (demora));
		    	   
	    	}
	    }
	    		  
		String mensajeFlash = (proyecto.getId() != null)
				? messageSource.getMessage("text.solicitud.flash.editar.success", null, locale)
				: messageSource.getMessage("text.solicitud.flash.crear.success", null, locale);
		
		
		proyectoService.save(proyecto);

		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/";
		
		}catch (Exception e){
			
			if(proyecto.getId() == null) {
				
				flash.addFlashAttribute("info", messageSource.getMessage("text.solicitud.flash.subirarchivo.error", null, locale));
				
				return "redirect:/solicitudes/form/";
				
			}
			
			flash.addFlashAttribute("info", messageSource.getMessage("text.solicitud.flash.subirarchivo.error", null, locale));
					
			return "redirect:/solicitudes/form/" + proyecto.getId();
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
	

}
