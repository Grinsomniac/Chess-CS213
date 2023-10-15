package chess;

import java.util.*;

public class Knight extends Piece {
    public Knight(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WN;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BN;
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
        // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Knights move in an L-shape: two squares in one direction and one square in a perpendicular direction
    if ((rankDifference == 2 && fileDifference == 1) || (rankDifference == 1 && fileDifference == 2)) {
        // The move is valid for a knight
        return true;
    }

    // The move is not valid for a knight
    return false;
    }

    public boolean isMoveValid(int newRank, PieceFile newFile) {
        // Check if the new rank and new file are the same as the current rank and file
    if (newRank == pieceRank && newFile == pieceFile) {
        // The knight hasn't moved, which is not a valid move
        return false;
    }

    // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Knights move in an L-shape: 2 squares in one direction and 1 square perpendicular
    if ((rankDifference == 2 && fileDifference == 1) || (rankDifference == 1 && fileDifference == 2)) {
        // The move is valid for a knight
        return true;
    }

    // The move is not valid for a knight
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

    @Override
    public void capture(ReturnPiece targetPiece, ReturnPiece takePiece, ArrayList<ReturnPiece> piecesOnBoard) {
      
            

    }

}
