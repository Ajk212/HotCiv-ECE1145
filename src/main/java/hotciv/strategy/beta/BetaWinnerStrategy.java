package hotciv.strategy.beta;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.strategy.WinnerStrategy;

import java.util.Iterator;

public class BetaWinnerStrategy implements WinnerStrategy {

    public Player getWinner(Game game) {
        Player possibleWinner = null;

        for(int r = 0; r < GameConstants.WORLDSIZE; r++){
            for(int c = 0; c < GameConstants.WORLDSIZE; c++){
                Position p = new Position(r, c);
                City city = game.getCityAt(p);
                if(city != null){
                    if(possibleWinner == null){
                        possibleWinner = city.getOwner();
                    }
                    else if(city.getOwner() != possibleWinner){
                        return null;
                    }
                }
            }
        }
        //If same player owns all cities, player is winner
        return possibleWinner;
    }

}
