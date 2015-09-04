package com.spay.config;

import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class CreatePassword {
	public static void main(String[] args) {
		
		StandardPBEStringEncryptor encryptor = new PropertiesConfig().configurationEncryptor();
		
		Scanner user_input = new Scanner( System.in );
		
		String orignalString;
		while(true) {
			String first_name;
			System.out.print("Input Orignal String : ");
			orignalString = user_input.next( );
			System.out.println("Encrypted String --> " + encryptor.encrypt(orignalString));
		}
		
	}

}
