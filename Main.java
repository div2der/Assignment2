import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// примеры

		HashTable<Integer, Integer> table = new HashTable<>();

		table.put(1, 2);
		table.put(257, 3);
		table.put(0, 5);

		table.remove(1);

		System.out.println(table.containsKey(257));
		System.out.println(table.containsValue(3));
		System.out.println(table.containsKey(1));
		System.out.println(table.containsValue(6));
		System.out.println(table.get(257));

		scanner.close();
	}
}