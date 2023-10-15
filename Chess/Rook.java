package chess;

import java.util.*;

public class Rook extends Piece {
    public Rook(PieceFile pieceFile, int pieceRank, boolean isWhite) {
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

    /*
     * public PieceColor getColor() {
     * return (pieceType == PieceType.WR || pieceType == PieceType.WP) ?
     * PieceColor.white : PieceColor.black;
     * }
     */

    public boolean getisWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard) {
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

    // The move is valid for a rook and unobstructed
    return true;
    }

    public boolean isMoveValid(int newRank, PieceFile newFile) {
        // Check if the new rank or file is the same as the current rank or file
    if (newRank == pieceRank && newFile == pieceFile) {
        // The rook hasn't moved, which is not a valid move
        return false;
    }

    // Check if the new rank or file is different from the current rank or file
    if (newRank != pieceRank && newFile != pieceFile) {
        // Rooks can only move vertically or horizontally, not diagonally
        return false;
    }

    // The move is valid if it's either vertical or horizontal
    return true;  
    }

    @Override
    public void executeMove(int newRank, ReturnPiece.PieceFile newFile) {
        // TODO
    }

    void rookMovement() {

    }

    public boolean isMoveValidTest(int newRank, PieceFile newFile) {
        System.out.println("In isMoveValidTest");

        return true;
    }
}

/* 
 if (isWhite) {
            int rankDifference = (newRank - pieceRank);
            int fileDifference = (newFile.ordinal() - pieceFile.ordinal());
            if ((rankDifference == 1 && fileDifference == 0)
                    || (rankDifference == 2 && fileDifference == 0 && pieceRank == 2)) {
                return true;
            }
        } else {
            int rankDifference = (pieceRank - newRank);
            int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
            if ((rankDifference == 1 && fileDifference == 0)
                    || (rankDifference == 2 && fileDifference == 0 && pieceRank == 7)) {
                return true;
            }
        }
        return false;
*/