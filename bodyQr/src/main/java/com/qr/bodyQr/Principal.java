package com.qr.bodyQr;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.qr.bodyQr.entity.Contacto;

public class Principal {

	 public static void main(String[] args) {
		 
		   // Crear objeto de contacto
	        Contacto contact = new Contacto();
	        contact.setNombre("BunkerGym");
	        contact.setNumero("7223109708");
	    
	        // Convertir a formato vCard
	        String vCardContent = contact.toVCardString();
		 
		 
	        String qrData = vCardContent;
	        String imagePath = "C:\\Users\\Emmanuel\\Documents\\qr\\logoBunker300.jpg";
	        String outputImagePath = "C:\\Users\\Emmanuel\\Documents\\qr\\logoBunkerQrGit.jpg";

	        try {
	            // Generar el código QR
	            MultiFormatWriter writer = new MultiFormatWriter();
	            BitMatrix matrix = writer.encode(qrData, BarcodeFormat.QR_CODE, 300,300);

	            // Crear una imagen a partir del código QR
	            BufferedImage qrImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB); // ARGB para canal alfa
	            Graphics2D graphics = qrImage.createGraphics();

	            // Rellenar el fondo blanco
	            graphics.setColor(java.awt.Color.WHITE);
	            graphics.fillRect(0, 0, 300, 300);

	            // Dibujar el código QR en negro
	            graphics.setColor(java.awt.Color.BLACK);
	            for (int i = 0; i < 300; i++) {
	                for (int j = 0; j < 300; j++) {
	                    if (matrix.get(i, j)) {
	                        graphics.fillRect(i, j, 1, 1);
	                    }
	                }
	            }		

	            // Cargar la imagen que se superpondrá en el código QR
	            BufferedImage overlayImage = ImageIO.read(new File(imagePath));

	            // Calcular la posición para centrar la imagen
	            int x = (qrImage.getWidth() - overlayImage.getWidth()) / 2;
	            int y = (qrImage.getHeight() - overlayImage.getHeight()) / 2;

	            // Establecer la transparencia para la superposición
	            AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f); // 0.5f = 50% de opacidad
	            graphics.setComposite(alpha);

	            // Superponer la imagen translúcida en el código QR
	            graphics.drawImage(overlayImage, x, y, null);

	            // Guardar la imagen resultante
	            ImageIO.write(qrImage, "png", new File(outputImagePath));

	            System.out.println("Código QR con imagen translúcida creada en: " + outputImagePath);
	        } catch (Exception e) {
	            System.out.println("Error al crear el código QR con imagen translúcida: " + e.getMessage());
	        }
	    }
}
