package chess;

import java.util.*;

public class Knight extends Piece {
    public Knight(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WN;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BN;
    }

    public boolean getisWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite) {
        // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Knights move in an L-shape: two squares in one direction and one square in a perpendicular direction
    if ((rankDifference == 2 && fileDifference == 1) || (rankDifference == 1 && fileDifference == 2)) {
        // The move is valid for a knight
        return true;
    }

    // The move is not valid for a knight
    return false;
    }

    @Override
    public void capture(ReturnPiece.PieceFile movingFile, int movingRank, ReturnPiece.PieceFile takeFile, int takeRank, ArrayList<ReturnPiece> piecesOnBoard) {
        // Remove the captured piece from the list
        for(int i = 0; i < piecesOnBoard.size(); i++){
            if(piecesOnBoard.get(i).pieceFile.toString().charAt(0) == takeFile.toString().charAt(0) && piecesOnBoard.get(i).pieceRank == takeRank){
                 piecesOnBoard.remove(i);
            }
        }
    }
}
