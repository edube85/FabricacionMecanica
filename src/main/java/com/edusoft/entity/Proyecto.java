package com.edusoft.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "proyectos")
public class Proyecto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String nombreTaller;
	
	private String nombre;

	private String descripcion;
	
	
	private int demora;
	
	private String oferta;
	
	private String albaran;
	
	private Double precio;
	
	private String solicitud;
	
	private String pedido;
	
	private String sscc;
	
	private String comentarios;
	
	private String estado;
	
	Date myDate = new Date();
	
	@NotNull
	@JsonFormat( pattern = "dd-MM-yyyy" )
	@Temporal(TemporalType.DATE)
	private Date fechaPed;
	@JsonFormat( pattern = "dd-MM-yyyy" )
	@Temporal(TemporalType.DATE)
	private Date fechaRec;
	@NotNull
	@JsonFormat( pattern = "dd-MM-yyyy" )
	@Temporal(TemporalType.DATE)
	private Date plazo;
	
	private String ofertaPdf;
	
	private String pedidoPdf;
	
	private String albaranPdf;
	
	private String solicitudPdf;
	
	private String ssccPdf;

	

	public Proyecto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	 public Proyecto(String nombre, Double precio) {
		    this.nombreTaller = nombre;
		    this.nombre= nombre;
		    this.precio  = precio;
    }

	public Proyecto(Long id, String nombreTaller, String nombre, String descripcion, int demora, String oferta,
			String albaran, Double precio, String solicitud, String pedido, String sscc, Date fechaPed, Date fechaRec,
			Date plazo,String ofertaPdf,String pedidoPdf, String albaranPdf, String solicitudPdf, String ssccPdf, String comentarios,String estado) {
		super();
		this.id = id;
		this.nombreTaller = nombreTaller;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.demora = demora;
		this.oferta = oferta;
		this.albaran = albaran;
		this.precio = precio;
		this.solicitud = solicitud;
		this.pedido = pedido;
		this.sscc = sscc;
		this.fechaPed = fechaPed;
		this.fechaRec = fechaRec;
		this.plazo = plazo;
		this.ofertaPdf=ofertaPdf;
		this.pedidoPdf=pedidoPdf;
		this.albaranPdf=albaranPdf;
		this.solicitudPdf=solicitudPdf;
		this.ssccPdf=ssccPdf;
		this.comentarios=comentarios;
		this.estado=estado;
	}






	public String getNombreTaller() {
		return nombreTaller;
	}

	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	public int getDemora() {
		
		Calendar cal = Calendar.getInstance();
		
		LocalDate fechaHoy = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
		LocalDate fechaAnterior = LocalDate.of(fechaPed.getYear()+1900, fechaPed.getMonth()+1, fechaPed.getDate());
		LocalDate fechaPlazo = LocalDate.of(plazo.getYear()+1900, plazo.getMonth()+1, plazo.getDate());
		
		int diferenciaDias = (int) ChronoUnit.DAYS.between(fechaAnterior, fechaHoy);
		int diferenciaPlazo = (int) ChronoUnit.DAYS.between(fechaAnterior, fechaPlazo);		
		
		if (fechaRec == null) {
		demora = diferenciaDias - diferenciaPlazo;	
		return diferenciaDias - diferenciaPlazo;
		
		}else {
			
			return demora;
		}
	}

	public void setDemora(int demora) {
		this.demora = demora;
	}

	public String getOferta() {
		return oferta;
	}

	public void setOferta(String oferta) {
		this.oferta = oferta;
	}

	public String getAlbaran() {
		return albaran;
	}

	public void setAlbaran(String albaran) {
		this.albaran = albaran;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getSscc() {
		return sscc;
	}

	public void setSscc(String sscc) {
		this.sscc = sscc;
	}



	public Date getFechaPed() {
		return fechaPed;
	}

	public void setFechaPed(Date fechaPed) {
		this.fechaPed = fechaPed;
	}

	public Date getFechaRec() {
		return fechaRec;
	}

	public void setFechaRec(Date fechaRec) {
		this.fechaRec = fechaRec;
	}

	public Date getPlazo() {
		return plazo;
	}

	public void setPlazo(Date plazo) {
		this.plazo = plazo;
	}


	public String getOfertaPdf() {
		return ofertaPdf;
	}


	public void setOfertaPdf(String ofertaPdf) {
		this.ofertaPdf = ofertaPdf;
	}


	public String getPedidoPdf() {
		return pedidoPdf;
	}


	public void setPedidoPdf(String pedidoPdf) {
		this.pedidoPdf = pedidoPdf;
	}


	public String getAlbaranPdf() {
		return albaranPdf;
	}






	public void setAlbaranPdf(String albaranPdf) {
		this.albaranPdf = albaranPdf;
	}


   



	public String getSolicitudPdf() {
		return solicitudPdf;
	}






	public void setSolicitudPdf(String solicitudPdf) {
		this.solicitudPdf = solicitudPdf;
	}





	public String getSsccPdf() {
		return ssccPdf;
	}






	public void setSsccPdf(String ssccPdf) {
		this.ssccPdf = ssccPdf;
	}






	public String getComentarios() {
		return comentarios;
	}




	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}



	

	public String getEstado() {
		return estado;
	}




	public void setEstado(String estado) {
		this.estado = estado;
	}




	@Override
	public String toString() {
		return "Proyecto [id=" + id + ", nombreTaller=" + nombreTaller + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", demora=" + demora + ", oferta=" + oferta + ", albaran=" + albaran + ", precio="
				+ precio + ", solicitud=" + solicitud + ", pedido=" + pedido + ", sscc=" + sscc + ", fechaPed="
				+ fechaPed + ", fechaRec=" + fechaRec + ", plazo=" + plazo + ", ofertaPdf=" + ofertaPdf + ", pedidoPdf="
				+ pedidoPdf + ", albaranPdf=" + albaranPdf + ", solicitudPdf=" + solicitudPdf + "]";
	}






	private static final long serialVersionUID = 1L;
}
