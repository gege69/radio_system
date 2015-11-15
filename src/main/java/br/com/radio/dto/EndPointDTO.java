package br.com.radio.dto;


public class EndPointDTO {
	
	private String controller;
	
	private String patternsCondition;

	private String methodsCondition;

	private String paramsCondition;

	private String headersCondition;

	private String consumesCondition;

	private String producesCondition;

	private String customCondition;

	public String getController()
	{
		return controller;
	}

	public void setController( String controller )
	{
		this.controller = controller;
	}

	public String getPatternsCondition()
	{
		return patternsCondition;
	}

	public void setPatternsCondition( String patternsCondition )
	{
		this.patternsCondition = patternsCondition;
	}

	public String getMethodsCondition()
	{
		return methodsCondition;
	}

	public void setMethodsCondition( String methodsCondition )
	{
		this.methodsCondition = methodsCondition;
	}

	public String getParamsCondition()
	{
		return paramsCondition;
	}

	public void setParamsCondition( String paramsCondition )
	{
		this.paramsCondition = paramsCondition;
	}

	public String getHeadersCondition()
	{
		return headersCondition;
	}

	public void setHeadersCondition( String headersCondition )
	{
		this.headersCondition = headersCondition;
	}

	public String getConsumesCondition()
	{
		return consumesCondition;
	}

	public void setConsumesCondition( String consumesCondition )
	{
		this.consumesCondition = consumesCondition;
	}

	public String getProducesCondition()
	{
		return producesCondition;
	}

	public void setProducesCondition( String producesCondition )
	{
		this.producesCondition = producesCondition;
	}

	public String getCustomCondition()
	{
		return customCondition;
	}

	public void setCustomCondition( String customCondition )
	{
		this.customCondition = customCondition;
	}

	
	

}
