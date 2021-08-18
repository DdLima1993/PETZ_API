package br.com.petz.model;

import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pet")
public class Pet {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column
	@NotNull
	@Size(min=3, max = 50)
	private String nome;
	
	@Column
	private String cor;
	
	@Column
	private String genero;
	
	@Column
	@NotNull
	private String especie;
	
	@Column
	private String observacao;
	
	private Long cliente;
	
	
	public Pet() {
		
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}




	public Long getCodigo() {
		return codigo;
	}




	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getCor() {
		return cor;
	}




	public void setCor(String cor) {
		this.cor = cor;
	}




	public String getGenero() {
		return genero;
	}




	public void setGenero(String genero) {
		this.genero = genero;
	}




	public String getEspecie() {
		return especie;
	}




	public void setEspecie(String especie) {
		this.especie = especie;
	}




	public String getObservacao() {
		return observacao;
	}




	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}




	public Long getCliente() {
		return cliente;
	}




	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	
}
