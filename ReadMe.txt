Start Date:  7/11/2024
Last Updated:  7/8/2026
Current Version 0.1.1.8

Why did I create this game engine?
	Personally, I've always found other game engines with UIs to be convoluted and confusing, with the 
hundreds of boxes and toggles for every little thing I drag onto the screen. Personally, I could never
figure out how to use them. While practice could have remedied that, I much prefer using code to describe
what I want. I like to know how every piece of code works in the background rather than being hidden behind
some UI. I also wanted a general game engine that was very modular and could meet all my needs, as long as
grow with me as I learn more about computer science at college.

Inspiration behind this game engine? 
	I've always loved how simple and easy the Processing Foundation p5.js and Processing4 libraries are to 
use. Specifically, I liked how I got to write graphical code myself without having to worry about the nuances 
of programming shapes to appear on a window, or having to worry about programming the deceivingly complicated 
aspects of a graphics library. But I also liked how, if you wanted to, you could modify those more complex
features of the graphics. I really like writing code and knowing how everything works in my head, so having 
that be an option creates an amazing user experience for programming something as small as an animation to
even making a small game. 

focus behind game engine?
	This engine will focus on compatibility with other graphics libraries (specifically p5.js and processing4)
while also being a standalone engine. I also don't want to use any external JAR files or libraries whatsoever 
in order to keep the engine clean and simple. While I'm also not a huge fan of UI, I think some sort of editor
that uses a UI and outputs code would be a great way to keep code from being tedious, as long as being a great
way to create assets. I also want to maintain structure and modularity as I expand on the engine in order to 
make refactoring parts of the engine easy. This engine, for now, will focus on 2D games until it is in a more 
finished state or until I get more contributors. This library should be beginner-friendly, but also 
accommodating towards advanced users.

AI stance?
	NO VIBE CODING PLEASE! I am completely ok with AI debugging code to find bugs or to give ideas on how to 
improve or optimize code, BUT I want a human to interpret that idea and write that code themselves. AI should 
be used as a tool, not as a replacement for humans. If I somehow catch a contributor using AI against these
rules, I will immediately remove them from the repository.

current features:
	Modular entities with abilities, movement, animation, and collision separated.
	Modular neural network with a custom matrix class, and all components separated.
	Tons of custom graphics and utility code (found in engine.util.ToolKit.java)

If you like what you read, feel free to get in touch with me or contribute. Cheers - MrDuckyTesla

TODO (JAVADOCS AND COMMENTS SHOULD ALWAYS BE PRIORITY):

I'm aware that some of these TODOs are a bit ambitious, but I won't stop working on this until they're all implemented
#1:	 Allow MoveSet to change movement types on the fly (to make knockback and interactions better)
#2:  Get rid of as many enums as possible along with replacing abstract classes with interfaces to make code more simple
#3:	 Instead of entities checking for triggers and storing them, have triggers check for entities
#4:	 Go through ToolKit and create new dist function that doesn't take square root for just checking whats closer
#5:	 Make basic physics engine to expand upon, make all MoveSet classes utilize physics classes, and use
		vectors instead of what im currently doing to allow angled movement and collisions. I want to
		also add soft bodies at some point along with springs. Basically, replace ToolKits collisions
		with new physics class based collisions
#6:	 Move away from processing and use built in java functions. I don't want any external jars
#7:	 Make ToolKit more "float friendly" with epsilon
#8:	 While at it, finish javadocs for ToolKit
#9:	 Make a "party" system that allows other entities to follow player (allow all members to fight in battles)
#10: Make enemies follow you if you get close enough
#11: Find ways to optimize code and fix existing issues in code such as:
		a: EightDirectionalMove snapping at corners, allowing you to fit bewteen spaces you shouldnt,
			and given enough obstacles, without total dist being tracked, entities can move an infinite
			amount of distance in one frame
		b: make Animations easier to customize, such as allowing vertical spritesheets, and allowing user
			to specify what animation should be played with what movements
		c: some colision code is probably suboptimal as I made most of it from scratch without looking any
			algorithms up. I am sure there are many issues I have glossed over as well
#12: Implement other movement types excluding PlatformerComplexMove
#13: Create engine.entity.attack package along with Attack class for NPCs (or make it an ability)
#14: Make a working menu system along with text boxes
#15: Implement saving and loading using new menu
#16: Implement keybinds that are able to be changed and saved
#17: Implement tile based background and editor
#18: EVENTS (dialogue and cutscenes and such)
#19: Make Sounds and Sound Effects easy to call and analyze
#20: Implement a UI that can be called upon as a function. I will try to make an outline here:
		a: UI allows user to click on tiles and edit said tiles to contain whatever tile they want, allowing
			the user to open a folder of images for said tiles.
		b: UI allows user to place down entities anywhere (not grid locked). This entity would be a blank
			rectangle that the user can click on to edit a list of properties. These properties would include
			x, y, width, height, scale, spritesheet, movement type, list of abilities, and color. The properties 
			should NOT be bloated and should be as limited as possible for ease of use.
		c: UI also allows entities to be chosen as Enemies/neutral NPCs, or Triggers. The user should be able
			to make rooms easily. This includes making rooms inside of entities (this allows triggers to move
			entities to other rooms or allows players to enter a battle for example)
		d: The UI should also allow the menu to be edited per room, with dragging elements around and choosing
			x, y, width, height, ...etc. as well. 
		e: this UI, upon finishing, should save what the user did to a text file, translating what the user did
			into its equivalent code counterpart for the user to copy paste into their project.
#21: Implement PlatformerComplexMove (Maybe do me sooner, since I'll be easy with the physics implemented!)
#22: Implement Enemy AI for platformer mode
#23: Implement Neural Network that can learn from other Entities (implement matrix and fraction class too) this
		Neural Net should be more general and easy to customize as user sees fit
#24: Implement multiplayer of some sorts (server vs client)
#25: Find some way to get steamworks integrated (maybe have built in achievements and features that are easy to
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

