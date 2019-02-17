import java.util.Scanner;

public class Game21 {
    public static void main(String[] args) {
        boolean again = true;
        while (again) {
            //cards and score
            final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
            final String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
            final int[] cardScore = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
            final int[] suitScore = {1, 1, 1, 1};
            final int deck = 3;
            final int NUM_OF_CARD = suits.length * ranks.length * deck;
            final String[] cards = new String[NUM_OF_CARD];
            final String[] score = new String[NUM_OF_CARD];
            final int MAX_ACE = 4 * deck;
            int cardNum = 0;

            //random
            int position;
            for (int i = 0; i < deck; i++) {
                for (int j = 0; j < ranks.length; ++j) {
                    for (int k = 0; k < suits.length; ++k) {
                        position = (j * suits.length) + k + (i * (suits.length * ranks.length));
                        score[position] = "" + (cardScore[j] * suitScore[k]);
                        cards[position] = ranks[j] + " (" + suits[k] + ")";
                    }
                }
            }
            for (int i = 0; i < cards.length; ++i) {
                final int rand = 1 + (int) (Math.random() * (cards.length - 1));
                final String r = cards[rand];
                cards[rand] = cards[i];
                cards[i] = r;
                final String s = score[rand];
                score[rand] = score[i];
                score[i] = s;
            }

            //ask number of players
            boolean ansGot = false;
            int playerNum = 0;
            while (!ansGot) {
                try {
                    while (!ansGot) {
                        System.out.println("\nHow many players do you want? (>2)");
                        Scanner s = new Scanner(System.in);
                        playerNum = s.nextInt();
                        if (playerNum > 1)
                            ansGot = true;
                        else
                            System.err.println("ERROR: Please enter more than 2 players!");
                    }
                } catch (final Exception e) {
                    System.err.println("ERROR: Please enter a number!");
                    ansGot = false;
                }
            }

            int[] finalScore = new int[playerNum];
            int[] aceNum = new int[playerNum];
            boolean[] bust = new boolean[playerNum];
            boolean[] stick = new boolean[playerNum];
            int totalAce = 0;
            boolean allBust;
            boolean allStick = false;

            for (int i = 0; i < playerNum; i++) {
                aceNum[i] = 0;
                bust[i] = false;

                int card1 = Integer.parseInt(score[cardNum]);
                cardNum++;
                int card2 = Integer.parseInt(score[cardNum]);
                cardNum++;

                finalScore[i] = card1 + card2;

                if (card1 == 11) {
                    aceNum[i]++;
                    totalAce++;
                }
                if (card2 == 11) {
                    aceNum[i]++;
                    totalAce++;
                }

                if (finalScore[i] > 21 && aceNum[i] >= 0) {
                    if (totalAce <= MAX_ACE) {
                        while (aceNum[i] > 0 && finalScore[i] > 21) {
                            finalScore[i] -= 10;
                            aceNum[i]--;
                        }
                    } else {
                        System.err.println("ERROR: Too many Aces.");
                    }
                }

                if (i == 0 && cardNum > 1) {
                    System.out.println("You get a " + cards[cardNum - 2] + " and a " + cards[cardNum - 1] + " with total score: " + finalScore[i]);
                } else {
                    System.out.println("One of the A.I." + i + "'s card: " + cards[cardNum - 2]);
                }
            }


            while (!allStick) {
                //ask stick or hit
                boolean ans2got = false;
                String answer2 = "";
                if (finalScore[0] <= 21 && !stick[0]) {
                    while (!ans2got) {
                        try {
                            while (!ans2got) {
                                System.out.println("\nDo you want another card? (stick / hit)");
                                Scanner t = new Scanner(System.in);
                                answer2 = t.nextLine();
                                if (answer2.equalsIgnoreCase("stick") || answer2.equalsIgnoreCase("hit"))
                                    ans2got = true;
                                else
                                    System.err.println("ERROR: Please enter stick or hit!");
                            }
                        } catch (final Exception e) {
                            System.err.println("ERROR: Please enter stick or hit!");
                            ans2got = false;
                        }
                    }
                } else {
                    stick[0] = true;
                }

                for (int i = 0; i < playerNum; i++) {
                    int card = Integer.parseInt(score[cardNum]);
                    if (i == 0) {
                        if (answer2.equalsIgnoreCase("hit")) {
                            answer2 = "";

                            finalScore[i] += card;

                            if (card == 11) {
                                aceNum[i]++;
                                totalAce++;
                            }

                            if (finalScore[i] > 21 && aceNum[i] >= 0) {
                                if (totalAce <= MAX_ACE) {
                                    while (aceNum[i] > 0 && finalScore[i] > 21) {
                                        finalScore[i] -= 10;
                                        aceNum[i]--;
                                    }
                                } else {
                                    System.err.println("ERROR: Too many Aces.");
                                }
                            }

                            System.out.println("You get a " + cards[cardNum] + " with total score: " + finalScore[i]);
                            cardNum++;
                        } else if (answer2.equalsIgnoreCase("stick")) {
                            stick[i] = true;
                        }
                    }


                    if (i > 0) {
                        if ((finalScore[i] < 13) || (finalScore[i] >= 13 && finalScore[i] <= 16 && finalScore[i] <= finalScore[0])) {
                            finalScore[i] += card;

                            if (card == 11) {
                                aceNum[i]++;
                                totalAce++;
                            }

                            if (finalScore[i] > 21 && aceNum[i] >= 0) {
                                if (totalAce <= MAX_ACE) {
                                    while (aceNum[i] > 0 && finalScore[i] > 21) {
                                        finalScore[i] -= 10;
                                        aceNum[i]--;
                                    }
                                } else {
                                    System.err.println("ERROR: Too many Aces.");
                                }
                            }

                            System.out.println("A.I." + i + " hits");
                            cardNum++;
                        } else {
                            stick[i] = true;
                        }
                    }

                    allStick = true;
                    for (boolean elem : stick) {
                        if (!elem) {
                            allStick = false;
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < playerNum; i++) {
                if (finalScore[i] > 21) {
                    bust[i] = true;
                }
            }

            allBust = true;
            for (boolean elem : bust) {
                if (!elem) {
                    allBust = false;
                    break;
                }
            }

            int max = 0;
            int maxCount = 0;
            for (int elem : finalScore) {
                if (elem <= 21 && elem > max) {
                    max = elem;
                }
            }
            for (int elem : finalScore) {
                if (elem == max) {
                    maxCount++;
                }
            }

            for (int i = 0; i < playerNum; i++) {
                if (i == 0) {
                    if (bust[i]) {
                        if (allBust) {
                            System.out.println("\nDraw! All Bust!\n");
                        } else {
                            System.out.println("\nYou Bust!\n");
                        }
                        System.out.println("Your final score is " + finalScore[i] + "  BUST!");
                    } else if (finalScore[i] == max && maxCount == 1) {
                        System.out.println("\nCongratulations! You Win!\n");
                        System.out.println("Your final score is " + finalScore[i] + "  !!WINNER!!");
                    } else if (finalScore[i] == max && maxCount > 1) {
                        System.out.println("\nDraw!\n");
                        System.out.println("Your final score is " + finalScore[i] + "  DRAW");
                    } else if (finalScore[i] != max) {
                        System.out.println("\nYou lose!\n");
                        System.out.println("Your final score is " + finalScore[i]);
                    }
                } else {
                    if (finalScore[i] == max && maxCount == 1) {
                        System.out.println("A.I." + i + " final score is " + finalScore[i] + "  !!WINNER!!");
                    } else if (finalScore[i] == max && maxCount > 1) {
                        System.out.println("A.I." + i + " final score is " + finalScore[i] + "  DRAW");
                    } else if (bust[i]) {
                        System.out.println("A.I." + i + " final score is " + finalScore[i] + "  BUST!");
                    } else {
                        System.out.println("A.I." + i + " final score is " + finalScore[i]);
                    }
                }
            }
            //ask replay
            boolean ans8got = false;
            String answer8 = "";
            while (!ans8got) {
                try {
                    while (!ans8got) {
                        System.out.println();
                        System.out.println("Do you want another game? (y/n)");
                        Scanner z = new Scanner(System.in);
                        answer8 = z.nextLine();
                        if (answer8.equalsIgnoreCase("y") || answer8.equalsIgnoreCase("n"))
                            ans8got = true;
                        else
                            System.err.println("ERROR: Please enter y or n!");
                    }
                } catch (final Exception e) {
                    System.err.println("ERROR: Please enter y or n!");
                    ans8got = false;
                }
            }
            again = answer8.equalsIgnoreCase("y");
        }
        System.out.println();
        System.out.println("Thank You for Playing!");
    }
}