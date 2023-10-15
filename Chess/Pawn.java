package chess;

import java.util.*;

public class Pawn extends Piece {
    public Pawn(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WP;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BP;
    }

    public boolean getisWhite() {
        return isWhite;
    }

    @Override
    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard) {
        // Check if the new rank and new file are the same as the current rank and file
    if (newRank == pieceRank && newFile == pieceFile) {
        // The pawn hasn't moved, which is not a valid move
        return false;
    }

    // Calculate the absolute differences between ranks and files
    int rankDifference = Math.abs(newRank - pieceRank);
    int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());

    // Determine the direction of movement
    int rankDirection = Integer.compare(newRank, pieceRank);
    int fileDirection = Integer.compare(newFile.ordinal(), pieceFile.ordinal());

    // Pawns move differently on their initial and subsequent moves
    if (rankDifference == 1 && fileDifference == 0) {
        // Subsequent move: one square forward
        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceRank == newRank && piece.pieceFile == newFile) {
                // The move is obstructed by another piece
                return false;
            }
        }
        return true;
    } else if (rankDifference == 2 && fileDifference == 0 && pieceRank == 2 && fileDirection == 0) {
        // Initial move: two squares forward from the second rank
        int intermediateRank = pieceRank + rankDirection;
        for (ReturnPiece piece : piecesOnBoard) {
            if ((piece.pieceRank == intermediateRank && piece.pieceFile == pieceFile) || (piece.pieceRank == newRank && piece.pieceFile == newFile)) {
                // The move is obstructed by another piece
                return false;
            }
        }
        return true;
    } else if (rankDifference == 1 && fileDifference == 1 && pieceFile.ordinal() + fileDirection == newFile.ordinal()) {
        // Capturing a piece diagonally
        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceRank == newRank && piece.pieceFile == newFile) {
                // The move is valid for capturing an opponent's piece
                return true;
            }
        }
    }
    
    // The move is not valid for a pawn
    return false;
    }

    @Override
    public void executeMove(int newRank, PieceFile newFile) {
        if (isMoveValid(newRank, newFile)) {
            this.pieceFile = newFile;
            this.pieceRank = newRank;
        }
        ReturnPlay returnPlay = new ReturnPlay();
        returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;

    }

    public boolean isMoveValid(int newRank, PieceFile newFile) {
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

        /*
         * // Check if the move is forward one square or two squares on the first move
         * if ((pieceType == PieceType.WP && rankDifference == 1 && fileDifference == 0)
         * ||
         * (pieceType == PieceType.BP && rankDifference == 1 && fileDifference == 0) ||
         * (pieceType == PieceType.WP && rankDifference == 2 && fileDifference == 0 &&
         * pieceRank == 2) ||
         * (pieceType == PieceType.BP && rankDifference == 2 && fileDifference == 0 &&
         * pieceRank == 7)) {
         * 
         * // Update this piece's rank and file
         * return true;
         * }
         */
        /*
         * // Check for diagonal capture
         * if ((pieceType == PieceType.WP && rankDifference == 1 && fileDifference == 1)
         * ||
         * (pieceType == PieceType.BP && rankDifference == 1 && fileDifference == 1)) {
         * 
         * // update rank and file
         * return true;
         * }
         */

        // Add en passant and promotion logic as needed

    }

    public boolean isMoveValidTest(int newRank, PieceFile newFile) {
        System.out.println("In isMoveValidTest");

        return true;
    }

}
