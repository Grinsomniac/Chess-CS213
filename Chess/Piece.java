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

    public abstract boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard);

    public abstract boolean isMoveValid(int newRank, PieceFile newFile);

    public abstract boolean isMoveValidTest(int newRank, PieceFile newFile);

    public abstract void executeMove(int newRank, PieceFile newFile);

}

// public abstract boolean isMoveValid(int newRank, PieceFile newFile);

/*
 * // Common method for moving a piece
 * public void movePiece(int newRank, PieceFile newFile, ArrayList<Piece>
 * piecesOnBoard) {
 * if (isMoveValid(newRank, newFile, piecesOnBoard)) {
 * executeMove(newRank, newFile);
 * }
 * 
 * boolean validateMove(String move){
 * 
 * 
 * return true;///Compiler appeasement
 * 
 * }
 */

// pieceFile inherited from ReturnPiece
// pieceRank inherited from ReturnPiece
// public static boolean isWhite;
// this.isWhite = isWhite;