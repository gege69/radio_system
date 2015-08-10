package br.com.radio.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.radio.util.UtilsNumbers;
import br.com.radio.util.UtilsStr;



/**
 * Permissao = PRM
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="permissao")
public class Permissao implements Model<Long> {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_permissao_prm", nullable = false )
	private Long id_permissao_prm;
	
	@NotNull( message = "O alias da Permissão é de preenchimento obrigatório" )
	@Column( name = "cd_permiss_prm", nullable = false, length = 100 )
	private String cd_permiss_prm;

	@NotNull( message = "A descrição da Permissão é de preenchimento obrigatório" )
	@Column( name = "ds_permiss_prm", nullable = false, length = 400 )
	private String ds_permiss_prm;
	
	@ManyToOne
	@JoinColumn(name="id_permissaopai_prm")
    private Permissao permissaoPai;
	
    @OneToMany(mappedBy="permissaoPai")
    private List<Permissao> permissoesFilhas;
	
	@Override
	public Long getId()
	{
		return id_permissao_prm;
	}

	@Override
	public void setId( Long id )
	{
		this.id_permissao_prm = id;
	}

	public Long getId_permissao_prm()
	{
		return id_permissao_prm;
	}

	public void setId_permissao_prm( Long id_permissao_prm )
	{
		this.id_permissao_prm = id_permissao_prm;
	}

	public String getCd_permiss_prm()
	{
		return cd_permiss_prm;
	}

	public void setCd_permiss_prm( String cd_permiss_prm )
	{
		this.cd_permiss_prm = cd_permiss_prm;
	}

	public String getDs_permiss_prm()
	{
		return ds_permiss_prm;
	}

	public void setDs_permiss_prm( String ds_permiss_prm )
	{
		this.ds_permiss_prm = ds_permiss_prm;
	}

	public Permissao getPermissaoPai()
	{
		return permissaoPai;
	}

	public void setPermissaoPai( Permissao permissaoPai )
	{
		this.permissaoPai = permissaoPai;
	}

	public List<Permissao> getPermissoesFilhas()
	{
		return permissoesFilhas;
	}

	public void setPermissoesFilhas( List<Permissao> permissoesFilhas )
	{
		this.permissoesFilhas = permissoesFilhas;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id_permissao_prm == null ) ? 0 : id_permissao_prm.hashCode() );
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
		Permissao other = (Permissao) obj;
		if ( id_permissao_prm == null )
		{
			if ( other.id_permissao_prm != null )
				return false;
		}
		else if ( !id_permissao_prm.equals( other.id_permissao_prm ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Permissao [id_permissao_prm=" + id_permissao_prm + ", cd_permiss_prm=" + cd_permiss_prm + ", ds_permiss_prm=" + ds_permiss_prm + ", permissaoPai=" + permissaoPai
				+ ", permissoesFilhas=" + permissoesFilhas + "]";
	}



	

}
