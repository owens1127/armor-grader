
public class Main {

	public static void main(String[] args) {
		int[] plugs = {11, 12, 13, 14, 15, 16, 17};
		int[] rarity = {6, 9, 24, 30, 23, 33, 12};
		int[] ghostRestriction = {0, 3, 6, 6, 12, 21, 12};
		int[] highRestriction = {0, 0, 0, 0, 0, 0, 6};
		
		int[] values = analyze(rarity, rarity, rarity, plugs);
		int[] ghostValues = analyze(ghostRestriction, rarity, rarity, plugs);
		int[] highStatvalues = analyze(highRestriction, rarity, rarity, plugs);
		int[] pohValues = analyze(highRestriction, highRestriction, rarity, plugs);

		
		
		int total = sum(values);
		System.out.println("Total Combinations: " + total);
		System.out.println(listOfValues(values, total));
	}

	public static int sum(int[] arr) {
		int sum = 0;
		for (int val:arr) {
			sum+=val;
		}
		return sum;
	}

	public static String listOfValues(int[] arr, int total) {
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			str += (
					(i + 44) + 
					" " + 
					arr[i] + 
					" " + 
					((arr[i] / (double) total 
							* 100
							)) + 
					"%" + 
					"\n");
		}
		return str;
	}
	
	public static int[] analyze(int[] restrictedRarity, int[] restrictedRarity2, int[] rarity, int[] plugs) {
		int[] values = new int[25];
		for (int i = 0; i < restrictedRarity.length; i++) {
			for (int j = 0; j < plugs.length; j++) {
				for (int k = 0; k < restrictedRarity2.length; k++) {
					for (int l = 0; l < plugs.length; l++) {
						int totalStat = plugs[i] + plugs[j] + plugs[k] + plugs[l];
						int count = restrictedRarity[i] * rarity[j] * restrictedRarity2[k] * rarity[l];
						values[totalStat - 44] += count;
					}
				}
			}
		}
		return values;
	}

}

