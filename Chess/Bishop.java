package chess;

import java.util.*;
public class Bishop extends Piece {
    public Bishop(PieceFile pieceFile, int pieceRank, boolean isWhite){
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WB;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BB;
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
