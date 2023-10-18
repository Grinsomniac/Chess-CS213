package chess;

import java.util.*;

public class King extends Piece {
    public King(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    @Override
    public PieceType getWhitePieceType() {
        return PieceType.WK;
    }

    @Override
    public PieceType getBlackPieceType() {
        return PieceType.BK;
    }

    public boolean getisWhite() {
        return isWhite;
    }

    @Override
    public ReturnPiece.PieceFile getPieceFile() {
        return pieceFile;
    }

    @Override
    public int getPieceRank() {
        return pieceRank;
    }

    public Rook whiteRook;
    public Rook blackRook;

    @Override
    public boolean isMoveValid(int newRank, ReturnPiece.PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard,
            boolean playerWhite, char promotionPiece) {
        // Calculate the absolute differences between ranks and files
        int rankDifference = Math.abs(newRank - pieceRank);
        int fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
        boolean obstructed = false;
        boolean emptySquare;

        // Check if the king is trying to castle
        if (rankDifference == 0 && fileDifference == 2) {
            // Check which direction the king is castling (kingside or queenside)
            if (newFile == PieceFile.g && pieceFile == PieceFile.e) {
                // Kingside castling
                // 1. The king and the kingside rook have not moved.
                // 2. There are no pieces between them.
                // 3. The squares they move through are not under attack.
                // If all conditions are met, return true for the castling move

            } else if (newFile == PieceFile.c && pieceFile == PieceFile.e) {
                // Queenside castling
                // Add logic to check the conditions for queenside castling here
                // 1. The king and the queenside rook have not moved.
                // 2. There are no pieces between them.
                // 3. The squares they move through are not under attack.
                // If all conditions are met, return true for the castling move.
            }
        }

        // Standard king move (one square in any direction)
        if (rankDifference <= 1 && fileDifference <= 1) {
            // The move is valid for a king
            return true;
        }

        // The move is not valid for a king
        return false;
    }

    public void kingSideCastle(ReturnPiece.PieceFile newFile, int newRank, ArrayList<ReturnPiece> piecesOnBoard,
            boolean playerWhite) {

        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceFile == ReturnPiece.PieceFile.h && piece.pieceRank == 1
                    && piece.pieceType.toString().charAt(2) == 'R' && playerWhite) {
                whiteRook = (Rook) piece;
            }
            if (piece.pieceFile == ReturnPiece.PieceFile.h && piece.pieceRank == 8
                    && piece.pieceType.toString().charAt(2) == 'R' && !playerWhite) {
                blackRook = (Rook) piece;
            }
        }

        if (playerWhite) {
            for (int i = pieceFile.ordinal(); i < whiteRook.pieceFile.ordinal(); i++) {

                for (ReturnPiece piece : piecesOnBoard) {
                    piece.isTileEmpty(piece.pieceFile, 1, piecesOnBoard);

                }
            }

        }

    }

    public void kingSideCastle(ReturnPiece.PieceFile newFile, int newRank, ArrayList<ReturnPiece> piecesOnBoard,
            boolean playerWhite) {
        Rook whiteRook = null;
        Rook blackRook = null;

        // Find the player's king-side rook
        for (ReturnPiece piece : piecesOnBoard) {
            if (piece.pieceFile == PieceFile.h && piece.pieceType.toString().charAt(2) == 'R'
                    && (piece.pieceType.toString().charAt(0) == 'W' && playerWhite)) {
                whiteRook = (Rook) piece;
                break;
            }
        }

        // Check if the rook is present and hasn't moved
        if (whiteRook != null && whiteRook.pieceMoveCount == 0) {
            int kingRank = playerWhite ? 1 : 8;

            // Check if the tiles between the king and rook are empty
            for (int fileOrdinal = pieceFile.ordinal() + 1; fileOrdinal < newFile.ordinal(); fileOrdinal++) {
                ReturnPiece.PieceFile tileFile = ReturnPiece.PieceFile.values()[fileOrdinal];
                if (!isTileEmpty(tileFile, kingRank, piecesOnBoard)) {
                    return; // If any tile is not empty, castling is not allowed
                }
            }

            // If all tiles are empty, and the king-side rook is available, you can perform
            // castling
            // Implement the castling logic here
            // Move the king and rook to their new positions
            // Update their pieceMoveCount, etc.
        }
    }

    public boolean isSquareInCheck(ArrayList<ReturnPiece> piecesOnBoard, ReturnPiece square) {

        int piecesChecking = 0;
        for (ReturnPiece piece : piecesOnBoard) {

            if (piece.pieceType.toString().charAt(1) == 'P') {
                Pawn castedPawnPiece = (Pawn) piece;

                if (castedPawnPiece.isMoveValid(square.pieceRank, square.pieceFile, piecesOnBoard,
                        castedPawnPiece.isWhite, ' ')) {
                    System.out.println(
                            "check from pawn" + castedPawnPiece.pieceFile.toString() + "" + castedPawnPiece.pieceRank);
                    piecesChecking++;
                }
            }

            if (piece.pieceType.toString().charAt(1) == 'Q') {
                Queen castedQueenPiece = (Queen) piece;

                if (castedQueenPiece.isMoveValid(square.pieceRank, square.pieceFile, piecesOnBoard,
                        castedQueenPiece.isWhite, ' ')) {
                    System.out.println("check from queen" + castedQueenPiece.pieceFile.toString() + ""
                            + castedQueenPiece.pieceRank);
                    piecesChecking++;
                }
            }

            if (piece.pieceType.toString().charAt(1) == 'B') {
                Bishop castedBishopPiece = (Bishop) piece;

                if (castedBishopPiece.isMoveValid(square.pieceRank, square.pieceFile, piecesOnBoard,
                        castedBishopPiece.isWhite, ' ')) {
                    System.out.println("check from bishop" + castedBishopPiece.pieceFile.toString() + ""
                            + castedBishopPiece.pieceRank);
                    piecesChecking++;

                }

            }

            if (piece.pieceType.toString().charAt(1) == 'R') {
                Rook castedRookPiece = (Rook) piece;

                if (castedRookPiece.isMoveValid(square.pieceRank, square.pieceFile, piecesOnBoard,
                        castedRookPiece.isWhite, ' ')) {
                    System.out.println(
                            "check from rook" + castedRookPiece.pieceFile.toString() + "" + castedRookPiece.pieceRank);
                    piecesChecking++;

                }

            }

            if (piece.pieceType.toString().charAt(1) == 'N') {
                Knight castedKnightPiece = (Knight) piece;

                if (castedKnightPiece.isMoveValid(square.pieceRank, square.pieceFile, piecesOnBoard,
                        castedKnightPiece.isWhite, ' ')) {
                    System.out.println("check from knight" + castedKnightPiece.pieceFile.toString() + ""
                            + castedKnightPiece.pieceRank);
                    piecesChecking++;

                }
            }
        } // end of navigate through arraylist

        if (piecesChecking > 0) {
            System.out.println("in check from" + piecesChecking + "pieces"); // test print for how many pieces are
                                                                             // checking the king
            return true; // the king is in check
        } else {

            return false; // the king is not in check
        }

    } // end of isKingInCheck

} // end of class
