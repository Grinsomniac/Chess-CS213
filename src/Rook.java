package chess;

import java.util.*;

public class Rook extends Piece {
    public Rook(PieceFile pieceFile, int pieceRank, boolean isWhite){
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WR;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BR;
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

    void rookMovement(){

    }
}
