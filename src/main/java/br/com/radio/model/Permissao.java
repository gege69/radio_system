package br.com.radio.model;

import java.io.Serializable;
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



/**
 * Permissao
 * 
 * @author pazin
 *
 */
@Entity
@Table(name="permissao")
public class Permissao implements Serializable {
	
	private static final long serialVersionUID = -7404421157947787150L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "id_permissao", nullable = false )
	private Long idPermissao;
	
	@NotNull( message = "O alias da Permissão é de preenchimento obrigatório" )
	@Column( name = "codigo", nullable = false, columnDefinition = "TEXT" )
	private String codigo;

	@NotNull( message = "A descrição da Permissão é de preenchimento obrigatório" )
	@Column( name = "descricao", nullable = false, columnDefinition = "TEXT" )
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="id_permissaopai")
    private Permissao permissaoPai;
	
    @OneToMany(mappedBy="permissaoPai")
    private List<Permissao> permissoesFilhas;
	
	public Long getIdPermissao()
	{
		return idPermissao;
	}

	public void setIdPermissao( Long idPermissao )
	{
		this.idPermissao = idPermissao;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo( String codigo )
	{
		this.codigo = codigo;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao( String descricao )
	{
		this.descricao = descricao;
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
		result = prime * result + ( ( idPermissao == null ) ? 0 : idPermissao.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		Permissao other = (Permissao) obj;
		if ( idPermissao == null )
		{
			if ( other.idPermissao != null )
				return false;
		}
		else if ( !idPermissao.equals( other.idPermissao ) )
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format( "Permissao [idPermissao=%s, codigo=%s, descricao=%s, permissaoPai=%s ]", idPermissao, codigo, descricao, permissaoPai );
	}
	

}
