package br.com.radio.conversao;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ConverterParameters {
	
	@NotNull
	private BitRateType bitRate = BitRateType.AVERAGE;   // Default

	@NotNull
	@NotEmpty
	private String valorBitRate;
	
	public ConverterParameters()
	{
		super();
	}

	public ConverterParameters( BitRateType bitRate, String valorBitRate )
	{
		super();
		this.bitRate = bitRate;
		this.valorBitRate = valorBitRate;
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
		return valorBitRate;
	}

	public void setValorBitRate( String valorBitRate )
	{
		this.valorBitRate = valorBitRate;
	}

	public VariableBitRateOption getVariableBitRate()
	{
		if ( bitRate.equals( BitRateType.VARIABLE ) && valorBitRate != null )
			return VariableBitRateOption.getByValue( this.valorBitRate );
		else
			return null;
	}

}
