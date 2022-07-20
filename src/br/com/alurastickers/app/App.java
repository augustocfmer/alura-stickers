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
			URI endereco = URI.create(this.url);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			String body = response.body();
			
			// extrair só os dados que interessam(titulo, poster, classificacao)
			JsonParser parser = new JsonParser();
			List<Map<String, String>> listaDeFilmes = parser.parse(body);
			
			//exibir e manipular os dados
			GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
			for (Map<String, String> filme : listaDeFilmes) {
				
				String urlImagem = filme.get("image");
				String nomeArquivo = "saida/" + filme.get("title").replace(":", "-") + ".png";
				InputStream inputStream = new URL(urlImagem).openStream();
				
				geradora.cria(inputStream, nomeArquivo, filme.get("imDbRating"));
				
				System.out.println("Título: "+ filme.get("title"));
	            System.out.println("Classificação: "+ filme.get("imDbRating"));
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
