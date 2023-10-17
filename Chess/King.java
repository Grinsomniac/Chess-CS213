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
    @Override
    public boolean getisWhite() {
        return isWhite;
    }
    @Override
    public ReturnPiece.PieceFile getPieceFile() {
        return pieceFile;
    }
    @Override
    public int getPieceRank() {
        return pieceRank;
    }
    public Rook whiteRook;
    public Rook blackRook;

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece) {
        // Calculate the absolute differences between ranks and files
        int rankDifference = Math.abs(newRank - pieceRank);
        int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
        boolean obstructed = false;
        boolean emptySquare;

        // Check if the king is trying to castle
        if (rankDifference == 0 && fileDifference == 2) {
            // Check which direction the king is castling (kingside or queenside)
            /**
             * if (king in check) {
             *     return false
             * }
             * if (pieces are there) {
             *     return false
             * }
             * return true
             */
            if (newFile == PieceFile.g && pieceFile == PieceFile.e) {
                // Kingside castling
                // 1. The king and the kingside rook have not moved.
                // 2. There are no pieces between them.
                // 3. The squares they move through are not under attack.
                // If all conditions are met, return true for the castling move

            } else if (newFile == PieceFile.c && pieceFile == PieceFile.e) {
                // Queenside castling
                // Add logic to check the conditions for queenside castling here
                // 1. The king and the queenside rook have not moved.
                // 2. There are no pieces between them.
                // 3. The squares they move through are not under attack.
                // If all conditions are met, return true for the castling move.
            }
        }

        // Standard king move (one square in any direction)
        if (rankDifference <= 1 && fileDifference <= 1) {
            // The move is valid for a king
            return true;
        }

        // The move is not valid for a king
        return false;
    }
    
    public void kingSideCastle(ReturnPiece.PieceFile newFile, int newRank, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite) {
        Rook whiteRook = null;
        Rook blackRook = null;
    
        // Find the king-side rook for the player
        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceFile == ReturnPiece.PieceFile.h && piece.pieceType.toString().charAt(2) == 'R') {
                if (piece.pieceType.toString().charAt(0) == 'W') {
                    whiteRook = (Rook) piece;
                } else {
                    blackRook = (Rook) piece;
                }
            }
        }
    
        if (playerWhite && whiteRook != null && whiteRook.getPieceMoveCount() == 0) {
            // Loop through squares between white King and kingside rook to check if they are empty
            if (!isTileEmpty(file, pieceRank, piecesOnBoard)) {
                //hardcode for each tile in between (if is empty and if tile is underattack)
                return; // Not all squares are empty; castling is not allowed
            }
            
    
            // Implement the castling logic here for white King's side
            // Move the King and Rook to their new positions
            // Update their pieceMoveCount, etc.
        } else if (!playerWhite && blackRook != null && blackRook.getPieceMoveCount() == 0) {
            // Loop through squares between black King and kingside rook to check if they are empty
            for (ReturnPiece.PieceFile file = pieceFile.nextFile(); file != ReturnPiece.PieceFile.h; file = file.nextFile()) {
                if (!isTileEmpty(file, pieceRank, piecesOnBoard)) {
                    return; // Not all squares are empty; castling is not allowed
                }
            }
    
            // Implement the castling logic here for black King's side
            // Move the King and Rook to their new positions
            // Update their pieceMoveCount, etc.
        }
        
    } 
    
} // end of class
