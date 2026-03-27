from graphics import *
from time import sleep


class Menu:
    def __init__(self, window):
        self.window = window
        # Store all the images for the Menu in a list
        self.images = [Image(Point(self.window.getWidth() // 2, self.window.getHeight() // 2), "Start_Menu.png"),
                       Image(Point(self.window.getWidth() // 2, self.window.getHeight() // 2), "Difficulty Levels.png")]
        self.images[1].transform(2.5)       # The image was too small

        # These variables will tell us what menu image we want to draw
        self.startMenu = True
        self.difficultyMenu = False
        self.imageIndex = 0  # The index of the image we are drawing
        self.difficulty = ""  # Initialize the difficulty text to an empty string for now

        # These are hit-boxes for each of the buttons
        self.startButton = Rectangle(Point(325, 205), Point(870, 395))
        self.stopButton = Rectangle(Point(385, 415), Point(845, 585))
        self.easyButton = Rectangle(Point(280, 275), Point(915, 350))
        self.mediumButton = Rectangle(Point(280, 380), Point(915, 460))
        self.hardButton = Rectangle(Point(280, 485), Point(915, 565))
        # This is the Text Box that will display the currently selected difficulty level

        self.difficultyTextBox = Text(Point(window.getWidth() // 2, 100),
                                      f"Testing difficulty setting: {self.getDifficulty()}")
        self.difficultyTextBox.setFill("white")
        self.difficultyTextBox.setSize(20)

        self.firstClick = True

    def updateMenu(self):
        # If we want to draw the startMenu
        if self.startMenu:
            # Undraw and draw the startMenu image
            self.images[self.imageIndex].undraw()
            self.images[self.imageIndex].draw(self.window)
        # If we want to draw the difficultyMenu
        elif self.difficultyMenu:
            # Undraw and draw the difficultyMenu image and text box
            self.images[self.imageIndex].undraw()
            self.difficultyTextBox.undraw()
            self.images[self.imageIndex].draw(self.window)
            self.difficultyTextBox.draw(self.window)

        self.testCollision()

    def changeImageIndex(self, index):
        self.imageIndex = index

    def testCollision(self):
        mouse = self.window.getCurrentMouseLocation()

        if self.window.mousePressed and self.firstClick:
            self.firstClick = False
            if self.startMenu:
                # If the mouse is colliding with the start button
                if Rectangle.testCollision_RectVsPoint(self.startButton, mouse):
                    # Undraw the current menuImage
                    self.images[self.imageIndex].undraw()
                    # Change the imageIndex to the index of the desired Image
                    self.changeImageIndex(1)
                    # Change our boolean variables to show what menu we want to be seeing
                    self.startMenu = False
                    self.difficultyMenu = True
                # If the mouse is colliding with the stop button
                elif Rectangle.testCollision_RectVsPoint(self.stopButton, mouse):
                    self.window.close()

            elif self.difficultyMenu:
                # If the mouse is colliding with the easy button
                if Rectangle.testCollision_RectVsPoint(self.easyButton, mouse):
                    self.difficulty = "Easy"
                # If the mouse is colliding with the medium button
                elif Rectangle.testCollision_RectVsPoint(self.mediumButton, mouse):
                    self.difficulty = "Medium"
                # If the mouse is colliding with the hard button
                elif Rectangle.testCollision_RectVsPoint(self.hardButton, mouse):
                    self.difficulty = "Hard"
                # Now we can reset the text for showing what the new difficulty setting is
                self.difficultyTextBox.setText(f"Testing difficulty setting: {menu.getDifficulty()}")

        elif not self.window.mousePressed and not self.firstClick:
            self.firstClick = True

    def getDifficulty(self):
        return self.difficulty


# =================================================================================================
# Testing Area
# =================================================================================================

window = GraphWin("Menu Demo", 1200, 800, autoflush=False)
window.setBackground("gray")
menu = Menu(window)

while not window.closed:
    menu.updateMenu()
    window.update()
    sleep(0.01)
