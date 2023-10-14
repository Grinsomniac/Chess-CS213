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

///////////////////////     NO CHANGES     /////////////////////////////////
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

///////////////////////   ADD TO CHESS CLASS ONLY    ///////////////////////
public class Chess {

	enum Player { white, black} // No Touch
    
	public static Player currentPlayer; 
	public static String whitePrompt = "\nWhite's Move: ";
	public static String blackPrompt = "\nBlack's Move: ";
	public static ArrayList<ReturnPiece> piecesOnBoard = new ArrayList<>();
	public static ReturnPlay returnPlay = new ReturnPlay();
	public static int moveCount;
	public static String source;
	public static String destination;

	public static ReturnPiece targetPiece = null;


	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for
	 *         details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		System.out.println("Current Player(play1) : " + currentPlayer.toString());//TESTING

		//Testing - Visibility for currentPlayer turn
		
		if (resignCheck(move)){
			return returnPlay;
		} // 1
		parseMove(move);

		//NOT WORKING YET - validatePlayerPiece is set to return "true" for now
		//Check to see is player(white/black) is attempting to move their own piece.
		if (!validatePlayerPiece(targetPiece)){
			//ReturnPlay Object with Invalid turn message
			returnPlay.message = ReturnPlay.Message.ILLEGAL_MOVE;
			returnPlay.piecesOnBoard = piecesOnBoard;
			return returnPlay;	
		}

		movePiece(targetPiece, destination);
	
		//Confirm before and after of targetPiece to ensure move was captured
		// system.out.println(" sourcePieceFile -> " + source.charAt(0) + " :: " + )
		//System.out.println(" targPieceFile -> " + targPieceFile + " :: " + targPieceRank);


		//Afterstate of target piece
		System.out.println(targetPiece.toString());
        
	  //return null; // 

	  //If move is completely Valid
		switchTurn();
		System.out.println("Current Player(play2): " + currentPlayer.toString());//TESTING

		/* 
		if (currentPlayer == Player.white){
			System.out.print(whitePrompt + " ");
		}
		else {
			System.out.print(blackPrompt + " ");
		}
		*/
		returnPlay.message = null;
		returnPlay.piecesOnBoard = piecesOnBoard;
        return returnPlay; 

	}
	/*
	 * Boolean resignCheck(String move)
	 */
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
		String[] parts = move.split(" ");

		// Testing - For visibility
		for (int i = 0; i < parts.length; i++) {
			System.out.print(parts[i] + ", ");
		}

		source = parts[0];
		destination = parts[1];

		searchPieces(piecesOnBoard, source);

		//	if (parts.length == 3) { draw(); }  // Need to finish 
	}

	public static void draw() {}  // Need to Finish

	public static void movePiece(ReturnPiece targetPiece, String destination) {

		char targetFile = destination.charAt(0);
		int targetRank = (destination.charAt(1)-48);

		// Update the file and rank of the targetPiece
		targetPiece.pieceFile = ReturnPiece.PieceFile.valueOf(String.valueOf(targetFile));
		targetPiece.pieceRank = targetRank;
	}

	//Toggle for player turn. Switched between black and white enum values for currentPlayer
	public static void switchTurn(){
		if (currentPlayer == Player.white){
			currentPlayer = Player.black;
		}
		else{
		currentPlayer = Player.white;
		}

	}

	public static Boolean validatePlayerPiece(ReturnPiece targetPiece){///////////////////// WORK IN PROGRESS ///////////////////////

		if(targetPiece.pieceType.toString().charAt(0) == 'W' && currentPlayer == Player.white){
			return true;
		}
		else{
			if(targetPiece.pieceType.toString().charAt(0) == 'B' && currentPlayer == Player.black){
				return true;
			}
		}
		return false;


		 //String targetPieceString = targetPiece.getColor();

		 /* 
		 if (currentPlayer.equals(targetPiece.getColor())) {
			return true;
		 }
		 return false;	 
		 */	


		/* 
		 //Testing 
		 System.out.println("Printing target piece toString(): "  );
		 for (int i = 0; i < targetPieceString.length(); i++){
			System.out.print(targetPieceString.charAt(i) + ", ");
		 }

		 //if (targetPieceString.charAt(3)){}
		 */
	}

	//Testing iteration through ArrayList<ReturnPiece> piecesOnBoard to find targetPiece
	public static void searchPieces(ArrayList<ReturnPiece> piecesOnBoard, String source /*String destination*/){

		char targetFile = source.charAt(0);
		int targetRank = (source.charAt(1)-48);

		//ReturnPiece targetPiece = null; -> Moved to static public in Chess class

		// Iterate through the ArrayList to find the Piece
		for (ReturnPiece piece : piecesOnBoard) {
    		if (piece.pieceFile.name().charAt(0) == targetFile && piece.pieceRank == targetRank) {
        	// Found the Piece with the matching File and Rank
        		targetPiece = piece;
        	break;  // Exit the loop once the Piece is found
    		}
		}
		//TESTING FOR PIECE FINDING VISIBILITY 
		if (targetPiece != null) {
    		// Handle the case when the Piece is found
    		System.out.println("Found piece: " + targetPiece);
		} else {
    		// Handle the case when the Piece is not found
   			System.out.println("Piece not found at File " + targetFile + " Rank " + targetRank);
		}
	}
	
	//Send move to Piece - Possibly redundant
//	public static void sendMoveToPiece(ReturnPiece.PieceFile pieceFile, int pieceRank) {}

	/**
	 * This method should reset the game, and start from scratch. - DONE
	 */
	public static void start() {

	//	printBoardTest(PlayChess.makeBlankBoard()); //TESTING
		InitializeBoard(); 
		currentPlayer = Player.white;
		moveCount = 0; // Eliminate if redundant with currentPlayer. (Once currentPlayer works)

		System.out.println("Current Player(start): " + currentPlayer.toString());//TESTING

		System.out.print(whitePrompt); // -> Copied to (if then) at top of Chess.play for Resign check


	}

//	Testing Board Print - DONE
	public static void printBoardTest(String[][] board) {

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				System.out.print(board[r][c]);
			}
			System.out.println(); // Move to the next row
		}

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
		 //Testing - To validate that all pieces were created
		 for (ReturnPiece piece : piecesOnBoard) {
		 	System.out.println(piece.toString());
		 }
		 */
		 
		PlayChess.printBoard(piecesOnBoard);
	}
}



//OLD CODE - Incase needed for Reference 

/* 
	public static ReturnPiece.PieceFile[] enums = { ReturnPiece.PieceFile.a, ReturnPiece.PieceFile.b,
			ReturnPiece.PieceFile.c,
			ReturnPiece.PieceFile.d, ReturnPiece.PieceFile.e, ReturnPiece.PieceFile.f, ReturnPiece.PieceFile.g,
			ReturnPiece.PieceFile.h };

*/


	// public static String[] parts;//Temp
	//public static char targPieceFile;// Temp
	//public static int targPieceRank;// Temp
	//public static char destPieceFile;// Temp
	//public static int destPieceRank;// Temp

//Old Code to update Piece File and Rank - STILL HERE FOR REFERENCE 
		/*
		 * if (parts.length == 2) {
		 * for (int i = 0; i < piecesOnBoard.size(); i++) {
		 * 
		 * if (piecesOnBoard.get(i).pieceFile.toString().charAt(0) == targPieceFile
		 * && piecesOnBoard.get(i).pieceRank == targPieceRank) {
		 * 
		 * for (int j = 0; j < 8; j++) {//LOOK HERE
		 * if (enums[j].toString().charAt(0) == destPieceFile) {
		 * piecesOnBoard.get(i).pieceFile = enums[j];
		 * }
		 * 
		 * }
		 * piecesOnBoard.get(i).pieceRank = destPieceRank;
		 * returnPlay.message = null;
		 * returnPlay.piecesOnBoard = piecesOnBoard;
		 * 
		 * return returnPlay;
		 * }
		 * }
		 * }
		 * 
		 * return returnPlay;
		 */
		
		 /* 
		char targPieceFile = source.charAt(0);
		int targPieceRank = ((int) source.charAt(1) - 48);// Solve the 48 issue
		char destPieceFile = destination.charAt(0);
		int destPieceRank = ((int) destination.charAt(1) - 48);//// Solve the 48 issue
		ReturnPiece piece;
		ReturnPiece.PieceType pieceType;
		
		
		for (int i = 0; i < piecesOnBoard.size(); i++) {
			// System.out.println(piecesOnBoard.get(i).toString());
			if (piecesOnBoard.get(i).pieceFile.toString().charAt(0) == targPieceFile
					&& piecesOnBoard.get(i).pieceRank == targPieceRank) {
				piece = piecesOnBoard.get(i); // gets the piece object to be moved
				pieceType = piecesOnBoard.get(i).pieceType; // gets the type of the piece to be moved

				System.out.println(pieceType.toString());
			}
		}
		*/

/* 
		 // Resign
		 if (move.toLowerCase().equals("resign")) {
		 	if (moveCount % 2 == 0) {
		 		returnPlay.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
				returnPlay.piecesOnBoard = piecesOnBoard;
		 	return returnPlay;
		 	} else {
		 	returnPlay.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
		 	returnPlay.piecesOnBoard = piecesOnBoard;
		 	return returnPlay;
			}
		}
		*/
		 

		/*
		 * move = move.trim();
		 * String[] parts = move.split(" ");
		 * for (int i = 0; i < parts.length; i++) {
		 * System.out.println(parts[i]);
		 * }
		 * 
		 * String source = parts[0];
		 * String destination = parts[1];
		 * char targPieceFile = source.charAt(0);
		 * int targPieceRank = ((int) source.charAt(1) - 48);
		 * char destPieceFile = destination.charAt(0);
		 * int destPieceRank = ((int) destination.charAt(1) - 48);
		 */

		// In case of draw
			/* 
		if (parts.length == 3) {/// Copied to method draw...

			if (parts[2].toLowerCase().equals("draw")) {
				for (int i = 0; i < piecesOnBoard.size(); i++) {
					// System.out.println(piecesOnBoard.get(i).toString());
					if (piecesOnBoard.get(i).pieceFile.toString().charAt(0) == targPieceFile
							&& piecesOnBoard.get(i).pieceRank == targPieceRank) {
						////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						for (int j = 0; j < 8; j++) {
							if (enums[j].toString().charAt(0) == destPieceFile) {
								piecesOnBoard.get(i).pieceFile = enums[j];
							}

						}

						// Adjusting pieceRank and
						piecesOnBoard.get(i).pieceRank = destPieceRank;

						returnPlay.message = ReturnPlay.Message.DRAW;
						returnPlay.piecesOnBoard = piecesOnBoard;
						// System.out.println(piecesOnBoard.get(i).toString());
						return returnPlay;
					}
				}

			}
		}
*/