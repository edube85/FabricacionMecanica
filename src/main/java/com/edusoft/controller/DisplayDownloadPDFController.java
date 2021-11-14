package com.edusoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edusoft.entity.Proyecto;
import com.edusoft.models.service.IProyectoService;


@Controller
@EnableAutoConfiguration
public class DisplayDownloadPDFController{

	
	@Autowired
	private IProyectoService proyectoService;
  
	private static final String FILE_PATH = "C:/fileDownloadExample/";
	
    @RequestMapping(value = "/ofertas/{id}", method = RequestMethod.GET)
    public String getFile(HttpServletResponse response,@PathVariable(value = "id") Long id,Map<String, Object> model,RedirectAttributes redirectAttrs) throws IOException {
     
    	try {
    	Proyecto proyecto = proyectoService.findOne(id);
 		 model.put("proyecto", proyecto);
    	 File file = new File(FILE_PATH);

    	
         response.setContentType("application/pdf");
         //Si se quiere descargar archivo
         //response.setHeader("Content-Disposition", "attachment;filename=" + file.getName() +"/"+ proyecto.getOfertaPdf().toString());
         BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file + "/" + "Solicitudes/"+proyecto.getSolicitud()+"/" + proyecto.getOfertaPdf().toString()));
         BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
         
         byte[] buffer = new byte[1024];
         int bytesRead = 0;
         while ((bytesRead = inStrem.read(buffer)) != -1) {
           outStream.write(buffer, 0, bytesRead);
         }
         outStream.flush();
         inStrem.close();
    	
      }catch(IOException ex) {
          System.err.println("An IOException was caught!");
          ex.printStackTrace();
          redirectAttrs
          .addFlashAttribute("mensaje", "Error, no existe el archivo en el directorio.");
 		 return "redirect:/";
      }
      
		return null;
		
      }
      
    
    @RequestMapping(value = "/albaranes/{id}", method = RequestMethod.GET)
    public String getFileAlbaran(HttpServletResponse response,@PathVariable(value = "id") Long id,Map<String, Object> model,RedirectAttributes redirectAttrs) throws IOException {
      
    	
    	try {
    	Proyecto proyecto = proyectoService.findOne(id);
 		 model.put("proyecto", proyecto);
    	 File file = new File(FILE_PATH);
    	
         response.setContentType("application/pdf");
         //response.setHeader("Content-Disposition", "attachment;filename=" + file.getName() +"/"+ proyecto.getAlbaranPdf().toString());
         BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file + "/" + "Albaranes/"+proyecto.getNombreTaller()+"/" +proyecto.getAlbaranPdf().toString()));
         BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
         
         byte[] buffer = new byte[1024];
         int bytesRead = 0;
         while ((bytesRead = inStrem.read(buffer)) != -1) {
           outStream.write(buffer, 0, bytesRead);
         }
         outStream.flush();
         inStrem.close();
    	}catch(IOException ex) {
         
             System.err.println("An IOException was caught!");
             ex.printStackTrace();
             redirectAttrs
             .addFlashAttribute("mensaje", "Error, no existe el archivo en el directorio.");
    		 return "redirect:/";
         }
         
   		return null;
   		
         }
    
    @RequestMapping(value = "/pedidos/{id}", method = RequestMethod.GET)
    public String getFilePedido(HttpServletResponse response,@PathVariable(value = "id") Long id,Map<String, Object> model,RedirectAttributes redirectAttrs) throws IOException {
      
    	try {
    	Proyecto proyecto = proyectoService.findOne(id);
 		 model.put("proyecto", proyecto);
    	 File file = new File(FILE_PATH);
    	// if (proyecto.getPedidoPdf()!=null ) {
         response.setContentType("application/pdf");
         //response.setHeader("Content-Disposition", "attachment;filename=" + file.getName() +"/"+ proyecto.getPedidoPdf().toString());
         BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file + "/" + "Solicitudes/"+proyecto.getSolicitud()+"/" +proyecto.getPedidoPdf().toString()));
         BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
         
         byte[] buffer = new byte[1024];
         int bytesRead = 0;
         while ((bytesRead = inStrem.read(buffer)) != -1) {
           outStream.write(buffer, 0, bytesRead);
         }
         outStream.flush();
         inStrem.close();
         
    	 }catch(IOException ex) {
             System.err.println("An IOException was caught!");
             ex.printStackTrace();
             redirectAttrs
             .addFlashAttribute("mensaje", "Error, no existe el archivo en el directorio.");
    		 return "redirect:/";
         }
         
   		return null;
   		
         }
    
    @RequestMapping(value = "/solicitudesPdf/{id}", method = RequestMethod.GET)
    public String getFileSolicitud(HttpServletResponse response,@PathVariable(value = "id") Long id,Map<String, Object> model,RedirectAttributes redirectAttrs) throws IOException {
      
    	try {
    	Proyecto proyecto = proyectoService.findOne(id);
 		 model.put("proyecto", proyecto);
    	 File file = new File(FILE_PATH);
         
    	 //if (proyecto.getSolicitudPdf()!=null ) {
         response.setContentType("application/pdf");
         
         //response.setHeader("Content-Disposition", "attachment;filename=" + file.getName() +"/"+ proyecto.getSolicitudPdf().toString());
         //Para subir pdf solicitudes (desactivado)
         //BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file + "/" + "Solicitudes/"+proyecto.getSolicitud()+"/" + proyecto.getSolicitudPdf().toString()));

         BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file + "/" + "Solicitudes/"+proyecto.getSolicitud()+"/" + proyecto.getSolicitud()+".pdf"));
         BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
         
         byte[] buffer = new byte[1024];
         int bytesRead = 0;
         while ((bytesRead = inStrem.read(buffer)) != -1) {
           outStream.write(buffer, 0, bytesRead);
         }
         outStream.flush();
         inStrem.close();
         
    	 }catch(IOException ex) {
             System.err.println("An IOException was caught!");
             ex.printStackTrace();
             redirectAttrs
             .addFlashAttribute("mensaje", "Error, no existe el archivo en el directorio.");
    		 return "redirect:/";
         }
         
   		return null;
   		
         }
    
    @RequestMapping(value = "/sscc/{id}", method = RequestMethod.GET)
    public String getFileSscc(HttpServletResponse response,@PathVariable(value = "id") Long id,Map<String, Object> model,RedirectAttributes redirectAttrs) throws IOException {
      try {
    	Proyecto proyecto = proyectoService.findOne(id);
 		 model.put("proyecto", proyecto);
    	 File file = new File(FILE_PATH);
    	 //if (proyecto.getSsccPdf()!=null ) {
         response.setContentType("application/pdf");
         //response.setHeader("Content-Disposition", "attachment;filename=" + file.getName() +"/"+ proyecto.getSsccPdf().toString());
         BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file + "/" + "Solicitudes/"+proyecto.getSolicitud()+"/" +proyecto.getSsccPdf().toString()));
         BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
         
         byte[] buffer = new byte[1024];
         int bytesRead = 0;
         while ((bytesRead = inStrem.read(buffer)) != -1) {
           outStream.write(buffer, 0, bytesRead);
         }
         outStream.flush();
         inStrem.close();
    	 }catch(IOException ex) {
             System.err.println("An IOException was caught!");
             ex.printStackTrace();
             redirectAttrs
             .addFlashAttribute("mensaje", "Error, no existe el archivo en el directorio.");
    		 return "redirect:/";
         }
         
   		return null;
   		
         }
    
    
    
    }

	
    
