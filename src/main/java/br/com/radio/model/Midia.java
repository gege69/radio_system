package br.com.radio.model;

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
public class Midia implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_midia_mid", nullable = false )
	private Long id_midia_mid;

	@NotNull( message = "O nome do Arquivo é de preenchimento obrigatório" )
	@Column( name = "nm_arquivo_mid", nullable = false, length = 200 )
	private String nm_arquivo_mid;
	
	@Column( name = "nm_extensao_mid", nullable = true, length = 10 )
	private String nm_extensao_mid;
	
	@NotNull( message = "O MIME Type é de preenchimento obrigatório" )
	@Column( name = "ds_mimetype_mid", nullable = false, length = 200 )
	private String ds_mimetype_mid;

	@Column( name = "ds_descricao_mid", nullable = true, columnDefinition = "TEXT" )
	private String ds_descricao_mid;
	
//	// Apenas para facilitar.... a tabela é que vai guardar a categoria de verdade...
//	@Enumerated( EnumType.STRING )
//	@Column( name = "ds_status_cha", nullable = false )
//	private CategoriaEnum ds_categoria_mid;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_categoria_cat")
	private Categoria categoria;
	

	// Ao contrário dos outros registros essa data serve para guardar quando o arquivo (música/gravação) foi criado/gravado/lançado
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_criacao_amb" )
	private Date dt_criacao_amb;

	// Essa data tem mais a ver com a data do registro... quando o arquivo foi enviado ao servidor.
	@JsonDeserialize(using=JSONDateDeserializer.class)
	@JsonSerialize(using=JSONDateSerializer.class)
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "dt_upload_amb", nullable = false )
	private Date dt_upload_amb;

	// Esse hash além de servir como nome do arquivo no filesystem será utilizado para verificação de consistência do arquivo... 
	@NotNull( message = "O hash do arquivo é de preenchimento obrigatório" )
	@Column( name = "ds_filehash_mid", nullable = false, length = 200 )
	private String ds_filehash_mid;
	
	@NotNull( message = "O caminho do arquivo no servidor é de preenchimento obrigatório" )
	@Column( name = "ds_filepath_mid", nullable = false, length = 200 )
	private String ds_filepath_mid;
	
	// Se o registro existe no filesystem (e pode ser acessado pelo servidor de aplicação) então está válido.
	@Column( name="fl_valido_mid")
	private Boolean fl_valido_mid;

	// Flag pra determinar se o arquivo precisa ser cacheado ou não...
	@Column( name="fl_cache_mid")
	private Boolean fl_cache_mid;
	
	
	// TAGs ID3v2.3.0
	private String ds_title_mid;
	
	private String ds_artist_mid;
	
	private String ds_album_mid;
	
	private String ds_comment_mid;
	
	private String ds_date_mid;
	
	private String ds_genre_mid;
	
 	

	@Override
	public Long getId()
	{
		return id_midia_mid;
	}

	@Override
	public void setId( Long id )
	{
		this.id_midia_mid = id;
	}

	public Long getId_midia_mid()
	{
		return id_midia_mid;
	}

	public void setId_midia_mid( Long id_midia_mid )
	{
		this.id_midia_mid = id_midia_mid;
	}

	public String getNm_arquivo_mid()
	{
		return nm_arquivo_mid;
	}

	public void setNm_arquivo_mid( String nm_arquivo_mid )
	{
		this.nm_arquivo_mid = nm_arquivo_mid;
	}

	public String getNm_extensao_mid()
	{
		return nm_extensao_mid;
	}

	public void setNm_extensao_mid( String nm_extensao_mid )
	{
		this.nm_extensao_mid = nm_extensao_mid;
	}

	public String getDs_mimetype_mid()
	{
		return ds_mimetype_mid;
	}

	public void setDs_mimetype_mid( String ds_mimetype_mid )
	{
		this.ds_mimetype_mid = ds_mimetype_mid;
	}

	public String getDs_descricao_mid()
	{
		return ds_descricao_mid;
	}

	public void setDs_descricao_mid( String ds_descricao_mid )
	{
		this.ds_descricao_mid = ds_descricao_mid;
	}

	public Date getDt_criacao_amb()
	{
		return dt_criacao_amb;
	}

	public void setDt_criacao_amb( Date dt_criacao_amb )
	{
		this.dt_criacao_amb = dt_criacao_amb;
	}

	public Date getDt_upload_amb()
	{
		return dt_upload_amb;
	}

	public void setDt_upload_amb( Date dt_upload_amb )
	{
		this.dt_upload_amb = dt_upload_amb;
	}

	public String getDs_filepath_mid()
	{
		return ds_filepath_mid;
	}

	public void setDs_filepath_mid( String ds_filepath_mid )
	{
		this.ds_filepath_mid = ds_filepath_mid;
	}

	public Boolean getFl_valido_mid()
	{
		return fl_valido_mid;
	}

	public void setFl_valido_mid( Boolean fl_valido_mid )
	{
		this.fl_valido_mid = fl_valido_mid;
	}

	public Boolean getFl_cache_mid()
	{
		return fl_cache_mid;
	}

	public void setFl_cache_mid( Boolean fl_cache_mid )
	{
		this.fl_cache_mid = fl_cache_mid;
	}

	public String getDs_filehash_mid()
	{
		return ds_filehash_mid;
	}

	public void setDs_filehash_mid( String ds_filehash_mid )
	{
		this.ds_filehash_mid = ds_filehash_mid;
	}
	
	
	// TAGS ID3v2.3.0
	public String getDs_title_mid()
	{
		return ds_title_mid;
	}

	public void setDs_title_mid( String ds_title_mid )
	{
		this.ds_title_mid = ds_title_mid;
	}

	public String getDs_artist_mid()
	{
		return ds_artist_mid;
	}

	public void setDs_artist_mid( String ds_artist_mid )
	{
		this.ds_artist_mid = ds_artist_mid;
	}

	public String getDs_album_mid()
	{
		return ds_album_mid;
	}

	public void setDs_album_mid( String ds_album_mid )
	{
		this.ds_album_mid = ds_album_mid;
	}

	public String getDs_comment_mid()
	{
		return ds_comment_mid;
	}

	public void setDs_comment_mid( String ds_comment_mid )
	{
		this.ds_comment_mid = ds_comment_mid;
	}

	public String getDs_date_mid()
	{
		return ds_date_mid;
	}

	public void setDs_date_mid( String ds_date_mid )
	{
		this.ds_date_mid = ds_date_mid;
	}

	public String getDs_genre_mid()
	{
		return ds_genre_mid;
	}

	public void setDs_genre_mid( String ds_genre_mid )
	{
		this.ds_genre_mid = ds_genre_mid;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id_midia_mid == null ) ? 0 : id_midia_mid.hashCode() );
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
		if ( id_midia_mid == null )
		{
			if ( other.id_midia_mid != null )
				return false;
		}
		else if ( !id_midia_mid.equals( other.id_midia_mid ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Midia [id_midia_mid=" + id_midia_mid + ", nm_arquivo_mid=" + nm_arquivo_mid + ", ds_filepath_mid=" + ds_filepath_mid + "]";
	}

	
	
	

}
