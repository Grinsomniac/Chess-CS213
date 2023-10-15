package chess;

import java.util.*;

///////////////////////     NO CHANGES     /////////////////////////////////
class ReturnPiece {
	static enum PieceType {
		WP, WR, WN, WB, WQ, WK,
		BP, BR, BN, BB, BK, BQ
	};

	static enum PieceFile {
		a, b, c, d, e, f, g, h
	};

	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank; // 1..8

	public String toString() {
		return "" + pieceFile + pieceRank + ":" + pieceType;
	}

	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece) other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

/////////////////////// NO CHANGES /////////////////////////////////
class ReturnPlay {
	enum Message {
		ILLEGAL_MOVE, DRAW,
		RESIGN_BLACK_WINS, RESIGN_WHITE_WINS,
		CHECK, CHECKMATE_BLACK_WINS, CHECKMATE_WHITE_WINS,
		STALEMATE
	};

	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

/////////////////////// ADD TO CHESS CLASS ONLY ///////////////////////
public class Chess {

	enum Player {
		white, black
	} // No Touch

	public static Player currentPlayer;
	public static String whitePrompt = "\nWhite's Move: ";
	public static String blackPrompt = "\nBlack's Move: ";
	public static ArrayList<ReturnPiece> piecesOnBoard = new ArrayList<>();
	public static ReturnPlay returnPlay = new ReturnPlay();
	public static int moveCount;
	public static String[] parts;
	public static String source;
	public static String destination;
	public static ReturnPiece targetPiece = null;

	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for
	 *         details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		System.out.println("Current Player(play1) : " + currentPlayer.toString());// TESTING

		// Testing - Visibility for currentPlayer turn
		if (resignCheck(move)) {
			return returnPlay;
		}
		parseMove(move);

		// Validates correct players piece is moved
		if (!validateMove(targetPiece)) {
			// ReturnPlay Object with Invalid turn message
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			returnPlay.piecesOnBoard = piecesOnBoard;
			return returnPlay;
		}

		movePiece(targetPiece, destination);

		// Afterstate of target piece
		System.out.println(targetPiece.toString());

		// If move is completely Valid
		switchTurn();
		System.out.println("Current Player(play2): " + currentPlayer.toString());// TESTING

		if (parts.length == 2) {
			returnPlay.message = null;
		}
		if (parts.length == 3) {
			returnPlay.message = ReturnPlay.Message.DRAW;
		}
		returnPlay.piecesOnBoard = piecesOnBoard;
		return returnPlay;

	}

	public static Boolean resignCheck(String move) {

		// Checking for Resign message
		if (move.trim().toLowerCase().equals("resign")) {
			if (currentPlayer == Player.black) {
				returnPlay.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
				returnPlay.piecesOnBoard = piecesOnBoard;
				return true;
			} else {
				returnPlay.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
				returnPlay.piecesOnBoard = piecesOnBoard;
				return true;
			}
		}
		return false;
	}

	public static void parseMove(String move) {
		move = move.trim();
		parts = move.split(" ");

		// Testing - For visibility
		for (int i = 0; i < parts.length; i++) {
			System.out.print(parts[i] + ", ");
		}

		source = parts[0];
		destination = parts[1];

		searchPieces(piecesOnBoard, source);
	}

	public static void movePiece(ReturnPiece targetPiece, String destination) {

		char targetFile = destination.charAt(0);
		int targetRank = (destination.charAt(1) - 48);

		// if(targetPiece.PieceType.toString().charAt(1))

		targetPiece.pieceFile = ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile));
		targetPiece.pieceRank = targetRank;
		
		if (targetPiece.pieceType.toString().charAt(1) == 'P') {
			Pawn castedPawnPiece = (Pawn) targetPiece;
			System.out.println("casted piece file:" + castedPawnPiece.toString());
			System.out.println("casted Piece color" + castedPawnPiece.getisWhite());// TESTING
			if (castedPawnPiece.isMoveValid(targetRank, ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile)))) {
				targetPiece.pieceFile = ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile));
				targetPiece.pieceRank = targetRank;
			} else {
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			}
		}
		if (targetPiece.pieceType.toString().charAt(1) == 'N') {
			Knight castedKnightPiece = (Knight) targetPiece;
			System.out.println("casted piece file:" + castedKnightPiece.toString());
			System.out.println("casted Piece color" + castedKnightPiece.getisWhite());// TESTING
			if (castedKnightPiece.isMoveValid(targetRank, ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile)))) {
				targetPiece.pieceFile = ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile));
				targetPiece.pieceRank = targetRank;
			} else {
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			}
		}
		if (targetPiece.pieceType.toString().charAt(1) == 'B') {
			Bishop castedBishopPiece = (Bishop) targetPiece;
			System.out.println("casted piece file:" + castedBishopPiece.toString());
			System.out.println("casted Piece color" + castedBishopPiece.getisWhite());// TESTING
			if (castedBishopPiece.isMoveValid(targetRank, ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile)))) {
				targetPiece.pieceFile = ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile));
				targetPiece.pieceRank = targetRank;
			} else {
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			}
		}
		if (targetPiece.pieceType.toString().charAt(1) == 'R') {
			Rook castedRookPiece = (Rook) targetPiece;
			System.out.println("casted piece file:" + castedRookPiece.toString());
			System.out.println("casted Piece color" + castedRookPiece.getisWhite());// TESTING
			if (castedRookPiece.isMoveValid(targetRank, ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile)))) {
				targetPiece.pieceFile = ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile));
				targetPiece.pieceRank = targetRank;
			} else {
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			}
		}
		if (targetPiece.pieceType.toString().charAt(1) == 'Q') {
			Queen castedQueenPiece = (Queen) targetPiece;
			System.out.println("casted piece file:" + castedQueenPiece.toString());
			System.out.println("casted Piece color" + castedQueenPiece.getisWhite());// TESTING
			if (castedQueenPiece.isMoveValid(targetRank, ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile)))) {
				targetPiece.pieceFile = ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile));
				targetPiece.pieceRank = targetRank;
			} else {
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			}
		}
		if (targetPiece.pieceType.toString().charAt(1) == 'K') {
			King castedKingPiece = (King) targetPiece;
			System.out.println("casted piece file:" + castedKingPiece.toString());
			System.out.println("casted Piece color" + castedKingPiece.getisWhite());// TESTING
			if (castedKingPiece.isMoveValid(targetRank, ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile)))) {
				targetPiece.pieceFile = ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile));
				targetPiece.pieceRank = targetRank;
			} else {
				returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			}
		}

		// Update file and rank for targetPiece

	}

	// Toggle for player turn.
	public static void switchTurn() {
		if (currentPlayer == Player.white) {
			currentPlayer = Player.black;
		} else {
			currentPlayer = Player.white;
		}

	}

	public static Boolean validateMove(ReturnPiece targetPiece) {


				char targetFile = destination.charAt(0);
		int targetRank = (destination.charAt(1) - 48);

		if ((targetPiece.pieceType.toString().charAt(0) == 'W' && currentPlayer == Player.white)){
			return true;
		} else {
			if ((targetPiece.pieceType.toString().charAt(0) == 'B' && currentPlayer == Player.black))
			return true;
		}
		
		return false;	
		
		/*
	    Pawn castedPiece = (Pawn) targetPiece;
		if ((targetPiece.pieceType.toString().charAt(0) == 'W' && currentPlayer == Player.white)
				&& castedPiece.isMoveValid(targetRank, ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile)))) {
			return true;
		} else {
			if ((targetPiece.pieceType.toString().charAt(0) == 'B' && currentPlayer == Player.black)
					&& castedPiece.isMoveValid(targetRank, ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile)))) {
				return true;
			}		
		}
		*/		
		
	}

	// Searches ArrayList<ReturnPiece> for targetPiece
	public static void searchPieces(ArrayList<ReturnPiece> piecesOnBoard, String source) {

		char targetFile = source.charAt(0);
		int targetRank = (source.charAt(1) - 48);

		// Iterate through the ArrayList to find the Piece
		for (ReturnPiece piece : piecesOnBoard) {
			if (piece.pieceFile.name().charAt(0) == targetFile && piece.pieceRank == targetRank) {
				// Found the Piece with the matching File and Rank
				targetPiece = piece;
				break; // Exit the loop once the Piece is found
			}
		}
		// TESTING FOR PIECE FINDING VISIBILITY
		if (targetPiece != null) {
			System.out.println("Found piece: " + targetPiece);
		} else {
			System.out.println("Piece not found at File " + targetFile + " Rank " + targetRank);
		}
	}

	// reset the game, and start from scratch. - DONE
	public static void start() {
		InitializeBoard();
		currentPlayer = Player.white;
		moveCount = 0;
		System.out.print(whitePrompt);
	}

	// Creates Pieces and draws starting board. - DONE
	public static void InitializeBoard() {

		// Add white pieces in their correct starting positions
		piecesOnBoard.add(new Rook(ReturnPiece.PieceFile.a, 1, true));
		piecesOnBoard.add(new Rook(ReturnPiece.PieceFile.h, 1, true));
		piecesOnBoard.add(new Knight(ReturnPiece.PieceFile.b, 1, true));
		piecesOnBoard.add(new Knight(ReturnPiece.PieceFile.g, 1, true));
		piecesOnBoard.add(new Bishop(ReturnPiece.PieceFile.c, 1, true));
		piecesOnBoard.add(new Bishop(ReturnPiece.PieceFile.f, 1, true));
		piecesOnBoard.add(new Queen(ReturnPiece.PieceFile.d, 1, true));
		piecesOnBoard.add(new King(ReturnPiece.PieceFile.e, 1, true));

		for (ReturnPiece.PieceFile file : ReturnPiece.PieceFile.values()) {
			piecesOnBoard.add(new Pawn(file, 2, true));
		}

		// Add black pieces in their correct starting positions
		piecesOnBoard.add(new Rook(ReturnPiece.PieceFile.a, 8, false));
		piecesOnBoard.add(new Rook(ReturnPiece.PieceFile.h, 8, false));
		piecesOnBoard.add(new Knight(ReturnPiece.PieceFile.b, 8, false));
		piecesOnBoard.add(new Knight(ReturnPiece.PieceFile.g, 8, false));
		piecesOnBoard.add(new Bishop(ReturnPiece.PieceFile.c, 8, false));
		piecesOnBoard.add(new Bishop(ReturnPiece.PieceFile.f, 8, false));
		piecesOnBoard.add(new Queen(ReturnPiece.PieceFile.d, 8, false));
		piecesOnBoard.add(new King(ReturnPiece.PieceFile.e, 8, false));

		for (ReturnPiece.PieceFile file : ReturnPiece.PieceFile.values()) {
			piecesOnBoard.add(new Pawn(file, 7, false));
		}

		/*
		 * //Testing - To validate that all pieces were created
		 * for (ReturnPiece piece : piecesOnBoard) {
		 * System.out.println(piece.toString());
		 * }
		 */

		PlayChess.printBoard(piecesOnBoard);
	}
}
