package br.com.radio.security.recaptcha;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope("request")
public class ReCaptchaImpl {

	private String privateKey;

	public boolean verifyUser(){



		return false;
	}

	public String getPrivateKey()
	{
		return privateKey;
	}

	public void setPrivateKey( String privateKey )
	{
		this.privateKey = privateKey;
	}
	
	

}
