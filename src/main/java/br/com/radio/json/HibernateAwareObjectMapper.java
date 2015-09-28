package br.com.radio.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 6086596131786494308L;

	public HibernateAwareObjectMapper()
	{
//		registerModule( new Hibernate4Module() );
	}

}
