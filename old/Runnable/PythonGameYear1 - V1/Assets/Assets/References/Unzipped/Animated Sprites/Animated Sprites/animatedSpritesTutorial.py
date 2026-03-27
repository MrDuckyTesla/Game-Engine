from graphics import *
from time import sleep

window = GraphWin("Testing", 1200, 800, autoflush=False)

class Knight:
    def __init__(self, x, y, window):
        self.x = x
        self.y = y
        self.window = window
        self.facingLeft = True        # If True facing left and if False facing right
        self.idle = False       # If True then idle and if False then running
        self.imageIndex = 0
        self.runLeftImages = []
        self.runRightImages = []
        self.idleLeftImages = []
        self.idleRightImages = []
        self.__loadImages()

    def __loadImages(self):
        """Loads all the images for the animation"""
        for i in range(10):
            image = Image(Point(self.x, self.y), f"knight/knightRunLeft_{i}.png")
            self.runLeftImages.append(image)
        for i in range(10):
            image = Image(Point(self.x, self.y), f"knight/knightRunRight_{i}.png")
            self.runRightImages.append(image)
        for i in range(10):
            image = Image(Point(self.x, self.y), f"knight/knightIdleRight_{i}.png")
            self.idleRightImages.append(image)
        for i in range(10):
            image = Image(Point(self.x, self.y), f"knight/knightIdleLeft_{i}.png")
            self.idleLeftImages.append(image)

    def undrawImage(self):
        """Undraws the Image based on the direction the Player is facing as well as if the player is idle or running."""
        if self.facingLeft:               # If the player is facing left
            if self.idle:           # If the player is idle
                self.idleLeftImages[int(self.imageIndex)].undraw()
            else:                   # If the player is moving
                self.runLeftImages[int(self.imageIndex)].undraw()
        else:                        # If the player is facing right
            if self.idle:            # If the player is idle
                self.idleRightImages[int(self.imageIndex)].undraw()
            else:                    # If the player is moving
                self.runRightImages[int(self.imageIndex)].undraw()

    def drawImage(self):
        """Draws the Image based on the direction the Player is facing as well as if the player is idle or running."""
        if self.facingLeft:  # If the player is facing left
            if self.idle:  # If the player is idle
                self.idleLeftImages[int(self.imageIndex)].draw(self.window)
            else:  # If the player is moving
                self.runLeftImages[int(self.imageIndex)].draw(self.window)
        else:  # If the player is facing right
            if self.idle:  # If the player is idle
                self.idleRightImages[int(self.imageIndex)].draw(self.window)
            else:  # If the player is moving
                self.runRightImages[int(self.imageIndex)].draw(self.window)

    def movePlayer(self):
        """Moves the player based off of keyboard input."""
        keys = self.window.checkKeys()

        # If the image index ever goes over ten reset it to zero since there are only ten images in each list.
        if self.imageIndex > 9:
            self.imageIndex = 0

        # Trying to go left
        if "a" in keys:
            # If currently facing left
            if self.facingLeft:
                self.imageIndex += 0.3
            # If currently facing right
            else:
                self.facingLeft = True
                self.imageIndex = 0
            self.x -= 5
            self.idle = False
            self.runLeftImages[int(self.imageIndex)].anchor.x = self.x

        # Trying to go right
        elif "d" in keys:
            if self.facingLeft:
                self.imageIndex = 0
                self.facingLeft = False
            else:
                self.imageIndex += 0.3
            self.x += 5
            self.idle = False
            self.runRightImages[int(self.imageIndex)].anchor.x = self.x

        # Not trying to run in either direction
        else:
            self.idle = True
            self.imageIndex += 0.3
            if self.facingLeft:
                self.idleLeftImages[int(self.imageIndex)].anchor.x = self.x
            else:
                self.idleRightImages[int(self.imageIndex)].anchor.x = self.x

    def update(self):
        self.undrawImage()
        self.movePlayer()
        self.drawImage()

knight = Knight(600, 400, window)

while not window.closed:
    knight.update()
    window.update()
    sleep(0.01)
