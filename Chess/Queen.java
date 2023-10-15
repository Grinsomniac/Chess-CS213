package chess;

import java.util.*;

public class Queen extends Piece {

    public Queen(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }
    @Override
    public PieceType getWhitePieceType() {return PieceType.WN;}
    @Override
    public PieceType getBlackPieceType() {return PieceType.BN;}
    public boolean getisWhite() {return isWhite;}


    @Override
    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite) {
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

    @Override
    public void capture(ReturnPiece.PieceFile movingFile, int movingRank, ReturnPiece.PieceFile takeFile, int takeRank, ArrayList<ReturnPiece> piecesOnBoard) {
             // Remove the captured piece from the list
        for(int i = 0; i < piecesOnBoard.size(); i++){
            if(piecesOnBoard.get(i).pieceFile.toString().charAt(0) == takeFile.toString().charAt(0) && piecesOnBoard.get(i).pieceRank == takeRank){
                 piecesOnBoard.remove(i);
            }
        }
    }

}
