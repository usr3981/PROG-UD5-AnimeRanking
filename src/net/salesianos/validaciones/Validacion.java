package net.salesianos.validaciones;

public class Validacion {

    // Verifica si un campo es texto válido (no vacío y es String)
    public static boolean validarCampoTexto(Object datoIngresado) {
        return datoIngresado instanceof String && !((String) datoIngresado).trim().isEmpty();
    }

    // Verifica si el número está en el rango 0 a 5 (inclusive)
    public static boolean validarCampoNumerico(int datoIngresado) {
        return datoIngresado >= 0 && datoIngresado <= 5;
    }
}
