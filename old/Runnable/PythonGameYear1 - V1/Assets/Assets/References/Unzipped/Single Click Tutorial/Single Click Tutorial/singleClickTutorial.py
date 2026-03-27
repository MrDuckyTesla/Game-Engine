from graphics import *
from random import randrange
from time import sleep

window = GraphWin("Single Click Tutorial", 1200, 800, autoflush=False)

class Ball:
    def __init__(self, window):
        self.window = window
        self.color = color_rgb(randrange(255), randrange(255), randrange(255))
        self.firstPress = True
        self.ball = Circle(Point(self.window.getWidth()//2, self.window.getHeight()//2), 100)
        self.ball.setFill(self.color)

    def changeColorWithMouse(self):

        if self.window.mousePressed and self.firstPress:
            self.firstPress = False
            self.color = color_rgb(randrange(255), randrange(255), randrange(255))
            self.ball.setFill(self.color)

        if not self.window.mousePressed and not self.firstPress:
            self.firstPress = True

    def changeColorWithKeys(self):

        keys = self.window.checkKeys()

        if "space" in keys and self.firstPress:
            self.firstPress = False
            self.color = color_rgb(randrange(255), randrange(255), randrange(255))
            self.ball.setFill(self.color)

        elif "space" not in keys and not self.firstPress:
            self.firstPress = True


    def update(self):
        self.ball.undraw()
        self.changeColorWithMouse()
        #self.changeColorWithKeys()
        self.ball.draw(self.window)


ball = Ball(window)

while not window.closed:
    ball.update()
    window.update()
    sleep(0.01)
