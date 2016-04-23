package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * 
 * Representa o ambiente onde o Player executa ( a loja ou mercado )
 * 
 * TODO: Controle de inativação e extrato de período quando esteve ativo ou não...
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="ambiente")
public class Ambiente implements Serializable {

	private static final long serialVersionUID = -703457623897298000L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_ambiente", nullable = false )
	private Long idAmbiente;

	@NotNull( message = "O nome do Ambiente é de preenchimento obrigatório" )
	@Column( name = "nome", nullable = false, length = 200 )
	private String nome;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn( name="id_cliente", nullable=false )
	private Cliente cliente;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, mappedBy= "ambiente")
	private AmbienteConfiguracao configuracao;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY, mappedBy= "ambiente")
	private Bloco bloco;

	@Column( name = "telefone1" )
	private String telefone1;
	
	@Column( name = "telefone2" )
	private String telefone2;
	
	@Column( name = "email1", nullable = true )
	private String email1;
	
	@Column( name = "email2" )
	private String email2;
	
	@Column( name = "anotacoes", columnDefinition = "TEXT" )
	private String anotacoes;

	@Column( name = "logradouro", columnDefinition = "TEXT" )
	private String logradouro;

	@Column( name = "numero", length = 20 )
	private String numero;
	
	@Column( name = "bairro", columnDefinition = "TEXT" )
	private String bairro;

	@Column( name = "cidade", columnDefinition = "TEXT" )
	private String cidade;

	@Column( name = "estado", length = 200 )
	private String estado;
	
	@Length( max = 8, message = "O campo de CEP só pode ter no máximo 8 caracteres.")
	@Column( name = "cep", length = 8)
	private String cep;
	
	@NotNull( message = "O nome de login é de preenchimento obrigatório" )
	@Column( name = "login", nullable = false, length = 40, unique=true )
	private String login;
	
	@JsonIgnore
	@NotNull( message = "A senha é de preenchimento obrigatório" )
	@Column( name = "password", length = 200 )
	private String password;
	
	@Column( name="urlambiente", columnDefinition = "TEXT" )
	private String urlambiente;
	
	@Column( name = "opcionais" )
	private Boolean opcionais;

	@Column( name = "sincronizar" )
	private Boolean sincronizar;

	@Column( name = "download" )
	private Boolean download;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação do Ambiente é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;
	
	@OneToOne
	@JoinColumn(name = "id_usuario" )
	private Usuario usuarioCriacao;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataalteracao" )
	private Date dataAlteracao;

	@OneToOne
	@JoinColumn(name="id_fusohorario")
	private FusoHorario fusoHorario;
	
	@Column( name = "horaIniExpediente" )
	private Integer horaIniExpediente;
	
	@Column( name = "minutoIniExpediente" )
	private Integer minutoIniExpediente;
	
	@Column( name = "horaFimExpediente" )
	private Integer horaFimExpediente;
	
	@Column( name = "minutoFimExpediente" )
	private Integer minutoFimExpediente;

	@JsonIgnore
	@Column( name = "logomarca" )
	private byte[] logomarca;
	
	@JsonIgnore
	@Column( name = "logomimetype")
	private String logomimetype;
	
	@Column( name = "ativo", columnDefinition = "BOOL default true")
	private Boolean ativo;
	
	
	// REST
	@Transient
	private Map<String,String> ambienteAPI = new HashMap<String,String>();
	
	
	

	public Ambiente()
	{
		super();
		this.opcionais = false;
		this.sincronizar = false;
		this.download = false;
		this.dataCriacao = new Date();
		this.ativo = true;
	}

	public Ambiente( Long idAmbiente )
	{
		super();
		this.idAmbiente = idAmbiente;
	}

	public Long getIdAmbiente()
	{
		return idAmbiente;
	}

	public void setIdAmbiente( Long idAmbiente )
	{
		this.idAmbiente = idAmbiente;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}


	public String getTelefone1()
	{
		return telefone1;
	}


	public void setTelefone1( String telefone1 )
	{
		this.telefone1 = telefone1;
	}


	public String getTelefone2()
	{
		return telefone2;
	}


	public void setTelefone2( String telefone2 )
	{
		this.telefone2 = telefone2;
	}


	public String getEmail1()
	{
		return email1;
	}


	public void setEmail1( String email1 )
	{
		this.email1 = email1;
	}


	public String getEmail2()
	{
		return email2;
	}


	public void setEmail2( String email2 )
	{
		this.email2 = email2;
	}


	public String getAnotacoes()
	{
		return anotacoes;
	}


	public void setAnotacoes( String anotacoes )
	{
		this.anotacoes = anotacoes;
	}


	public String getLogradouro()
	{
		return logradouro;
	}


	public void setLogradouro( String logradouro )
	{
		this.logradouro = logradouro;
	}


	public String getNumero()
	{
		return numero;
	}


	public void setNumero( String numero )
	{
		this.numero = numero;
	}


	public String getBairro()
	{
		return bairro;
	}


	public void setBairro( String bairro )
	{
		this.bairro = bairro;
	}


	public String getCidade()
	{
		return cidade;
	}


	public void setCidade( String cidade )
	{
		this.cidade = cidade;
	}


	public String getEstado()
	{
		return estado;
	}


	public void setEstado( String estado )
	{
		this.estado = estado;
	}


	public String getLogin()
	{
		return login;
	}


	public void setLogin( String login )
	{
		this.login = login;
	}

	@JsonIgnore
	public String getPassword()
	{
		return password;
	}

	@JsonProperty
	public void setPassword( String password )
	{
		this.password = password;
	}


	public Boolean getOpcionais()
	{
		return opcionais;
	}


	public void setOpcionais( Boolean opcionais )
	{
		this.opcionais = opcionais;
	}


	public Boolean getSincronizar()
	{
		return sincronizar;
	}


	public void setSincronizar( Boolean sincronizar )
	{
		this.sincronizar = sincronizar;
	}


	public Boolean getDownload()
	{
		return download;
	}


	public void setDownload( Boolean download )
	{
		this.download = download;
	}


	public Date getDataCriacao()
	{
		return dataCriacao;
	}


	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}


	public Usuario getUsuarioCriacao()
	{
		return usuarioCriacao;
	}


	public void setUsuarioCriacao( Usuario usuarioCriacao )
	{
		this.usuarioCriacao = usuarioCriacao;
	}


	public Date getDataAlteracao()
	{
		return dataAlteracao;
	}


	public void setDataAlteracao( Date dataAlteracao )
	{
		this.dataAlteracao = dataAlteracao;
	}


	public FusoHorario getFusoHorario()
	{
		return fusoHorario;
	}


	public void setFusoHorario( FusoHorario fusoHorario )
	{
		this.fusoHorario = fusoHorario;
	}


	public String getUrlambiente()
	{
		return urlambiente;
	}


	public void setUrlambiente( String urlambiente )
	{
		this.urlambiente = urlambiente;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente( Cliente cliente )
	{
		this.cliente = cliente;
	}

	@JsonAnyGetter
	public Map<String, String> getAmbienteAPI()
	{
		return ambienteAPI;
	}

	@JsonAnySetter
	public void setAmbienteAPI( Map<String, String> ambienteAPI )
	{
		this.ambienteAPI = ambienteAPI;
	}

	public AmbienteConfiguracao getConfiguracao()
	{
		return configuracao;
	}

	public void setConfiguracao( AmbienteConfiguracao configuracao )
	{
		this.configuracao = configuracao;
	}

	public Integer getHoraIniExpediente()
	{
		return horaIniExpediente;
	}

	public void setHoraIniExpediente( Integer horaIniExpediente )
	{
		this.horaIniExpediente = horaIniExpediente;
	}

	public Integer getMinutoIniExpediente()
	{
		return minutoIniExpediente;
	}

	public void setMinutoIniExpediente( Integer minutoIniExpediente )
	{
		this.minutoIniExpediente = minutoIniExpediente;
	}

	public Integer getHoraFimExpediente()
	{
		return horaFimExpediente;
	}

	public void setHoraFimExpediente( Integer horaFimExpediente )
	{
		this.horaFimExpediente = horaFimExpediente;
	}

	public Integer getMinutoFimExpediente()
	{
		return minutoFimExpediente;
	}

	public void setMinutoFimExpediente( Integer minutoFimExpediente )
	{
		this.minutoFimExpediente = minutoFimExpediente;
	}

	public Bloco getBloco()
	{
		return bloco;
	}

	public void setBloco( Bloco bloco )
	{
		this.bloco = bloco;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idAmbiente == null ) ? 0 : idAmbiente.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( !( obj instanceof Ambiente ) )
			return false;
		Ambiente other = (Ambiente) obj;
		if ( getIdAmbiente() == null )
		{
			if ( other.getIdAmbiente() != null )
				return false;
		}
		else if ( !getIdAmbiente().equals( other.getIdAmbiente() ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "Ambiente [idAmbiente=%s, nome=%s]", idAmbiente, nome );
	}

	public byte[] getLogomarca()
	{
		return logomarca;
	}

	public void setLogomarca( byte[] logomarca )
	{
		this.logomarca = logomarca;
	}

	public String getLogomimetype()
	{
		return logomimetype;
	}

	public void setLogomimetype( String logomimetype )
	{
		this.logomimetype = logomimetype;
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}

	public String getCep()
	{
		return cep;
	}

	public void setCep( String cep )
	{
		this.cep = cep;
	}

	
	
	
}
