# Cee-Lo
Cee-Lo dice game
Fall 2014 project
Created b Andrew Goddu

This project uses the wheelsunh.jar library

Terms:
--Pair-point: A roll consisting of two equal numbers and one different number, for example a 4-4-5 would be called a “pair-5”
--Ace negative: Pair-1
--Three of a kind: A roll consisting of three equal numbers
--Strung Flower: The dice roll a 4-5-6 (order doesnt matter)
--Dancing Dragon: The dice roll a 1-2-3 (order doesnt matter)


Banker wins instantly if either player rolls:
--A three of a kind
--A pair-6
--Strung Flower

Player wins instantly if either player rolls:
--Ace Negative
--Dancing Dragon

If the banker rolls a pair-point other than 6 or 1, the point becomes the banker’s point and it is the player’s turn to roll three dice. If the player does not get one of the above combinations or a pair-point, he/she continues to roll until he/she does.

If the banker and player both roll pair-points (other than 1 or 6) then if:
--The banker's point and the player's point are equal then it is a push
--The banker's point is highter than the player's point then the banker wins
--The player's point is highter than the banker's point then the player wins
