package com.ingenious.algorithm.bot.impl.greedy;

import com.ingenious.algorithm.bot.BotAlgorithm;
import com.ingenious.algorithm.support.nodegenerators.AllBaseMovesGenerator;
import com.ingenious.engine.Game;
import com.ingenious.engine.logic.calculation.ScoreCalculatorLogic;
import com.ingenious.model.BoardNode;
import com.ingenious.model.Move;
import com.ingenious.model.Piece;
import com.ingenious.model.Tile;
import com.ingenious.model.players.impl.Bot;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by carolley on 12-Dec-17.
 */
public class GreedyAlgorithm extends BotAlgorithm {

    public ArrayList<Move> getAvailableMoves(Game game) {
        ArrayList<Move> generator = generateAllMoves(game);
                //generateAllBaseMoves(game);
        return generator;
    }

    public ArrayList<Move> generateAllMoves(Game game){
        ArrayList<Move> available = new ArrayList<>();
        for(BoardNode boardNode : game.getBoard().getBoardNodes()){
            if(boardNode.isEmpty()){
                for(BoardNode neighbour: game.getBoard().getAvailableNeighboursOfNode(boardNode)){
                    for(Piece piece: game.getCurrentPlayer().getRack().getPieces()){
                       /* if(game.getCurrentPlayer()  instanceof Bot){
                            System.out.println("BOTTT");
                        }*/
                        Move move = new Move(boardNode, neighbour,piece);
                        available.add(move);
                    }
                }
            }
        }
        return available;
    }


    public ArrayList<ScoreMove> generateScoreMoves(Game game, ArrayList<Move> allMoves) {
        ArrayList<ScoreMove> scoreMoves = new ArrayList<>();
        for (Move aMove : allMoves) {
            if(aMove.getPiece().hasEqualTiles()){
                ScoreCalculatorLogic calc = new ScoreCalculatorLogic(game,aMove);
                int scoreHead = calc.getScoreStreakHeadPiece();
                int scoreTail = calc.getScoreStreakTailPiece();
                Tile color = aMove.getPiece().getHead();
                ScoreMove scoreMove = new ScoreMove(aMove, scoreHead, scoreTail, color, color);
                if (scoreMove.getTotalScore() > 0)
                    scoreMoves.add(scoreMove);
            }
            else{
                ScoreCalculatorLogic calc = new ScoreCalculatorLogic(game, aMove);
                int score1 = calc.getScoreStreakHeadPiece();
                int score2 = calc.getScoreStreakTailPiece();
                Tile color1 = aMove.getPiece().getHead();
                Tile color2 = aMove.getPiece().getTail();
                ScoreMove scoreMove1 = new ScoreMove(aMove, score1, score2, color1, color2);
                if (scoreMove1.getTotalScore() > 0)
                    scoreMoves.add(scoreMove1);
            }
        }
        return scoreMoves;
    }

    public Move getGlobalHigh(ArrayList<ScoreMove> scoreMoves){
        Move move = scoreMoves.get(0).getMove();
        ScoreMove scoreMove = scoreMoves.get(0);
        for(ScoreMove aScoreMove : scoreMoves){
            if(scoreMove.getTotalScore() < aScoreMove.getTotalScore()){
                move = aScoreMove.getMove();
                scoreMove = aScoreMove;
            }
        }
        return move;
    }

    public ArrayList<ScoreMove> color_move(Tile color, ArrayList<ScoreMove> scoreMoves){
        ArrayList<ScoreMove> moves = new ArrayList<>();
        for(ScoreMove aScoreMove: scoreMoves){
            if(aScoreMove.contains_color(color)){
                moves.add(aScoreMove);
            }
        }
        return moves;
    }

    public Move getBestMoveColor(Tile tile, ArrayList<ScoreMove> scoreMoves){
        ArrayList<ScoreMove> moves = color_move(tile, scoreMoves);
        if(moves.size()==0){
            return null;
        }
        else{
            int score = moves.get(0).scoreColor(tile);
            ScoreMove move = moves.get(0);
            for(ScoreMove aScoreMove: scoreMoves){
                if(aScoreMove.scoreColor(tile)> score){
                    score = aScoreMove.scoreColor(tile);
                    move = aScoreMove;
                }
            }
            return move.getMove();
        }
    }


    public Move selectBestMove(Game game, ArrayList<ScoreMove> scoreMoves) {
       if(!playHighest(game)){
           Tile[] sorted = game.getCurrentPlayer().getScore().sort();
           for(Tile color: sorted){
               if(getBestMoveColor(color,scoreMoves) != null ){
                   return getBestMoveColor(color, scoreMoves);
               }
           }
       }
            return getGlobalHigh(scoreMoves);
    }


    public boolean playHighest(Game game) {
        Tile[] sorted = game.getCurrentPlayer().getScore().sort();
        int low = game.getCurrentPlayer().getScore().getScore(sorted[0]);
        int high = game.getCurrentPlayer().getScore().getScore(sorted[5]);
        for(int i=5; i>0; i--){
            if(game.getCurrentPlayer().getScore().getScore(sorted[i])!=18){
                high = game.getCurrentPlayer().getScore().getScore(sorted[i]);
                break;
            }
        }
        if (high < (low * 2)) {
            return true;
        }
        return false;
    }

    @Override
    public Move execute(Game game) {
        Game simulatedGame = game;
        ArrayList<Move> allMoves = this.getAvailableMoves(simulatedGame);
        ArrayList<ScoreMove> scoreMoves = this.generateScoreMoves(simulatedGame, allMoves);
        return selectBestMove(simulatedGame, scoreMoves);
    }
}
