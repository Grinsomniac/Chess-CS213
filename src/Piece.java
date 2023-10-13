package chess;

import java.util.*;

public abstract class Piece extends ReturnPiece {

    // pieceFile inherited from ReturnPiece
    // pieceRank inherited from ReturnPiece

    // Constructor
    public Piece(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        this.pieceType = isWhite ? getWhitePieceType() : getBlackPieceType();
        this.pieceFile = pieceFile;
        this.pieceRank = pieceRank;
    }

    public abstract PieceType getWhitePieceType();

    public abstract PieceType getBlackPieceType();

    // public abstract boolean isMoveValid(int newRank, PieceFile newFile,
    // ArrayList<ReturnPiece> piecesOnBoard);

    public abstract boolean isMoveValid(int newRank, PieceFile newFile);

    public abstract void executeMove(int newRank, PieceFile newFile);

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

}
