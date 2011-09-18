package com.github.tpc;

class Score {
		
	int playersCount = -1;
	int buyIn = -1;
	int finalPosition = -1;
	
    double countPointsForPosition(int position) {
        int multiplePoints = buyIn < 1000 ? 10 : 20;
        double result = multiplePoints * (Math.sqrt((Math.sqrt(buyIn) * (playersCount - 6) / position)));
        return Math.ceil(result);
    }

    String[][] getPointsList() {
        int numberPlaces = new Double(Math.ceil(playersCount * 0.2)).intValue();
        String[][] points = new String[numberPlaces][2];
        for (int i = 0; i < numberPlaces; i++) {
            points[i][0] = String.valueOf(i + 1);
            points[i][1] = String.valueOf(countPointsForPosition(i + 1));
        }
        return points;
    }
    
    boolean isBuyInValid() {
    	return buyIn > 0; 
    }
    
    boolean isPlayersCountValid() {
    	return playersCount > 6;
    }
    
    boolean isFinalPositionValid() {
    	return finalPosition <= playersCount;    	
    }
    
    boolean isValid() {
    	return isBuyInValid() && isPlayersCountValid() && isFinalPositionValid();
    }
}
