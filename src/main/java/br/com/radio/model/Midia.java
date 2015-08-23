package br.com.radio.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.validation.constraints.NotNull;

import br.com.radio.json.JSONDateDeserializer;
import br.com.radio.json.JSONDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 * Midia = MID
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
	@Column( name = "id_midia_mid", nullable = false )
	private Long id;

	@NotNull( message = "O nome do Arquivo é de preenchimento obrigatório" )
	@Column( name = "nm_arquivo_mid", nullable = false, length = 200 )
	private String nome;
	
	@Column( name = "nm_extensao_mid", nullable = true, length = 10 )
	private String extensao;
	
	@NotNull( message = "O MIME Type é de preenchimento obrigatório" )
	@Column( name = "ds_mimetype_mid", nullable = false, length = 200 )
	private String mimetype;

	@Column( name = "ds_descricao_mid", nullable = true, columnDefinition = "TEXT" )
	private String descricao;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_categoria_cat")
	private Categoria categoria;

	// Ao contrário dos outros registros essa data serve para guardar quando o arquivo (música/gravação) foi criado/gravado/lançado
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_amb" )
	private Date dataCriacao;

	// Essa data tem mais a ver com a data do registro... quando o arquivo foi enviado ao servidor.
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_upload_amb", nullable = false )
	private Date dataUpload;

	// Esse hash além de servir como nome do arquivo no filesystem será utilizado para verificação de consistência do arquivo... 
	@NotNull( message = "O hash do arquivo é de preenchimento obrigatório" )
	@Column( name = "ds_filehash_mid", nullable = false, length = 200 )
	private String filehash;
	
	@NotNull( message = "O caminho do arquivo no servidor é de preenchimento obrigatório" )
	@Column( name = "ds_filepath_mid", nullable = false, length = 200 )
	private String filepath;
	
	// Se o registro existe no filesystem (e pode ser acessado pelo servidor de aplicação) então está válido.
	@Column( name="fl_valido_mid")
	private Boolean valido;

	// Flag pra determinar se o arquivo precisa ser cacheado ou não...
	@Column( name="fl_cached_mid")
	private Boolean cached;
	
	
	// TAGs ID3v2.3.0
	@Column( name="ds_title_mid")
	private String title;

	@Column( name="ds_artist_mid")
	private String artist;

	@Column( name="ds_album_mid")
	private String album;

	@Column( name="ds_comment_mid")
	private String comment;

	@Column( name="ds_datetag_mid")
	private String datetag;

	@Column( name="ds_genre_mid")
	private String genre;
	
 	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		Midia other = (Midia) obj;
		if ( id == null )
		{
			if ( other.id != null )
				return false;
		}
		else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
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

	public Categoria getCategoria()
	{
		return categoria;
	}

	public void setCategoria( Categoria categoria )
	{
		this.categoria = categoria;
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

	
	

}
