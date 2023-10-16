package chess;

import java.util.*;

public class Pawn extends Piece {
    public Pawn(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }
    @Override
    public PieceType getWhitePieceType() { return PieceType.WP; }
    @Override
    public PieceType getBlackPieceType() { return PieceType.BP; }
    public boolean getisWhite() { return isWhite; }

    @Override
    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece) {
        // Check if the new rank and new file are the same as the current rank and file
        if (newRank == pieceRank && newFile == pieceFile) {
            // The pawn hasn't moved, which is not a valid move
            return false;
        }
        int rankDifference;
        int fileDifference;
        if (getisWhite()) {
            rankDifference = (newRank - pieceRank);
            fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
        } else {
            rankDifference = (pieceRank - newRank);
            fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
        }

        // Determine the direction of movement
        int rankDirection = Integer.compare(newRank, pieceRank);
        int fileDirection = Integer.compare(newFile.ordinal(), pieceFile.ordinal());

        // Pawns move differently on their initial and subsequent moves
        if (rankDifference == 1 && fileDifference == 0) {
            // Subsequent move: one square forward
            for (ReturnPiece piece : piecesOnBoard) {
                if (piece.pieceRank == newRank && piece.pieceFile == newFile) {
                    // The move is obstructed by another piece
                    return false;
                }
            }
            // This is where we should check for promotion when reaching the last rank
            pawnPromo(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
            return true;
        } else if (rankDifference == 2 && fileDifference == 0 && ((isWhite && pieceRank == 2) || (!isWhite && pieceRank == 7)) && fileDirection == 0) {
            // Initial move: two squares forward from the second rank (based on whether it's a white or black pawn)
            int intermediateRank = pieceRank + rankDirection;
            for (ReturnPiece piece : piecesOnBoard) {
                if ((piece.pieceRank == intermediateRank && piece.pieceFile == pieceFile) || (piece.pieceRank == newRank && piece.pieceFile == newFile)) {
                    // The move is obstructed by another piece
                    return false;
                }
            } 
            return true;
        } else if (rankDifference == 1 && fileDifference == 1 && pieceFile.ordinal() + fileDirection == newFile.ordinal()) {
            // Capturing a piece diagonally
            for (ReturnPiece piece : piecesOnBoard) {
                if (piece.pieceRank == newRank && piece.pieceFile == newFile) {
                    // The move is valid for capturing an opponent's piece
                    if (piece.pieceType.toString().charAt(0) == 'W' && !playerWhite) {
                        capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        // This is where we should check for promotion when reaching the last rank
                        pawnPromo(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
                        return true;
                    } else if (piece.pieceType.toString().charAt(0) == 'B' && playerWhite) {
                        capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        // This is where we should check for promotion when reaching the last rank
                        pawnPromo(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        // The move is not valid for a pawn
        return false;
    }

    public void pawnPromo(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece ){
        // This is where we should check for promotion when reaching the last rank
        if ((newRank == 1 && !playerWhite) || (newRank == 8 && playerWhite)) {
               
        // Remove the promoted pawn (original piece)
         piecesOnBoard.remove(this);
         Piece promotedPiece;
              
            switch (promotionPiece) {
                case 'b':
                    promotedPiece = new Bishop(newFile, newRank, playerWhite);
                    break;
                case 'n':
                    promotedPiece = new Knight(newFile, newRank, playerWhite);
                    break;
                case 'r':
                    promotedPiece = new Rook(newFile, newRank, playerWhite);
                    break;
                case 'q':
                    promotedPiece = new Queen(newFile, newRank, playerWhite);
                    break;
                case ' ':
                    promotedPiece = new Queen(newFile, newRank, playerWhite);
                    break;
                default:
                   promotedPiece = new Queen(newFile, newRank, playerWhite);
                   break;
            }
            piecesOnBoard.add(promotedPiece);
        }
    }
}
