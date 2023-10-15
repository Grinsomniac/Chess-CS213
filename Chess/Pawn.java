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
        if(getisWhite()){
            rankDifference = (newRank - pieceRank);
            fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
        }
        else {
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
        return true;
        }  else if (rankDifference == 2 && fileDifference == 0 && ((isWhite && pieceRank == 2) || (!isWhite && pieceRank == 7)) && fileDirection == 0) {
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
                    if(piece.pieceType.toString().charAt(0) == 'W' && !playerWhite){   
                        capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        //New Code for Pawn Promo//////////////////////////////////////////////////////////////
                        if (newRank == 1 && !playerWhite) {
                            piecesOnBoard.remove(this);
                            Piece promotedPiece;
    
                            switch (promotionPiece) {
                                case 'b':
                                    promotedPiece = new Bishop(newFile, newRank, false);
                                    break;
                                case 'n':
                                    promotedPiece = new Knight(newFile, newRank, false);
                                    break;
                                case 'r':
                                    promotedPiece = new Rook(newFile, newRank, false);
                                    break;
                                case 'q':
                                    promotedPiece = new Queen(newFile, newRank, false);
                                    break;
                                case ' ':
                                    promotedPiece = new Queen(newFile, newRank, false);
                                    break;
                                default:
                                    // Handle any other case here, or use a default piece (e.g., Queen) if not specified
                                    promotedPiece = new Queen(newFile, newRank, false);
                                    break;
                            }
                            piecesOnBoard.add(promotedPiece);
                        }
                        //New Code for Pawn Promo//////////////////////////////////////////////////////////////
                        return true;
                    }
                    else { 
                        if(piece.pieceType.toString().charAt(0) == 'B' && playerWhite){   
                                                                                   
                     capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                     //New Code for Pawn Promo//////////////////////////////////////////////////////////////
                     if (newRank == 8 && playerWhite) {
                        piecesOnBoard.remove(this);
                        Piece promotedPiece;
    
                        switch (promotionPiece) {
                            case 'b':
                                promotedPiece = new Bishop(newFile, newRank, true);
                                break;
                            case 'n':
                                promotedPiece = new Knight(newFile, newRank, true);
                                break;
                            case 'r':
                                promotedPiece = new Rook(newFile, newRank, true);
                                break;
                            case 'q':
                                promotedPiece = new Queen(newFile, newRank, true);
                                break;
                            case ' ':
                                promotedPiece = new Queen(newFile, newRank, true);
                                break;
                            default:
                                promotedPiece = new Queen(newFile, newRank, true);
                                break;
                        }
                        piecesOnBoard.add(promotedPiece);
                    }
                        //New Code for Pawn Promo//////////////////////////////////////////////////////////////
                        return true;
                    }
                    else {
                        return false;
                    }
                    }
                
                }
            }   
        }
        // The move is not valid for a pawn
        return false;
    }
}
