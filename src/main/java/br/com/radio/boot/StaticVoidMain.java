package br.com.radio.boot;

import java.util.Arrays;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;


public class StaticVoidMain {
	
	public static void main(String[] args)
	{
		String password = "123456f";
		
		Zxcvbn zxcvbn = new Zxcvbn();
		Strength strength = zxcvbn.measure( password, Arrays.asList( "eterion", "rdcenter", "radio", "ambiente", "som" ) );		
		
		System.out.println(strength.getScore());
		
	}

}
