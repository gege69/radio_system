package br.com.radio.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.radio.validation.PasswordMatches;

@PasswordMatches
public class AlterarSenhaAmbienteDTO implements IPasswordMatch {
	
	@NotNull
	private String login;
	
	@NotNull
    @NotEmpty
    private String password;
	
	@NotNull
    @NotEmpty
    private String matchingPassword;

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

	public String getLogin()
	{
		return login;
	}

	public void setLogin( String login )
	{
		this.login = login;
	}
}
