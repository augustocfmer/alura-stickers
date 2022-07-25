package br.com.alurastickers.app;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


/**
 * 
 * Classe que representa a execucao do consumo da API.
 * 
 * @author Augusto Ribeiro
 * @version 0.1
 *
 */
public class App {
		
		
		private String url;
		
		
		public void conectaEBusca() throws IOException, InterruptedException {
			
			//fazer uma conexao HTTP e buscar os top 250 filmes
			
			ClienteHttp http = new ClienteHttp();
			String json = http.buscaDados(this.url);
			
			//exibir e manipular os dados
			ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
			List<Conteudo> conteudos = extrator.extraiConteudo(json);
			
			GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
			
			for (int i=0; i < 3; i++) {
				
				Conteudo conteudo = conteudos.get(i);
				
				String nomeArquivo = "saida/" + conteudo.getTitulo().replace(":", "-") + ".png";
				InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
				
				geradora.cria(inputStream, nomeArquivo);
				
				System.out.println("Título: "+ conteudo.getTitulo());
	            System.out.println();
			}
		}
		
		
		public String getUrl() {
			return url;
		}
		
		public void setUrl(String url) {
			this.url = url;
		}
}
