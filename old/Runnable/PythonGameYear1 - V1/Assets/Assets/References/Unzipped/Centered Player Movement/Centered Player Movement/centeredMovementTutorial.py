from graphics import *
from time import sleep

window = GraphWin("Centered Movement Tutorial", 1200, 600, autoflush=False)
# background = Image(Point(1920, 60), "mario_level.jpg")
player = Rectangle(Point(0, 525), Point(25, 550))
player.setFill("red")

while not window.closed:
    # background.undraw()
    player.undraw()

    keys = window.checkKeys()

    if "a" in keys:
        # If the player is at the beginning of the and is in the center of the window or to the left of the window move the player
        if background.anchor.x >= background.getWidth()//2 and player.getCenter().x <= window.getWidth()//2:
            # If the player is not about to go off the window to the left
            if player.p1.x > 0:
                player.move(-10, 0)
        # If the player is at the end of the level move the player
        elif player.getCenter().x >= window.getWidth()// 2:
            player.move(-10, 0)
        # In any other case move the background
        else:
            background.move(10, 0)

    if "d" in keys:
        # If the player is at the end of the level and is in the center of the window or to the right of the center of the window
        if background.anchor.x <= window.getWidth() - background.getWidth()//2 and player.getCenter().x >= window.getWidth() // 2:
            # If the player is not about to go off the window to the right
            if player.p2.x < winddow.getWidth():
                player.move(10, 0)
        # If the player is at the beginning of the level and is to the left of the center of the window
        elif player.getCenter().x <= window.getWidth() //2:
            player.move(10, 0)
        # In any other instance move the background
        else:
            background.move(-10, 0)


    # background.draw(window)
    player.draw(window)
    window.update()
    sleep(0.01)
