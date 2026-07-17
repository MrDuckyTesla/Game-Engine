# Quick Statistics

Start Date:  7/11/2024 <br>
Last Updated:  7/17/2026 <br>
Current Version 0.1.2.0 <br>

![Repo Size](https://img.shields.io/github/repo-size/MrDuckyTesla/Game-Engine)
![Commits](https://img.shields.io/github/commit-activity/y/MrDuckyTesla/Game-Engine)
![Last Commit](https://img.shields.io/github/last-commit/MrDuckyTesla/Game-Engine)

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

## High effort
> - Implement basic but modular physics engine to expand upon using vectors, make all MoveSet implementations 
utilize these physics classes, and allow angled movement and collisions. Add soft bodies at some point along with 
springs. Basically, replace Toolkit's collisions with new physics class-based collisions. <br>
> - Remove external jars, move away from processing and use built-in Java functions. <br>
> - Implement pathfinding and allow enemies to follow entities if you get close enough. <br>
> - Rework animations class to be more customizable such as allowing vertical spritesheets, irregularly sized sprites,
and customizing animations to be for different things such as movements. This might be best done by creating an 
animation interface. <br>
> - Implement PlatformerComplexMove. <br>
> - Implement a working UI/menu system. <br>
> - EVENTS (dialogue and cutscenes and such). <br>
> - Implement sound system for creating and calling sounds. <br>
> - Implement a UI that can be called upon as a function. I will try to outline here: <br>
		- UI allows user to click on tiles and edit said tiles to contain whatever tile they want, allowing
			the user to open a folder of images for said tiles. <br>
		- UI allows user to place down entities anywhere (not grid locked). This entity would be a blank
			rectangle that the user can click on to edit a list of properties. These properties would include
			x, y, width, height, scale, spritesheet, movement type, list of abilities, and color. The properties 
			should NOT be bloated and should be as limited as possible for ease of use. <br>
		- UI also allows entities to be chosen as Enemies/neutral NPCs, or Triggers. The user should be able
			to make rooms easily. This includes making rooms inside of entities (this allows triggers to move
			entities to other rooms or allows players to enter a battle, for example). <br>
		- The UI should also allow the menu to be edited per room, with dragging elements around and choosing
			x, y, width, height, ...etc. as well. <br>
		- This UI, upon finishing, should save what the user did to a text file, translating what the user did
			into its equivalent code for the user to copy and paste into their project. <br>
> Implement multiplayer of some sort. <br>


## Medium effort
> - Allow MoveSet to change movement types on the fly (to make knockback and interactions better). <br>
> - Get rid of as many enums as possible, along with replacing abstract classes with interfaces to make the code simpler.<br>
> - Instead of entities checking for triggers and storing them, have triggers check for entities. <br>
> - Given enough obstacles, without total dist being tracked, entities can move an infinite amount of distance in one frame.<br>
> - EightDirectionalMove snaps at corners, allowing you to fit between spaces you shouldn't. <br>
> - Implement other movement types excluding PlatformerComplexMove. <br>
> - Make a "party" system that allows other entities to follow the player (and allows all members to fight in battles). <br>
> - keybinds that can be changed and saved. <br>
> - Implement tile-based background system. <br>
> - Implement Enemy AI for platformer mode. <br>
> - Look into creating engine.entity.attack package along with Attack class for NPCs (or make it an ability). <br>
> - Implement other empty network classes. <br>
> Implement a better save and load function/class to encode data such as save files or a neural networks weights. <br>

## Low effort
> - Writing Javadocs and commenting code <br>
> - Go through Toolkit and create a new dist function that doesn't take the square root for just checking what's closer. <br>
> - Make ToolKit more "float friendly" by removing exact with epsilon. <br>
> - Majority of code is most likely suboptimal as most was made from scratch, so rewriting certain algorithms to be 
faster is welcome. <br>

	 	
## Small demos that I wanna make to prove that the engine works: <br>
> -	RPG overworld 8d, 4d, 2d <br>
> -	RPG battle menu, 8d <br>
> -	Platformer <br>
> -	Tower Defense <br>
> -	Idle game <br>
> -	Chess <br>
> -	Arrow-key Rhythm Game <br>
> -	Snake <br>
> -	Tank Movement game <br>

