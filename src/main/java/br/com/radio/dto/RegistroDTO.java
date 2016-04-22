package br.com.radio.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.radio.validation.PasswordMatches;
import br.com.radio.validation.ValidacaoEmail;

@PasswordMatches
public class RegistroDTO implements IPasswordMatch {
	
	@NotNull
    @NotEmpty
	@Length(min=5, max=80, message="A Razão Social do Cliente deve ter entre 5 e 80 caracteres.")
    private String nmEmpresa;

	@NotNull
    @NotEmpty
	@Length(min=11, max=18, message="O CNPJ (ou CPF) do Cliente deve ter entre 15 e 18 caracteres.")
    private String cdCNPJCPF;

	@NotNull
    @NotEmpty
	@Length(min=5, max=80, message="O nome do Usuário deve ter entre 5 e 80 caracteres.")
    private String nmUsuario;
     
    @NotNull
    @NotEmpty
	@Length(min=5, max=40, message="O login do Usuário deve ter entre 5 e 40 caracteres.")
    private String cdLogin;
     
    @NotNull
    @NotEmpty
	@Length(min=7, max=40, message="A senha do Usuário deve ter entre no mínimo 7 caracteres.")
    private String password;
    private String matchingPassword;
     
    @ValidacaoEmail
    @NotNull
    @NotEmpty
    private String cdEmail;

    private String estabelecimento;

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

	public String getNmEmpresa()
	{
		return nmEmpresa;
	}

	public void setNmEmpresa( String nmEmpresa )
	{
		this.nmEmpresa = nmEmpresa;
	}

	public String getCdCNPJCPF()
	{
		if ( cdCNPJCPF != null )
			return cdCNPJCPF.replaceAll("\\D+","");
		else
			return null;
	}

	public void setCdCNPJCPF( String cdCNPJCPF )
	{
		this.cdCNPJCPF = cdCNPJCPF;
	}

	public String getEstabelecimento()
	{
		return estabelecimento;
	}

	public void setEstabelecimento( String estabelecimento )
	{
		this.estabelecimento = estabelecimento;
	}

	

}
