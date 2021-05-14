package fpdualeveris;

import operators.Pinball;

/**
 * Formacion Java
 * 
 * @author driverog
 *
 */
public class FPDual {
	/**
	 * Metodo Principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		operatorsChallenge();

	}

	/**
	 * Metodo Privado de la clase
	 * 
	 */
	private static void operatorsChallenge() {
		// Generacion de objetos

		Pinball pb1 = new Pinball();

		// Comprobacion de que objeto es de la clase Pinball

		if (pb1 instanceof Pinball) {

			// llamada del metodo
			pb1.launchBall();

		} else {

			System.out.println("El objeto utilizado no pertence a la clse Pinball");

		}

	}
}
