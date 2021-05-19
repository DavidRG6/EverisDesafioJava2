package operators;

/**
 * Formacion Java
 * 
 * @author driverog
 *
 */
public class Pinball {
	/** Variable possibilities que baraja el suceso que puede ocurrir en el swicth */
	private int possibilities;
	/** Variable multiplier almacena el multiplicador de los elementos */
	private double multiplier;
	/** Variable punctuationBase es la puntuacion base que tienes nada mas empezar siempre */
	private static int punctuationBase;
	/** Variable points es una variable que se usa de apoyo para realizart corectamente los calculos para punctuationFinal */
	private double points;
	/** Variable punctuationFinal es la variable que muestra el resultado final de los calculos de puntuacion */
	private long punctuationFinal;
	/** Variable bouncersRound que es un contador de las veces que se impacta con este elemento, luego e usa para las puntuaciones extras */
	private int bouncersRound;
	/** Variable leverLeft que es un contador de las veces que se impacta con este elemento, luego se usa para las puntuaciones extras */
	private int leverLeft;
	/** Variable leverRight que es un contador de las veces que se impacta con este elemento, luego se usa para las puntuaciones extras */
	private int leverRight;
	/** Variable special es un boolean que muestra si el elemento especial esta activo o no ,para asi recibir puntuaciones mayores cuando se impacta con el */
	private boolean special;
	/** Variable extraBall es una boolean que muestra si se posee o no una bola extra */
	private boolean extraBall;
	/** Variable numberExtraBall es un contador para saber el numero total de bolas extras que se han usado */
	private int numberExtraBall;
	/** Variable out boolean que permite la salida del bucle se lanzamiento de la bola */
	private boolean out;

	/**
	 * Constructor metodo
	 */
	public Pinball() {
		this.punctuationBase = 100;
		this.punctuationFinal = this.punctuationBase;
		this.points = this.punctuationBase;
		this.bouncersRound = 0;
		this.leverLeft = 1;
		this.leverRight = 0;
		this.special = true;
		this.extraBall = false;
		this.numberExtraBall = 0;
		this.out = false;
	}

	/**
	 * Funcion para la realizacion del reto (launchBall)
	 */
	public void launchBall() {

		// Posibilidades

		launchBalls();

		// Calculos de puntuacion extra por especial

		this.punctuationFinal = this.special ? (this.punctuationFinal += 500) : (this.punctuationFinal -= 500);

		// Calculos de puntuacion extra por impactos en las palancas
		leverExtraPoints();

		// Rebotadores a los que se han impactado
		System.out.println("Rebotaste en un total de: " + this.bouncersRound + " Rebotadores");

		// Numero de veces que se han impactado las palancas
		System.out.println("Rebotaste un total de: " + (this.leverRight + this.leverLeft) + " en las palancas// " + this.leverRight
		        + " veces en la palanca derecha y " + this.leverLeft + " veces en la palanca izquierda");

		// Numero de bolas extras usadas
		System.out.println("Y usaste un total de: " + this.numberExtraBall + " bolas extras");

		// Puntuacion final
		System.out.println("Por lo tanto la suma total de tu puntuacion es ="
		        + (this.punctuationFinal <= 0 ? (int) (this.punctuationFinal = 0) : (int) (this.punctuationFinal)) + " puntos");

	}

	/**
	 * Puntuacion segun las veces que has impactado con las palancas
	 */
	private void leverExtraPoints() {
		if (this.leverRight == this.leverLeft) {
			this.punctuationFinal += 300;

		} else if (pairOddEquals()) {
			this.punctuationFinal += 150;

		} else {
			this.punctuationFinal -= 70;

		}
		;
	}

	/**
	 * @return
	 */
	private boolean pairOddEquals() {
		return (this.leverRight % 2 == 0 && this.leverLeft % 2 == 0 && this.leverRight != 0 && this.leverLeft != 0)
		        || (this.leverRight % 2 == 1 && this.leverLeft % 2 == 1);
	}

	/**
	 * Posibilidades de choque que hay al lanzar la bola
	 */
	private void launchBalls() {
		do {

			// Quitar bola extra si posee
			this.extraBall = false;

			// Calculo de las posibilidades
			this.possibilities = (int) (Math.random() * 5);

			// Posibilidades que ocurren
			switch (this.possibilities) {

			// Fuera

			case 0: {

				if (bouncersRound >= 1 && this.leverRight >= 2 && this.leverLeft >= 2) {

					this.out = true;
				}

				break;
			}

			// Rebote Redondos
			case 1: {

				this.multiplier = 0.75;
				pointBounce(this.bouncersRound, this.multiplier);
				break;

			}

			// Palanca Izquierda
			case 2: {

				this.multiplier = 0.25;
				pointBounce(this.leverLeft, this.multiplier);
				break;

			}

			// Palanca Derecha
			case 3: {

				this.multiplier = 0.25;
				pointBounce(this.leverRight, this.multiplier);
				break;

			}

			// Especial
			case 4: {

				especialElement();
				break;
			}
			}

			// Para conseguir bolas extras
			possibilityExtraBall();

		} while (!this.out && !this.extraBall);
	}

	/**
	 * Multiplicador especial si la bola impacta con el elemento especial
	 */
	private void especialElement() {
		if ((this.leverRight >= 1 || this.leverLeft >= 1) && this.special) {

			this.punctuationFinal *= 3;
			this.points = this.punctuationFinal;
			this.special = false;

		} else if (!this.special && this.leverRight % 2 == 0 && this.leverLeft % 2 == 0) {

			this.punctuationFinal *= 1.5;
			this.points = this.punctuationFinal;
			this.special = true;

		} else {
			this.punctuationFinal += (this.points / 2);
			this.points = this.punctuationFinal;
		}
	}

	/**
	 * Casculos para saber si el jugador puede obtener un bola extra o no
	 */
	private void possibilityExtraBall() {
		if (!this.special && punctuationFinal >= 500) {
			System.out.println("Tienes una bola extra puedes seguir jugando!!");
			this.extraBall = true;
			this.numberExtraBall++;
		} else if (this.special && punctuationFinal >= 300) {
			System.out.println("Tienes una bola extra puedes seguir jugando!!");
			this.extraBall = true;
			this.numberExtraBall++;
		}
		;
	}

	/**
	 * Calculo de los multiplicadores cuando la bola choca con los distintos elementos
	 */
	private void pointBounce(int cosa, double multiplicate) {
		this.punctuationFinal += (this.points *= multiplicate);
		this.points = this.punctuationFinal;
		cosa++;
	}

	/**
	 * @return the puntuacionBase
	 */
	public static int getPuntuacionBase() {
		return punctuationBase;
	}

	/**
	 * @param puntuacionBase
	 *            the puntuacionBase to set
	 */
	public static void setPuntuacionBase(int puntuacionBase) {
		Pinball.punctuationBase = puntuacionBase;
	}

	/**
	 * @return the bolaExtra
	 */
	public boolean isBolaExtra() {
		return extraBall;
	}

	/**
	 * @param bolaExtra
	 *            the bolaExtra to set
	 */
	public void setBolaExtra(boolean bolaExtra) {
		this.extraBall = bolaExtra;
	}

	/**
	 * @return the rebotadoresRedondos
	 */
	public int getRebotadoresRedondos() {
		return bouncersRound;
	}

	/**
	 * @param rebotadoresRedondos
	 *            the rebotadoresRedondos to set
	 */
	public void setRebotadoresRedondos(int rebotadoresRedondos) {
		this.bouncersRound = rebotadoresRedondos;
	}

	/**
	 * @return the posibilidades
	 */
	public int getPosibilidades() {
		return possibilities;
	}

	/**
	 * @param posibilidades
	 *            the posibilidades to set
	 */
	public void setPosibilidades(int posibilidades) {
		this.possibilities = posibilidades;
	}

	/**
	 * @return the palancaIzquierda
	 */
	public int getPalancaIzquierda() {
		return leverLeft;
	}

	/**
	 * @param palancaIzquierda
	 *            the palancaIzquierda to set
	 */
	public void setPalancaIzquierda(int palancaIzquierda) {
		this.leverLeft = palancaIzquierda;
	}

	/**
	 * @return the palancaDerecha
	 */
	public int getPalancaDerecha() {
		return leverRight;
	}

	/**
	 * @param palancaDerecha
	 *            the palancaDerecha to set
	 */
	public void setPalancaDerecha(int palancaDerecha) {
		this.leverRight = palancaDerecha;
	}

	/**
	 * @return the especial
	 */
	public boolean isEspecial() {
		return special;
	}

	/**
	 * @param especial
	 *            the especial to set
	 */
	public void setEspecial(boolean especial) {
		this.special = especial;
	}

	/**
	 * @return the fuera
	 */
	public boolean isFuera() {
		return out;
	}

	/**
	 * @param fuera
	 *            the fuera to set
	 */
	public void setFuera(boolean fuera) {
		this.out = fuera;
	}

	/**
	 * @return the puntos
	 */
	public double getPuntos() {
		return points;
	}

	/**
	 * @param puntos
	 *            the puntos to set
	 */
	public void setPuntos(double puntos) {
		this.points = puntos;
	}

	/**
	 * @return the puntuacionFinal
	 */
	public long getPuntuacionFinal() {
		return punctuationFinal;
	}

	/**
	 * @param puntuacionFinal
	 *            the puntuacionFinal to set
	 */
	public void setPuntuacionFinal(long puntuacionFinal) {
		this.punctuationFinal = puntuacionFinal;
	}

	/**
	 * @return the numeroBolaExtra
	 */
	public int getNumeroBolaExtra() {
		return numberExtraBall;
	}

	/**
	 * @param numeroBolaExtra
	 *            the numeroBolaExtra to set
	 */
	public void setNumeroBolaExtra(int numeroBolaExtra) {
		this.numberExtraBall = numeroBolaExtra;
	}

	/**
	 * @return the multiplier
	 */
	public double getMultiplier() {
		return multiplier;
	}

	/**
	 * @param multiplier
	 *            the multiplier to set
	 */
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

}
