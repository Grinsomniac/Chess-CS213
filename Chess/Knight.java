package chess;

import java.util.*;

public class Knight extends Piece {
    public Knight(PieceFile pieceFile, int pieceRank, boolean isWhite) {
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

    /* 

    public boolean firstIsMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece){

             for(ReturnPiece piece : piecesOnBoard){         // first check to make sure the move doesnt put king in check
                if(playerWhite){
                if(piece.pieceType.toString() == "WK"){
                    Piece king = (Piece) piece;
                    if(king.isSquareInCheck(piecesOnBoard)){
                        return false;
                                                        }
                                                  }
                         }
                        if(!playerWhite){
                        if(piece.pieceType.toString() == "BK"){
                            Piece king = (Piece) piece;
                            if(king.isSquareInCheck(piecesOnBoard)){
                                return false;
                                                        }
                                                  }
                         }
                                        }       // end of first check

       return isMoveValid(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
        
       
    }
    */

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece) {
        // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Knights move in an L-shape: two squares in one direction and one square in a perpendicular direction
    if ((rankDifference == 2 && fileDifference == 1) || (rankDifference == 1 && fileDifference == 2)) {
        // The move is valid for a knight
        for(ReturnPiece piece : piecesOnBoard){
            if(piece.pieceRank == newRank && piece.pieceFile.toString().charAt(0) == newFile.toString().charAt(0)){
                
                    if(piece.pieceType.toString().charAt(0) == 'W' && !playerWhite){   
                        if(piece.pieceType.toString().charAt(1) != 'K'){       // check that piece being captured is not a king.
                       // capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        }
                    return true;
                }
                else{
                if(piece.pieceType.toString().charAt(0) == 'B' && playerWhite){   
                    if(piece.pieceType.toString().charAt(1) != 'K'){       // check that piece being captured is not a king.                                                       
                   //  capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                    }
                        return true;
                }
                else{
                    return false;
                }
            }
        }
    }
        return true;
    }

    // The move is not valid for a knight
    return false;
    }

    

}
