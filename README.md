# BullsAndCows

## About
Write your own advanced version of the classic code breaking game "Bulls and Cows". Start with recreating the original version of the game and then take it to a new level.

## Learning outcomes
Learn how to use integer arithmetic, generate random numbers, store data in data structures, and handle errors.

Examples
The greater-than symbol followed by a space > represents the user input. Note that it's not part of the input.

Example 1

Input the length of the secret code:
> 6
Input the number of possible symbols in the code:
> 5
Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.
Example 2

Input the length of the secret code:
> abc 0 -7
Error: "abc 0 -7" isn't a valid number.
Example 3

Input the length of the secret code:
> 6
Input the number of possible symbols in the code:
> 37
Error: maximum number of possible symbols in the code is 36 (0-9, a-z).
Example 4

Input the length of the secret code:
> 4
Input the number of possible symbols in the code:
> 12
The secret is prepared: **** (0-9, a-b).
Okay, let's start a game!
Turn 1:
> a234
Grade: 1 bull and 1 cow
Turn 2:
> 73b4
Grade: 2 bulls and 1 cow
Turn 3:
> 9374
Grade: 4 bulls
Congratulations! You guessed the secret code.
