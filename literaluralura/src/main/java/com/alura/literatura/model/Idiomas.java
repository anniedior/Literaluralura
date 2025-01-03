package com.alura.literatura.model;

public enum Idiomas {
    INGLES("en"),
    CASTELLANO("es"),
    PORTUGUES("pt"),
    FRANCES("fr"),
    ALEMAN("de"),
    ITALIANO("it");

    private String idioma;

    Idiomas (String idioma){
        this.idioma = idioma;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idiomas1 : Idiomas.values()) {
            if (idiomas1.idioma.equalsIgnoreCase(text)) {
                return idiomas1;
            }
        }
        throw new IllegalArgumentException("Idioma no encontrado: " + text);
    }

    @Override
    public String toString() {
        return idioma;
    }

}
