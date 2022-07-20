package br.com.alurastickers.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

	public void cria(InputStream inputStream, String nomeArquivo, String classificacao) throws IOException {
		
		//leitura da imagem
		//InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_1.jpg").openStream();
		BufferedImage imagemOriginal = ImageIO.read(inputStream);
		
		// cria nova imagem em memória com transparência e com novo tamanho
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		double rating = Double.parseDouble(classificacao);
		BufferedImage seguraBebe;
		
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
		
		// copiar a imagem original para a nova imagem (em memória)
		Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);
		
		//configurar a fonte
		Font fonte = new Font("Comic Sans MS", Font.BOLD, (int)(largura/15));
		graphics.setColor(Color.YELLOW);
		graphics.setFont(fonte);
		
		seguraBebe = incluiBebe(rating);
		// escrever uma frase na nova imagem
		if(rating < 6) {
			graphics.drawString("MAMÃE, UMA MERDA!", (int)(largura/3), novaAltura - 100);
			graphics.drawImage(seguraBebe, (int)(largura/80), novaAltura - 400, null);
		} else if(rating >= 6 && rating < 8){
			graphics.drawString("MAMÃE, É BOM!", (int)(largura/3), novaAltura - 100);
			graphics.drawImage(seguraBebe, (int)(largura/80), novaAltura - 400, null);
		}else if(rating >= 8) {
			graphics.drawString("MAMÃE, TOPZERA!", (int)(largura/3), novaAltura - 100);
			graphics.drawImage(seguraBebe, (int)(largura/80), novaAltura - 400, null);
		}
		
		
		// escrever a nova imagem em um arquivo
		File figurinha = new File(nomeArquivo);
		figurinha.mkdirs();
		ImageIO.write(novaImagem, "png", figurinha);
		
	}
	
	
	public BufferedImage incluiBebe(double rating) throws IOException {
		
		BufferedImage imagemOriginal = ImageIO.read(new File("entrada/bebetop.png"));
		if(rating < 6) {
			 imagemOriginal = ImageIO.read(new File("entrada/beberuim.png"));
		} else if(rating >= 6 && rating < 8) {
			 imagemOriginal = ImageIO.read(new File("entrada/bebemedio.png"));
		}else if(rating >= 8) {
			 imagemOriginal = ImageIO.read(new File("entrada/bebetop.png"));
		}
		
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura;
		int novaLargura = largura;
		
		BufferedImage novaImagem = new BufferedImage(novaLargura, novaAltura, BufferedImage.TRANSLUCENT);
		
		Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);
		
		return novaImagem;
	}
}
