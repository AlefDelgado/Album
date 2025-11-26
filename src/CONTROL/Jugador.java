package CONTROL;

import java.text.Normalizer;

public class Jugador {
    private String nombreEquipo;
    private String nombreJugador;
    private String posicion;
    private String numero;
    private String nacionalidad;
    private int edad;
    private String division;
    private String rutaImagen;
    private String rutaEscudo;
    private String tipo;
    
    public Jugador() {
        this.numero = "";
        this.tipo = "Jugador";
        this.rutaImagen = "";
        this.rutaEscudo = "";
    }
    
    public Jugador(String nombreEquipo, String nombreJugador, String posicion, 
                   String numero, String nacionalidad, int edad, String division,
                   String rutaImagen, String rutaEscudo, String tipo) {
        this.nombreEquipo = nombreEquipo;
        this.nombreJugador = nombreJugador;
        this.posicion = posicion;
        this.numero = numero;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.division = division;
        this.rutaImagen = rutaImagen;
        this.rutaEscudo = rutaEscudo;
        this.tipo = tipo;
    }
    
    // Getters
    public String getNombreEquipo() 
    {
        return nombreEquipo; 
    }
    public String getNombreJugador()
    {
        return nombreJugador; 
    }
    public String getPosicion()
    {
        return posicion; 
    }
    public String getNumero()
    {
        return numero; 
    }
    public String getNacionalidad() 
    {
        return nacionalidad; 
    }
    public int getEdad()
    {
        return edad; 
    }
    public String getDivision()
    {
        return division; 
    }
    public String getRutaImagen()
    {
        return rutaImagen; 
    }
    public String getRutaEscudo()
    {
        return rutaEscudo;
    }
    public String getTipo()
    {
        return tipo; 
    }
    
    // Setters
    public void setNombreEquipo(String nombreEquipo)
    {
        this.nombreEquipo = nombreEquipo;
    }
    public void setNombreJugador(String nombreJugador)
    {
        this.nombreJugador = nombreJugador;
    }
    public void setPosicion(String posicion)
    {
        this.posicion = posicion;
    }
    public void setNumero(String numero)
    {
        this.numero = numero;
    }
    public void setNacionalidad(String nacionalidad)
    {
        this.nacionalidad = nacionalidad;
    }
    public void setEdad(int edad)
    {
        this.edad = edad; 
    }
    public void setDivision(String division) 
    {
        this.division = division; 
    }
    public void setRutaImagen(String rutaImagen)
    {
        this.rutaImagen = rutaImagen; 
    }
    public void setRutaEscudo(String rutaEscudo)
    {
        this.rutaEscudo = rutaEscudo;
    }
    public void setTipo(String tipo) 
    {
        this.tipo = tipo;
    }
    
    /*
     * Normaliza un texto removiendo acentos y caracteres especiales
     */
    private String normalizarTexto(String texto) 
    {
        // Remover acentos usando NFD (Normalization Form Canonical Decomposition)
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        // Remover los caracteres diacríticos (acentos)
        normalizado = normalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        // Convertir a minúsculas y reemplazar espacios por guiones bajos
        normalizado = normalizado.toLowerCase().replace(" ", "_");
        return normalizado;
    }
    
    /*
     * Genera automáticamente la ruta de la imagen del jugador
     * Formato: imagenes/jugadores/{equipo_normalizado}_{numero}.jpg
     * Ejemplo: imagenes/jugadores/barcelona_10.jpg
     */
    public String generarRutaImagenAuto() 
    {
        String equipoNormalizado = normalizarTexto(nombreEquipo);
        String numeroNormalizado = tipo.equals("Director Técnico") ? "dt" : numero;
        return "imagenes/jugadores/" + equipoNormalizado + "_" + numeroNormalizado + ".jpg";
    }
    
    /*
     * Genera automáticamente la ruta del escudo del equipo
     * Formato: imagenes/escudos/{equipo_normalizado}.png
     * Ejemplo: imagenes/escudos/barcelona.png
     */
    public String generarRutaEscudoAuto() 
    {
        String equipoNormalizado = normalizarTexto(nombreEquipo);
        return "imagenes/escudos/" + equipoNormalizado + ".png";
    }
    
    /*
     * Convierte el jugador a formato String para guardar en archivo
     * Formato: equipo|nombre|posicion|numero|nacionalidad|edad|division|rutaImagen|rutaEscudo|tipo
     */
    public String toFileString() 
    {
        return nombreEquipo + "|" + nombreJugador + "|" + posicion + "|" + 
               numero + "|" + nacionalidad + "|" + edad + "|" + division + "|" +
               rutaImagen + "|" + rutaEscudo + "|" + tipo;
    }
    
    /*
     * Crea un objeto Jugador desde una línea de texto del archivo
     */
    public static Jugador fromFileString(String linea) 
    {
        try 
        {
            String[] datos = linea.split("\\|", -1); // -1 para incluir campos vacíos
            if (datos.length == 10) 
            {
                return new Jugador(
                    datos[0], // nombreEquipo
                    datos[1], // nombreJugador
                    datos[2], // posicion
                    datos[3], // numero
                    datos[4], // nacionalidad
                    Integer.parseInt(datos[5]), // edad
                    datos[6], // division
                    datos[7], // rutaImagen
                    datos[8], // rutaEscudo
                    datos[9]  // tipo
                );
            }
        } catch (Exception e) 
        {
            System.err.println("❌ Error al parsear línea: " + linea);
            System.err.println("   Detalle: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public String toString() 
    {
        return nombreJugador + " (#" + numero + ") - " + nombreEquipo;
    }
}