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
		
		App url = new App();
		
		ArquivoDePropriedades fileprop = new ArquivoDePropriedades();
		
		Properties prop = fileprop.getProp();		
		url.setUrl("https://api.mocki.io/v2/549a5d8b/" + prop.getProperty("prop.mostPopularTVs"));
		
		url.conectaEBusca();

	}

}
