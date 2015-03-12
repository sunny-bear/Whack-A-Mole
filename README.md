# Whack-A-Mole
test github

src/Game.java
The starter of the game. 
Used java swing component to build the graphic interface of the game.
Created a thread for every "hole", which is represented by a jButton. 
When "start" button is clicked, add a new thread for each of the 16(4*4) holes.

src/MoleThread.java
Represent a hole in 4*4 grids. 
It performs routines of a hole: a mole may show up every 0.5s~2.5s randomly, stay up to 1s randomly, get knocked by hammer or not.
It holds a jButton as member variable. If a mole get knocked by hammer(this button is clicked), update scoreboard.

src/TimerThread.java
Controller of the game. It count the time and number of clicks.

resources/xx.jpg
empty hole, hole with mole, mole get knocked, hammer, hammer knock

Thanks to dear Ming to provide the images~
