package br.com.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.radio.enumeration.PosicaoVinheta;



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
	
	@OneToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;

	@Enumerated
	@Column( name = "posicaoVinheta" )
	private PosicaoVinheta posicaoVinheta;
	
	@Column( name = "qtdMusicas")
	private Integer qtdMusicas;
	
	@Column( name = "qtdComerciais" )
	private Integer qtdComerciais;
	
	@Column( name = "indexInstitucionais")
	private Integer indexInstitucionais;    // O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um INSTITUCIONAL ( Ex : 6 -> Após 6 músicas inserir um Institucional )
	
	@Column( name = "indexProgrametes")
	private Integer indexProgrametes;    // O número gravado aqui nesse campo é a quantidade de músicas que serão tocadas antes do programa inserir um PROGRAMETE ( Ex : 6 -> Após 6 músicas inserir um Programete )
	
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

	
	

}
