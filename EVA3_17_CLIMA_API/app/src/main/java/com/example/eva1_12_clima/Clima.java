package com.example.eva1_12_clima;

public class Clima {
    private int imagen;
    private String ciudad, desc;
    private double temp;

    public Clima() {
        this.imagen = R.drawable.sunny;
        this.ciudad = "Juarez";
        this.temp = 27.3;
        this.desc = "Rojo atardecer con crepusculos arrebolados";
    }
    public Clima(int imagen, String ciudad, double temp, String desc) {
        this.imagen = imagen;
        this.ciudad = ciudad;
        this.temp = temp;
        this.desc = desc;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
