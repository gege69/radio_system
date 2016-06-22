package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.radio.enumeration.VozLocucao;
import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateTimeSerializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="ambiente_configuracao")
public class AmbienteConfiguracao implements Serializable {

	private static final long serialVersionUID = 7846499560224915976L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_ambconfig", nullable = false )
	private Long idAmbConfig;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="id_ambiente")
	private Ambiente ambiente;
	
	@Column(name="autoplay", columnDefinition = "BOOL default false")
	private boolean autoplay;
	
	@Column(name="selecaoGenero", columnDefinition = "BOOL default false")
	private boolean selecaoGenero;
	
	@Column(name="avancarRetornar", columnDefinition = "BOOL default false")
	private boolean avancarRetornar;
	
	@Column(name="atendimento", columnDefinition = "BOOL default false")
	private boolean atendimento;
	
	@Column(name="chamVeiculo", columnDefinition = "BOOL default false")
	private boolean chamVeiculo;
	
	@Column(name="chamFuncionarios", columnDefinition = "BOOL default false")
	private boolean chamFuncionarios;
	
	@Column(name="chamVariosFuncionarios", columnDefinition = "BOOL default false")
	private boolean chamVariosFuncionarios;
	
	@Column(name="chamInstantanea", columnDefinition = "BOOL default false")
	private boolean chamInstantanea;
	
	@Column(name="rodoviarias", columnDefinition = "BOOL default false")
	private boolean rodoviarias;
	
	@Column(name="horoscopo", columnDefinition = "BOOL default false")
	private boolean horoscopo;
	
	@Column(name="agendMidia", columnDefinition = "BOOL default false")
	private boolean agendMidia;
	
	@Column(name="relatoriosMidia", columnDefinition = "BOOL default false")
	private boolean relatoriosMidia;
	
	@Column(name="controleBlocos", columnDefinition = "BOOL default false")
	private boolean controleBlocos;
	
	@Column(name="controleComerciais", columnDefinition = "BOOL default false")
	private boolean controleComerciais;
	
	@Column(name="controleInstitucionais", columnDefinition = "BOOL default false")
	private boolean controleInstitucionais;
	
	@Column(name="controleProgrametes", columnDefinition = "BOOL default false")
	private boolean controleProgrametes;
	
	@Column(name="nobreak", columnDefinition = "BOOL default false")
	private boolean nobreak;
	
	@Column(name="menuDownloads", columnDefinition = "BOOL default false")
	private boolean menuDownloads;
	
	@Column(name="locutorVirtual", columnDefinition = "BOOL default false")
	private boolean locutorVirtual;
	
	@Column(name="pedidoMusical", columnDefinition = "BOOL default false")
	private boolean pedidoMusical;
	
	@Column(name="pedidoMusicalVinheta", columnDefinition = "BOOL default false")
	private boolean pedidoMusicalVinheta;
	
	@Column(name="generosByCC", columnDefinition = "BOOL default false")
	private boolean generosByCC;
	
	@Column(name="opcionais", columnDefinition = "BOOL default false")
	private boolean opcionais;
	
	@Enumerated( EnumType.STRING )
	@Column(name="vozLocucao")
	private VozLocucao vozLocucao;
	
	@Column(name="controleVolumeIndividual", columnDefinition = "BOOL default false")
	private boolean controleVolumeIndividual;
	
	@Column(name="volumeMusicas")
	private Integer volumeMusicas;
	
	@Column(name="volumeChamadas")
	private Integer volumeChamadas;
	
	@Column(name="volumeComerciais")
	private Integer volumeComerciais;
	
	@Column(name="volumeGeral")
	private Integer volumeGeral;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@NotNull( message = "A data de criação da Configuração do Ambiente é de preenchimento obrigatório" )	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao", nullable = false )
	private Date dataCriacao;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_usuario" )
	private Usuario usuarioCriacao;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateTimeSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataalteracao" )
	private Date dataAlteracao;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_usuarioAlteracao" )
	private Usuario usuarioAlteracao;

	@Column(name="botaoStop", columnDefinition = "BOOL default false")
	private boolean botaoStop;
	

	public Long getIdAmbConfig()
	{
		return idAmbConfig;
	}

	public void setIdAmbConfig( Long idAmbConfig )
	{
		this.idAmbConfig = idAmbConfig;
	}

	public Ambiente getAmbiente()
	{
		return ambiente;
	}

	public void setAmbiente( Ambiente ambiente )
	{
		this.ambiente = ambiente;
	}

	public boolean getAutoplay()
	{
		return autoplay;
	}

	public void setAutoplay( boolean autoplay )
	{
		this.autoplay = autoplay;
	}

	public boolean getSelecaoGenero()
	{
		return selecaoGenero;
	}

	public void setSelecaoGenero( boolean selecaoGenero )
	{
		this.selecaoGenero = selecaoGenero;
	}

	public boolean getAvancarRetornar()
	{
		return avancarRetornar;
	}

	public void setAvancarRetornar( boolean avancarRetornar )
	{
		this.avancarRetornar = avancarRetornar;
	}

	public boolean getAtendimento()
	{
		return atendimento;
	}

	public void setAtendimento( boolean atendimento )
	{
		this.atendimento = atendimento;
	}

	public boolean getChamVeiculo()
	{
		return chamVeiculo;
	}

	public void setChamVeiculo( boolean chamVeiculo )
	{
		this.chamVeiculo = chamVeiculo;
	}

	public boolean getChamFuncionarios()
	{
		return chamFuncionarios;
	}

	public void setChamFuncionarios( boolean chamFuncionarios )
	{
		this.chamFuncionarios = chamFuncionarios;
	}

	public boolean getChamVariosFuncionarios()
	{
		return chamVariosFuncionarios;
	}

	public void setChamVariosFuncionarios( boolean chamVariosFuncionarios )
	{
		this.chamVariosFuncionarios = chamVariosFuncionarios;
	}

	public boolean getChamInstantanea()
	{
		return chamInstantanea;
	}

	public void setChamInstantanea( boolean chamInstantanea )
	{
		this.chamInstantanea = chamInstantanea;
	}

	public boolean getRodoviarias()
	{
		return rodoviarias;
	}

	public void setRodoviarias( boolean rodoviarias )
	{
		this.rodoviarias = rodoviarias;
	}

	public boolean getHoroscopo()
	{
		return horoscopo;
	}

	public void setHoroscopo( boolean horoscopo )
	{
		this.horoscopo = horoscopo;
	}

	public boolean getAgendMidia()
	{
		return agendMidia;
	}

	public void setAgendMidia( boolean agendMidia )
	{
		this.agendMidia = agendMidia;
	}

	public boolean getControleBlocos()
	{
		return controleBlocos;
	}

	public void setControleBlocos( boolean controleBlocos )
	{
		this.controleBlocos = controleBlocos;
	}

	public boolean getControleComerciais()
	{
		return controleComerciais;
	}

	public void setControleComerciais( boolean controleComerciais )
	{
		this.controleComerciais = controleComerciais;
	}

	public boolean getControleInstitucionais()
	{
		return controleInstitucionais;
	}

	public void setControleInstitucionais( boolean controleInstitucionais )
	{
		this.controleInstitucionais = controleInstitucionais;
	}

	public boolean getControleProgrametes()
	{
		return controleProgrametes;
	}

	public void setControleProgrametes( boolean controleProgrametes )
	{
		this.controleProgrametes = controleProgrametes;
	}

	public boolean getNobreak()
	{
		return nobreak;
	}

	public void setNobreak( boolean nobreak )
	{
		this.nobreak = nobreak;
	}

	public boolean getMenuDownloads()
	{
		return menuDownloads;
	}

	public void setMenuDownloads( boolean menuDownloads )
	{
		this.menuDownloads = menuDownloads;
	}

	public boolean getLocutorVirtual()
	{
		return locutorVirtual;
	}

	public void setLocutorVirtual( boolean locutorVirtual )
	{
		this.locutorVirtual = locutorVirtual;
	}

	public boolean getPedidoMusical()
	{
		return pedidoMusical;
	}

	public void setPedidoMusical( boolean pedidoMusical )
	{
		this.pedidoMusical = pedidoMusical;
	}

	public boolean getPedidoMusicalVinheta()
	{
		return pedidoMusicalVinheta;
	}

	public void setPedidoMusicalVinheta( boolean pedidoMusicalVinheta )
	{
		this.pedidoMusicalVinheta = pedidoMusicalVinheta;
	}

	public boolean getGenerosByCC()
	{
		return generosByCC;
	}

	public void setGenerosByCC( boolean generosByCC )
	{
		this.generosByCC = generosByCC;
	}

	public boolean getOpcionais()
	{
		return opcionais;
	}

	public void setOpcionais( boolean opcionais )
	{
		this.opcionais = opcionais;
	}

	public VozLocucao getVozLocucao()
	{
		return vozLocucao;
	}

	public void setVozLocucao( VozLocucao vozLocucao )
	{
		this.vozLocucao = vozLocucao;
	}

	public boolean getControleVolumeIndividual()
	{
		return controleVolumeIndividual;
	}

	public void setControleVolumeIndividual( boolean controleVolumeIndividual )
	{
		this.controleVolumeIndividual = controleVolumeIndividual;
	}

	public Integer getVolumeMusicas()
	{
		return volumeMusicas;
	}

	public void setVolumeMusicas( Integer volumeMusicas )
	{
		this.volumeMusicas = volumeMusicas;
	}

	public Integer getVolumeChamadas()
	{
		return volumeChamadas;
	}

	public void setVolumeChamadas( Integer volumeChamadas )
	{
		this.volumeChamadas = volumeChamadas;
	}

	public Integer getVolumeComerciais()
	{
		return volumeComerciais;
	}

	public void setVolumeComerciais( Integer volumeComerciais )
	{
		this.volumeComerciais = volumeComerciais;
	}

	public Integer getVolumeGeral()
	{
		return volumeGeral;
	}

	public void setVolumeGeral( Integer volumeGeral )
	{
		this.volumeGeral = volumeGeral;
	}

	public boolean getRelatoriosMidia()
	{
		return relatoriosMidia;
	}

	public void setRelatoriosMidia( boolean relatoriosMidia )
	{
		this.relatoriosMidia = relatoriosMidia;
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

	public Usuario getUsuarioAlteracao()
	{
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao( Usuario usuarioAlteracao )
	{
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
	public boolean isBotaoStop()
	{
		return botaoStop;
	}

	public void setBotaoStop( boolean botaoStop )
	{
		this.botaoStop = botaoStop;
	}

	public AmbienteConfiguracao()
	{
		super();
		this.dataCriacao = new Date();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idAmbConfig == null ) ? 0 : idAmbConfig.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		AmbienteConfiguracao other = (AmbienteConfiguracao) obj;
		if ( idAmbConfig == null )
		{
			if ( other.idAmbConfig != null )
				return false;
		}
		else if ( !idAmbConfig.equals( other.idAmbConfig ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String
				.format(
						"AmbienteConfiguracao [idAmbConfig=%s, ambiente=%s, autoplay=%s, selecaoGenero=%s, avancarRetornar=%s, atendimento=%s, chamVeiculo=%s, chamFuncionarios=%s, chamVariosFuncionarios=%s, chamInstantanea=%s, rodoviarias=%s, horoscopo=%s, agendMidia=%s, relatoriosMidia=%s, controleBlocos=%s, controleComerciais=%s, controleInstitucionais=%s, controleProgrametes=%s, nobreak=%s, menuDownloads=%s, locutorVirtual=%s, pedidoMusical=%s, pedidoMusicalVinheta=%s, generosByCC=%s, opcionais=%s, vozLocucao=%s, controleVolumeIndividual=%s, volumeMusicas=%s, volumeChamadas=%s, volumeComerciais=%s, volumeGeral=%s, dataCriacao=%s, usuarioCriacao=%s, dataAlteracao=%s, usuarioAlteracao=%s]",
						idAmbConfig, ambiente, autoplay, selecaoGenero, avancarRetornar, atendimento, chamVeiculo, chamFuncionarios, chamVariosFuncionarios, chamInstantanea, rodoviarias, horoscopo,
						agendMidia, relatoriosMidia, controleBlocos, controleComerciais, controleInstitucionais, controleProgrametes, nobreak, menuDownloads, locutorVirtual, pedidoMusical,
						pedidoMusicalVinheta, generosByCC, opcionais, vozLocucao, controleVolumeIndividual, volumeMusicas, volumeChamadas, volumeComerciais, volumeGeral, dataCriacao, usuarioCriacao,
						dataAlteracao, usuarioAlteracao );
	}


	
	
	
}
