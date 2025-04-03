package org.DiegoHVZ.model;

public class GeneroMusical extends Catalogo {
    private String generoMusical;

    public GeneroMusical() {
    }

    public GeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    @Override
    public String toString() {
        return "Genero Musical{" +
                "Genero musical='" + generoMusical + '\'' +
                ", id=" + id +
                '}';
    }
}
