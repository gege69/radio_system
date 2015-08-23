package br.com.radio.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.radio.validation.PasswordMatches;
import br.com.radio.validation.ValidacaoEmail;

@PasswordMatches
public class UserDTO implements IPasswordMatch {
	
	@NotNull
    @NotEmpty
    private String nmUsuario;
     
    @NotNull
    @NotEmpty
    private String cdLogin;
     
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
     
    @ValidacaoEmail
    @NotNull
    @NotEmpty
    private String cdEmail;

	public String getNmUsuario()
	{
		return nmUsuario;
	}

	public void setNmUsuario( String nmUsuario )
	{
		this.nmUsuario = nmUsuario;
	}

	public String getCdLogin()
	{
		return cdLogin;
	}

	public void setCdLogin( String cdLogin )
	{
		this.cdLogin = cdLogin;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getMatchingPassword()
	{
		return matchingPassword;
	}

	public void setMatchingPassword( String matchingPassword )
	{
		this.matchingPassword = matchingPassword;
	}

	public String getCdEmail()
	{
		return cdEmail;
	}

	public void setCdEmail( String cdEmail )
	{
		this.cdEmail = cdEmail;
	}

	

}
