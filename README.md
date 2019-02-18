# SCI 124 Mobile Communications and the Internet
## Programing assignment
The aim of the assignment is to program a text based simulation of the game of 21. For your convenience, the general rules that you should use are included below. By making use of the programing techniques you have learnt a simplified version of the game can be programed.<br/>
<br/>
The aim of the game is to get as close to 21 as possible. The game uses a full deck of cards without the jokers. Number cards score their value, and face cards score 10.  For the simplified version of the game, treat Aces as scoring 11. If player scores more than 21, he is bust and loses. If both player have the same, or both bust, then it is a draw.<br/>
<br/>
The following mark scheme will be used for this work. Note that there are possibly more than 100% of marks available. The score will be capped at 100%, but by attempting to add more features it increases the chance of full marks.<br/>
<br/>

Feature | Maximum Score available
--- | ---
Program Compiles | 10%
Program Runs | 10%
Program Creates Array for Deck of Cards | 10%
Program can draw two unique cards for player 1 and 2 unique cards for player 2 | 10%
Program calculates score and determines winner | 10%
Appropriate use of FINAL | 5%
Sensible Variable names | 5%
Use of scanner for user input (stick or hit see below) | 10%
Programming additional computer player logic (see below) to allow to play against computer | 20%
Asking number of players and if user wants another game | 10%
Use of TRY and CATCH to validate numerical input | 10%
Treating aces as 11 or 1 | 10%

## User input
Player asked to hit or stick. If hit is selected, a new unique card is dealt, if stick, then play swaps to other player.

## Computer controlled player logic
This will require the following rules to be implemented:-<br/>
If computer score is greater than 16 then the AI player sticks<br/>
If the computer player is lower than 13 the computer always takes a new card<br/>
If the computer player scores less than or equal to 16, greater than or equal to 13, and less than or equal to the human player score, the AI will take a new card.<br/>
The computer player will always go last.<br/>

## Aces as 11 or 1
This should be implemented so as to be able to convert any ace to a 1 if needed. A simplified version of this will be acceptable (no choice, just treat an ace as 1 if the score goes over 21). An easy way to do this is by keeping a counter of all the aces drawn. Then if the score goes above 21, reduce this counter and reduce the score by 10.
