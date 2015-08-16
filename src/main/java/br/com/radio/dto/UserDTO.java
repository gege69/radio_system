package br.com.radio.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.radio.validation.PasswordMatches;
import br.com.radio.validation.ValidacaoEmail;

@PasswordMatches
public class UserDTO {
	
	@NotNull
    @NotEmpty
    private String nm_usuario_usu;
     
    @NotNull
    @NotEmpty
    private String cd_login_usu;
     
    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;
     
    @ValidacaoEmail
    @NotNull
    @NotEmpty
    private String cd_email_usu;

	public String getNm_usuario_usu()
	{
		return nm_usuario_usu;
	}

	public void setNm_usuario_usu( String nm_usuario_usu )
	{
		this.nm_usuario_usu = nm_usuario_usu;
	}

	public String getCd_login_usu()
	{
		return cd_login_usu;
	}

	public void setCd_login_usu( String cd_login_usu )
	{
		this.cd_login_usu = cd_login_usu;
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

	public String getCd_email_usu()
	{
		return cd_email_usu;
	}

	public void setCd_email_usu( String cd_email_usu )
	{
		this.cd_email_usu = cd_email_usu;
	}
    
    

}
