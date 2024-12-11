package dao;

import beans.Jugador;

public interface EquipoDao {
	/*
	 * -	addEquipo(String nombre)
	 * -	insertPlayer(String nombreEquipo, Jugador jugador)
	 * -	deletePlayer(String nombreEquipo, String nombreJugador)
	 * -	updatePosition(String nombreEquipo, String nombreJugador, int posición)
	 */
	void addEquipo(String nombre);
	void insertPlayer(String nombreEquipo, Jugador jugador);
	void deletePlayer(String nombreEquipo, String nombreJugador);
	void updatePosition(String nombreEquipo, String nombreJugador, int posición);
	void listPlayers();
}
