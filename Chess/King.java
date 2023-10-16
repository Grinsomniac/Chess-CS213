package chess;

import java.util.*;

public class King extends Piece {
    public King(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() { return PieceType.WN; }
    @Override
    public PieceType getBlackPieceType() { return PieceType.BN; }
    public boolean getisWhite() { return isWhite;}
    @Override
    public ReturnPiece.PieceFile getPieceFile() { return pieceFile; }
    @Override
    public int getPieceRank() { return pieceRank; }

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece) {
        // Calculate the absolute differences between ranks and files
        int rankDifference = Math.abs(newRank - pieceRank);
        int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

        // Check if the king is trying to castle
        if (rankDifference == 0 && fileDifference == 2) {
            // Check which direction the king is castling (kingside or queenside)
            if (newFile == PieceFile.g && pieceFile == PieceFile.e) {
                // Kingside castling
                // 1. The king and the kingside rook have not moved.
                // 2. There are no pieces between them.
                // 3. The squares they move through are not under attack.
                // If all conditions are met, return true for the castling move.
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

        public boolean isKingInCheck(ArrayList<ReturnPiece> piecesOnBoard, King king) {
            ReturnPiece.PieceFile kingFile = king.getPieceFile();
            int kingRank = king.getPieceRank();
            boolean isWhite = king.getisWhite();
            boolean pieceWhite;
            // Iterate through all the opponent's pieces
            for (ReturnPiece piece : piecesOnBoard) {
                if(piece.pieceType.toString().charAt(0) == 'W'){
                    pieceWhite = true;
                }else{pieceWhite = false;}
                if (pieceWhite != isWhite) {
                    // Check if the opponent's piece can attack the king's square
                    if (piece.isMoveValid(kingRank, kingFile, piecesOnBoard, !isWhite, ' ')) {
                        // The king is in check
                        return true;
                    }
                }
            }
        
            // The king is not in check
            return false;
        }
}
