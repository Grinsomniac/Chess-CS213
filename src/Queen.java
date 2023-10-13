package chess;

import java.util.*;

public class Queen extends Piece {

    private PieceFile queenPieceFile;
    private int queenPieceRank;


    public Queen(PieceFile pieceFile, int pieceRank, boolean isWhite){
        super(pieceFile, pieceRank, isWhite);
        
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WQ;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BQ;
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
