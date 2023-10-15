package chess;

import java.util.*;

public class King extends Piece {
    public King(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WK;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BK;
    }

    public boolean getisWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite) {
        // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Kings can move one square in any direction
    if (rankDifference <= 1 && fileDifference <= 1) {
        // The move is valid for a king
        return true;
    }

    // The move is not valid for a king
    return false;
    }
}
