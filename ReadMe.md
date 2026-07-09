# Quick Statistics

Start Date:  7/11/2024 <br>
Last Updated:  7/8/2026 <br>
Current Version 0.1.1.9 <br>

![Repo Size](https://img.shields.io/github/repo-size/MrDuckyTesla/Game-Engine)
![Commits](https://img.shields.io/github/commit-activity/t/MrDuckyTesla/Game-Engine)
![Last Commit](https://img.shields.io/github/last-commit/MrDuckyTesla/Game-Engine)

Java Files: {{JAVA_FILES}}
Lines of Code: {{LOC}}

Current features: <br>
>	- Modular entities with decoupled abilities, movement, and animation. <br>
>	- Modular neural network with a custom matrix class, and all components separated. <br>
>	- Tons of custom graphics and utility code (found in engine.util.ToolKit.java) <br>

# Q & A <br>

## Why did you create this game engine? <br>
>	Personally, I've always found other game engines with UIs to be convoluted and confusing, with the 
 hundreds of boxes and toggles for every little thing I drag onto the screen. I could never figure out
how to use them. While practice could have remedied that eventually, I much prefer using code to
describe what I want. It's important to me to know how every piece of code works in the background
rather than being hidden behind some UI. I also wanted a general game engine that was simple, clean,
and modular so that it could grow with me as I learn more about computer science at college.

## What was your inspiration behind this game engine? <br>
>	I've always loved how simple and easy the Processing Foundation p5.js and Processing4 libraries are to 
use. Specifically, I liked how I got to write graphical code myself without having to worry about the nuances 
of programming shapes to appear on a window, or having to worry about programming the deceivingly complicated 
aspects of a graphics library. But I also liked how, if you wanted to, you could modify those more complex
features of the graphics. I really like writing code and knowing how everything works in my head, so having 
that be an option creates an amazing user experience for programming something as small as an animation to
even making a small game. 

## What was your main focus behind the game engine? <br>
>	This engine will focus on compatibility with other graphics libraries (specifically p5.js and Processing4)
while also being a standalone engine. I also don't want to use any external JAR files or libraries whatsoever 
to keep the engine clean and simple. While I'm also not a huge fan of UI, I think some sort of editor
that uses a UI and outputs code would be a great way to keep code from being tedious, while being a great
way to create assets. I want to maintain structure and modularity as I expand on the engine in order to 
make refactoring parts of the engine easy. This engine, for now, will focus on 2D games until it is in a more 
finished state or until I get more contributors. This library should be beginner-friendly, but also 
accommodating towards advanced users.

## What is your stance on AI generated code? <br>
>	NO VIBE CODING PLEASE! I am completely ok with AI debugging code to find bugs or to give ideas on how to 
improve or optimize code, BUT I want a human to interpret that idea and write that code themselves. AI should 
be used as a tool, not as a replacement for humans. I feel like these rules are reasonable, and I'd greatly
appreciate compliance with these rules, or else I will remove you from the repository.

## If you are interested in learning more or helping with my project, feel free to get in touch with me or contribute. Cheers - MrDuckyTesla <br>

# TO-DO 

I'm aware that some of these TO-DOs are a bit ambitious, but I won't stop working on this until they're all implemented. <br>

JAVADOCS AND COMMENTS SHOULD ALWAYS BE A TOP PRIORITY

#1:	 Allow MoveSet to change movement types on the fly (to make knockback and interactions better).
#2:  Get rid of as many enums as possible, along with replacing abstract classes with interfaces to make the code simpler.
#3:	 Instead of entities checking for triggers and storing them, have triggers check for entities.
#4:	 Go through Toolkit and create a new dist function that doesn't take the square root for just checking what's closer.
#5:	 Make a basic physics engine to expand upon, make all MoveSet classes utilize physics classes, and use
		vectors instead of what I'm currently doing to allow angled movement and collisions. I want to
		also add soft bodies at some point along with springs. Basically, replace Toolkit's collisions
		with new physics class-based collisions.
#6:	 Move away from processing and use built-in Java functions. I don't want any external jars.
#7:	 Make ToolKit more "float friendly" with epsilon.
#8:	 Finish javadocs for ToolKit.
#9:	 Make a "party" system that allows other entities to follow the player (and allows all members to fight in battles).
#10: Make enemies follow you if you get close enough.
#11: Find ways to optimize code and fix existing issues in code such as:
		a: EightDirectionalMove snapping at corners, allowing you to fit between spaces you shouldn't,
			and given enough obstacles, without total dist being tracked, entities can move an infinite
			amount of distance in one frame.
		b: Make Animations easier to customize, such as allowing vertical spritesheets, and allowing user
			to specify what animation should be played with what movements.
		c: Some collision code is probably suboptimal as I made most of it from scratch without looking any
			algorithms up. I am sure I have missed some issues as well.
#12: Implement other movement types excluding PlatformerComplexMove.
#13: Create engine.entity.attack package along with Attack class for NPCs (or make it an ability).
#14: Make a working menu system along with text boxes.
#15: Implement saving and loading using a new menu.
#16: Implement keybinds that can be changed and saved.
#17: Implement tile-based background and editor.
#18: EVENTS (dialogue and cutscenes and such).
#19: Make Sounds and Sound Effects easy to call and analyze.
#20: Implement a UI that can be called upon as a function. I will try to outline here:
		a: UI allows user to click on tiles and edit said tiles to contain whatever tile they want, allowing
			the user to open a folder of images for said tiles.
		b: UI allows user to place down entities anywhere (not grid locked). This entity would be a blank
			rectangle that the user can click on to edit a list of properties. These properties would include
			x, y, width, height, scale, spritesheet, movement type, list of abilities, and color. The properties 
			should NOT be bloated and should be as limited as possible for ease of use.
		c: UI also allows entities to be chosen as Enemies/neutral NPCs, or Triggers. The user should be able
			to make rooms easily. This includes making rooms inside of entities (this allows triggers to move
			entities to other rooms or allows players to enter a battle, for example).
		d: The UI should also allow the menu to be edited per room, with dragging elements around and choosing
			x, y, width, height, ...etc. as well. 
		e: This UI, upon finishing, should save what the user did to a text file, translating what the user did
			into its equivalent code for the user to copy and paste into their project.
#21: Implement PlatformerComplexMove (Maybe do me sooner, since I'll be easy with the physics implemented!).
#22: Implement Enemy AI for platformer mode.
#23: Implement a Neural Network that can learn from other Entities (implement matrix and fraction class too).
		- Neural Net should be more general and easy to customize as user sees fit.
#24: Implement multiplayer of some sort (server vs client).
#25: Find some way to get Steamworks integrated (maybe have built-in achievements and features that are easy to
	 	connect to Steam somehow? I might be forced to use steamworks4j)
	 	
>> Small demos that I wanna make to prove that the engine works:
	> RPG (menu + 8d + 2d)
		- Overworld 8d, 4d, 2d
		- Battle menu, 8d
	> Platformer
	> Tower Defense
	> Idle game
	> Chess
	> Arrow-key Rhythm Game
	> Snake
	> Tank Movement game

