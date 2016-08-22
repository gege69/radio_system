package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import br.com.radio.json.JSONDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name="cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7625966973767359067L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_cliente", nullable = false )
	private Long idCliente;
	
	@NotNull( message = "O CNPJ do cliente é de preenchimento obrigatório" )
	@Column( name = "cnpj", nullable = false, length = 14 )
	private String cnpj;

	@Column( name = "codigo", columnDefinition = "TEXT" )
	@Length(min=5, max=80, message="O Código do Cliente deve ter entre 5 e 40 caracteres.")
	private String codigo;
	
	@NotNull( message = "A razão social do cliente é de preenchimento obrigatório" )
	@Length(min=5, max=80, message="A Razão Social do Cliente deve ter entre 5 e 80 caracteres.")
	@Column( name = "razaosocial", nullable = false, columnDefinition = "TEXT" )
	private String razaosocial;
	
	@Column( name = "nomefantasia", columnDefinition = "TEXT" )
	@Length(min=5, max=80, message="O Nome Fantasia do Cliente deve ter entre 5 e 80 caracteres.")
	private String nomefantasia;
	
	@Column( name = "dominio", columnDefinition = "TEXT" )
	@Length(min=5, max=80, message="O Domínio do Cliente deve ter entre 5 e 80 caracteres.")
	private String dominio;
	
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;
	
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataalteracao" )
	private Date dataAlteracao;
	
	@Email(message="Email inválido")
	@Column( name="email", columnDefinition = "TEXT")
	private String email;
	
	@Column( name="logradouro", columnDefinition = "TEXT")
	private String logradouro;
	
	@Column( name="cidade", columnDefinition = "TEXT")
	private String cidade;
	
	@Column( name="bairro", columnDefinition = "TEXT")
	private String bairro;
	
	@Column( name="estado", length = 2)
	private String estado;
	
	@Column( name="numero", length = 20)
	private String numero;
	
	@Column( name="cep", length=8 )
	private String cep;

	@Column( name="complemento", columnDefinition = "TEXT")
	private String complemento;
	
	@OneToMany( fetch = FetchType.EAGER, mappedBy="cliente" )
	@NotFound(action=NotFoundAction.IGNORE)
	@Fetch(FetchMode.SUBSELECT)
    private List<Telefone> telefones;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY, mappedBy="cliente" )
	private List<Ambiente> ambientes;
	
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY, mappedBy="cliente" )
	private List<Titulo> titulos;
	
	// Essa dia é a data padrão de pagamento do cliente todo mês
	@Column( name = "diavencimento")
	private Integer diaVencimento;
	
	// Lista com as condiçoes comerciais
	
	@Column( name = "ativo" )
	private Boolean ativo;

	@Column( name = "estabelecimento", length = 40 )
	private String estabelecimento;

		
	public Cliente()
	{
		super();
		this.ativo = false;
		this.dataCriacao = new Date();
		this.diaVencimento = 10;
	}

	public Long getIdCliente()
	{
		return idCliente;
	}

	public void setIdCliente( Long idCliente )
	{
		this.idCliente = idCliente;
	}

	public String getCnpj()
	{
		return cnpj;
	}

	public void setCnpj( String cnpj )
	{
		this.cnpj = cnpj;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	public String getRazaosocial()
	{
		return razaosocial;
	}

	public void setRazaosocial( String razaosocial )
	{
		this.razaosocial = razaosocial;
	}

	public String getNomefantasia()
	{
		return nomefantasia;
	}

	public void setNomefantasia( String nomefantasia )
	{
		this.nomefantasia = nomefantasia;
	}

	public String getDominio()
	{
		return dominio;
	}

	public void setDominio( String dominio )
	{
		this.dominio = dominio;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAlteracao()
	{
		return dataAlteracao;
	}

	public void setDataAlteracao( Date dataAlteracao )
	{
		this.dataAlteracao = dataAlteracao;
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}

	@Override
	public String toString()
	{
		return String.format( "Cliente [idCliente=%s, cnpj=%s, nomefantasia=%s]", idCliente, cnpj, nomefantasia );
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idCliente == null ) ? 0 : idCliente.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Cliente other = (Cliente) obj;
		if ( idCliente == null )
		{
			if ( other.idCliente != null )
				return false;
		}
		else if ( !idCliente.equals( other.idCliente ) )
			return false;
		return true;
	}

	public String getLogradouro()
	{
		return logradouro;
	}

	public void setLogradouro( String logradouro )
	{
		this.logradouro = logradouro;
	}

	public String getCidade()
	{
		return cidade;
	}

	public void setCidade( String cidade )
	{
		this.cidade = cidade;
	}

	public String getBairro()
	{
		return bairro;
	}

	public void setBairro( String bairro )
	{
		this.bairro = bairro;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado( String estado )
	{
		this.estado = estado;
	}

	public String getCep()
	{
		return cep;
	}

	public void setCep( String cep )
	{
		this.cep = cep;
	}

	public List<Telefone> getTelefones()
	{
		return telefones;
	}

	public void setTelefones( List<Telefone> telefones )
	{
		this.telefones = telefones;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public List<Ambiente> getAmbientes()
	{
		return ambientes;
	}

	public void setAmbientes( List<Ambiente> ambientes )
	{
		this.ambientes = ambientes;
	}

	public List<Titulo> getTitulos()
	{
		return titulos;
	}

	public void setTitulos( List<Titulo> titulos )
	{
		this.titulos = titulos;
	}

	public String getNumero()
	{
		return numero;
	}

	public void setNumero( String numero )
	{
		this.numero = numero;
	}

	public String getComplemento()
	{
		return complemento;
	}

	public void setComplemento( String complemento )
	{
		this.complemento = complemento;
	}

	public Integer getDiaVencimento()
	{
		return diaVencimento;
	}

	public void setDiaVencimento( Integer diaVencimento )
	{
		this.diaVencimento = diaVencimento;
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