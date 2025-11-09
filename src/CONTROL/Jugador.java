package CONTROL;

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
    public String getNombreEquipo() { return nombreEquipo; }
    public String getNombreJugador() { return nombreJugador; }
    public String getPosicion() { return posicion; }
    public String getNumero() { return numero; }
    public String getNacionalidad() { return nacionalidad; }
    public int getEdad() { return edad; }
    public String getDivision() { return division; }
    public String getRutaImagen() { return rutaImagen; }
    public String getRutaEscudo() { return rutaEscudo; }
    public String getTipo() { return tipo; }
    
    // Setters
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }
    public void setNombreJugador(String nombreJugador) { this.nombreJugador = nombreJugador; }
    public void setPosicion(String posicion) { this.posicion = posicion; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setDivision(String division) { this.division = division; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
    public void setRutaEscudo(String rutaEscudo) { this.rutaEscudo = rutaEscudo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String toFileString() {
        return nombreEquipo + "|" + nombreJugador + "|" + posicion + "|" + 
               numero + "|" + nacionalidad + "|" + edad + "|" + division + "|" +
               rutaImagen + "|" + rutaEscudo + "|" + tipo;
    }
    
    public static Jugador fromFileString(String linea) {
        String[] datos = linea.split("\\|");
        if (datos.length == 10) {
            return new Jugador(
                datos[0], datos[1], datos[2], datos[3], datos[4],
                Integer.parseInt(datos[5]), datos[6], datos[7], datos[8], datos[9]
            );
        }
        return null;
    }
    
    @Override
    public String toString() {
        return nombreJugador + " (#" + numero + ") - " + nombreEquipo;
    }
}