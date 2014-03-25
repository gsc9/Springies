Springies README
=========

CompSci 308 (Software Design & Implementation)

Group Members: Grace Chen, Shenghan Chen

Started 1/27/14

Finished 2/11/14

Hours: beyond count.

Division of workload:
=====
Grace: forces (except center of mass), environment parser, keyboard listener, 
	Springies class, initial code for objects classes, refactoring/making sure
	global variables were private

Shenghan: objects, object parser, keyboard listener, mouse listener, center of mass,
	refactoring

**Our final push was late because Grace realized that the ObjectParser class was full 
of public global variables, and went back into to the code to fix that. The Assembly
class was also modified because it referenced several of the variables in 
ObjectParser. This was done with permission from Professor Duvall. 

Sources Consulted:
=====

Box2D manual: http://box2d.org/manual.pdf (for a more general idea of what goes on)

Soda E-book: http://www.cs4fn.org/alife/images/sodamathspaperfull.pdf

JGame documentation: http://www.13thmonkey.org/~boris/jgame/JGame/javadoc/overview
	-summary.html

Java Docs for certain features (i.e. KeyEvent)

Piazza

TA (Grant Oakley)

Files Required to Start the Prject:
=====

Main.java, default package

Files Required to Test Project:
=====

N/A

Data/Resource Files Required:
=====

Assembly and environment .xml files were moved from the assets folder to the parent
directory, because Shenghan uses a Mac and Grace uses a Windows laptop, and calling
files in the assets folder is coded differently for the two computers.

Information About Using the Program:
=====

Should be pretty straight-forward. The status of each force (i.e. whether it is 
active or not) is printed at the top left corner of the screen.

Known Bugs/Crashes/Etc. Problems:
=====

There is a finite number of masses that can be added to the simulation;
The file chooser will work on Shenghan's computer, but not on Grace's.

Extra Features:
=====

During the simulation, user should press B to create a new ball that bounces around
the screen. (Grace included this to test forces.)

Impressions:
=====

We believe we should have attempted to do more pairs programming, because 
having to incorporate my partner's code without him physically present to explain it
was difficult. Also, we thought we should have either had one person handle both 
forces and objects, or we should have done them both jointly, because getting forces 
to apply to objects requires thorough knowledge of the code for both.
