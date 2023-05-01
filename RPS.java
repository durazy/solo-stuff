import java.util.Scanner;

/**
 * Version 1.12
 * This program is just me messing around with conditionals 
 * and input checking to create a Rock-paper-scissors game
 * that kicks you out for not following directions.
 * @author durazy
 *
 */
public class RPS {

	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;
	public static final String[] OPTIONS = {"ROCK", "PAPER", "SCISSORS"};

	public static void main(String[] args) {
		boolean play = true;
		int runBack = 1;
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to RPS! (Rock. Paper. Scissors.)");
		while (play) {
			runBack = playGame(in);
			if (runBack == 1) {
				play = getPlayAgain(in);
			} else {
				play = false;
			}
		}
	}

	/*
	 * runs all the main functions of the game
	 * Returns 1 to indicate the game can be run again
	 * Returns 2 to indicate the user has played too much
	 */
	public static int playGame(Scanner in) {
		System.out.println("How many rounds would you like to play?");
		System.out.print("> ");
		int rounds = getRounds(in);
		int result = 0;
		boolean hasPlayed = false;
		if (rounds != -69 && rounds > 0) {
			hasPlayed = true;
			result = runRounds(rounds, in);
		}
		if (hasPlayed) {
			computeScore(result);
			return 1;
		} else {
			System.out.println("\nGAME OVER.");
			return 2;
		}
	}

	/*
	 * Ask player if they want to continue playing, if they wanna
	 * play with the bot, they get punished
	 * TODO: make difficulty harder by choice
	 */
	public static boolean getPlayAgain(Scanner in) {
		int answer = 0;
		int messups = 0;
		while (answer != 1 && answer != 2) {
			System.out.println("Wanna run it back?"); 
			System.out.println("1 = YES | 2 = NO");
			try {
				answer = Integer.parseInt(in.nextLine());
				if (answer != 1 && answer != 2) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				messups++;
				if (messups < 3) {
					System.out.println("Enter a valid response\n");
				} else if (messups < 4) {
					System.out.println("Pls don't do this bro\n");
				} else {
					System.out.println("You're done.\n");
					return false;
				}
			}
		}
		if (answer == 2) {
			System.out.println("\nAight, cya.");
			return false;
		}
		return true;
	}

	/*
	 * Runs the rounds of RPS
	 * Gets the player choice, generates CPU choice, then processes the choices
	 */
	public static int runRounds(int rounds, Scanner in) {
		int score = 0;
		for (int i = 1; i <= rounds; i++) {
			System.out.println("         -- ROUND " + i + " --");
			System.out.println("           Score: " + score);
			int choice = getChoice(in); 
			int cpuChoice = (int) (Math.random() * 3) + 1;
			System.out.println("\nThe player has chosen -" + OPTIONS[choice-1] + "- and the "
					+ "computer has chosen -" + OPTIONS[cpuChoice-1] + "-");
			score += processChoice(choice, cpuChoice);
		}
		return score;
	}

	/*
	 * Gets how many rounds to run the game based on the player's input
	 */
	public static int getRounds(Scanner in) {
		int rds = -1;
		int messUps = 0;
		while (rds < 0) {
			try {
				//throw exception if input is not int or a negative number
				rds = Integer.parseInt(in.nextLine());
				if (rds < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				if (messUps == 3) {
					System.out.println("Ight, keep playin' games. Now nobody gets to play.");
					return -69;
				}
				if (messUps < 2) {
					System.out.println("Please enter a valid number.");
				} else {
					System.out.println("Please. Enter. A valid. Number.");
				}
				System.out.print("> ");
				messUps++;
			}
		}
		return rds;
	}

	/*
	 * Asks the player to choose rock, paper, or scissors
	 */
	public static int getChoice(Scanner in) {
		int choice = 0;
		while (choice < 1 || choice > 3) {
			System.out.println("      What will you choose?");
			System.out.println("1 = ROCK | 2 = PAPER | 3 = SCISSORS");
			try {
				choice = Integer.parseInt(in.nextLine());
			} catch (NumberFormatException e) {
				choice = 0;
			}
		}
		return choice;
	}

	/*
	 * Return 1 if player wins, 0 if draw, -1 if computer wins
	 * Checks win conditions or if draw, else computer won
	 */
	public static int processChoice(int playerChoice, int cpuChoice) {
		boolean playerWin = (playerChoice == 1 && cpuChoice == 3) || 
				(playerChoice == 2 && cpuChoice == 1) || 
				(playerChoice == 3 && cpuChoice == 2);
		if (playerChoice == cpuChoice) {
			System.out.println("Draw.\n");
			return 0;
		} else if (playerWin) {
			System.out.println("The player won this round.\n");
			return 1;
		}
		System.out.println("The computer won this round.\n");
		return -1;
	}

	/*
	 * Computes the final score and prints the results
	 */
	public static void computeScore(int score) {
		System.out.println("Score: " + score);
		System.out.println();
		if (score == 0) {
			System.out.println("Damn, nobody won huh.\n");
		} else if (score > 0) {
			System.out.println("You beat the computer, not bad.\n");
		} else {
			System.out.println("Bro really lost to an AI T^T\n");
		}
	}



}
