package br.com.radio.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.radio.validation.PasswordMatches;

@PasswordMatches
public class AlterarSenhaDTO implements IPasswordMatch {
	
	@NotNull
    @NotEmpty
	private String senha_atual;
	
	@NotNull
    @NotEmpty
    private String password;
	
	@NotNull
    @NotEmpty
    private String matchingPassword;

	public String getSenha_atual()
	{
		return senha_atual;
	}

	public void setSenha_atual( String senha_atual )
	{
		this.senha_atual = senha_atual;
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

	
}
