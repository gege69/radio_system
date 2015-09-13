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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.radio.enumeration.VozLocucao;
import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

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
	
	@Column(name="autoplay")
	private Boolean autoplay;
	
	@Column(name="selecaoGenero")
	private Boolean selecaoGenero;
	
	@Column(name="avancarRetornar")
	private Boolean avancarRetornar;
	
	@Column(name="atendimento")
	private Boolean atendimento;
	
	@Column(name="chamVeiculo")
	private Boolean chamVeiculo;
	
	@Column(name="chamFuncionarios")
	private Boolean chamFuncionarios;
	
	@Column(name="chamVariosFuncionarios")
	private Boolean chamVariosFuncionarios;
	
	@Column(name="chamInstantanea")
	private Boolean chamInstantanea;
	
	@Column(name="rodoviarias")
	private Boolean rodoviarias;
	
	@Column(name="horoscopo")
	private Boolean horoscopo;
	
	@Column(name="agendMidia")
	private Boolean agendMidia;
	
	@Column(name="relatoriosMidia")
	private Boolean relatoriosMidia;
	
	@Column(name="controleBlocos")
	private Boolean controleBlocos;
	
	@Column(name="controleComerciais")
	private Boolean controleComerciais;
	
	@Column(name="controleInstitucionais")
	private Boolean controleInstitucionais;
	
	@Column(name="controleProgrametes")
	private Boolean controleProgrametes;
	
	@Column(name="nobreak")
	private Boolean nobreak;
	
	@Column(name="menuDownloads")
	private Boolean menuDownloads;
	
	@Column(name="locutorVirtual")
	private Boolean locutorVirtual;
	
	@Column(name="pedidoMusical")
	private Boolean pedidoMusical;
	
	@Column(name="pedidoMusicalVinheta")
	private Boolean pedidoMusicalVinheta;
	
	@Column(name="generosByCC")
	private Boolean generosByCC;
	
	@Column(name="opcionais")
	private Boolean opcionais;
	
	@Enumerated( EnumType.STRING )
	@Column(name="vozLocucao")
	private VozLocucao vozLocucao;
	
	@Column(name="controleVolumeIndividual")
	private Boolean controleVolumeIndividual;
	
	@Column(name="volumeMusicas")
	private Integer volumeMusicas;
	
	@Column(name="volumeChamadas")
	private Integer volumeChamadas;
	
	@Column(name="volumeComerciais")
	private Integer volumeComerciais;
	
	@Column(name="volumeGeral")
	private Integer volumeGeral;
	
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@NotNull( message = "A data de criação da Configuração do Ambiente é de preenchimento obrigatório" )	
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
	@JoinColumn(name = "id_usuarioAlteracao" )
	private Usuario usuarioAlteracao;


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

	public Boolean getAutoplay()
	{
		return autoplay;
	}

	public void setAutoplay( Boolean autoplay )
	{
		this.autoplay = autoplay;
	}

	public Boolean getSelecaoGenero()
	{
		return selecaoGenero;
	}

	public void setSelecaoGenero( Boolean selecaoGenero )
	{
		this.selecaoGenero = selecaoGenero;
	}

	public Boolean getAvancarRetornar()
	{
		return avancarRetornar;
	}

	public void setAvancarRetornar( Boolean avancarRetornar )
	{
		this.avancarRetornar = avancarRetornar;
	}

	public Boolean getAtendimento()
	{
		return atendimento;
	}

	public void setAtendimento( Boolean atendimento )
	{
		this.atendimento = atendimento;
	}

	public Boolean getChamVeiculo()
	{
		return chamVeiculo;
	}

	public void setChamVeiculo( Boolean chamVeiculo )
	{
		this.chamVeiculo = chamVeiculo;
	}

	public Boolean getChamFuncionarios()
	{
		return chamFuncionarios;
	}

	public void setChamFuncionarios( Boolean chamFuncionarios )
	{
		this.chamFuncionarios = chamFuncionarios;
	}

	public Boolean getChamVariosFuncionarios()
	{
		return chamVariosFuncionarios;
	}

	public void setChamVariosFuncionarios( Boolean chamVariosFuncionarios )
	{
		this.chamVariosFuncionarios = chamVariosFuncionarios;
	}

	public Boolean getChamInstantanea()
	{
		return chamInstantanea;
	}

	public void setChamInstantanea( Boolean chamInstantanea )
	{
		this.chamInstantanea = chamInstantanea;
	}

	public Boolean getRodoviarias()
	{
		return rodoviarias;
	}

	public void setRodoviarias( Boolean rodoviarias )
	{
		this.rodoviarias = rodoviarias;
	}

	public Boolean getHoroscopo()
	{
		return horoscopo;
	}

	public void setHoroscopo( Boolean horoscopo )
	{
		this.horoscopo = horoscopo;
	}

	public Boolean getAgendMidia()
	{
		return agendMidia;
	}

	public void setAgendMidia( Boolean agendMidia )
	{
		this.agendMidia = agendMidia;
	}

	public Boolean getControleBlocos()
	{
		return controleBlocos;
	}

	public void setControleBlocos( Boolean controleBlocos )
	{
		this.controleBlocos = controleBlocos;
	}

	public Boolean getControleComerciais()
	{
		return controleComerciais;
	}

	public void setControleComerciais( Boolean controleComerciais )
	{
		this.controleComerciais = controleComerciais;
	}

	public Boolean getControleInstitucionais()
	{
		return controleInstitucionais;
	}

	public void setControleInstitucionais( Boolean controleInstitucionais )
	{
		this.controleInstitucionais = controleInstitucionais;
	}

	public Boolean getControleProgrametes()
	{
		return controleProgrametes;
	}

	public void setControleProgrametes( Boolean controleProgrametes )
	{
		this.controleProgrametes = controleProgrametes;
	}

	public Boolean getNobreak()
	{
		return nobreak;
	}

	public void setNobreak( Boolean nobreak )
	{
		this.nobreak = nobreak;
	}

	public Boolean getMenuDownloads()
	{
		return menuDownloads;
	}

	public void setMenuDownloads( Boolean menuDownloads )
	{
		this.menuDownloads = menuDownloads;
	}

	public Boolean getLocutorVirtual()
	{
		return locutorVirtual;
	}

	public void setLocutorVirtual( Boolean locutorVirtual )
	{
		this.locutorVirtual = locutorVirtual;
	}

	public Boolean getPedidoMusical()
	{
		return pedidoMusical;
	}

	public void setPedidoMusical( Boolean pedidoMusical )
	{
		this.pedidoMusical = pedidoMusical;
	}

	public Boolean getPedidoMusicalVinheta()
	{
		return pedidoMusicalVinheta;
	}

	public void setPedidoMusicalVinheta( Boolean pedidoMusicalVinheta )
	{
		this.pedidoMusicalVinheta = pedidoMusicalVinheta;
	}

	public Boolean getGenerosByCC()
	{
		return generosByCC;
	}

	public void setGenerosByCC( Boolean generosByCC )
	{
		this.generosByCC = generosByCC;
	}

	public Boolean getOpcionais()
	{
		return opcionais;
	}

	public void setOpcionais( Boolean opcionais )
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

	public Boolean getControleVolumeIndividual()
	{
		return controleVolumeIndividual;
	}

	public void setControleVolumeIndividual( Boolean controleVolumeIndividual )
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

	public Boolean getRelatoriosMidia()
	{
		return relatoriosMidia;
	}

	public void setRelatoriosMidia( Boolean relatoriosMidia )
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

	public AmbienteConfiguracao()
	{
		super();
		this.dataCriacao = new Date();
	}

	
}
