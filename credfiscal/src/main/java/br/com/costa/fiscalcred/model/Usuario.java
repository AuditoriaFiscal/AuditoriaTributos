package br.com.costa.fiscalcred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CREDFISCAL.USUARIO")
public class Usuario {

	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="SENHA")
	private String senha;

	@Column(name="NOME")
	private String nome;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}