package br.com.radio.enumeration;

public enum CategoriaEnum {
	
	MUSICA("M", "Música"),
	VINHETA("V", "Vinheta"),
	INSTITUCIONAL("I", "Institucional"),
	COMERCIAL("C", "Comercial"),
	PROGRAMETE("P", "Programete"),
	CHAMADA_FUNC("F", "Chamada de Funcionários"),
	CHAMADA_INST("H", "Chamada Instantânea");
	
	private String fl_categoria;
	
	private String ds_categoria;
	
	private CategoriaEnum( String fl, String desc )
	{
		this.fl_categoria = fl;
		this.ds_categoria = desc;
	}

	public String getFl_categoria()
	{
		return fl_categoria;
	}

	public void setFl_categoria( String fl_categoria )
	{
		this.fl_categoria = fl_categoria;
	}

	public String getDs_categoria()
	{
		return ds_categoria;
	}

	public void setDs_categoria( String ds_categoria )
	{
		this.ds_categoria = ds_categoria;
	}
	

}
