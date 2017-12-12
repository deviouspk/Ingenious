package com.ingenious.algorithm.bot.impl.alphabeta;

import com.ingenious.engine.Game;
import com.ingenious.model.Move;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TreeNode
{
    public ArrayList<TreeNode> children;
    public ArrayList<Move> parentMoves;
    public Move move;
    public int evaluationScore;

    public TreeNode(Move move, ArrayList<Move> parentMoves)
    {
        this.move = move;
        this.children = new ArrayList<>();
        this.parentMoves = parentMoves;
    }

    public TreeNode(){}

    public ArrayList<TreeNode> getChildren()
    {
        return children;
    }

    public void addChild(TreeNode newChild)
    {
        this.children.add(newChild);
    }

    public ArrayList<Move> getFullPath()
    {
        ArrayList<Move> fullPath = new ArrayList<Move>();
        for(Move move : this.parentMoves)
        {
            fullPath.add(move);
        }
        fullPath.add(this.move);
        return fullPath;
    }

    public void addEvaluation(Game parentState, Game currentState)
    {
        this.evaluationScore = ScoreEvaluationFunction.evaluateScores(parentState, currentState);
    }

    public boolean hasChildren()
    {
        if(children.size() > 0)
            return true;
        else
            return false;
    }
}
