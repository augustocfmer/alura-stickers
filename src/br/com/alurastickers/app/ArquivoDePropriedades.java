package br.com.alurastickers.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * 
 * Classe que representa o acesso ao arquivo das chaves da API.
 * 
 * @author Augusto Ribeiro
 * @version 0.1
 *
 */
public class ArquivoDePropriedades {

	
	public Properties getProp() throws IOException {
		
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("src/dados.properties");
		props.load(file);
		return props;
	}
}
