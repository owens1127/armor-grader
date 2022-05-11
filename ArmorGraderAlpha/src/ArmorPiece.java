
public class ArmorPiece {
	
	private int mob;
	private int res;
	private int rec;
	private int dis;
	private int itl;
	private int str;
	
	public static final Integer[] availableStats = {2,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
	public static final Integer[] availableMWStats = {4,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32};
	public static final int maxPerStat = 32;
	
	public ArmorPiece() {
		this.mob = 0;
		this.res = 0;
		this.rec = 0;
		this.dis = 0;
		this.itl = 0;
		this.str = 0;
	}
	
	public ArmorPiece(int mob, int res, int rec, int dis, int itl, int str) {
		this.mob = mob;
		this.res = res;
		this.rec = rec;
		this.dis = dis;
		this.itl = itl;
		this.str = str;
	}
	
	public int[] getStats() {
		int[] stats = {mob, res, rec, dis, itl, str};
		return stats;
	}

	public void addStats(int mob, int res, int rec, int dis, int itl, int str) {
		this.mob += mob;
		this.res += res;
		this.rec += rec;
		this.dis += dis;
		this.itl += itl;
		this.str += str;
		
	}
	
	public int getTopTotal() {
		return mob + res + rec;
	}
	
	public int getBottomTotal() {
		return dis + itl + str;
	}
	
	public int[] getTopTriplet() {
		int[] triplet = {mob-2, res-2, rec-2};
		return triplet;
	}
	
	public int[] getBottomTriplet() {
		int[] triplet = {dis-2, itl-2, str-2};
		return triplet;
	}

	public void addStat(int i, int j) {
        switch (i) {
        case 0:  this.addMob(j);
        			break;
        case 1:  this.addRes(j);
        			break;
        case 2:  this.addRec(j);
        			break;
        case 3:  this.addDis(j);
        			break;
        case 4:  this.addItl(j);
        			break;
        case 5:  this.addStr(j);
        			break;
        }
		
	}
	private void addMob(int i) {
		this.mob += i;
	}
	private void addRes(int i) {
		this.res += i;
	}
	private void addRec(int i) {
		this.rec += i;
	}
	private void addDis(int i) {
		this.dis += i;
	}
	private void addItl(int i) {
		this.itl += i;
	}
	private void addStr(int i) {
		this.str += i;
	}

	public static double tripletRarity(int[] triplet) {
		double count = 0;
		
		for (DoublePlug p:DoublePlug.doublePlugs) {
			if (p.getA() >= triplet[0]-2 && p.getB() >= triplet[1]-2 && p.getC() >= triplet[2]-2) {
				count++;
			}
		}
		
		
		return count / (double) ArmorGrader.combinations;
		
	}
	
	public String toString() {
		return "mob " + mob + "\n" + "res " + res + "\n" + "rec " + rec + "\n" + "dis " + dis + "\n" + "itl " + itl + "\n" + "str " + str + "\n";	
	}
	
	public int statTotal() {
		return mob + res + rec + dis + itl + str;
	}

	public void setStat(int i, int j) {
		switch (i) {
        case 0:  mob = j;
        break;
        case 1:  res = j;
        break;
        case 2:  rec = j;
        break;
        case 3:  dis = j;
        break;
        case 4:  itl = j;
        break;
        case 5:  str = j;
        break;
        }
		
	}
	
	public int getStat(int i) {
		int ret = 0;
		switch (i) {
        case 0:  ret = mob;
        break;
        case 1:  ret = res;
        break;
        case 2:  ret = rec;
        break;
        case 3:  ret = dis;
        break;
        case 4:  ret = itl;
        break;
        case 5:  ret = str;
        break;
        }
		
		return ret;
		
	}

	public static boolean verify(ArmorPiece a) {
		if (a.getTopTotal() > 34 || a.getTopTotal() < 22) return false;
		else if (a.getBottomTotal() > 34 || a.getBottomTotal() < 22) return false;
		else return true;
	}
	

}
