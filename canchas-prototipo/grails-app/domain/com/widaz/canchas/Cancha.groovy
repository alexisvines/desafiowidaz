package com.widaz.canchas

class Cancha {

	TipoCancha tipoCancha
	int numeroCancha
	ComplejoDeportivo complejoDeportivo

    static constraints = {
    }
	
	String toString(){
	 	return numeroCancha.toString()
	}
}
