import java.util.*;

public class ElectionSystem {

	// https://1000randomnames.com
	private static final String[] NAMES = {
			"Anya Moyer", "Ahmir Salas", "Amber Flores", "Lincoln Rivera", "Lillian Romero",
			"Bryson Coffey", "Paola Daniel", "Grady Perez", "Eleanor McCarty", "Blaise Long",
			"Jade Petersen", "Samson Pennington", "Yareli Schmidt", "Zayden Booker", "Nataly Benson",
			"Desmond Moran", "Celeste Solomon", "Musa Carson", "Nalani Clayton", "Boston Hudson",
			"Kamila Dejesus", "Rio Flores", "Emilia Beil", "Ariel Rojas", "Adaline Rosales",
			"Wilder Chase", "Angie Holmes", "King Nielsen", "Vienna Galindo", "Salvatore Cortez",
			"Haven Tang", "Rogelio Glenn", "Blaire Flowers", "Saul Chandler", "Viviana Murillo",
			"Lance Blanchard", "Layne Frost", "Dario Jacobson", "Royal Reyna", "Reginald Jenkins",
			"Rylee Lawson", "Lane Stevenson", "Regina Cross", "Fabian Barry", "Waverly Zamora",
			"Quentin Jones", "Sophia Ford", "Luis Potter", "Rory Sims", "Brian Woodard",
			"Aubrie Peters", "Patrick Galindo", "Corinne Buckley", "Aryan Sloan", "Selene Craig",
			"Odin Hamilton", "Mackenzie Bryant", "Jonah Waters", "Bristol Howe", "Alaric Bradley",
			"Vanessa Alvarez", "Xavier Keith", "Elyse Black", "Matteo Stephens", "Millie Nicholson",
			"Rodrigo Schroeder", "Cameron Rangel", "Saint Simpson", "Anastasia Tucker", "Ivan Sanford",
			"Emerald Mathews", "Jamir Ortega", "Lilah Potts", "Alfred Rosas", "Joelle Sanchez",
			"Joseph Elliott", "Noelle Dudley", "Colter Butler", "Athena Owens", "Adriel Knapp",
			"Linda Malone", "Ruben Haley", "Addilynn Guerrero", "Bryce Adkins", "Emelia Woodward",
			"Jeremias Church", "Ayleen Correa", "Zakai Booker", "Nataly Jackson", "Sebastian Christian",
			"Anahi Pruitt", "Gatlin Phillips", "Naomi Wheeler", "Kenneth Chambers", "Makayla Ingram",
			"Tripp Branch", "Luisa Webster", "Shawn Little", "Harley Walker", "Luke Shaffer"
	};
	private static final Random random = new Random();

	public static void randomElection(int maxCandidates, int maxVotes) {
		if (maxCandidates > 100) maxCandidates = 100; // only have 100 names
		// ensure at least 1 candidate and vote
		int c = random.nextInt(maxCandidates) + 1;
		int v = random.nextInt(maxVotes) + 1;

		LinkedList<String> candidates = new LinkedList<>();
		for (int i = 0; i < c; i++) {
			candidates.add(NAMES[random.nextInt(100)]);
		}

		Election e = new Election();
		e.initializeCandidates(candidates);

		for (int i = 0; i < v; i++) {
			e.castRandomVote();
		}

		int k = (random.nextInt(c) / 10) + 3;
		System.out.println("Top " + k + " candidates after " + v + " votes: " + e.getTopKCandidates(k));

		e.rigElection(candidates.get(random.nextInt(c)));

		System.out.println("Top " + k + " candidates after rigging the election: " + e.getTopKCandidates(k));

		e.auditElection();
	}

	public static void main(String[] args) {
		System.out.println("Testing example election");
		LinkedList<String> candidates = new LinkedList<>(Arrays.asList("Marcus Fenix", "Dominic Santiago", "Damon Baird", "Cole Train", "Anya Stroud"));
		Election e = new Election();
		e.initializeCandidates(candidates);
		e.castVote("Cole Train");
		e.castVote("Cole Train");
		e.castVote("Marcus Fenix");
		e.castVote("Anya Stroud");
		e.castVote("Anya Stroud");
		System.out.println("Top 3 candidates after 5 votes: " + e.getTopKCandidates(3));
		e.rigElection("Marcus Fenix");
		System.out.println("Top 3 candidates after rigging the election: " + e.getTopKCandidates(3));
		e.auditElection();
		System.out.println();

		System.out.println("Testing random election #1");
		randomElection(10, 10);
		System.out.println();

		System.out.println("Testing random election #2");
		randomElection(20, 50);
		System.out.println();

		System.out.println("Testing random election #3");
		randomElection(100, 100000);
	}

}
