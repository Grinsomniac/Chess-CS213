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
    public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard, boolean playerWhite) {
        // Check if the new rank and new file are the same as the current rank and file
        if (newRank == pieceRank && newFile == pieceFile) {
        // The pawn hasn't moved, which is not a valid move
            return false;
        }
        int rankDifference;
        int fileDifference;
        if(getisWhite()){
            rankDifference = (newRank - pieceRank);
            fileDifference = Math.abs(newFile.ordinal() - pieceFile.ordinal());
        }
        else{
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
        return true;
        }  else if (rankDifference == 2 && fileDifference == 0 && ((isWhite && pieceRank == 2) || (!isWhite && pieceRank == 7)) && fileDirection == 0) {
        // Initial move: two squares forward from the second rank (based on whether it's a white or black pawn)
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
                if(piece.pieceType.toString().charAt(0) == 'W' && !playerWhite){    //maybe doesnt work
                                                                                    //implement way to call capture method
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

    }

    public boolean isMoveValidTest(int newRank, PieceFile newFile) {
        System.out.println("In isMoveValidTest");

        return true;
    }

    @Override
    public void capture(ReturnPiece.PieceFile movingFile, int movingRank, ReturnPiece.PieceFile takeFile, int takeRank, ArrayList<ReturnPiece> piecesOnBoard) {

        // Remove the captured piece from the list
        for(int i = 0; i < piecesOnBoard.size(); i++){
            if(piecesOnBoard.get(i).pieceFile.toString().charAt(0) == takeFile.toString().charAt(0) && piecesOnBoard.get(i).pieceRank == takeRank){
                 piecesOnBoard.remove(i);
            }
        }
      
        

        // Update the position of the capturing piece
        //targetPiece.pieceFile = takePiece.pieceFile;
        //targetPiece.pieceRank = takePiece.pieceRank;
    }

        public boolean isMoveValid(int a, ReturnPiece.PieceFile b, ArrayList<ReturnPiece> c){   // delete later
            return true;
        }

/* 
	@Override
	public boolean isMoveValid(int newRank, PieceFile newFile, ArrayList<ReturnPiece> piecesOnBoard) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'isMoveValid'");
	}
*/
}
