package chess;

import java.util.*;


public abstract class Piece extends ReturnPiece {

    public boolean isWhite;
    public int pieceMoveCount; // FOR PAWN MOVEMENT, CASTLING (ROOK & KING)

    // Constructor
    public Piece(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        this.pieceType = isWhite ? getWhitePieceType() : getBlackPieceType();
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
        this.isWhite = isWhite;
        this.pieceMoveCount = 0; // initializes moveCount to 0
        
    }

    public abstract PieceType getWhitePieceType();

    public abstract PieceType getBlackPieceType();

    public abstract PieceFile getPieceFile();

    public abstract int getPieceRank();

    public abstract boolean getisWhite();

    public abstract boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece);

    /* 
    public boolean firstIsMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece){

        for(ReturnPiece piece : piecesOnBoard){         // first check to make sure the move doesnt put king in check
           if(playerWhite){
           if(piece.pieceType.toString() == "WK"){
               Piece king = (Piece) piece;
               if(king.isSquareInCheck(piecesOnBoard, king.pieceFile, king.pieceRank)){
                   return false;
                                                   }
                                             }
                    }
                   if(!playerWhite){
                   if(piece.pieceType.toString() == "BK"){
                       Piece king = (Piece) piece;
                       if(king.isSquareInCheck(piecesOnBoard, king.pieceFile, king.pieceRank)){
                           return false;
                                                   }
                                             }
                    }
                                   }       // end of first check

        
                return isMoveValid(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
   
  
    }
    */

    public int getPieceMoveCount() {
        return this.pieceMoveCount;
    }

    public void capture(ReturnPiece.PieceFile takeFile, int takeRank, ArrayList<ReturnPiece> piecesOnBoard){
        /// Remove the captured piece from the list
        for(int i = 0; i < piecesOnBoard.size(); i++){
            if(piecesOnBoard.get(i).pieceFile.toString().charAt(0) == takeFile.toString().charAt(0) && piecesOnBoard.get(i).pieceRank == takeRank){
                 piecesOnBoard.remove(i);
            }
        }
    }

    public boolean isTileEmpty(ReturnPiece.PieceFile targetFile, int targetRank, ArrayList<ReturnPiece> piecesOnBoard) {
        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceFile == targetFile && piece.pieceRank == targetRank) {
                // A piece is occupying the target tile, so it's not empty
                return false;
            }
        }
        // No piece is occupying the target tile, so it's empty
        return true;
    }

    public boolean isSquareInCheck(ArrayList<ReturnPiece> piecesOnBoard, ReturnPiece.PieceFile newFile, int newRank) {

        int piecesChecking = 0;
        for (ReturnPiece piece : piecesOnBoard) {

            if (piece.pieceType.toString().charAt(1) == 'P') {
                
                Pawn castedPawnPiece = (Pawn) piece;
                if (castedPawnPiece.isMoveValid(newRank, newFile, piecesOnBoard, castedPawnPiece.isWhite, ' ')) {
                    System.out.println(
                            "check from pawn" + castedPawnPiece.pieceFile.toString() + "" + castedPawnPiece.pieceRank);
                    piecesChecking++;
                    System.out.println("In is Square");
                }
            }

            if (piece.pieceType.toString().charAt(1) == 'Q') {
                Queen castedQueenPiece = (Queen) piece;

                if (castedQueenPiece.isMoveValid(newRank, newFile, piecesOnBoard, castedQueenPiece.isWhite, ' ')) {
                    System.out.println("check from queen" + castedQueenPiece.pieceFile.toString() + ""
                            + castedQueenPiece.pieceRank);
                    piecesChecking++;
                }
            }

            if (piece.pieceType.toString().charAt(1) == 'B') {
                Bishop castedBishopPiece = (Bishop) piece;

                if (castedBishopPiece.isMoveValid(newRank, newFile, piecesOnBoard, castedBishopPiece.isWhite, ' ')) {
                    System.out.println("check from bishop" + castedBishopPiece.pieceFile.toString() + ""
                            + castedBishopPiece.pieceRank);
                    piecesChecking++;

                }

            }

            if (piece.pieceType.toString().charAt(1) == 'R') {
                Rook castedRookPiece = (Rook) piece;

                if (castedRookPiece.isMoveValid(newRank, newFile, piecesOnBoard, castedRookPiece.isWhite, ' ')) {
                    System.out.println(
                            "check from rook" + castedRookPiece.pieceFile.toString() + "" + castedRookPiece.pieceRank);
                    piecesChecking++;

                }

            }

            if (piece.pieceType.toString().charAt(1) == 'N') {
                Knight castedKnightPiece = (Knight) piece;

                if (castedKnightPiece.isMoveValid(newRank, newFile, piecesOnBoard, castedKnightPiece.isWhite, ' ')) {
                    System.out.println("check from knight" + castedKnightPiece.pieceFile.toString() + ""
                            + castedKnightPiece.pieceRank);
                    piecesChecking++;

                }
            }
        } // end of navigate through arraylist
        System.out.println("Exit arraylist");
        if (piecesChecking > 0) {
            System.out.println("in check from" + piecesChecking + "pieces"); // test print for how many pieces are
                                                                             // checking the king
            return true; // the king is in check
        } else {

            return false; // the king is not in check
        }

    } // end of isKingInCheck
}
/* 
 public boolean isSquareInCheck(ArrayList<ReturnPiece> piecesOnBoard) {

        int piecesChecking = 0;
        for (ReturnPiece piece : piecesOnBoard) {

            if (piece.pieceType.toString().charAt(1) == 'P') {
                Pawn castedPawnPiece = (Pawn) piece;

                if (castedPawnPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard,
                        castedPawnPiece.isWhite, ' ')) {
                    System.out.println(
                            "check from pawn" + castedPawnPiece.pieceFile.toString() + "" + castedPawnPiece.pieceRank);
                    piecesChecking++;
                }
            }

            if (piece.pieceType.toString().charAt(1) == 'Q') {
                Queen castedQueenPiece = (Queen) piece;

                if (castedQueenPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard,
                        castedQueenPiece.isWhite, ' ')) {
                    System.out.println("check from queen" + castedQueenPiece.pieceFile.toString() + ""
                            + castedQueenPiece.pieceRank);
                    piecesChecking++;
                }
            }

            if (piece.pieceType.toString().charAt(1) == 'B') {
                Bishop castedBishopPiece = (Bishop) piece;

                if (castedBishopPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard,
                        castedBishopPiece.isWhite, ' ')) {
                    System.out.println("check from bishop" + castedBishopPiece.pieceFile.toString() + ""
                            + castedBishopPiece.pieceRank);
                    piecesChecking++;

                }

            }

            if (piece.pieceType.toString().charAt(1) == 'R') {
                Rook castedRookPiece = (Rook) piece;

                if (castedRookPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard,
                        castedRookPiece.isWhite, ' ')) {
                    System.out.println(
                            "check from rook" + castedRookPiece.pieceFile.toString() + "" + castedRookPiece.pieceRank);
                    piecesChecking++;

                }

            }

            if (piece.pieceType.toString().charAt(1) == 'N') {
                Knight castedKnightPiece = (Knight) piece;

                if (castedKnightPiece.isMoveValid(pieceRank, pieceFile, piecesOnBoard,
                        castedKnightPiece.isWhite, ' ')) {
                    System.out.println("check from knight" + castedKnightPiece.pieceFile.toString() + ""
                            + castedKnightPiece.pieceRank);
                    piecesChecking++;

                }
            }
        } // end of navigate through arraylist

        if (piecesChecking > 0) {
            System.out.println("in check from" + piecesChecking + "pieces"); // test print for how many pieces are
                                                                             // checking the king
            return true; // the king is in check
        } else {

            return false; // the king is not in check
        }

    } // end of isKingInCheck


*/