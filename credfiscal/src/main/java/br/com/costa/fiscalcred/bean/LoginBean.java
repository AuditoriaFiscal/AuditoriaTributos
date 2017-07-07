package br.com.costa.fiscalcred.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Query;

import br.com.costa.fiscalcred.model.Usuario;
import br.com.costa.fiscalcred.service.LoginService;

@ManagedBean
@SessionScoped
public class LoginBean {

	@ManagedProperty("#{loginService}")
	private LoginService loginService;
	private Usuario usuario = new Usuario();

	private boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String recuperaUsuario() {
		return "TESTE";
	}

	public String redirectToLogin() {
		return "/login.xhtml?faces-redirect=true";
	}

	public String redirectToWelcome() {
		return "/secured/nfe.xhtml?faces-redirect=true";
	}

	@SuppressWarnings("unchecked")
	public String doLogin() {

		Query q = loginService.getEm()
				.createNativeQuery("SELECT u.* FROM USUARIO u WHERE u.usuario = ? and u.senha = ?", Usuario.class)
				.setParameter(1, usuario.getUsuario()).setParameter(2, usuario.getSenha());
		;
		List<Object> lista = q.getResultList();

		if (lista.size() > 0) {
			this.loggedIn = true;
			return redirectToWelcome();
		}else{
			FacesMessage msg = new FacesMessage("Usuário ou Senha inválido!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}

	}

	public String doLogout() {
		this.loggedIn = false;
		return redirectToLogin();
	}

}
