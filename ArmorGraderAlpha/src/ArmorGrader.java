
public class ArmorGrader {
	
	public static final int[] plugs = {8, 9, 10, 11, 12, 13, 14};
	public static final int[] plugCount = {6, 9, 24, 30, 23, 33, 12};
	public static final int combinations = 352275361;

	public static void main(String[] args) {
		
		// DEFINITIONS
		DoublePlug.generateDPs();
		ArmorPiece testpiece = new ArmorPiece(6, 2, 26, 16, 16, 2);
		int[] testDesired = {6, 1, 6, 5, 7, 1};
		int tier = calcTier(testDesired);
		String statString = "";
		for (int n:testDesired) {
			statString += (10*n + " ");
		}
		
		String grade = calculateGrade(testpiece, testDesired).getLetterGrade();

	}
	
	public static GradeAndStats calculateGrade(ArmorPiece a, int[] desiredStats) {
		int[] statTiers1 = new int[6];
		int[] statTiers2 = new int[6];
		
		for (int i = 0; i < desiredStats.length; i++) {
			statTiers1[i] = 10*desiredStats[i];
			statTiers2[i] = 10*desiredStats[i];
		}
		
		ArmorPiece[] armorBuilder = {new ArmorPiece(), new ArmorPiece(), new ArmorPiece()};
		
		
		int[] remaining = calcRemaining(a, statTiers1);
		
		
		if (isImpossible(remaining)) 
			return new GradeAndStats("F-", null);
		
		if(alwaysPossible(remaining))
			return new GradeAndStats("X", null);
			
		
		assignStats(remaining, armorBuilder);
		
		Score statsObj = calculateScore(a, armorBuilder, statTiers2);
		
		double score = statsObj.getScore();
		
		String grade;
		if (score == 13) {
			grade = "S+";
		} else if (score == 12) {
			grade = "S";
		} else if (score >= 11) {
			grade = "A+";
		} else if (score >= 10) {
			grade = "A";
		} else if (score >= 9) {
			grade = "A-";
		} else if (score >= 8) {
			grade = "B";
		} else if (score >= 7) {
			grade = "C";
		} else if (score >= 6) {
			grade = "D";
		} else {
			grade = "F";
		}
		
		return new GradeAndStats(grade, statsObj);
		
		
	}


	public static Score calculateScore(ArmorPiece a, ArmorPiece[] armorBuilder, int[] desired) {
		
		double rarityOfOthers = rarityOfBuilder(armorBuilder)*combinations;
		double rarityOfThis = rarityOfPiece(a)*combinations;
		
		
		// negative natural log
		double x = -Math.log(rarityOfThis);
		double y = -Math.log(rarityOfOthers);
		

		// higher rarity for that piece is considered
		double primitiveScore = x - (y - x);
		
		int desiredTier = calcTier(desired);
		
		double score = Math.min(primitiveScore, 11);
		
		// adds 1 to score for T25 Loadouts, 2 to T26
		if (desiredTier == 25) score++;
		if (desiredTier == 26) score+=2;
		
		return new Score(score, primitiveScore, rarityOfThis, rarityOfOthers, rarityOfOthers/rarityOfThis);
		
	}
	
	public static int[] calcRemaining(ArmorPiece a, int[] desiredStats) {
		int[] armorStats = a.getStats();
		
		int[] remaining = desiredStats;
		for (int i = 0; i < remaining.length; i++) {
			remaining[i] = Math.max(desiredStats[i] - armorStats[i], 0);
		}
		return remaining;
	}
	
	private static boolean isImpossible(int[] remaining) {
		if ((remaining[0] + remaining[1] + remaining[2]) > 102 ) {
			return true;
		} else if ((remaining[3] + remaining[4] + remaining[5]) > 102 ) {
			return true;
		}
		return false;

	}
	
	
	private static boolean alwaysPossible(int[] remaining) {
		for (int x:remaining) {
			if (x > 6) return false;
		}
		
		return true;
	}
	
	public static int[] place2 (int[] values, ArmorPiece[] builder) {

		
		int[] remaining = values;
		for (ArmorPiece a:builder) {
			a.addStats(2, 2, 2, 2, 2, 2);
		}
		
		for (int i = 0; i < remaining.length; i++) {
			remaining[i] = Math.max(remaining[i] - 6, 0);
		}
		
		return remaining;
	}
	
	public static ArmorPiece[] assignStats(int[] stats, ArmorPiece[] builder) {
		int[] availableStats = place2(stats, builder);
		
		
		// process < makes sure an armor piece doesn't have 3,4,5 stats
		int j = 0;
		for (int i = 0; i < availableStats.length; i++) {
			while (availableStats[i] > 0 && availableStats[i] <= 12) {
				builder[(j++)%3].addStat(i, 4);
				availableStats[i] = Math.max(availableStats[i] - 4, 0);
			}
				     	
		}
	
		
		// process top stats
		while ((availableStats[0] + availableStats[1] + availableStats[2]) > 0) {
			for (int k = 0; k < 3; k++) {
				int avg = (builder[0].getTopTotal() + builder[1].getTopTotal() + builder[2].getTopTotal()) / 3;
				for (int i = 0; i < builder.length; i++) {
					if (availableStats[k] > 0 && builder[i].getTopTotal() <= avg) {
						builder[i].addStat(k, 1);
						availableStats[k]--;
					}
				}
			}
			
		}
		
		// process bottom stats
		while ((availableStats[3] + availableStats[4] + availableStats[5]) > 0) {
			for (int k = 3; k < 6; k++) {
				int avg = (builder[0].getBottomTotal() + builder[1].getBottomTotal() + builder[2].getBottomTotal()) / 3;
				for (int i = 0; i < builder.length; i++) {
					if (availableStats[k] > 0 && builder[i].getBottomTotal() <= avg) {
						builder[i].addStat(k, 1);
						availableStats[k]--;
					}
				}
			}
			
		}
		
		
		return builder;
	}
	
	public static double rarityOfBuilder(ArmorPiece[] a) {
		double rarity = 0;
		
		double[] top = new double[3], bottom = new double[3];
		for (int i = 0; i < 3; i++) {
			top[i] = ArmorPiece.tripletRarity(a[i].getTopTriplet());
			bottom[i] = ArmorPiece.tripletRarity(a[i].getBottomTriplet());
		}
		
		for (double x:top) {
			for (double y:bottom) {
				rarity += (x*y);
			}
		}
		
		double avgRarity = rarity / 9;
		return avgRarity;
	}
	
	public static double rarityOfPiece(ArmorPiece a) {
		
		 double x = ArmorPiece.tripletRarity(a.getTopTriplet());
		 double y = ArmorPiece.tripletRarity(a.getBottomTriplet());
		 return x*y;
	}
	
	public static int calcTier(int[] stats) {
		int tier = 0;
		for (int x:stats) {
			tier += x/10;
		}
		return tier;
	}
}

