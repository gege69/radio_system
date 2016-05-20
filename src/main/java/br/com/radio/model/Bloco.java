package br.com.radio.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.radio.enumeration.PosicaoComercial;
import br.com.radio.enumeration.PosicaoVinheta;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Blocos
 * 
 * @author pazin
 *
 */
@Entity
@Table( name="bloco")
public class Bloco implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_bloco", nullable = false )
	private Long idBloco;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_ambiente", unique = true)
	private Ambiente ambiente;

	@Enumerated(EnumType.STRING)
	@Column( name = "posicaoVinheta" )
	private PosicaoVinheta posicaoVinheta;

	/**
	 * Quantidade de Músicas em sequência ( a única coisa que pode quebrar é a vinheta )
	 */
	@Column( name = "qtdMusicas")
	private Integer qtdMusicas;

	@Enumerated(EnumType.STRING)
	@Column( name = "posicaoComercial" )
	private PosicaoComercial posicaoComercial;
	
	/**
	 * Quantidade de Comerciais em sequência  
	 */
	@Column( name = "qtdComerciais" )
	private Integer qtdComerciais;      
	
	/** 
	 * ---------------------------------------------------------------------------------------------------------------------------------------------------------------
	 * Os campos abaixo tem que ser dinamicamente alterados com base na quantidade de músicas em sequencia....
	 * 
	 * Por exemplo se a quantidade de músicas em sequência é 2 esses campos tem que múltiplos de 2 : 
	 * 
	 * 2, 4, 6, 8...
	 * 
	 * em caso de 5 músicas :
	 * 
	 * 5, 10, 15, 20...
	 * ---------------------------------------------------------------------------------------------------------------------------------------------------------------- 
	 */

	/**
	 * O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um INSTITUCIONAL ( Ex : 6 -> Após 6 músicas inserir um Institucional )
	 */
	@Column( name = "indexInstitucionais")
	private Integer indexInstitucionais;   
	
	/**
	 *  O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um PROGRAMETE ( Ex : 6 -> Após 6 músicas inserir um Programete )
	 */
	@Column( name = "indexProgrametes")
	private Integer indexProgrametes;  
	
	/**
	 * O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um OPCIONAL
	 */
	@Column( name = "indexOpcionais")
	private Integer indexOpcionais; 
	
	/**
	 * Quais opcionais foram selecionados para esse Bloco
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	   @JoinTable(name="bloco_audio_opcional", joinColumns = { 
	        @JoinColumn(name="id_bloco", nullable=false, updatable=false) }, inverseJoinColumns = { 
	        @JoinColumn(name="id_opcional", nullable=false, updatable=false) })
	private List<AudioOpcional> opcionais;	
	

	public Long getIdBloco()
	{
		return idBloco;
	}

	public void setIdBloco( Long idBloco )
	{
		this.idBloco = idBloco;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public PosicaoVinheta getPosicaoVinheta()
	{
		return posicaoVinheta;
	}

	public void setPosicaoVinheta( PosicaoVinheta posicaoVinheta )
	{
		this.posicaoVinheta = posicaoVinheta;
	}

	public Integer getQtdMusicas()
	{
		return qtdMusicas;
	}

	public void setQtdMusicas( Integer qtdMusicas )
	{
		this.qtdMusicas = qtdMusicas;
	}

	public Integer getQtdComerciais()
	{
		return qtdComerciais;
	}

	public void setQtdComerciais( Integer qtdComerciais )
	{
		this.qtdComerciais = qtdComerciais;
	}

	public Integer getIndexInstitucionais()
	{
		return indexInstitucionais;
	}

	public void setIndexInstitucionais( Integer indexInstitucionais )
	{
		this.indexInstitucionais = indexInstitucionais;
	}

	public Integer getIndexProgrametes()
	{
		return indexProgrametes;
	}

	public void setIndexProgrametes( Integer indexProgrametes )
	{
		this.indexProgrametes = indexProgrametes;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idBloco == null ) ? 0 : idBloco.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Bloco other = (Bloco) obj;
		if ( idBloco == null )
		{
			if ( other.idBloco != null )
				return false;
		}
		else if ( !idBloco.equals( other.idBloco ) )
			return false;
		return true;
	}

	public PosicaoComercial getPosicaoComercial()
	{
		return posicaoComercial;
	}

	public void setPosicaoComercial( PosicaoComercial posicaoComercial )
	{
		this.posicaoComercial = posicaoComercial;
	}

	public Integer getIndexOpcionais()
	{
		return indexOpcionais;
	}

	public void setIndexOpcionais( Integer indexOpcionais )
	{
		this.indexOpcionais = indexOpcionais;
	}

	@Override
	public String toString()
	{
		return String.format(
				"Bloco [idBloco=%s, ambiente=%s, posicaoVinheta=%s, qtdMusicas=%s, posicaoComercial=%s, qtdComerciais=%s, indexInstitucionais=%s, indexProgrametes=%s, indexOpcionais=%s]", idBloco,
				ambiente, posicaoVinheta, qtdMusicas, posicaoComercial, qtdComerciais, indexInstitucionais, indexProgrametes, indexOpcionais );
	}

	public List<AudioOpcional> getOpcionais()
	{
		return opcionais;
	}

	public void setOpcionais( List<AudioOpcional> opcionais )
	{
		this.opcionais = opcionais;
	}

}
