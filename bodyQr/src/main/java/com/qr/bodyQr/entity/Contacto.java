package com.qr.bodyQr.entity;

public class Contacto {


	String Nombre;
	String Numero;
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getNumero() {
		return Numero;
	}
	public void setNumero(String numero) {
		Numero = numero;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Contacto [Nombre=" + Nombre + ", Numero=" + Numero + "]";
	}
	
//	Se genera vCard lo cual es el formato que se vera en el celular.
	public String toVCardString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BEGIN:VCARD\n");
        sb.append("VERSION:3.0\n");
        sb.append("FN:").append(Nombre).append("\n");
        sb.append("TEL:").append(Numero).append("\n");
        sb.append("END:VCARD\n");
        return sb.toString();
    }
	
	
}
