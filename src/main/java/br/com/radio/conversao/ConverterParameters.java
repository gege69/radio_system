package br.com.radio.conversao;

import javax.validation.constraints.NotNull;

public class ConverterParameters {
	
	@NotNull
	private Long idMidia;
	
	@NotNull
	private BitRateType bitRate = BitRateType.AVERAGE;   // Default

	@NotNull
	private String valorBitRate;
	
	private VariableBitRateOption variableBitRate;

	public ConverterParameters()
	{
		super();
	}

	public ConverterParameters( BitRateType bitRate, String valorBitRate, VariableBitRateOption variableBitRate )
	{
		super();
		this.bitRate = bitRate;
		this.valorBitRate = valorBitRate;
		this.variableBitRate = variableBitRate;
		if ( variableBitRate != null )
			this.valorBitRate = variableBitRate.getCommandValue();
	}

	public BitRateType getBitRate()
	{
		if ( bitRate == null )
			bitRate = BitRateType.AVERAGE;
		return bitRate;
	}

	public void setBitRate( BitRateType bitRate )
	{
		this.bitRate = bitRate;
	}

	public String getValorBitRate()
	{
		if ( variableBitRate != null && bitRate.equals( BitRateType.VARIABLE ) )
			return variableBitRate.getCommandValue();
		else {
			if ( valorBitRate == null )
				valorBitRate = "96";
		
			return valorBitRate;
		}
	}

	public void setValorBitRate( String valorBitRate )
	{
		this.valorBitRate = valorBitRate;
	}

	public VariableBitRateOption getVariableBitRate()
	{
		return variableBitRate;
	}

	public void setVariableBitRate( VariableBitRateOption variableBitRate )
	{
		this.variableBitRate = variableBitRate;
		this.valorBitRate = variableBitRate.getCommandValue();
	}

	public Long getIdMidia()
	{
		return idMidia;
	}

	public void setIdMidia( Long idMidia )
	{
		this.idMidia = idMidia;
	}
	

	
}
