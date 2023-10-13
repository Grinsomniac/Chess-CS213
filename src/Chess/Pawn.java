package chess;

import java.util.*;

public class Pawn extends Piece {
    public Pawn(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WP;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BP;
    }

    @Override
    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isMoveValid'");
    }

    @Override
    public void executeMove(int newRank, PieceFile newFile) {
        if (isMoveValid(newRank, newFile)) {
            this.pieceFile = newFile;
            this.pieceRank = newRank;
        }
        ReturnPlay returnPlay = new ReturnPlay();
        returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;

    }

    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile) {
        int rankDifference = Math.abs(newRank - pieceRank);
        int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

        // Check if the move is forward one square or two squares on the first move
        if ((pieceType == PieceType.WP && rankDifference == 1 && fileDifference == 0) ||
                (pieceType == PieceType.BP && rankDifference == 1 && fileDifference == 0) ||
                (pieceType == PieceType.WP && rankDifference == 2 && fileDifference == 0 && pieceRank == 2) ||
                (pieceType == PieceType.BP && rankDifference == 2 && fileDifference == 0 && pieceRank == 7)) {

            // Update this piece's rank and file
            return true;
        }

        // Check for diagonal capture
        if ((pieceType == PieceType.WP && rankDifference == 1 && fileDifference == 1) ||
                (pieceType == PieceType.BP && rankDifference == 1 && fileDifference == 1)) {

            // update rank and file
            return true;
        }

        // Add en passant and promotion logic as needed

        return false; // Invalid move
    }

}
