package com.tictactoe.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tictactoe.models.Board;
import com.tictactoe.models.Player;

public class MainController {
	public static final int PLAYER1_ID = 1;
	public static final int PLAYER2_ID = 2;
	public static int currentTurn;
	public static Board currentBoard;
	public static Player player1;
	public static Player player2;
	
	public static void beginGame(HttpServletRequest request, HttpServletResponse response) throws IOException {
		currentBoard = new Board();
		player1 = new Player();
		player2 = new Player();
		
		currentTurn = PLAYER1_ID;
		PrintWriter pw = response.getWriter();
		pw.write("<h1>Welcome! Let's begin this game of Tic Tac Toe</h1>");
		playerTurn(request, response, pw);
	}
	
	public static void nextGame(HttpServletRequest request, HttpServletResponse response) throws IOException {
		currentBoard.clear();
		currentTurn = PLAYER1_ID;
		PrintWriter pw = response.getWriter();
		pw.write("<h1>Let's begin the next game of Tic Tac Toe</h1>");
		playerTurn(request, response, pw);
	}
	
	public static void playerTurn(HttpServletRequest request, HttpServletResponse response, PrintWriter pw) throws IOException {
		if (currentBoard == null || player1 == null || player2 == null) {
			beginGame(request, response);
			return;
		}
		pw.write("<svg width=\"300\" height=\"300\">" + 
				"		<rect width=\"300\" height=\"300\" style=\"border-style:solid;fill:rgb(255,255,255);stroke:rgb(0,0,0)\"/>" + 
				"		<line x1=\"100\" y1=\"0\" x2=\"100\" y2=\"300\" style=\"stroke:rgb(0,0,0)\"/>" + 
				"		<line x1=\"200\" y1=\"0\" x2=\"200\" y2=\"300\" style=\"stroke:rgb(0,0,0)\"/>" + 
				"		<line x1=\"0\" y1=\"100\" x2=\"300\" y2=\"100\" style=\"stroke:rgb(0,0,0)\"/>" + 
				"		<line x1=\"0\" y1=\"200\" x2=\"300\" y2=\"200\" style=\"stroke:rgb(0,0,0)\"/>");
		int pos = 1;
		for (int x = 0; x < currentBoard.getBoard().length; x++) {
			for (int y = 0; y < currentBoard.getBoard()[x].length; y++) {
				if (currentBoard.getBoard()[x][y] == 0) {
					int x1 = 45 + 100*x;
					int y1 = 45 + 100*y;
					pw.write("<text x=\"" + x1 + "\" y=\"" + y1 + "\" fill=\"black\">" + pos + "</text>");
				} else if (currentBoard.getBoard()[x][y] == PLAYER1_ID) {
					int cx = 50 + 100*x;
					int cy = 50 + 100*y;
					pw.write("<circle cx=\"" + cx + "\" cy=\"" + cy + "\" r=\"25\" style=\"fill:rgb(255,255,255);stroke:rgb(0,0,0)\"/>");
				} else if (currentBoard.getBoard()[x][y] == PLAYER2_ID) {
					int x1 = 25 + 100*x;
					int x2 = 75 + 100*x;
					int y1 = 25 + 100*y;
					int y2 = 75 + 100*y;
					pw.write("<line x1=\"" + x1 + "\" y1=\"" + y1 + "\" x2=\"" + x2 + "\" y2=\"" + y2 + "\" style=\"stroke:rgb(0,0,0)\"/>");
					pw.write("<line x1=\"" + x2 + "\" y1=\"" + y1 + "\" x2=\"" + x1 + "\" y2=\"" + y2 + "\" style=\"stroke:rgb(0,0,0)\"/>");
				}
				pos++;
			}
		}
		pw.write("<svg/>");
		pw.write("<p>Player 1's score: " + player1.getScore() + "</p>");
		pw.write("<p>Player 2's score: " + player2.getScore() + "</p>");
		if (currentTurn == PLAYER1_ID) {
			pw.write("<h2>Player 1's Turn</h2>");
		} else {
			pw.write("<h2>Player 2's Turn</h2>");
		}
		pw.write("<br><form action = \"/TicTacToe/game\" method=\"post\"><p>Type in the number you want to mark</p>");
		pw.write("<input name=\"square\" type=\"number\">");
		pw.write("</form>");
	}
	
	public static void submitInput(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		int pos = 0;
		try {
			pos = Integer.parseInt(request.getParameter("square"));
		} catch(NumberFormatException e) { 
			pw.write("<h1>Not a number.  Please enter a number between 1 and 9 not yet taken.</h1>");
			playerTurn(request, response, pw);
			return;
	    } catch(NullPointerException e) {
	    	pw.write("<h1>Empty entry.  Please enter a number between 1 and 9 not yet taken.</h1>");
			playerTurn(request, response, pw);
			return;
	    }
		if (pos < 1 || pos > 9) {
			pw.write("<h1>Invalid number.  Please select one between 1 and 9 not yet taken.</h1>");
			playerTurn(request, response, pw);
			return;
		}
		
		switch(pos) {
			case 1:
				if (!currentBoard.playerMove(0, 0, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
			case 2:
				if (!currentBoard.playerMove(0, 1, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
			case 3:
				if (!currentBoard.playerMove(0, 2, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
			case 4:
				if (!currentBoard.playerMove(1, 0, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
			case 5:
				if (!currentBoard.playerMove(1, 1, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
			case 6:
				if (!currentBoard.playerMove(1, 2, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
			case 7:
				if (!currentBoard.playerMove(2, 0, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
			case 8:
				if (!currentBoard.playerMove(2, 1, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
			case 9:
				if (!currentBoard.playerMove(2, 2, currentTurn)) {
					pw.write("<h1>You must choose a square that is not already chosen.</h1>");
					playerTurn(request, response, pw);
					return;
				}
				break;
		}
		
		if ((currentBoard.getBoard()[0][0] == currentTurn
				&& currentBoard.getBoard()[1][0] == currentTurn
				&& currentBoard.getBoard()[2][0] == currentTurn) ||
				(currentBoard.getBoard()[0][0] == currentTurn
				&& currentBoard.getBoard()[1][1] == currentTurn
				&& currentBoard.getBoard()[2][2] == currentTurn) ||
				(currentBoard.getBoard()[0][0] == currentTurn
				&& currentBoard.getBoard()[0][1] == currentTurn
				&& currentBoard.getBoard()[0][2] == currentTurn) ||
				(currentBoard.getBoard()[1][0] == currentTurn
				&& currentBoard.getBoard()[1][1] == currentTurn
				&& currentBoard.getBoard()[1][2] == currentTurn) ||
				(currentBoard.getBoard()[0][1] == currentTurn
				&& currentBoard.getBoard()[1][1] == currentTurn
				&& currentBoard.getBoard()[2][1] == currentTurn) ||
				(currentBoard.getBoard()[2][0] == currentTurn
				&& currentBoard.getBoard()[2][1] == currentTurn
				&& currentBoard.getBoard()[2][2] == currentTurn) ||
				(currentBoard.getBoard()[0][2] == currentTurn
				&& currentBoard.getBoard()[1][2] == currentTurn
				&& currentBoard.getBoard()[2][2] == currentTurn) ||
				(currentBoard.getBoard()[0][2] == currentTurn
				&& currentBoard.getBoard()[1][1] == currentTurn
				&& currentBoard.getBoard()[2][0] == currentTurn)) {
			pw.write("<h1>Player " + currentTurn + " Wins!</h1>");
			if (currentTurn == PLAYER1_ID) {
				player1.setScore(player1.getScore() + 1);
			} else {
				player2.setScore(player2.getScore() + 1);
			}
			pw.write("<form action=\"/TicTacToe/game\" method=\"get\">"
					+ "<button type=\"submit\">Start next game</button></form>");
		} else if (currentBoard.getBoard()[0][0] != 0
				&& currentBoard.getBoard()[0][1] != 0
				&& currentBoard.getBoard()[0][2] != 0
				&& currentBoard.getBoard()[1][0] != 0
				&& currentBoard.getBoard()[1][1] != 0
				&& currentBoard.getBoard()[1][2] != 0
				&& currentBoard.getBoard()[2][0] != 0
				&& currentBoard.getBoard()[2][1] != 0
				&& currentBoard.getBoard()[2][2] != 0) {
			pw.write("<h1>It is a Tie.</h1>");
			pw.write("<form action=\"/TicTacToe/game\" method=\"get\">"
					+ "<button type=\"submit\">Start next game</button></form>");
		} else {
			if (currentTurn == PLAYER1_ID) {
				currentTurn = PLAYER2_ID;
			} else {
				currentTurn = PLAYER1_ID;
			}
			pw.write("<h1>Player " + currentTurn + "'s turn</h1>");
			playerTurn(request, response, pw);
			return;
		}
	}
}
