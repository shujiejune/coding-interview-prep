int solution(int[] fish, int[] baits) {
	Arrays.sort(fish);
	Arrays.sort(baits);
	int num = 0;
	int i = fish.length - 1;
	for (int j = baits.length - 1; j >= 0; j--) {
		int count = 0;
		while (count < 3 && i >= 0 && fish[i] > baits[j]) {
			i--;
			count++;
			num++;
		}
	}
	return num;
}
