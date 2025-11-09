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
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }
    
    public ArrayList<Jugador> cargarJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        
        if (!archivo.exists()) {
            return crearDatosPrueba();
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Jugador j = Jugador.fromFileString(linea);
                if (j != null) {
                    jugadores.add(j);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
        
        return jugadores;
    }
    
    private ArrayList<Jugador> crearDatosPrueba() {
        ArrayList<Jugador> prueba = new ArrayList<>();
        
        // ==================== EQUIPOS MASCULINOS ====================
        
        // BARCELONA
        prueba.add(new Jugador("Barcelona", "Joan García", "Portero", "1", "España", 23, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Jules Koundé", "Defensa central", "2", "Francia", 26, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Ronald Araújo", "Defensa central", "3", "Uruguay", 26, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Pau Cubarsí", "Defensa central", "4", "España", 18, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Alejandro Balde", "Lateral izquierdo", "5", "España", 21, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Frenkie de Jong", "Mediocampista", "6", "Países Bajos", 28, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Pedri González", "Mediocampista ofensivo", "7", "España", 22, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Raphinha", "Extremo derecho", "8", "Brasil", 28, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Lamine Yamal", "Extremo derecho", "9", "España", 17, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Robert Lewandowski", "Delantero centro", "10", "Polonia", 37, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Marcus Rashford", "Extremo izquierdo", "11", "Inglaterra", 28, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona", "Hansi Flick", "Director Técnico", "--", "Alemania", 59, "Primera División", "", "", "Director Técnico"));
        
        // MANCHESTER CITY
        prueba.add(new Jugador("Manchester City", "Gianluigi Donnarumma", "Portero", "1", "Italia", 26, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Matheus Nunes", "Mediocampista", "2", "Portugal", 27, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Rúben Díaz", "Defensa central", "3", "Portugal", 28, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Josko Gvardiol", "Defensa central", "4", "Croacia", 23, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Nathan Aké", "Defensa lateral", "5", "Países Bajos", 30, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Bernardo Silva", "Mediocampista ofensivo", "6", "Portugal", 31, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Rodri Hernández", "Mediocampista defensivo", "7", "España", 29, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Tijjani Reijnders", "Mediocampista", "8", "Países Bajos", 27, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Rayan Cherki", "Extremo / Mediapunta", "9", "Francia", 22, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Erling Haaland", "Delantero centro", "10", "Noruega", 25, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Phil Foden", "Extremo / Mediocampista", "11", "Inglaterra", 25, "Premier League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City", "Pep Guardiola", "Director Técnico", "--", "España", 54, "Premier League", "", "", "Director Técnico"));
        
        // BAYERN MUNICH
        prueba.add(new Jugador("Bayern Munich", "Manuel Neuer", "Portero", "1", "Alemania", 39, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Dayot Upamecano", "Defensa central", "2", "Francia", 27, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Jonathan Tah", "Defensa central", "3", "Alemania", 29, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Alphonso Davies", "Lateral izquierdo", "4", "Canadá", 25, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Josip Stanišić", "Defensa lateral", "5", "Croacia", 25, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Joshua Kimmich", "Mediocampista defensivo", "6", "Alemania", 30, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Florian Wirtz", "Mediocampista ofensivo", "7", "Alemania", 22, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Jamal Musiala", "Mediocampista ofensivo", "8", "Alemania", 22, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Luis Diaz", "Extremo", "9", "Colombia", 29, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Harry Kane", "Delantero centro", "10", "Inglaterra", 32, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Michael Olise", "Extremo derecho", "11", "Francia", 24, "Bundesliga", "", "", "Jugador"));
        prueba.add(new Jugador("Bayern Munich", "Vincent Kompany", "Director Técnico", "--", "Bélgica", 39, "Bundesliga", "", "", "Director Técnico"));
        
        // ATLETICO DE MADRID
        prueba.add(new Jugador("Atletico de Madrid", "Jan Oblak", "Portero", "1", "Eslovenia", 32, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Nahuel Molina", "Lateral derecho", "2", "Argentina", 27, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Clément Lenglet", "Defensa central", "3", "Francia", 30, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Robin Le Normand", "Defensa central", "4", "España", 28, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Javi Galán", "Lateral izquierdo", "5", "España", 30, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Conor Gallagher", "Mediocampista mixto", "6", "Inglaterra", 25, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Rodrigo De Paul", "Mediocampista ofensivo", "7", "Argentina", 31, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Pablo Barrios", "Mediocampista", "8", "España", 22, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Giuliano Simeone", "Delantero / Extremo", "9", "Argentina", 22, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Julián Álvarez", "Delantero centro", "10", "Argentina", 25, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Antoine Griezmann", "Delantero / Mediapunta", "11", "Francia", 34, "Primera División", "", "", "Jugador"));
        prueba.add(new Jugador("Atletico de Madrid", "Diego Simeone", "Director Técnico", "--", "Argentina", 55, "Primera División", "", "", "Director Técnico"));
        
        // PSG
        prueba.add(new Jugador("PSG", "Lucas Chevalier", "Portero", "1", "Francia", 23, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Achraf Hakimi", "Lateral derecho", "2", "Marruecos", 27, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Lucas Beraldo", "Defensa central", "3", "Brasil", 21, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Willian Pacho", "Defensa central", "4", "Ecuador", 24, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Nuno Mendes", "Lateral izquierdo", "5", "Portugal", 23, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Fabián Ruiz", "Mediocampista", "6", "España", 29, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Vitinha", "Mediocampista ofensivo", "7", "Portugal", 25, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "João Neves", "Mediocampista", "8", "Portugal", 21, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Ousmane Dembélé", "Extremo derecho", "9", "Francia", 28, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Désiré Doué", "Extremo izquierdo", "10", "Francia", 20, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Khvicha Kvaratskhelia", "Delantero / Extremo", "11", "Georgia", 24, "Ligue 1", "", "", "Jugador"));
        prueba.add(new Jugador("PSG", "Luis Enrique", "Director Técnico", "--", "España", 55, "Ligue 1", "", "", "Director Técnico"));
        
        // ==================== EQUIPOS FEMENINOS ====================
        
        // BARCELONA FEMENINO
        prueba.add(new Jugador("Barcelona Femenino", "Cata Coll", "Portera", "1", "España", 24, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Irene Paredes", "Defensa central", "2", "España", 33, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "María León", "Defensa central", "3", "España", 29, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Salma Paralluelo", "Extrema / Delantera", "4", "España", 21, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Patri Guijarro", "Mediocampista", "5", "España", 27, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Aitana Bonmatí", "Mediocampista ofensiva", "6", "España", 27, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Ewa Pajor", "Delantera centro", "7", "Polonia", 28, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Clàudia Pina", "Delantera / Extrema", "8", "España", 24, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Vicky López", "Mediocampista ofensiva", "9", "España", 18, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Alexia Putellas", "Mediocampista ofensiva", "10", "España", 31, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Esmee Brugts", "Mediocampista / Lateral", "11", "Países Bajos", 22, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Barcelona Femenino", "Pere Romeu", "Director Técnico", "--", "España", 40, "Liga F", "", "", "Director Técnico"));
        
        // MANCHESTER CITY FEMENINO
        prueba.add(new Jugador("Manchester City Femenino", "Khiara Keating", "Portera", "1", "Inglaterra", 20, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Gracie Prior", "Defensa lateral", "2", "Inglaterra", 19, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Alex Greenwood", "Defensa central", "3", "Inglaterra", 31, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Leila Ouahabi", "Defensa lateral", "4", "España", 32, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Kerstin Casparij", "Defensa / Mediocampista", "5", "Países Bajos", 25, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Laura Blindkilde", "Mediocampista", "6", "Inglaterra", 21, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Yui Hasegawa", "Mediocampista central", "7", "Japón", 28, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Lauren Hemp", "Extrema izquierda", "8", "Inglaterra", 25, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Iman Beney", "Delantera", "9", "Suiza", 18, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Vivianne Miedema", "Delantera centro", "10", "Países Bajos", 29, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Bunny Shaw", "Delantera centro", "11", "Jamaica", 28, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Manchester City Femenino", "Nick Cushing", "Director Técnico", "--", "Inglaterra", 40, "Women's Super League", "", "", "Director Técnico"));
        
        // CHELSEA FEMENINO
        prueba.add(new Jugador("Chelsea Femenino", "Zecira Musovic", "Portera", "1", "Suecia", 29, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Nathalie Björn", "Defensa central", "2", "Suecia", 27, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Millie Bright", "Defensa central", "3", "Inglaterra", 32, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Niamh Charles", "Lateral izquierdo", "4", "Inglaterra", 26, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Mayra Ramírez", "Delantera / Extrema", "5", "Colombia", 25, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Ève Périsset", "Lateral derecho", "6", "Francia", 30, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Erin Cuthbert", "Mediocampista central", "7", "Escocia", 27, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Oriane Jean-François", "Mediocampista defensiva", "8", "Francia", 23, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Catarina Macário", "Mediocampista ofensiva", "9", "EE.UU.", 26, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Maika Hamano", "Extrema / Delantera", "10", "Japón", 21, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Agnes Beever-Jones", "Extrema / Delantera", "11", "Inglaterra", 21, "Women's Super League", "", "", "Jugador"));
        prueba.add(new Jugador("Chelsea Femenino", "Sonia Bompastor", "Directora Técnica", "--", "Francia", 44, "Women's Super League", "", "", "Director Técnico"));
        
        // REAL MADRID FEMENINO
        prueba.add(new Jugador("Real Madrid Femenino", "Merle Frohms", "Portera", "1", "Alemania", 30, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Shei", "Defensa lateral", "2", "España", 27, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Maëlle Lakrar", "Defensa central", "3", "Francia", 25, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "María Méndez", "Defensa central", "4", "España", 23, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Yasmin Mrabet", "Mediocampista defensiva", "5", "Marruecos", 25, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Sandie Toletti", "Mediocampista central", "6", "Francia", 29, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Filippa Angeldahl", "Mediocampista", "7", "Suecia", 31, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Sara Däbritz", "Mediocampista ofensiva", "8", "Alemania", 31, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Athenea del Castillo", "Extrema / Delantera", "9", "España", 24, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Linda Caicedo", "Extrema / Delantera", "10", "Colombia", 20, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Signe Bruun", "Delantera centro", "11", "Dinamarca", 27, "Liga F", "", "", "Jugador"));
        prueba.add(new Jugador("Real Madrid Femenino", "Pau Quesada", "Director Técnico", "--", "España", 42, "Liga F", "", "", "Director Técnico"));
        
        // JUVENTUS FEMENINO
        prueba.add(new Jugador("Juventus Femenino", "Pauline Peyraud-Magnin", "Portera", "1", "Francia", 32, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Emma Kullberg", "Defensa central", "2", "Suecia", 33, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Cecilia Salvai", "Defensa central", "3", "Italia", 30, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Lia Walti", "Lateral derecha", "4", "Inglaterra", 24, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Paulina Krumbiegel", "Lateral izquierda", "5", "Alemania", 24, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Sarah Schatzer", "Mediocampista central", "6", "Italia", 21, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Barbara Bonansea", "Extrema / Mediocampista", "7", "Italia", 33, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Tatiana Pinto", "Mediocampista ofensiva", "8", "Portugal", 31, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Stolen-Godo", "Mediocampista defensiva", "9", "Noruega", 26, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Cristiana Girelli", "Delantera centro", "10", "Italia", 34, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Rikke Vangsgaard", "Delantera", "11", "Dinamarca", 29, "Serie A", "", "", "Jugador"));
        prueba.add(new Jugador("Juventus Femenino", "Massimiliano Brambilla", "Director Técnico", "--", "Italia", 51, "Serie A Femenina", "", "", "Director Técnico"));
        
        guardarJugadores(prueba);
        return prueba;
    }
}