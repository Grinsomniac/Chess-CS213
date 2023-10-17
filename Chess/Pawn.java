package chess;

import java.util.*;

public class Pawn extends Piece {
    public Pawn(PieceFile pieceFile, int pieceRank, boolean isWhite) {
        super(pieceFile, pieceRank, isWhite);
    }

    public static boolean enPassant = false;

    @Override
    public PieceType getWhitePieceType() { return PieceType.WP; }
    @Override
    public PieceType getBlackPieceType() { return PieceType.BP; }
    @Override
    public boolean getisWhite() { return isWhite; }
     @Override
    public ReturnPiece.PieceFile getPieceFile() { return pieceFile; }
    @Override
    public int getPieceRank() { return pieceRank; }
    

    @Override
    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece) {
        // Check if the new rank and new file are the same as the current rank and file
        if (newRank == pieceRank && newFile == pieceFile) {
            // The pawn hasn't moved, which is not a valid move
            return false;
        }
        int rankDifference;
        int fileDifference;
        if (getisWhite()) {
            rankDifference = (newRank - pieceRank);
            fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
        } else {
            rankDifference = (pieceRank - newRank);
            fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
        }

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
            // This is where we should check for promotion when reaching the last rank
            pawnPromo(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
            enPassant = false;
            pieceMoveCount++;
            return true;
        } else if (rankDifference == 2 && fileDifference == 0 && ((isWhite && pieceRank == 2) || (!isWhite && pieceRank == 7)) && fileDirection == 0) {
            // Initial move: two squares forward from the second rank (based on whether it's a white or black pawn)
            int intermediateRank = pieceRank + rankDirection;
            for (ReturnPiece piece : piecesOnBoard) {
                if ((piece.pieceRank == intermediateRank && piece.pieceFile == pieceFile) || (piece.pieceRank == newRank && piece.pieceFile == newFile)) {
                    // The move is obstructed by another piece
                    return false;
                }
            } 
            pieceMoveCount++;
            if (pieceMoveCount == 1){
                enPassant = true;
            }
            return true;
        } else if (rankDifference == 1 && fileDifference == 1 && pieceFile.ordinal() + fileDirection == newFile.ordinal()) {
            // Check for en passant capture
            if ((enPassant && (playerWhite && pieceRank == 5)) || (enPassant && (!playerWhite && pieceRank == 4))) {
                System.out.println(enPassant);
                System.out.println("passed check for enpassant");
                // Determine the direction of movement for en passant capture
                int enPassantRank = (playerWhite) ? (newRank - 1) : (newRank + 1);
                int enPassantFile = newFile.ordinal();
                // Check if there's an opponent's pawn at the en passant square
                for (ReturnPiece piece : piecesOnBoard) {
                    if (piece.pieceRank == enPassantRank && piece.pieceFile == PieceFile.values()[enPassantFile]) {
                        // Capture the opponent's pawn en passant
                        enPassantCapture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard, playerWhite);
                        pieceMoveCount++;
                        return true;
                    }
                }
            }
            // Capturing a piece diagonally
            for (ReturnPiece piece : piecesOnBoard) {
                if (piece.pieceRank == newRank && piece.pieceFile == newFile) {
                    // The move is valid for capturing an opponent's piece
                    if (piece.pieceType.toString().charAt(0) == 'W' && !playerWhite) {
                        if(piece.pieceType.toString().charAt(1) != 'K'){       // check that piece being captured is not a king.
                       // capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        }
                        // This is where we should check for promotion when reaching the last rank
                        pawnPromo(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
                        
                        pieceMoveCount++;
                        
                        return true;
                    } else if (piece.pieceType.toString().charAt(0) == 'B' && playerWhite) {
                        if(piece.pieceType.toString().charAt(1) != 'K'){       // check that piece being captured is not a king.
                       // capture(pieceFile, pieceRank, newFile, newRank, piecesOnBoard);
                        }
                         //This is where we should check for promotion when reaching the last rank
                        pawnPromo(newRank, newFile, piecesOnBoard, playerWhite, promotionPiece);
                        pieceMoveCount++;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        // The move is not valid for a pawn
        return false;
    }

    public void enPassantCapture(ReturnPiece.PieceFile movingFile, int movingRank, ReturnPiece.PieceFile takeFile, int takeRank, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite){
        /// Remove the captured piece from the list
        for(int i = 0; i < piecesOnBoard.size(); i++){
            if(playerWhite){
            if(piecesOnBoard.get(i).pieceFile.toString().charAt(0) == takeFile.toString().charAt(0) && piecesOnBoard.get(i).pieceRank == takeRank - 1){
                 piecesOnBoard.remove(i);
            }
            }
            if(!playerWhite){
            if(piecesOnBoard.get(i).pieceFile.toString().charAt(0) == takeFile.toString().charAt(0) && piecesOnBoard.get(i).pieceRank == takeRank + 1){
                 piecesOnBoard.remove(i);
            }
            }
        }
    }

    
    public void pawnPromo(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite, char promotionPiece ){
        // This is where we should check for promotion when reaching the last rank
        if ((newRank == 1 && !playerWhite) || (newRank == 8 && playerWhite)) {
               
        // Remove the promoted pawn (original piece)
         piecesOnBoard.remove(this);
         Piece promotedPiece;
              
            switch (promotionPiece) {
                case 'b':
                    promotedPiece = new Bishop(newFile, newRank, playerWhite);
                    break;
                case 'n':
                    promotedPiece = new Knight(newFile, newRank, playerWhite);
                    break;
                case 'r':
                    promotedPiece = new Rook(newFile, newRank, playerWhite);
                    break;
                case 'q':
                    promotedPiece = new Queen(newFile, newRank, playerWhite);
                    break;
                case ' ':
                    promotedPiece = new Queen(newFile, newRank, playerWhite);
                    break;
                default:
                   promotedPiece = new Queen(newFile, newRank, playerWhite);
                   break;
            }
            piecesOnBoard.add(promotedPiece);
        }
    }
}
