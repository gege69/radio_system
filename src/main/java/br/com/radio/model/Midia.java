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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Midia
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="midia")
public class Midia implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_midia", nullable = false )
	private Long idMidia;

	@NotNull( message = "O nome do Arquivo é de preenchimento obrigatório" )
	@Column( name = "nome", nullable = false, columnDefinition = "TEXT" )
	private String nome;
	
	@Column( name = "extensao", nullable = true, length = 10 )
	private String extensao;
	
	@NotNull( message = "O MIME Type é de preenchimento obrigatório" )
	@Column( name = "mimetype", nullable = false, length = 200 )
	private String mimetype;

	@Column( name = "descricao", nullable = true, columnDefinition = "TEXT" )
	private String descricao;

	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	   @JoinTable(name="midia_categoria", joinColumns = { 
	        @JoinColumn(name="id_midia", nullable=false, updatable=false) }, inverseJoinColumns = { 
	        @JoinColumn(name="id_categoria", nullable=false, updatable=false) })
    private List<Categoria> categorias;

	/* Para saber por qual motivo essa mídia foi selecionada. Útil na geração da programação, é gravada no registro de transmissao */
	@Transient
	private Categoria categoriaSelecionada;

	@Transient
	private Map<String,Boolean> categoriasView = new HashMap<String,Boolean>();

	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	   @JoinTable(name="midia_ambiente", joinColumns = { 
	        @JoinColumn(name="id_midia", nullable=false, updatable=false) }, inverseJoinColumns = { 
	        @JoinColumn(name="id_ambiente", nullable=false, updatable=false) })
	private List<Ambiente> ambientes;

	// Ao contrário dos outros registros essa data serve para guardar quando o arquivo (música/gravação) foi criado/gravado/lançado
	@JsonIgnore
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "datacriacao" )
	private Date dataCriacao;

	// Essa data tem mais a ver com a data do registro... quando o arquivo foi enviado ao servidor.
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dataupload", nullable = false )
	private Date dataUpload;

	// Esse hash além de servir como nome do arquivo no filesystem será utilizado para verificação de consistência do arquivo...
	@JsonIgnore
	@NotNull( message = "O hash do arquivo é de preenchimento obrigatório" )
	@Column( name = "filehash", nullable = false, columnDefinition = "TEXT" )
	private String filehash;
	
	@JsonIgnore
	@NotNull( message = "O caminho do arquivo no servidor é de preenchimento obrigatório" )
	@Column( name = "filepath", nullable = false, columnDefinition = "TEXT" )
	private String filepath;

	@JsonIgnore
	@NotNull( message = "O tamanho ao arquivo é de preenchimento obrigatório" )
	@Column( name = "filesize", nullable = false )
	private Integer filesize;
	
	// Se o registro existe no filesystem (e pode ser acessado pelo servidor de aplicação) então está válido.
	@JsonIgnore
	@Column( name="valido")
	private Boolean valido;

	// Flag pra determinar se o arquivo precisa ser cacheado ou não...
	@JsonIgnore
	@Column( name="cached")
	private Boolean cached;
	
	// Duração em segundos do playback 
	@Column( name="duracao")
	private Integer duracao;
	
	// TAGs ID3v2.3.0
	@Column( name="title", columnDefinition = "TEXT")
	private String title;

	@Column( name="artist", columnDefinition = "TEXT")
	private String artist;

	@Column( name="album", columnDefinition = "TEXT")
	private String album;

	@Column( name="comment", columnDefinition = "TEXT")
	private String comment;

	@Column( name="datetag", columnDefinition = "TEXT")
	private String datetag;

	@Column( name="genre", columnDefinition = "TEXT")
	private String genre;
	
	@Column( name="ativo")
	private Boolean ativo;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY)
	   @JoinTable(name="midia_genero", joinColumns = { 
	        @JoinColumn(name="id_midia", nullable=false, updatable=false) }, inverseJoinColumns = { 
	        @JoinColumn(name="id_genero", nullable=false, updatable=false) })
    private List<Genero> generos;
	
	
	@JsonIgnore
	@Transient
	private Double posicaoShuffle;
	
	
	public Long getIdMidia()
	{
		return idMidia;
	}

	public void setIdMidia( Long idMidia )
	{
		this.idMidia = idMidia;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome( String nome )
	{
		this.nome = nome;
	}

	public String getExtensao()
	{
		return extensao;
	}

	public void setExtensao( String extensao )
	{
		this.extensao = extensao;
	}

	public String getMimetype()
	{
		return mimetype;
	}

	public void setMimetype( String mimetype )
	{
		this.mimetype = mimetype;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
	}

	public List<Categoria> getCategorias()
	{
		return categorias;
	}

	public void setCategorias( List<Categoria> categorias )
	{
		this.categorias = categorias;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao( Date dataCriacao )
	{
		this.dataCriacao = dataCriacao;
	}

	public Date getDataUpload()
	{
		return dataUpload;
	}

	public void setDataUpload( Date dataUpload )
	{
		this.dataUpload = dataUpload;
	}

	public String getFilehash()
	{
		return filehash;
	}

	public void setFilehash( String filehash )
	{
		this.filehash = filehash;
	}

	public String getFilepath()
	{
		return filepath;
	}

	public void setFilepath( String filepath )
	{
		this.filepath = filepath;
	}

	public Boolean getValido()
	{
		return valido;
	}

	public void setValido( Boolean valido )
	{
		this.valido = valido;
	}

	public Boolean getCached()
	{
		return cached;
	}

	public void setCached( Boolean cached )
	{
		this.cached = cached;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist( String artist )
	{
		this.artist = artist;
	}

	public String getAlbum()
	{
		return album;
	}

	public void setAlbum( String album )
	{
		this.album = album;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment( String comment )
	{
		this.comment = comment;
	}

	public String getDatetag()
	{
		return datetag;
	}

	public void setDatetag( String datetag )
	{
		this.datetag = datetag;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre( String genre )
	{
		this.genre = genre;
	}

	public Integer getFilesize()
	{
		return filesize;
	}

	public void setFilesize( Integer filesize )
	{
		this.filesize = filesize;
	}

	@JsonAnyGetter
	public Map<String, Boolean> getCategoriasView()
	{
		return categoriasView;
	}

	@JsonAnySetter
	public void setCategoriasView( Map<String, Boolean> categoriasView )
	{
		this.categoriasView = categoriasView;
	}

	public Midia()
	{
		super();
		this.dataCriacao = new Date();
	}

	public List<Ambiente> getAmbientes()
	{
		return ambientes;
	}

	public void setAmbientes( List<Ambiente> ambientes )
	{
		this.ambientes = ambientes;
	}
	
	
	public Integer getDuracao()
	{
		return duracao;
	}

	public void setDuracao( Integer duracao )
	{
		this.duracao = duracao;
	}



	@Override
	public String toString()
	{
//		return String.format( "Midia [idMidia=%s, nome=%s, artist=%s]", idMidia, nome, artist );
//		return String.format( "Midia [idMidia=%s, artist=%s, posicaoShuffle=%10.8f]", idMidia, artist, posicaoShuffle );
		return String.format( "Midia [idMidia=%s, categoria=%s]", idMidia, categorias );
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( idMidia == null ) ? 0 : idMidia.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Midia other = (Midia) obj;
		if ( idMidia == null )
		{
			if ( other.idMidia != null )
				return false;
		}
		else if ( !idMidia.equals( other.idMidia ) )
			return false;
		return true;
	}

	public List<Genero> getGeneros()
	{
		return generos;
	}

	public void setGeneros( List<Genero> generos )
	{
		this.generos = generos;
	}

	public Double getPosicaoShuffle()
	{
		return posicaoShuffle;
	}

	public void setPosicaoShuffle( Double posicaoShuffle )
	{
		this.posicaoShuffle = posicaoShuffle;
	}

	public Categoria getCategoriaSelecionada()
	{
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada( Categoria categoriaSelecionada )
	{
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public Boolean getAtivo()
	{
		return ativo;
	}

	public void setAtivo( Boolean ativo )
	{
		this.ativo = ativo;
	}

	
	
}
