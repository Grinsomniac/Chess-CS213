package chess;

import java.util.*;

public class Queen extends Piece {

    private PieceFile queenPieceFile;
    private int queenPieceRank;

    public Queen(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);

    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WQ;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BQ;
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
        // The queen hasn't moved, which is not a valid move
        return false;
    }

    // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Determine the direction of movement
    int rankDirection = Integer.compare(newRank, pieceRank);
    int fileDirection = Integer.compare(newFile.ordinal(), pieceFile.ordinal());

    // Queens can move vertically, horizontally, or diagonally
    if (rankDifference == 0 || fileDifference == 0 || rankDifference == fileDifference) {
        // Check if the path is unobstructed
        int currentRank = pieceRank + rankDirection;
        PieceFile currentFile = PieceFile.values()[pieceFile.ordinal() + fileDirection];
        while (currentRank != newRank || currentFile != newFile) {
            for (ReturnPiece piece : piecesOnBoard) {
                if (piece.pieceRank == currentRank && piece.pieceFile == currentFile) {
                    // The move is obstructed by another piece
                    return false;
                }
            }
            currentRank += rankDirection;
            currentFile = PieceFile.values()[currentFile.ordinal() + fileDirection];
        }

        // The move is valid for a queen and unobstructed
        return true;
    }

    // The move is not valid for a queen
    return false;
    }

    public boolean isMoveValid(int newRank, PieceFile newFile) {
        // Check if the new rank and new file are the same as the current rank and file
    if (newRank == pieceRank && newFile == pieceFile) {
        // The queen hasn't moved, which is not a valid move
        return false;
    }

    // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Queens can move vertically, horizontally, or diagonally
    if (rankDifference == 0 || fileDifference == 0 || rankDifference == fileDifference) {
        // The move is valid for a queen
        return true;
    }

    // The move is not valid for a queen
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
    public void capture(ReturnPiece.PieceFile movingFile, int movingRank, ReturnPiece.PieceFile takeFile, int takeRank, ArrayList<ReturnPiece> piecesOnBoard) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'capture'");
    }

    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite){      // DELETE LATER
        return true;
    }

}
