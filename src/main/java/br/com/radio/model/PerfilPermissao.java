package br.com.radio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Tabela de Ligação
 * 
 * Perfil x Permissao = PRP
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="perfil_permissao")
public class PerfilPermissao implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_perfperm_prp", nullable = false )
	private Long id_perfperm_prp;

	@ManyToOne
	@JoinColumn(name="id_perfil_per")
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name="id_permissao_prm")
	private Permissao permissao;

	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação da permisão de Perfil é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_prp", nullable = false )
	private Date dt_criacao_prp;
	

	@Override
	public Long getId()
	{
		return id_perfperm_prp;
	}

	@Override
	public void setId( Long id )
	{
		this.id_perfperm_prp = id;
	}

	public PerfilPermissao()
	{
		super();
		this.dt_criacao_prp = new Date();
	}

	
	

}
