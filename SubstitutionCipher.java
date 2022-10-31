/*Amritpal Singh
 * CUS 1185
 * Substitution Cipher Project
 */

import java.util.*;
import java.util.Map.Entry;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileReader;
import java.io.*;

public class SubstitutionCipher {

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("\nAlice's point of view");
		System.out.println("Randomly generating key for substitution cypher...");
		char M[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
				'u', 'v', 'w', 'x', 'y', 'z' };
		char C[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z' };

		//Randomly creating a corresponding value to each lowecase letter
		Random rand = new Random();
		char temp;
		HashMap<Character, Character> key = new LinkedHashMap<Character, Character>();
		temp = C[rand.nextInt(26)];
		for (int i = 0; i < M.length; i++) { //loops through arrays if there is a new corresponding uppercase letter that hasn't been used before then adds to hashtable otherwise randomly generates a new uppercase letter until no duplicate exists
			if (!key.containsValue(temp)) {
				key.put(M[i], temp);
			}

			else {
				while (key.containsValue(temp) && key.size() < 26) {
					temp = C[rand.nextInt(26)];
					if (!key.containsValue(temp)) {
						key.put(M[i], temp);
						i++;
					}
				}

			}

		}

		System.out.println("\nThe key has been generated as follows:");
		Set<Character> mList = key.keySet();
		System.out.println("plaintext= " + "\t" + mList); //prints out plaintext
		Collection<Character> cList = key.values();
		System.out.println("ciphertext = " + "\t" + cList); //prints out ciphertext correspondence

		// _____________________________________________

		Scanner kb = new Scanner(System.in); //asks user for the string they would like to encrypt and encrypts it based on the hashtable previously created 
		System.out.println(
				"Please enter a string that you would like to encrypt (type a space followed by -1 when done)");
		String encryptedString = "";

		while (kb.hasNext()) {
			String input = kb.next();
			if (input.equals("-1")) {
				break;
			}
			for (int i = 0; i < input.length(); i++) {
				char currentChar = input.charAt(i);

				currentChar = Character.toLowerCase(currentChar);
				encryptedString += key.get(currentChar);
			}

		}
		System.out.println("\nThe String has been encrypted as: ");
		System.out.println(encryptedString);
		System.out.println(
				"____________________________________________________________________________________________________");

		// ____________________________________________________

		System.out.println("\nBob's Point of View");
		System.out.println("The key that you have obtained is as follows:" + "\nCiphertext = " + "\t" + cList //retrieves key and encrypted ciphertext that Bob would have access to in order to decrypt
				+ "\nPlaintext" + "\t" + mList);
		System.out.println("The encrypted string sent to you by Alice is: " + encryptedString);

		String decryptedString = "";

		System.out.println("\nDecrypting the ciphertext using the key..."); //Decrypts by doing the reverse - looks up original lowercase value that corresponds to the recieved ciphertext character
		for (int i = 0; i < encryptedString.length(); i++) {
			char currentChar = encryptedString.charAt(i);

			for (Entry<Character, Character> entry : key.entrySet()) {
				if (entry.getValue().equals(currentChar)) {
					decryptedString += entry.getKey();
				}
			}

		}
		System.out.print("The ciphertext has successfully been decrypted!" + "\nThe original plaintext was: " + "\n"
				+ decryptedString);
		System.out.println(
				"\n____________________________________________________________________________________________________");
		// _________________________________________

		System.out.println("\n\nEve's Point of View");
		System.out.println(
				"\nEncrypting the original input file, 'in.txt' to 'out.txt', the ciphertext that Eve can see...");

		File inEncryption = new File("in.txt");
		File outEncryption = new File("out.txt");
		FileReader plaintextReader = new FileReader(inEncryption);

		BufferedReader br = null;
		FileWriter myWriter = new FileWriter(outEncryption);

		br = new BufferedReader(plaintextReader);

		while ((br.readLine()) != null) { //reads input from in.txt and encrypts based on original hashtable named key and writes it all into out.txt line by line

			String currentString = (br.readLine());
			char[] charsBeingRead = currentString.toCharArray();
			for (int i = 0; i < charsBeingRead.length; i++) {
				if (Character.isUpperCase(charsBeingRead[i])) {
					charsBeingRead[i] = Character.toLowerCase(charsBeingRead[i]);
				}
				if (key.containsKey(charsBeingRead[i])) {
					myWriter.write(key.get(charsBeingRead[i]));
					if (i == charsBeingRead.length - 1) {
						myWriter.write("\n");
					}
				}
			}
		}
		myWriter.close();
		plaintextReader.close();
		br.close();

		System.out.println("In.txt has successfully been encrypted into the output file, out.txt");

		// ____________________________________________________________________

		

		HashMap<Character, Integer> characterCounter = new HashMap<Character, Integer>(); //new hashtable characterCounter created to keep track of the frequency of each character
		characterCounter.put('A', 0);
		characterCounter.put('B', 0);
		characterCounter.put('C', 0);
		characterCounter.put('D', 0);
		characterCounter.put('E', 0);
		characterCounter.put('F', 0);
		characterCounter.put('G', 0);
		characterCounter.put('H', 0);
		characterCounter.put('I', 0);
		characterCounter.put('J', 0);
		characterCounter.put('K', 0);
		characterCounter.put('L', 0);
		characterCounter.put('M', 0);
		characterCounter.put('N', 0);
		characterCounter.put('O', 0);
		characterCounter.put('P', 0);
		characterCounter.put('Q', 0);
		characterCounter.put('R', 0);
		characterCounter.put('S', 0);
		characterCounter.put('T', 0);
		characterCounter.put('U', 0);
		characterCounter.put('V', 0);
		characterCounter.put('W', 0);
		characterCounter.put('X', 0);
		characterCounter.put('Y', 0);
		characterCounter.put('Z', 0);

		int totalCharCount = 0;
		Scanner eveReader = new Scanner(outEncryption);
		while (eveReader.hasNext()) { //reads from out.txt (the ciphertext file) and counts the frequency of each character by storing in the characterCounter hashtable as well as the total number of characters read stored in totalCharCount
			String currentString = eveReader.next();
			char[] charsBeingRead = currentString.toCharArray();
			for (int i = 0; i < charsBeingRead.length; i++) {
				char charBR = charsBeingRead[i];
				if (Character.isLowerCase(charBR)) {
					charBR = Character.toUpperCase(charBR);
				}
				if (characterCounter.containsKey(charBR)) {
					characterCounter.put(charBR, characterCounter.get(charBR) + 1);
					totalCharCount++;
				}

			}
		}

		eveReader.close();
		
		
		// ____________________________________________________________________
		
		
		System.out.println("\nThe count of each character in the out.txt file is as follows:");  //prints out the amount of times each character is seen in the ciphertext by iterating through characterCounter
		System.out.println(characterCounter);

		List<Map.Entry<Character, Integer>> freqCount = new ArrayList<Entry<Character, Integer>>(
				characterCounter.entrySet());
		Collections.sort(freqCount, new Comparator<Map.Entry<Character, Integer>>() {

			public int compare(Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
				return entry1.getValue().compareTo(entry2.getValue());
			}

		});

		// ____________________________________________________________________
		
		
		Map<Character, Double> letterFrequency = new LinkedHashMap<Character, Double>(); //stores the actual frequency (decimal) in a new hasTable letterFrequency

		for (Map.Entry<Character, Integer> entry : freqCount) {
			if (entry.getValue() == 0) {
				throw new IllegalArgumentException(
						"Argument 'divisor' is 0, at least one of the letters in the english alphabet is not present in the in.txt file");
			}
			letterFrequency.put(entry.getKey(), (double) entry.getValue() / totalCharCount);
		}

		// ____________________________________________________________________
		
		
		System.out.println("Number of total character entries: " + totalCharCount); //Prints out total char count and iterates through letterFrequency, printing it out
		System.out.println("\nThe sorted list by letter freuency:");


		for (Map.Entry<Character, Double> entry : letterFrequency.entrySet()) {
			Character letter = entry.getKey();
			Double frequency = entry.getValue();

			DecimalFormat df = new DecimalFormat("#.####");
			System.out.println(letter + "=\t" + df.format(frequency));

		}

		
		// ____________________________________________________________________
		
		

		HashMap<Character, Double> topFrequencies = new HashMap<Character, Double>(); //creates new hashtable topFrequencies to store the frequency percentages of the top 4 letters in the English language
		topFrequencies.put('e', 0.13);
		topFrequencies.put('t', 0.091);
		topFrequencies.put('a', 0.082);
		topFrequencies.put('o', 0.075);
		
		
		System.out.println(); 
		for (Map.Entry<Character, Double> entry2 : letterFrequency.entrySet()) { //if the letterFrequency is close enough to the topFrequencies, prints out statement telling you
			Double frequency = entry2.getValue();
			for (Entry<Character, Double> match : topFrequencies.entrySet()) {
				Double currVal = match.getValue();
				if (currVal - .005 <= frequency && frequency <= currVal + .005) {
					System.out.println("The letter " + entry2.getKey() + " is likely " + match.getKey()
							+ " because it is close to the value of " + match.getValue());
				}
			}
		}

	}
}
