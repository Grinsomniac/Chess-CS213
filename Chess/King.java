package chess;

import java.util.*;

public class King extends Piece {
    public King(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() { return PieceType.WK; }
    @Override
    public PieceType getBlackPieceType() { return PieceType.BK; }
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

        public boolean isKingInCheck(ArrayList<ReturnPiece> piecesOnBoard) {
      
                            int piecesChecking = 0;
                            for(ReturnPiece piece : piecesOnBoard){

                                if (piece.pieceType.toString().charAt(1) == 'P') {
                                    Pawn castedPawnPiece = (Pawn) piece;

                                    if(castedPawnPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard, castedPawnPiece.isWhite, ' ')){
                                        System.out.println("check from pawn" + castedPawnPiece.pieceFile.toString() + "" + castedPawnPiece.pieceRank);
                                        piecesChecking++;
                                    }
                            }

                             if (piece.pieceType.toString().charAt(1) == 'Q') {
                                    Queen castedQueenPiece = (Queen) piece;

                                    if(castedQueenPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard, castedQueenPiece.isWhite, ' ')){
                                        System.out.println("check from queen" + castedQueenPiece.pieceFile.toString() + "" + castedQueenPiece.pieceRank);
                                        piecesChecking++;
                                    }
                            }

                             if (piece.pieceType.toString().charAt(1) == 'B') {
                                    Bishop castedBishopPiece = (Bishop) piece;

                                    if(castedBishopPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard, castedBishopPiece.isWhite, ' ')){
                                        System.out.println("check from bishop" + castedBishopPiece.pieceFile.toString() + "" + castedBishopPiece.pieceRank);
                                        piecesChecking++;

                                    }

                            }

                             if (piece.pieceType.toString().charAt(1) == 'R') {
                                    Rook castedRookPiece = (Rook) piece;

                                    if(castedRookPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard, castedRookPiece.isWhite, ' ')){
                                        System.out.println("check from rook" + castedRookPiece.pieceFile.toString() + "" + castedRookPiece.pieceRank);
                                        piecesChecking++;

                                    }

                            }

                             if (piece.pieceType.toString().charAt(1) == 'N') {
                                    Knight castedKnightPiece = (Knight) piece;

                                    if(castedKnightPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard, castedKnightPiece.isWhite, ' ')){
                                        System.out.println("check from knight" + castedKnightPiece.pieceFile.toString() + "" + castedKnightPiece.pieceRank);
                                        piecesChecking++;

                                    }
                            }
                        } // end of navigate through arraylist

                    
            if(piecesChecking > 0){
                System.out.println("in check from" + piecesChecking + "pieces"); // test print for how many pieces are checking the king
                return true;        // the king is in check
            }
            else{
                
                return false; // the king is not in check
            }
         
           
        } // end of isKingInCheck
}
