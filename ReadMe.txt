Start Date:  7/11/2024
Last Updated:  6/14/2026
Current Version 0.1.1.0

To view code, navigate to src/engine.
To view assets, navigate to src/Assets.

Current build contains a main class that runs a test "game"

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

I also want this library to pair well with processing4 and not have any external jar files and NO AI GENERATED CODE PLEASE!
	I'm fine with using AI to find bugs for instance or give ideas to improve/optimize code, but a human better be the one 
	intrepreting that idea and writing that code themselves. I don't want this engine to be vibe coded slop, if I was fine
	with that, I wouldnt be writing this game engine from the ground up now would I?

TODO (JAVADOCS AND COMMENTS SHOULD ALWAYS BE PRIORITY):

I'm aware that some of these TODOs are a bit ambitious, but I won't stop working on this until theyre all implemented
#1:	 Allow MoveSet to change movement types on fly (to make knockback and interractions better)
#2:	 Instead of entities checking for triggers and storing them, have triggers check for entities
#3:	 Go through ToolKit and create new dist function that doesnt take square root and replace with that function
#4:	 Make basic physics engine to expand upon, make all MoveSet classes implement physics class and use
		vectors instead of what im currently doing to allow angled movement and collisions. I want to
		also add soft bodies at some point along with springs. basically, replace ToolKits collisions
		with new physics class based collisions
#5:	 Move away from processing and use built in java functions. I dont want any external jars
#6:	 Make ToolKit more "float friendly" with epsilon
#7:	 While at it, finish javadocs for ToolKit
#8:	 Make a "party" system that allows other entities to follow player (allow all members to fight in battles)
#9:	 Make enemies follow you if you get close enough
#10: Find ways to optimize code and fix existing issues in code such as:
		a: EightDirectionalMove snapping at corners, allowing you to fit bewteen spaces you shouldnt,
			and given enough obstacles, without total dist being tracked, entities can move an infinite
			amount of distance in one frame
		b: make Animations easier to customize, such as allowing vertical spritesheets, and allowing user
			to specify what animation should be played with what movements
		c: some colision code is probably suboptimal as I made most of it from scratch without looking any
			algorithms up. I am sure there are many issues I have glossed over as well
#11:	 Implement other movement types excluding PlatformerComplexMove
#12: Create engine.entity.attack package along with Attack class for NPCs (or make it an ability)
#13: Make working menu system along with text boxes
#14: Implement saving and loading using new menu
#15: Implement keybinds that are able to be changed and saved
#16: Implement tile based background and editor
#17: EVENTS (dialogue and cutscenes and such)
#18: Make Sounds and Sound Effects easy to call and analyze
#19: Implement an UI that can be called upon as a function. I will try to make an outline here:
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
#20: Implement PlatformerComplexMove (Maybe do me sooner because I'll be easy with physics implemented!)
#21: Implement Enemy AI for platformer mode
#22: Implement Neural Network that can learn from other Entities (implement matrix and fraction class too) this
		Neural Net should be more general and easy to customize as user sees fit
#23: Implement multiplayer of some sorts (server vs client)
#24: Find some way to get steamworks integrated (maybe have built in achievements and features that are easy to
	 	connect to steam somehow? I might be forced to use steamworks4j)
	 	
Small demos that I wanna make to prove that the engine works:
	RPG (menu + 8d + 2d)
		Overworld 8d, 4d, 2d
		Battle menu, 8d
	platformer
	tower defense
	idle game
	chess
	arrow key rhythm game
	snake
	tank movement game

