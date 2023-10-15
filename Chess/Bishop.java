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
        // Check if the new rank and new file are the same as the current rank and file
    if (newRank == pieceRank && newFile == pieceFile) {
        // The bishop hasn't moved, which is not a valid move
        return false;
    }

    // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Check if the move is diagonal
    if (rankDifference != fileDifference) {
        // The move is not diagonal, which is not valid for a bishop
        return false;
    }

    // Determine the direction of movement
    int rankDirection = Integer.compare(newRank, pieceRank);
    int fileDirection = Integer.compare(newFile.ordinal(), pieceFile.ordinal());

    // Check if the path is unobstructed
    int currentRank = pieceRank + rankDirection;
    PieceFile currentFile = PieceFile.values()[pieceFile.ordinal() + fileDirection];
    while (currentRank != newRank && currentFile != newFile) {
        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceRank == currentRank && piece.pieceFile == currentFile) {
                // The move is obstructed by another piece
                return false;
            }
        }
        currentRank += rankDirection;
        currentFile = PieceFile.values()[currentFile.ordinal() + fileDirection];
    }

    // The move is valid for a bishop and unobstructed
    return true;
    }

    public boolean isMoveValid(int newRank, PieceFile newFile) {

        System.out.println("IN ROOK isMOVEVALID");
        // Check if the new rank and new file are the same as the current rank and file
    if (newRank == pieceRank && newFile == pieceFile) {
        // The bishop hasn't moved, which is not a valid move
        return false;
    }

    // Check if the absolute difference between ranks and files is the same
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    if (rankDifference == fileDifference) {
        // The move is diagonal, which is valid for a bishop
        return true;
    }

    // The move is not valid for a bishop
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
