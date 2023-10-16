package chess;

import java.util.*;

public class Rook extends Piece {
    public Rook(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() { return PieceType.WR; }
    @Override
    public PieceType getBlackPieceType() { return PieceType.BR; }
    public boolean getisWhite() { return isWhite;}
    @Override
    public ReturnPiece.PieceFile getPieceFile() { return pieceFile; }
    @Override
    public int getPieceRank() { return pieceRank; }

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece) {
       // Check if the new rank and new file are the same as the current rank and file
    if (newRank == pieceRank && newFile == pieceFile) {
        // The rook hasn't moved, which is not a valid move
        return false;
    }

    // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Rooks can only move vertically or horizontally
    if (rankDifference != 0 && fileDifference != 0) {
        // The move is not vertical or horizontal, which is not valid for a rook
        return false;
    }

    // Check if the path is unobstructed
    if (rankDifference == 0) {
        // Horizontal move
        int fileDirection = Integer.compare(newFile.ordinal(), pieceFile.ordinal());
        for (PieceFile file = PieceFile.values()[pieceFile.ordinal() + fileDirection]; file != newFile; file = PieceFile.values()[file.ordinal() + fileDirection]) {
            for (ReturnPiece piece : piecesOnBoard) {
                if (piece.pieceRank == pieceRank && piece.pieceFile == file) {
                    // The move is obstructed by another piece
                    return false;
                }
            }
        }
    } else {
        // Vertical move
        int rankDirection = Integer.compare(newRank, pieceRank);
        for (int rank = pieceRank + rankDirection; rank != newRank; rank += rankDirection) {
            for (ReturnPiece piece : piecesOnBoard) {
                if (piece.pieceRank == rank && piece.pieceFile == pieceFile) {
                    // The move is obstructed by another piece
                       
                    return false;
                       
                }
            }
        }
    }
        for(ReturnPiece piece : piecesOnBoard){
            if(piece.pieceRank == newRank && piece.pieceFile.toString().charAt(0) == newFile.toString().charAt(0)){
                
                    if(piece.pieceType.toString().charAt(0) == 'W' && !playerWhite){   
                     capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                    return true;
                }
                else{
                if(piece.pieceType.toString().charAt(0) == 'B' && playerWhite){   
                                                                                   
                     capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        return true;
                }
                else{
                    return false;
                }
            }
        }
    }

    // The move is valid for a rook and unobstructed
    return true;
    }
}

