package chess;

import java.util.*;

public class Pawn extends Piece {
    public Pawn(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    public static boolean enPassant = false;

    @Override
    public PieceType getWhitePieceType() { return PieceType.WP; }
    @Override
    public PieceType getBlackPieceType() { return PieceType.BP; }
    @Override
    public boolean getisWhite() { return isWhite; }
     @Override
    public ReturnPiece.PieceFile getPieceFile() { return pieceFile; }
    @Override
    public int getPieceRank() { return pieceRank; }


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
            enPassant = false;///////////////////////////////////// enPassant Toggle - False (or False after 1 tile first move or subsequent moves)) ///////////////////////
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
            enPassant = true;/////////////////////////////////////////////////////////// enPassant Toggle ///////////////////////////////////////////////////////
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
            //////////////////////////  New Code - EnPassant Capture  ////////////////////////////////////
            // Check for en passant capture
            if (enPassant && (playerWhite && pieceRank == 5) || (!playerWhite && pieceRank == 4)) {
                // Determine the direction of movement for en passant capture
                int enPassantRank = (playerWhite) ? (pieceRank + 1) : (pieceRank - 1);
                int enPassantFile = newFile.ordinal();

                // Check if there's an opponent's pawn at the en passant square
                for (ReturnPiece piece : piecesOnBoard) {
                    if (piece.pieceRank == enPassantRank && piece.pieceFile == PieceFile.values()[enPassantFile]) {
                        // Capture the opponent's pawn en passant
                        capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);//////// - >>>>> NEEDS A NEW CAPTURE METHOD IN THIS CLASS FOR SPECICIFALLY ENPASSANT
                        // Remove the captured piece from the board
                        piecesOnBoard.remove(piece);
                        pawnPromo(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
                        return true;
                    }
                }
            }
            //////////////////////////  New Code - EnPassant Capture  ////////////////////////////////////  
        }
        // The move is not valid for a pawn
        return false;
    }

    public void enPassantCapture(){

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
