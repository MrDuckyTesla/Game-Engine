Start Date:  7/11/2024
Last Updated:  4/27/2026
Current Version 0.1.0.1

To view code, navigate to src/game.
To view assets, navigate to src/Assets.

Why did I decide to make my own game engine? 
Well I've always loved the processing foundations p5.js and processing4 libraries. I liked how simple it was to use, and 
how I got to write all of the code myself without having to worry about annoying things like making a window with a circle 
or rectangle on it that updates every frame. I really like writing code and knowing how everything works in my head, and I 
feel like this engine will be more focused to people who rather stare at code than some confusing and convoluted UI with a 
thousand different toggles and boxes. I also like the structure of java and how it forces you to at minimum make decent code. 
I want to make a minimal game engine that can cover a huge basis of game generas. for now Im going to focus on 2D games, but 
if people actually contribute to this repository, then maybe we can delve into 3D. Anyhoo, for the time being its all gonna 
be me doing the work so if you like any of what you read, feel free to get in touch with me to contribute. Cheers - MrDuckyTesla

tldr: fully code based game engine -> make tedious code simple and streamlined -> minimal UI if any -> beginner friendly

TODO:

#1: Allow MoveSet to change movement types on fly (to make knockback and interractions better)
#2: Instead of entites checking for triggers and storing them, have triggers check for entites
#3: Make ToolKit more "float friendly" with epsilon
#4: Make working menu system
#5: Implement tile based background and editor
#6: EVENTS (dialogue and cutscenes and such)
#7: UI for making levels

note that theoretically given enough obstacles, player can move infinite amount in one frame

I want to have functions you can call that display a UI that will allow you to edit different things such as a level 
editor where you can place events, tiles, and entities freely onto a map so that way you can save the code to a text file.
Same thing but with menus too

OLD TODO:
#01:  Refactor by moving some character code into room code, make the code modular
#02:  add npc following overworld (this includes pathfinding, so maybe later)
#03:  add enemy overworld
#04:  add overworld interacting
#05:  add overworld and battle dialogue
#06:  optimize code with this: https://www.w3schools.com/js/js_performance.asp
#07:  add menu
#08:  add enemy battle ai
#09:  add save files
#11:  Add battle animations (using procedural animation)
