package chess;

import java.util.*;

public class Bishop extends Piece {
    public Bishop(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WB;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BB;
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
    }

    @Override
    public void executeMove(int newRank, ReturnPiece.PieceFile newFile) {
        // TODO
    }

    public boolean isMoveValidTest(int newRank, PieceFile newFile) {
        System.out.println("In isMoveValidTest");

        return true;
    }
}
