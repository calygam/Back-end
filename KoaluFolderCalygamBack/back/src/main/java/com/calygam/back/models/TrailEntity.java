package com.calygam.back.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_trail")
public class TrailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_trail")
	private Integer idTrail;
	
	@Column(name="nome_trail")
	private String nomeTrail;
	
	@Column(name="descricao_Trail")
	private String descricaoTrail;
	@Column(name="password_Trail")
	private String passwordTrail;

	public Integer getIdTrail() {
		return idTrail;
	}

	public void setIdTrail(Integer idTrail) {
		this.idTrail = idTrail;
	}

	public String getNomeTrail() {
		return nomeTrail;
	}

	public void setNomeTrail(String nomeTrail) {
		this.nomeTrail = nomeTrail;
	}

	public TrailEntity(Integer idTrail, String nomeTrail, String descricaoTrail) {
		super();
		this.idTrail = idTrail;
		this.nomeTrail = nomeTrail;
		this.descricaoTrail = descricaoTrail;
	}

	
	
	
	
	
	
}
