package CONTROL;

import ARCHIVOS.ManejadorArchivos;
import java.util.ArrayList;

public class GestorAlbum {

    private ArrayList<Jugador> jugadores;
    private ManejadorArchivos manejadorArchivos;

    public GestorAlbum() {
        jugadores = new ArrayList<>();
        manejadorArchivos = new ManejadorArchivos();
        cargarDatos();
    }

    private void cargarDatos() {
        jugadores = manejadorArchivos.cargarJugadores();
    }

    public void guardarDatos() {
        manejadorArchivos.guardarJugadores(jugadores);
    }

    public boolean agregarJugador(Jugador jugador) {
        if (buscarJugador(jugador.getNombreEquipo(), jugador.getNumero()) != null) {
            return false;
        }
        jugadores.add(jugador);
        guardarDatos();
        return true;
    }

    public Jugador buscarJugador(String nombreEquipo, String numero) {
        for (Jugador j : jugadores) {
            if (j.getNombreEquipo().equalsIgnoreCase(nombreEquipo)
                    && j.getNumero().equals(numero)) {
                return j;
            }
        }
        return null;
    }

    public boolean modificarJugador(String equipoBuscar, String numeroBuscar, Jugador nuevosDatos) {
        Jugador jugador = buscarJugador(equipoBuscar, numeroBuscar);
        if (jugador != null) {
            jugador.setNombreEquipo(nuevosDatos.getNombreEquipo());
            jugador.setNombreJugador(nuevosDatos.getNombreJugador());
            jugador.setPosicion(nuevosDatos.getPosicion());
            jugador.setNumero(nuevosDatos.getNumero());
            jugador.setNacionalidad(nuevosDatos.getNacionalidad());
            jugador.setEdad(nuevosDatos.getEdad());
            jugador.setDivision(nuevosDatos.getDivision());
            jugador.setRutaImagen(nuevosDatos.getRutaImagen());
            jugador.setRutaEscudo(nuevosDatos.getRutaEscudo());
            jugador.setTipo(nuevosDatos.getTipo());
            guardarDatos();
            return true;
        }
        return false;
    }

    public boolean eliminarJugador(String nombreEquipo, String numero) {
        Jugador jugador = buscarJugador(nombreEquipo, numero);
        if (jugador != null) {
            jugadores.remove(jugador);
            guardarDatos();
            return true;
        }
        return false;
    }

    public ArrayList<Jugador> obtenerTodosJugadores() {
        return new ArrayList<>(jugadores);
    }

    public ArrayList<Jugador> obtenerJugadoresPorEquipo(String nombreEquipo) {
        ArrayList<Jugador> resultado = new ArrayList<>();
        for (Jugador j : jugadores) {
            if (j.getNombreEquipo().equalsIgnoreCase(nombreEquipo)) {
                resultado.add(j);
            }
        }
        return resultado;
    }

    public ArrayList<String> obtenerEquipos() {
        ArrayList<String> equipos = new ArrayList<>();
        for (Jugador j : jugadores) {
            if (!equipos.contains(j.getNombreEquipo())) {
                equipos.add(j.getNombreEquipo());
            }
        }
        return equipos;
    }

    public static boolean validarCampos(Jugador jugador) {
        return jugador.getNombreEquipo() != null && !jugador.getNombreEquipo().trim().isEmpty()
                && jugador.getNombreJugador() != null && !jugador.getNombreJugador().trim().isEmpty()
                && jugador.getPosicion() != null && !jugador.getPosicion().trim().isEmpty()
                && jugador.getNacionalidad() != null && !jugador.getNacionalidad().trim().isEmpty()
                && jugador.getEdad() > 0
                && jugador.getDivision() != null && !jugador.getDivision().trim().isEmpty();
    }
}
