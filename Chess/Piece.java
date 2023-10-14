package chess;

import java.util.*;

public abstract class Piece extends ReturnPiece {

    public static enum PieceColor { white, black}; //Trying enum to capture color for getter/setter
    //PieceColor pieceColor;
    public static boolean isWhite;
    public static int pieceMoveCount; // FOR PAWN MOVEMENT, CASTLING (ROOK & KING)

    // Constructor
    public Piece(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        this.pieceType = isWhite ? getWhitePieceType() : getBlackPieceType();
        /* 
        if (isWhite){
            pieceColor = PieceColor.white;
        } else {
            pieceColor = PieceColor.black;
        }
        */
        this.isWhite = isWhite;
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
    } 

    public abstract PieceType getWhitePieceType();
    public abstract PieceType getBlackPieceType();
    //public abstract PieceColor getColor();
    public abstract boolean getisWhite();

    public abstract boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard);

    public abstract void executeMove(int newRank, PieceFile newFile);

}


    //public abstract boolean isMoveValid(int newRank, PieceFile newFile);
 
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
    //public static boolean isWhite;
    //this.isWhite = isWhite;