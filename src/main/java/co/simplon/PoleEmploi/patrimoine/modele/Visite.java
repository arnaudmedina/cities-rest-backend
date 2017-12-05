package co.simplon.PoleEmploi.patrimoine.modele;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VISITS")
@NamedQueries({
	@NamedQuery(name = "Visite.findAll", query = " SELECT v FROM Visite v ORDER BY v.id "),
	@NamedQuery(name = "Visite.findAllByMonumentId", query = " SELECT v FROM Visite v JOIN v.monument m WHERE m.identifiant = :id"),
	@NamedQuery(name = "Visite.deleteById", query = " DELETE FROM Visite v WHERE v.id = :id") })
public class Visite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "DATE_VISITE")
	private Date dateVisite;

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MONUMENT_ID")
	private Monument monument;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(Date dateVisite) {
		this.dateVisite = dateVisite;
	}

	public Monument getMonument() {
		return monument;
	}

	public void setMonument(Monument monument) {
		this.monument = monument;
	}
}
