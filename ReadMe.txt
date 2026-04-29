Start Date:  7/11/2024
Last Updated:  4/29/2026
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

#1:	 Allow MoveSet to change movement types on fly (to make knockback and interractions better)
#2:	 Instead of entities checking for triggers and storing them, have triggers check for entities
#3:	 Make ToolKit more "float friendly" with epsilon
#4:	 Make a "party" system that allows other entities to follow player
#5:	 Make enemies follow you if you get close enough
#6:	 Find ways to optimize code and fix existing issues in code such as:
		a: EightDirectionalMove snapping at corners, allowing you to fit bewteen spaces you shouldnt,
			and given enough obstacles, without total dist being tracked, entities can move an infinite
			amount of distance in one frame
		b: make Animations easier to customize, such as allowing vertical spritesheets, and allowing user
			to specify what animation should be played with what movements
		c: some colision code is probably suboptimal as I made most of it from scratch without looking any
			algorithms up. I am sure there are many issues I have glossed over as well
#7:	 Implement other movement types
#8:	 Make working menu system along with text boxes
#9: Implement saving and loading using new menu
#10: Implement tile based background and editor
#11: EVENTS (dialogue and cutscenes and such)
#12: Implement an UI that can be called upon as a function. I will try to make an outline here:
		a: UI allows user to click on tiles and edit said tiles to contain whatever tile they want, allowing
			the user to open a folder of images for said tiles.
		b: UI allows user to place down entities anywhere (not grid locked). this entity would be a blank
			rectangle that the user can click on to edit a list of properties. these properties would include
			x, y, width, height, scale, spritesheet, movement type, list of abilites, and color. The properties 
			should NOT be bloated and should be as limited as possible for ease of use.
		c: UI also allows entities to be chosen as Enemies/neutral NPCs, or Triggers. The user should be able
			to make rooms easily. this includes making rooms inside of entities (this allows triggers to move
			entities to other rooms or allows players to enter a battle for example)
		d: The UI should also allow the menu to be edited per room, with dragging elements around and choosing
			x, y, width, height, ...etc. as well. 
		e: this UI, upon finishing, should save what the user did to a text file, translating what the user did
			into its equivalent code counterpart for the user to copy paste into their project.
#13: Implement PlatformerComplexMove
