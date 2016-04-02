package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name="bloco")
public class Bloco implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_bloco", nullable = false )
	private Long idBloco;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;

	@Enumerated(EnumType.STRING)
	@Column( name = "posicaoVinheta" )
	private PosicaoVinheta posicaoVinheta;

	/* Quantidade de Músicas em sequência ( a única coisa que pode quebrar é a vinheta )*/
	@Column( name = "qtdMusicas")
	private Integer qtdMusicas;

	@Enumerated(EnumType.STRING)
	@Column( name = "posicaoComercial" )
	private PosicaoComercial posicaoComercial;
	
	/* Quantidade de Comerciais em sequência  */
	@Column( name = "qtdComerciais" )
	private Integer qtdComerciais;      
	
	/* Os campos abaixo tem que ser dinamicamente alterados com base na quantidade de músicas em sequencia....
	 * 
	 * Por exemplo se a quantidade de músicas em sequência é 2 esses campos tem que múltiplos de 2 : 
	 * 
	 * 2, 4, 6, 8...
	 * 
	 * em caso de 5 músicas :
	 * 
	 * 5, 10, 15, 20...
	 */
	
	@Column( name = "indexInstitucionais")
	private Integer indexInstitucionais;    // O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um INSTITUCIONAL ( Ex : 6 -> Após 6 músicas inserir um Institucional )
	
	@Column( name = "indexProgrametes")
	private Integer indexProgrametes;    // O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um PROGRAMETE ( Ex : 6 -> Após 6 músicas inserir um Programete )
	
	@Column( name = "indexHoroscopo")
	private Integer indexHoroscopo;     // O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um HOROSCOPO

	@Column( name = "indexHoraCerta")
	private Integer indexHoraCerta;     // O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um HORACERTA, porém esse campo deve ser preenchido com múltiplos de 3 ( Ex : 9 -> Após 9 músicas inserir um Programete )

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

	public Integer getIndexHoraCerta()
	{
		return indexHoraCerta;
	}

	public void setIndexHoraCerta( Integer indexHoraCerta )
	{
		this.indexHoraCerta = indexHoraCerta;
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

	public Integer getIndexHoroscopo()
	{
		return indexHoroscopo;
	}

	public void setIndexHoroscopo( Integer indexHoroscopo )
	{
		this.indexHoroscopo = indexHoroscopo;
	}

	@Override
	public String toString()
	{
		return String
				.format(
						"Bloco [idBloco=%s, ambiente=%s, posicaoVinheta=%s, qtdMusicas=%s, posicaoComercial=%s, qtdComerciais=%s, indexInstitucionais=%s, indexProgrametes=%s, indexHoroscopo=%s, indexHoraCerta=%s]",
						idBloco, ambiente, posicaoVinheta, qtdMusicas, posicaoComercial, qtdComerciais, indexInstitucionais, indexProgrametes, indexHoroscopo, indexHoraCerta );
	}


}
