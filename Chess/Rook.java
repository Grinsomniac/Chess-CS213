package chess;

import java.util.*;

public class Rook extends Piece {
    public Rook(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WR;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BR;
    }

    /*
     * public PieceColor getColor() {
     * return (pieceType == PieceType.WR || pieceType == PieceType.WP) ?
     * PieceColor.white : PieceColor.black;
     * }
     */

    public boolean getisWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard) {
        // TODO
        return true;

    }

    public boolean isMoveValid(int newRank, PieceFile newFile) {
        // Check if the new rank or file is the same as the current rank or file
    if (newRank == pieceRank && newFile == pieceFile) {
        // The rook hasn't moved, which is not a valid move
        return false;
    }

    // Check if the new rank or file is different from the current rank or file
    if (newRank != pieceRank && newFile != pieceFile) {
        // Rooks can only move vertically or horizontally, not diagonally
        return false;
    }

    // The move is valid if it's either vertical or horizontal
    return true;  
    }

    @Override
    public void executeMove(int newRank, ReturnPiece.PieceFile newFile) {
        // TODO
    }

    void rookMovement() {

    }

    public boolean isMoveValidTest(int newRank, PieceFile newFile) {
        System.out.println("In isMoveValidTest");

        return true;
    }
}

/* 
 if (isWhite) {
            int rankDifference = (newRank - pieceRank);
            int fileDifference = (newFile.ordinal() - pieceFile.ordinal());
            if ((rankDifference == 1 && fileDifference == 0)
                    || (rankDifference == 2 && fileDifference == 0 && pieceRank == 2)) {
                return true;
            }
        } else {
            int rankDifference = (pieceRank - newRank);
            int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
            if ((rankDifference == 1 && fileDifference == 0)
                    || (rankDifference == 2 && fileDifference == 0 && pieceRank == 7)) {
                return true;
            }
        }
        return false;
*/