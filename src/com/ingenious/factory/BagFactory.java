package com.ingenious.factory;

import com.ingenious.model.Bag;
import com.ingenious.model.Piece;

public class BagFactory {
    private Bag bag;

    public BagFactory() {
        this.bag = new Bag();
    }

    public Bag generate() {

        /* FILL THE BAG WITH 6 PIECES OF A DIFFERENT COLOR */
        for (int i = 0; i < 6; i++) {
            this.bag.addPiece(Piece.RED_BLUE);
            this.bag.addPiece(Piece.RED_GREEN);
            this.bag.addPiece(Piece.RED_ORANGE);
            this.bag.addPiece(Piece.RED_YELLOW);
            this.bag.addPiece(Piece.RED_PURPLE);

            this.bag.addPiece(Piece.BLUE_GREEN);
            this.bag.addPiece(Piece.BLUE_ORANGE);
            this.bag.addPiece(Piece.BLUE_YELLOW);
            this.bag.addPiece(Piece.BLUE_PURPLE);

            this.bag.addPiece(Piece.GREEN_ORANGE);
            this.bag.addPiece(Piece.GREEN_YELLOW);
            this.bag.addPiece(Piece.GREEN_PURPLE);

            this.bag.addPiece(Piece.ORANGE_YELLOW);
            this.bag.addPiece(Piece.ORANGE_PURPLE);

            this.bag.addPiece(Piece.YELLOW_PURPLE);
        }

        /* FILL THE BAG WITH 5 PIECES OF THE SAME COLOR */
        for (int i = 0; i < 5; i++) {
            this.bag.addPiece(Piece.RED_RED);
            this.bag.addPiece(Piece.BLUE_BLUE);
            this.bag.addPiece(Piece.GREEN_GREEN);
            this.bag.addPiece(Piece.ORANGE_ORANGE);
            this.bag.addPiece(Piece.YELLOW_YELLOW);
            this.bag.addPiece(Piece.PURPLE_PURPLE);
        }
        return this.bag;
    }


}
