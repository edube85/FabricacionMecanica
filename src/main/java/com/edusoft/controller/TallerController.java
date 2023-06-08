package com.edusoft.controller;


import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edusoft.entity.Taller;
import com.edusoft.models.service.ITallerService;

@Controller
@SessionAttributes("talleres")
public class TallerController {
	
	
	@Autowired
	private ITallerService tallerService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@RequestMapping(value = { "talleres/listTalleres" }, method = RequestMethod.GET)
	public String listarTalleres( Model model) {
		
		model.addAttribute("talleres", tallerService.findAll());
		
		return "talleres/listTalleres";
		
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash,
			Locale locale) {
		Taller taller = null;
		if (id > 0) {
			taller = tallerService.findOne(id);
			if (taller == null) {
				flash.addFlashAttribute("error", messageSource.getMessage("text.taller.flash.db.error", null, locale));
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", messageSource.getMessage("text.taller.flash.id.error", null, locale));
			return "redirect:/listar";
		}
		
		model.put("taller", taller);
		model.put("titulo", messageSource.getMessage("text.taller.form.titulo.editar", null, locale));
		return "form";
	}
	
	
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Taller taller, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status, Locale locale) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", messageSource.getMessage("text.taller.form.titulo", null, locale));
			return "form";
		}
		
		
		String mensajeFlash = (taller.getId() != null)
				? messageSource.getMessage("text.taller.flash.editar.success", null, locale)
				: messageSource.getMessage("text.taller.flash.crear.success", null, locale);
				
				
		tallerService.save(taller);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model, Locale locale) {
		Taller taller = new Taller();
		model.put("taller", taller);
		model.put("titulo", messageSource.getMessage("text.taller.form.titulo.crear", null, locale));
		return "form";

	}
	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash, Locale locale) {

		if (id > 0) {
			
			tallerService.delete(id);
			flash.addFlashAttribute("success",
					messageSource.getMessage("text.taller.flash.eliminar.success", null, locale));
			
		}
		return "redirect:/listar";

	}

}
