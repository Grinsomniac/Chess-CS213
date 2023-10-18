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

    public void capture(ReturnPiece.PieceFile movingFile, int movingRank, ReturnPiece.PieceFile takeFile, int takeRank, ArrayList<ReturnPiece> piecesOnBoard){
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

}

