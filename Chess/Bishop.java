package chess;

import java.util.*;

public class Bishop extends Piece {
    public Bishop(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }
    @Override
    public PieceType getWhitePieceType() { return PieceType.WB; }
    @Override
    public PieceType getBlackPieceType() { return PieceType.BB; }
    public boolean getisWhite() { return isWhite; }

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece) {
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

    for (ReturnPiece piece : piecesOnBoard) {
         if(piece.pieceRank == newRank && piece.pieceFile.toString().charAt(0) == newFile.toString().charAt(0)){
                
                    if(piece.pieceType.toString().charAt(0) == 'W' && !playerWhite){   
                     capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                    return true;
                }
                else{
                if(piece.pieceType.toString().charAt(0) == 'B' && playerWhite){   
                                                                                   
                     capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        return true;
                }
                else{
                    return false;
                }
            }
        }
    }
    // The move is valid for a bishop and unobstructed
    return true;
    }
}
