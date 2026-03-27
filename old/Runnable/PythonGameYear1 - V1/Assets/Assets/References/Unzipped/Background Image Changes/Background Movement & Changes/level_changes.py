from graphics import *

class Level:
    def __init__(self, window):
        self.window = window
        self.backgrounds = []           # List that will contain all the background images (in order) for the level
        self.backgroundIndex = 0        # The index of the background image we are currently drawing from the list
        self.loadBackgrounds()

    def loadBackgrounds(self):
        for i in range(2):
            #                   Centering the background image in the window            Names of the files in the Folder
            bg = Image(Point(self.window.getWidth()//2, self.window.getHeight()//2), f"Images/background_{i}.png")
            self.backgrounds.append(bg)     # Appending each of the images to the list

    def changeBackgroundIndex(self, newIndex):
        self.backgroundIndex = newIndex

    def update(self):
        self.backgrounds[self.backgroundIndex].undraw()
        self.backgrounds[self.backgroundIndex].draw(self.window)

class Player:
    def __init__(self, x, y, size, window):
        self.x = x
        self.y = y
        self.size = size
        self.window = window
        self.player = Rectangle(Point(x, y), Point(x + size, y + size))
        self.player.setFill("red")

    def move(self):
        keys = self.window.checkKeys()

        if "a" in keys:
            self.player.move(-5, 0)
            self.x -= 5

        if "d" in keys:
            self.player.move(5, 0)
            self.x += 5

    def repositionPlayer(self, newX):
        # Changing the x coordinate of our Player object
        self.x = newX
        # Changing the left x coordinate of the Rectangle that represents our player
        self.player.p1.x = self.x
        # Changing the right x coordinate of the Rectangle that represents our player
        self.player.p2.x = self.x + self.size

    def update(self):
        self.player.undraw()
        self.move()
        self.player.draw(self.window)


window = GraphWin("Background Changes", 1200, 800, autoflush=False)
level = Level(window)
player = Player(0, 510, 50, window)

def changeBackground():
    """Checks to see if the player has advanced to the next section of the Level
    and changes the background image if necessary."""
    # If the player goes off to the right of the window  and  If we are currently not drawing the last image in the list
    if player.x + player.size > window.getWidth() and level.backgroundIndex != len(level.backgrounds) - 1:
        # Go to the next image in the images list
        level.changeBackgroundIndex(level.backgroundIndex + 1)
        # Position the player back onto the left side of the window
        player.repositionPlayer(0)

    # If the player goes off to the left of the window and If we are currently not drawing the first image in the list
    elif player.x < 0 and level.backgroundIndex != 0:
        # Go to the previous image in the images list
        level.changeBackgroundIndex(level.backgroundIndex - 1)
        # Position the player back onto the right side of the window
        player.repositionPlayer(window.getWidth() - player.size)


while not window.closed:
    level.update()
    player.update()
    window.update()
    changeBackground()
    time.sleep(0.01)

