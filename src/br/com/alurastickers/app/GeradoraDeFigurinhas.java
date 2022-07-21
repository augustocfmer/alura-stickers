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

	public void cria(InputStream inputStream, String nomeArquivo) throws IOException {
		
		//leitura da imagem
		BufferedImage imagemOriginal = ImageIO.read(inputStream);
		
		// cria nova imagem em memória com transparência e com novo tamanho
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
		
		// copiar a imagem original para a nova imagem (em memória)
		Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);
		
		//configurar a fonte
		Font fonte = new Font("Comic Sans MS", Font.BOLD, (int)(largura/15));
		graphics.setColor(Color.YELLOW);
		graphics.setFont(fonte);
		
		graphics.drawString("TOPZERA!", (int)(largura/3), novaAltura - 100);
		
		
		// escrever a nova imagem em um arquivo
		File figurinha = new File(nomeArquivo);
		figurinha.mkdirs();
		ImageIO.write(novaImagem, "png", figurinha);
		
	}
	
}
