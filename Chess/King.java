package chess;

import java.util.*;
public class King extends Piece {
    public King(PieceFile pieceFile, int pieceRank, boolean isWhite){
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

    /* 
    public PieceColor getColor() {
        return (pieceType == PieceType.WR || pieceType == PieceType.WP) ? PieceColor.white : PieceColor.black;
    }
    */

    public boolean getisWhite(){
        return isWhite;
    }
    

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard) {
        // TODO
        return true;
    }

    @Override
    public void executeMove(int newRank, ReturnPiece.PieceFile newFile) {
        // TODO
    }

   
}
