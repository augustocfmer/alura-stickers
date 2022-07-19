package br.com.alurastickers.app;


import java.io.IOException;
import java.net.URI;
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
			for (Map<String, String> filme : listaDeFilmes) {
				System.out.println("Título: \u001b[3m " + filme.get("title"));  
				System.out.println("Poster: \u001b[3m " + filme.get("image")); 
				System.out.println("\\u001b[37;1m \\u001b[44;1m Classificação: \\u001b[3m " + filme.get("imDbRating")); 
				addEstrela(filme.get("imDbRating"));
				System.out.println();
			}
		}
		
		public void addEstrela(String rating) {
			for(int i = (int) Double.parseDouble(rating); i>= 1; i--) {
				System.out.print("*");
			}
		}
		
		public String getUrl() {
			return url;
		}
		
		public void setUrl(String url) {
			this.url = url;
		}
}
