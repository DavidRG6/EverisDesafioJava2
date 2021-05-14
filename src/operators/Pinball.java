package operators;

/**
 *Formacion Java
 * 
 * @author driverog
 *
 */
public class Pinball {
	/** Variables del metodo */
	
	private static int puntuacionBase;
	private boolean bolaExtra;
	private int rebotadoresRedondos;
	private int posibilidades;
	private int palancaIzquierda;
	private int palancaDerecha;
	private boolean especial;
	private boolean fuera;
	private double puntos;
	private long puntuacionFinal;
	private int numeroBolaExtra;

	/** Constructor de la clase */
	
	public Pinball() {
		this.puntuacionBase = 100;
		this.bolaExtra = false;
		this.rebotadoresRedondos = 0;
		this.palancaIzquierda = 1;
		this.palancaDerecha = 0;
		this.especial = true;
		this.fuera = false;
		this.puntuacionFinal = this.puntuacionBase;
		this.puntos = this.puntuacionBase;
		this.numeroBolaExtra = 0;
	}

	/**
	 * Funcion para la realizacion del reto
	 */
	
	public void launchBall() {
		// Posibilidades
		do {

			this.bolaExtra = false;
			// Calculo de las posibilidades
			this.posibilidades = (int) (Math.random() * 5);

			// Posibilidades que ocurren
			switch (this.posibilidades) {

			// Fuera
			case 0: {

				if (rebotadoresRedondos >= 1 && this.palancaDerecha >= 2 && this.palancaIzquierda >= 2) {

					this.fuera = true;
				}

				break;
			}

			// Rebote Redondos
			case 1: {

				this.puntuacionFinal += (this.puntos *= 0.75);
				this.puntos=this.puntuacionFinal;
				rebotadoresRedondos++;
				break;

			}
			// Palanca Izquierda
			case 2: {

				this.puntuacionFinal += (this.puntos *= 0.25);
				this.puntos=this.puntuacionFinal;
				palancaIzquierda++;
				break;

			}

			// Palanca Derecha
			case 3: {

				this.puntuacionFinal += (this.puntos *= 0.25);
				this.puntos=this.puntuacionFinal;
				palancaDerecha++;
				break;

			}

			// Especial
			case 4: {

				if ((this.palancaDerecha >= 3 || this.palancaIzquierda >= 3) && this.especial) {

					this.puntuacionFinal *= 3;
					this.puntos = this.puntuacionFinal;
					this.especial = false;

				} else if (!this.especial && this.palancaDerecha % 2 == 0 && this.palancaIzquierda % 2 == 0) {

					this.puntuacionFinal += (this.puntos *= 1.5);
					this.puntos=this.puntuacionFinal;
					this.especial = true;

				} else {
					this.puntuacionFinal += (this.puntos *= 0.5);
					this.puntos=this.puntuacionFinal;
				}
				break;
			}
			}

			// Para conseguir bolas extras
			if (!this.especial && puntuacionFinal >= 500) {
				System.out.println("Tienes una bola extra puedes seguir jugando!!");
				this.bolaExtra = true;
				this.numeroBolaExtra++;
			} else if (this.especial && puntuacionFinal >= 300) {
				System.out.println("Tienes una bola extra puedes seguir jugando!!");
				this.bolaExtra = true;
				this.numeroBolaExtra++;
			}

		} while (!this.fuera && !this.bolaExtra);

		// Calculos de puntuacion extra por especial

		this.puntuacionFinal = this.especial ? (this.puntuacionFinal += 500) : (this.puntuacionFinal -= 500);

		// Calculos de puntuacion extra por impactos en las palancas
		if (this.palancaDerecha == this.palancaIzquierda) {
			this.puntuacionFinal += 300;

		} else if ((this.palancaDerecha % 2 == 0 && this.palancaIzquierda % 2 == 0 && this.palancaDerecha != 0 && this.palancaIzquierda != 0)
		        || (this.palancaDerecha % 2 == 1 && this.palancaIzquierda % 2 == 1)) {
			this.puntuacionFinal += 150;

		} else {
			this.puntuacionFinal -= 70;

		}
		;

		// Rebotadores a los que se han impactado
		System.out.println("Rebotaste en un total de: " + this.rebotadoresRedondos + " Rebotadores");

		// Numero de veces que se han impactado las palancas
		System.out.println("Rebotaste un total de: " + (this.palancaDerecha + this.palancaIzquierda) + " en las palancas// " + this.palancaDerecha
		        + " veces en la palanca derecha y " + this.palancaIzquierda + " veces en la palanca izquierda");

		// Numero de bolas extras usadas
		System.out.println("Y usaste un total de: " + this.numeroBolaExtra + " bolas extras");

		// Puntuacion final
		System.out.println("Por lo tanto la suma total de tu puntuacion es ="
		        + (this.puntuacionFinal <= 0 ? (int) (this.puntuacionFinal = 0) : (int) (this.puntuacionFinal)) + " puntos");

		
	}

	/**
	 * @return the puntuacionBase
	 */
	public static int getPuntuacionBase() {
		return puntuacionBase;
	}

	/**
	 * @param puntuacionBase
	 *            the puntuacionBase to set
	 */
	public static void setPuntuacionBase(int puntuacionBase) {
		Pinball.puntuacionBase = puntuacionBase;
	}

	/**
	 * @return the bolaExtra
	 */
	public boolean isBolaExtra() {
		return bolaExtra;
	}

	/**
	 * @param bolaExtra
	 *            the bolaExtra to set
	 */
	public void setBolaExtra(boolean bolaExtra) {
		this.bolaExtra = bolaExtra;
	}

	/**
	 * @return the rebotadoresRedondos
	 */
	public int getRebotadoresRedondos() {
		return rebotadoresRedondos;
	}

	/**
	 * @param rebotadoresRedondos
	 *            the rebotadoresRedondos to set
	 */
	public void setRebotadoresRedondos(int rebotadoresRedondos) {
		this.rebotadoresRedondos = rebotadoresRedondos;
	}

	/**
	 * @return the posibilidades
	 */
	public int getPosibilidades() {
		return posibilidades;
	}

	/**
	 * @param posibilidades
	 *            the posibilidades to set
	 */
	public void setPosibilidades(int posibilidades) {
		this.posibilidades = posibilidades;
	}

	/**
	 * @return the palancaIzquierda
	 */
	public int getPalancaIzquierda() {
		return palancaIzquierda;
	}

	/**
	 * @param palancaIzquierda
	 *            the palancaIzquierda to set
	 */
	public void setPalancaIzquierda(int palancaIzquierda) {
		this.palancaIzquierda = palancaIzquierda;
	}

	/**
	 * @return the palancaDerecha
	 */
	public int getPalancaDerecha() {
		return palancaDerecha;
	}

	/**
	 * @param palancaDerecha
	 *            the palancaDerecha to set
	 */
	public void setPalancaDerecha(int palancaDerecha) {
		this.palancaDerecha = palancaDerecha;
	}

	/**
	 * @return the especial
	 */
	public boolean isEspecial() {
		return especial;
	}

	/**
	 * @param especial
	 *            the especial to set
	 */
	public void setEspecial(boolean especial) {
		this.especial = especial;
	}

	/**
	 * @return the fuera
	 */
	public boolean isFuera() {
		return fuera;
	}

	/**
	 * @param fuera
	 *            the fuera to set
	 */
	public void setFuera(boolean fuera) {
		this.fuera = fuera;
	}

	/**
	 * @return the puntos
	 */
	public double getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos
	 *            the puntos to set
	 */
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	/**
	 * @return the puntuacionFinal
	 */
	public long getPuntuacionFinal() {
		return puntuacionFinal;
	}

	/**
	 * @param puntuacionFinal
	 *            the puntuacionFinal to set
	 */
	public void setPuntuacionFinal(long puntuacionFinal) {
		this.puntuacionFinal = puntuacionFinal;
	}

	/**
	 * @return the numeroBolaExtra
	 */
	public int getNumeroBolaExtra() {
		return numeroBolaExtra;
	}

	/**
	 * @param numeroBolaExtra
	 *            the numeroBolaExtra to set
	 */
	public void setNumeroBolaExtra(int numeroBolaExtra) {
		this.numeroBolaExtra = numeroBolaExtra;
	}

}
