/**
 * Pseudo random byte generator.
 *
 * This random byte generator is very week and should not be used for real things.
 *
 * The only point is that the code is transposable in Pep/8 with the same determinism.
 *
 * (c) 2016 Jean Privat
 *
 * */
public class Rnd {
	/** Random seed.
	 *
	 * Set it once to have a deterministic sequence of pseudo-random bytes.
	 *
	 * Require seed != 0 or else the sequence will only contain 0.
	 *
	 * Note: only the 16 lowest bits are used to ensure consistency with Pep/8 signed 16 bits arithmetic.
	 * */
	public static int srnd = 100;

	/** Returns the next random byte n in the sequence.
	 *
	 * Ensure that 0 <= n < 256.
	 *
	 * Note: We use a very simple instance of a Xorshift generator to achieve pseudo-randomness.
	 * See https://en.wikipedia.org/wiki/Xorshift for details.
	 *
	 * */
	public static int rnd() {
		/* We use short to have a consistent signed 16bits arithmetic. */
		short x = (short)srnd;
		x ^= x << 5; /* x = x XOR (x ASL 5) */
		x ^= x >> 4;
		x ^= x << 9;
		srnd = x;
		return x & 255 /* Filter to keep only the 8 lowest bits to have a byte. */;
	}

	/**
	 * Example program of the random generator that outputs a 10x10 grid of `*` and `.`.
	 *
	 * First argument, if given, is the random seed to use.
	 * Second argument, if given, is the 256th of probability to get a `*`.
	 * */
	public static void main(String[] args) {
		int seed = 100; /* default seed. */
		int prob = 64; /* default probability. 64/256 == 25% */
		if (args.length > 0) seed = Integer.parseInt(args[0]);
		if (args.length > 1) prob = Integer.parseInt(args[1]);

		srnd = seed;
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
				System.out.print(rnd() < prob ? '*' : '.');
			}
			System.out.print('\n');
		}
	}
}
