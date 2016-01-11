package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * 
 * Representa uma conversação... pode ter uma ou mais mensagens e vários participantes ( Usuários ou Ambientes )
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="conversa")
public class Conversa implements Serializable {

	private static final long serialVersionUID = -5826454955770953637L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_conversa", nullable = false )
	private Long idConversa;

	// Já existe no ambiente... apenas para facilitar
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	
	@ManyToMany(fetch=FetchType.EAGER)
	   @JoinTable(name="conversa_usuario", joinColumns = { 
	        @JoinColumn(name="id_conversa", nullable=false, updatable=false) }, inverseJoinColumns = { 
	        @JoinColumn(name="id_usuario", nullable=false, updatable=false) })
	private List<Usuario> usuarios;

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datavigenciainicio" )
	private Date dataVigenciaInicio;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datavigenciafim" )
	private Date dataVigenciaFim;

	@Column(name="emailcontato")
	private String emailContato;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER, mappedBy="conversa")
	@OrderBy("idMensagem")
    private List<Mensagem> mensagens;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao" )
	private Date dataCriacao;
	
	@Column(name="ativo")  // caso precise esconder a mensagem e manter o log
	private Boolean ativo;
	
	@Transient
	private Map<String,String> conversaView = new HashMap<String,String>();
	

	public Long getIdConversa()
	{
		return idConversa;
	}

	public void setIdConversa( Long idConversa )
	{
		this.idConversa = idConversa;
	}

	public List<Usuario> getUsuarios()
	{
		return usuarios;
	}

	public void setUsuarios( List<Usuario> usuarios )
	{
		this.usuarios = usuarios;
	}

	public Date getDataVigenciaInicio()
	{
		return dataVigenciaInicio;
	}

	public void setDataVigenciaInicio( Date dataVigenciaInicio )
	{
		this.dataVigenciaInicio = dataVigenciaInicio;
	}

	public Date getDataVigenciaFim()
	{
		return dataVigenciaFim;
	}

	public void setDataVigenciaFim( Date dataVigenciaFim )
	{
		this.dataVigenciaFim = dataVigenciaFim;
	}

	public String getEmailContato()
	{
		return emailContato;
	}

	public void setEmailContato( String emailContato )
	{
		this.emailContato = emailContato;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}
	
	public List<Mensagem> getMensagens()
	{
		return mensagens;
	}

	public void setMensagens( List<Mensagem> mensagens )
	{
		this.mensagens = mensagens;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idConversa == null ) ? 0 : idConversa.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Conversa other = (Conversa) obj;
		if ( idConversa == null )
		{
			if ( other.idConversa != null )
				return false;
		}
		else if ( !idConversa.equals( other.idConversa ) )
			return false;
		return true;
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
	public Map<String, String> getConversaView()
	{
		return conversaView;
	}

	@JsonAnySetter
	public void setConversaView( Map<String, String> conversaView )
	{
		this.conversaView = conversaView;
	}

	
	
	public String resumoParticipantes()
	{
		String result = "";
		
		if ( usuarios != null && usuarios.size() > 0 )
		{
			for ( Usuario u : usuarios )
			{
				if ( !result.equals( "" ) )
					result += ", " + u.getNome(); 
				else
					result += u.getNome();
				
				if ( result.length() > 28 )
				{
					result += " ... (" +usuarios.size() + " no total)";
					break;
				}				
			}
		}
		
		return result;
	}
	
	public void buildView()
	{
		conversaView.put( "participantes", this.resumoParticipantes() );
	}
	
	
}
