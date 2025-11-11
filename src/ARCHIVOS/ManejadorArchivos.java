package ARCHIVOS;

import CONTROL.Jugador;
import java.io.*;
import java.util.ArrayList;

public class ManejadorArchivos {
    private static final String ARCHIVO = "jugadores.txt";
    
    public void guardarJugadores(ArrayList<Jugador> jugadores) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Jugador j : jugadores) {
                writer.write(j.toFileString());
                writer.newLine();
            }
            System.out.println("✅ Guardados " + jugadores.size() + " jugadores en " + ARCHIVO);
        } catch (IOException e) {
            System.err.println("❌ Error al guardar: " + e.getMessage());
        }
    }
    
    public ArrayList<Jugador> cargarJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
                String linea;
                int contador = 0;
                while ((linea = reader.readLine()) != null) {
                    contador++;
                    Jugador j = Jugador.fromFileString(linea);
                    if (j != null) {
                        jugadores.add(j);
                    } else {
                        System.err.println("⚠️ Línea " + contador + " mal formateada");
                    }
                }
                System.out.println("📂 Cargados " + jugadores.size() + " jugadores desde archivo");
                
                // Si hay menos de 100 jugadores, recrear datos completos
                if (jugadores.size() < 100) {
                    System.out.println("⚠️ Datos incompletos (" + jugadores.size() + "/132). Recreando base de datos...");
                    archivo.delete();
                    return crearDatosPrueba();
                }
            } catch (IOException e) {
                System.err.println("❌ Error al cargar: " + e.getMessage());
                return crearDatosPrueba();
            }
        } else {
            System.out.println("📝 Archivo no existe. Creando datos iniciales...");
            return crearDatosPrueba();
        }
        
        return jugadores;
    }
    
    private ArrayList<Jugador> crearDatosPrueba() {
        ArrayList<Jugador> prueba = new ArrayList<>();
        
        System.out.println("🏗️ Creando base de datos completa con imágenes...");
        
        // ==================== EQUIPOS MASCULINOS ====================
        
        // BARCELONA
        prueba.add(crearJugadorConImagenes("Barcelona", "Joan García", "Portero", "1", "España", 23, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Jules Koundé", "Defensa central", "2", "Francia", 26, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Ronald Araújo", "Defensa central", "3", "Uruguay", 26, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Pau Cubarsí", "Defensa central", "4", "España", 18, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Alejandro Balde", "Lateral izquierdo", "5", "España", 21, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Frenkie de Jong", "Mediocampista", "6", "Países Bajos", 28, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Pedri González", "Mediocampista ofensivo", "7", "España", 22, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Raphinha", "Extremo derecho", "8", "Brasil", 28, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Lamine Yamal", "Extremo derecho", "9", "España", 17, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Robert Lewandowski", "Delantero centro", "10", "Polonia", 37, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Marcus Rashford", "Extremo izquierdo", "11", "Inglaterra", 28, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona", "Hansi Flick", "Director Técnico", "DT", "Alemania", 59, "Primera División", "Director Técnico"));
        
        // MANCHESTER CITY
        prueba.add(crearJugadorConImagenes("Manchester City", "Gianluigi Donnarumma", "Portero", "1", "Italia", 26, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Matheus Nunes", "Mediocampista", "2", "Portugal", 27, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Rúben Díaz", "Defensa central", "3", "Portugal", 28, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Josko Gvardiol", "Defensa central", "4", "Croacia", 23, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Nathan Aké", "Defensa lateral", "5", "Países Bajos", 30, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Bernardo Silva", "Mediocampista ofensivo", "6", "Portugal", 31, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Rodri Hernández", "Mediocampista defensivo", "7", "España", 29, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Tijjani Reijnders", "Mediocampista", "8", "Países Bajos", 27, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Rayan Cherki", "Extremo", "9", "Francia", 22, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Erling Haaland", "Delantero centro", "10", "Noruega", 25, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Phil Foden", "Extremo", "11", "Inglaterra", 25, "Premier League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City", "Pep Guardiola", "Director Técnico", "DT", "España", 54, "Premier League", "Director Técnico"));
        
        // BAYERN MUNICH
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Manuel Neuer", "Portero", "1", "Alemania", 39, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Dayot Upamecano", "Defensa central", "2", "Francia", 27, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Jonathan Tah", "Defensa central", "3", "Alemania", 29, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Alphonso Davies", "Lateral izquierdo", "4", "Canadá", 25, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Josip Stanišić", "Defensa lateral", "5", "Croacia", 25, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Joshua Kimmich", "Mediocampista defensivo", "6", "Alemania", 30, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Florian Wirtz", "Mediocampista ofensivo", "7", "Alemania", 22, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Jamal Musiala", "Mediocampista ofensivo", "8", "Alemania", 22, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Luis Diaz", "Extremo", "9", "Colombia", 29, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Harry Kane", "Delantero centro", "10", "Inglaterra", 32, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Michael Olise", "Extremo derecho", "11", "Francia", 24, "Bundesliga", "Jugador"));
        prueba.add(crearJugadorConImagenes("Bayern Munich", "Vincent Kompany", "Director Técnico", "DT", "Bélgica", 39, "Bundesliga", "Director Técnico"));
        
        // ATLETICO DE MADRID
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Jan Oblak", "Portero", "1", "Eslovenia", 32, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Nahuel Molina", "Lateral derecho", "2", "Argentina", 27, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Clément Lenglet", "Defensa central", "3", "Francia", 30, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Robin Le Normand", "Defensa central", "4", "España", 28, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Javi Galán", "Lateral izquierdo", "5", "España", 30, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Conor Gallagher", "Mediocampista mixto", "6", "Inglaterra", 25, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Rodrigo De Paul", "Mediocampista ofensivo", "7", "Argentina", 31, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Pablo Barrios", "Mediocampista", "8", "España", 22, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Giuliano Simeone", "Delantero", "9", "Argentina", 22, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Julián Álvarez", "Delantero centro", "10", "Argentina", 25, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Antoine Griezmann", "Delantero", "11", "Francia", 34, "Primera División", "Jugador"));
        prueba.add(crearJugadorConImagenes("Atletico de Madrid", "Diego Simeone", "Director Técnico", "DT", "Argentina", 55, "Primera División", "Director Técnico"));
        
        // PSG
        prueba.add(crearJugadorConImagenes("PSG", "Lucas Chevalier", "Portero", "1", "Francia", 23, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Achraf Hakimi", "Lateral derecho", "2", "Marruecos", 27, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Lucas Beraldo", "Defensa central", "3", "Brasil", 21, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Willian Pacho", "Defensa central", "4", "Ecuador", 24, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Nuno Mendes", "Lateral izquierdo", "5", "Portugal", 23, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Fabián Ruiz", "Mediocampista", "6", "España", 29, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Vitinha", "Mediocampista ofensivo", "7", "Portugal", 25, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "João Neves", "Mediocampista", "8", "Portugal", 21, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Ousmane Dembélé", "Extremo derecho", "9", "Francia", 28, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Désiré Doué", "Extremo izquierdo", "10", "Francia", 20, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Khvicha Kvaratskhelia", "Delantero", "11", "Georgia", 24, "Ligue 1", "Jugador"));
        prueba.add(crearJugadorConImagenes("PSG", "Luis Enrique", "Director Técnico", "DT", "España", 55, "Ligue 1", "Director Técnico"));
        
        // ==================== EQUIPOS FEMENINOS ====================
        
        // BARCELONA FEMENINO
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Cata Coll", "Portera", "1", "España", 24, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Irene Paredes", "Defensa central", "2", "España", 33, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "María León", "Defensa central", "3", "España", 29, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Salma Paralluelo", "Extrema", "4", "España", 21, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Patri Guijarro", "Mediocampista", "5", "España", 27, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Aitana Bonmatí", "Mediocampista ofensiva", "6", "España", 27, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Ewa Pajor", "Delantera centro", "7", "Polonia", 28, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Clàudia Pina", "Delantera", "8", "España", 24, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Vicky López", "Mediocampista ofensiva", "9", "España", 18, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Alexia Putellas", "Mediocampista ofensiva", "10", "España", 31, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Esmee Brugts", "Mediocampista", "11", "Países Bajos", 22, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Barcelona Femenino", "Pere Romeu", "Director Técnico", "DT", "España", 40, "Liga F", "Director Técnico"));
        
        // MANCHESTER CITY FEMENINO
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Khiara Keating", "Portera", "1", "Inglaterra", 20, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Gracie Prior", "Defensa lateral", "2", "Inglaterra", 19, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Alex Greenwood", "Defensa central", "3", "Inglaterra", 31, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Leila Ouahabi", "Defensa lateral", "4", "España", 32, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Kerstin Casparij", "Defensa", "5", "Países Bajos", 25, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Laura Blindkilde", "Mediocampista", "6", "Inglaterra", 21, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Yui Hasegawa", "Mediocampista central", "7", "Japón", 28, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Lauren Hemp", "Extrema izquierda", "8", "Inglaterra", 25, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Iman Beney", "Delantera", "9", "Suiza", 18, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Vivianne Miedema", "Delantera centro", "10", "Países Bajos", 29, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Bunny Shaw", "Delantera centro", "11", "Jamaica", 28, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Manchester City Femenino", "Nick Cushing", "Director Técnico", "DT", "Inglaterra", 40, "Women's Super League", "Director Técnico"));
        
        // CHELSEA FEMENINO
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Zecira Musovic", "Portera", "1", "Suecia", 29, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Nathalie Björn", "Defensa central", "2", "Suecia", 27, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Millie Bright", "Defensa central", "3", "Inglaterra", 32, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Niamh Charles", "Lateral izquierdo", "4", "Inglaterra", 26, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Mayra Ramírez", "Delantera", "5", "Colombia", 25, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Ève Périsset", "Lateral derecho", "6", "Francia", 30, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Erin Cuthbert", "Mediocampista central", "7", "Escocia", 27, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Oriane Jean-François", "Mediocampista defensiva", "8", "Francia", 23, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Catarina Macário", "Mediocampista ofensiva", "9", "EE.UU.", 26, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Maika Hamano", "Extrema", "10", "Japón", 21, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Agnes Beever-Jones", "Extrema", "11", "Inglaterra", 21, "Women's Super League", "Jugador"));
        prueba.add(crearJugadorConImagenes("Chelsea Femenino", "Sonia Bompastor", "Directora Técnica", "DT", "Francia", 44, "Women's Super League", "Director Técnico"));
        
        // REAL MADRID FEMENINO
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Merle Frohms", "Portera", "1", "Alemania", 30, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Shei", "Defensa lateral", "2", "España", 27, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Maëlle Lakrar", "Defensa central", "3", "Francia", 25, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "María Méndez", "Defensa central", "4", "España", 23, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Yasmin Mrabet", "Mediocampista defensiva", "5", "Marruecos", 25, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Sandie Toletti", "Mediocampista central", "6", "Francia", 29, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Filippa Angeldahl", "Mediocampista", "7", "Suecia", 31, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Sara Däbritz", "Mediocampista ofensiva", "8", "Alemania", 31, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Athenea del Castillo", "Extrema", "9", "España", 24, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Linda Caicedo", "Extrema", "10", "Colombia", 20, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Signe Bruun", "Delantera centro", "11", "Dinamarca", 27, "Liga F", "Jugador"));
        prueba.add(crearJugadorConImagenes("Real Madrid Femenino", "Pau Quesada", "Director Técnico", "DT", "España", 42, "Liga F", "Director Técnico"));
        
        // JUVENTUS FEMENINO
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Pauline Peyraud-Magnin", "Portera", "1", "Francia", 32, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Emma Kullberg", "Defensa central", "2", "Suecia", 33, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Cecilia Salvai", "Defensa central", "3", "Italia", 30, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Lia Walti", "Lateral derecha", "4", "Inglaterra", 24, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Paulina Krumbiegel", "Lateral izquierda", "5", "Alemania", 24, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Sarah Schatzer", "Mediocampista central", "6", "Italia", 21, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Barbara Bonansea", "Extrema", "7", "Italia", 33, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Tatiana Pinto", "Mediocampista ofensiva", "8", "Portugal", 31, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Stolen-Godo", "Mediocampista defensiva", "9", "Noruega", 26, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Cristiana Girelli", "Delantera centro", "10", "Italia", 34, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Rikke Vangsgaard", "Delantera", "11", "Dinamarca", 29, "Serie A", "Jugador"));
        prueba.add(crearJugadorConImagenes("Juventus Femenino", "Massimiliano Brambilla", "Director Técnico", "DT", "Italia", 51, "Serie A", "Director Técnico"));
        
        System.out.println("✅ Base de datos creada con " + prueba.size() + " registros");
        guardarJugadores(prueba);
        return prueba;
    }
    
    // MÉTODO AUXILIAR para crear jugadores con rutas automáticas
    private Jugador crearJugadorConImagenes(String equipo, String nombre, String posicion, 
                                            String numero, String nacionalidad, int edad, 
                                            String division, String tipo) {
        Jugador j = new Jugador(equipo, nombre, posicion, numero, nacionalidad, edad, 
                                division, "", "", tipo);
        j.setRutaImagen(j.generarRutaImagenAuto());
        j.setRutaEscudo(j.generarRutaEscudoAuto());
        return j;
    }
}