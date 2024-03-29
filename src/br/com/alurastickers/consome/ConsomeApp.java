package br.com.alurastickers.consome;

import java.io.IOException;
import java.util.Properties;

import br.com.alurastickers.app.*;


/**
 * Classe que executa a chamada do consumo da API.
 * 
 * @author Augusto Ribeiro
 * @version 0.1
 *
 */
public class ConsomeApp {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		App inicia = new App();
		
		ArquivoDePropriedades fileprop = new ArquivoDePropriedades();
		
		Properties prop = fileprop.getProp();		
		inicia.setUrl("http://localhost:8081/linguagens"); // + prop.getProperty("prop.mostPopularTVs"));
		
		inicia.conectaEBusca();

	}

}
