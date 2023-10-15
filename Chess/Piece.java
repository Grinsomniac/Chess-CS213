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

    }

    public abstract PieceType getWhitePieceType();

    public abstract PieceType getBlackPieceType();

    public abstract boolean getisWhite();

    public abstract boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite);

    public abstract void capture(ReturnPiece.PieceFile movingFile, int movingRank, ReturnPiece.PieceFile takeFile, int takeRank, ArrayList<ReturnPiece> piecesOnBoard);

   

}
