package net.salesianos.elementos;

public class Elemento {
    private String codigo;
    private String titulo;
    private String descripcion;
    private byte puntuacion;

    public Elemento(String codigo, String titulo, String descripcion, byte puntuacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.puntuacion = puntuacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(byte puntuacion) {
        this.puntuacion = puntuacion;
    }

}
