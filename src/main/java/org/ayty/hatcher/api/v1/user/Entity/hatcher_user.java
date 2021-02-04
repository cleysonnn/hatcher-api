package org.ayty.hatcher.api.v1.user.Entity;

import javax.persistence.*;

@Entity
public class hatcher_user {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO , generator = "User_generator")
	@SequenceGenerator(name = "User_generator", sequenceName = "User_sequence", allocationSize = 1)
	private long id;


	@Column(unique = true )
	private String login;
	@Column(unique = true )
	private String email;
	@Column(name = "nome")
	private String nomeCompleto;
	private boolean admin;
	private String senha;
	




	public  String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNomeCompleto() {
		return nomeCompleto;
	}


	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	





	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public hatcher_user() {
		super();
		// TODO Auto-generated constructor stub
	}


	public hatcher_user(long id, String login, String email, String nomeCompleto, boolean admin, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.nomeCompleto = nomeCompleto;
		this.admin = admin;
		this.senha = senha;
	}


	@Override
	public String toString() {
		return "hatcher_user [id=" + id + ", login=" + login + ", email=" + email + ", nomeCompleto=" + nomeCompleto
				+ ", admin=" + admin + ", senha=" + senha + "]";
	}


	


	
	
	
	
	

}
