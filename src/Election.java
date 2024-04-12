import java.util.*;

public class Election {

	private final LinkedList<String> candNames = new LinkedList<>();
	private final HashMap<String, Candidate> candMap = new HashMap<>();
	private final PriorityQueue<Candidate> candHeap = new PriorityQueue<>(Comparator.comparingInt((Candidate c) -> c.votes).reversed());
	private final Random random = new Random();

	// time: O(n), where n = number of candidates
	// space: O(n), where n = number of candidates
	public void initializeCandidates(LinkedList<String> candidates) {
		for (String s : candidates) { // time: O(n), space: O(n)
			candMap.put(s, new Candidate(s)); // time: O(1), space: O(1)
		}
		candNames.addAll(candMap.keySet()); // time: O(n), space: O(n)
	}

	// time: O(1)
	// space: O(1)
	public void castVote(String candidate) {
		candMap.get(candidate).votes++;
	}

	// time: O(1)
	// space: O(1)
	public void castRandomVote() {
		candMap.get(candNames.get(random.nextInt(candNames.size()))).votes++;
	}

	// time: O(v * n * log n), where v = total votes and n = number of candidates
	// space: O(1)
	public void rigElection(String candidate) {
		Candidate c = candMap.get(candidate);

		// construct heap
		candHeap.clear(); // time: O(n)
		candHeap.addAll(candMap.values()); // time: O(n * log n), space: O(1) (does not add or create new elements)

		// worst case, runs once for every vote
		while (candHeap.peek() != null && !candHeap.peek().equals(c)) {
			// for every other candidate: if they have a vote, remove it and add it to c
			for (Candidate other : candMap.values()) { // O(n)
				if (other.votes > 0) {
					other.votes--;
					c.votes++;
				}
			}

			// if c.votes is still 0 (or less, which shouldn't happen), this means that all
			//     candidates had 0 votes
			// then election cannot be rigged w/o retaining number of votes, so we return
			if (c.votes <= 0) return;

			// reset candHeap by clearing and re-adding
			candHeap.clear(); // time: O(n)
			candHeap.addAll(candMap.values()); // time: O(n * log n), space: O(1) (does not add or create new elements)
		}
	}

	// time: O(n * log n), where n = number of candidates
	// space: O(k), where k = the input k
	public LinkedList<String> getTopKCandidates(int k) {
		candHeap.clear(); // time: O(n)
		candHeap.addAll(candMap.values()); // time: O(n * log n), space: O(1) (does not add or create new elements)

		LinkedList<String> topKNames = new LinkedList<>();
		LinkedList<Candidate> topK = new LinkedList<>();

		if (k < 1) k = 1;
		if (k > candHeap.size()) k = candHeap.size();
		for (int i = 0; i < k; i++) { // time: O(k * log n), space: O(k)
			topK.add(candHeap.poll()); // time: O(log n), space: O(1)
		}

		for (Candidate c : topK) { // time & space: O(k)
			topKNames.add(c.getName()); // time & space: O(1)
		}

		// reconstruct heap
		candHeap.addAll(topK); // time: O(k * log n), space: O(1) (does not add or create new elements)

		return topKNames;
	}

	// time: O(n * log n), where n = number of candidates
	// space: O(1)
	public void auditElection() {
		candHeap.clear(); // time: O(n)
		candHeap.addAll(candMap.values()); // time: O(n * log n), space: O(1) (does not add or create new elements)

		Candidate current;
		while (!candHeap.isEmpty()) {
			current = candHeap.poll();
			System.out.println(current.getName() + ": " + current.votes + " votes");
		}
	}

	private static class Candidate {
		private final String name;
		public int votes;
		public Candidate(String name) {
			this.name = name;
			votes = 0;
		}

		public String getName() {
			return name;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Candidate) {
				return name.equals(((Candidate) obj).name);
			} else return false;
		}
	}

}
