package chess;

import java.util.*;

public class Queen extends Piece {

    public Queen(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {return PieceType.WQ; }
    @Override
    public PieceType getBlackPieceType() {return PieceType.BQ;}
    public boolean getisWhite() { return isWhite; }
    @Override
    public ReturnPiece.PieceFile getPieceFile() { return pieceFile; }
    @Override
    public int getPieceRank() { return pieceRank; }

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece) {
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

        // Check if there is a piece at the destination
        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceRank == newRank && piece.pieceFile == newFile) {
                if (piece.pieceType.toString().charAt(0) != pieceType.toString().charAt(0)) {
                    // The move is valid for capturing an opponent's piece
                    if(piece.pieceType.toString().charAt(0) == 'W' && !playerWhite){   
                      //  if(piece.pieceType.toString().charAt(1) != 'K'){       // check that piece being captured is not a king.
                     //   capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                      //  }
                    return true;
                }
                else{
                if(piece.pieceType.toString().charAt(0) == 'B' && playerWhite){   
                 //    if(piece.pieceType.toString().charAt(1) != 'K'){           // check that piece being captured is not a king.                                                          
                 //    capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                 //    }
                     return true;
                }
                else{
                    return false;
                }
            }
                }
                return false;
            }
        }

        // The move is valid for a queen and unobstructed
        return true;
    }
   
    // The move is not valid for a queen
    return false;
    }
}


