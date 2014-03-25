Springies Design Document
=======

High-Level Design Goals:
=====

Besides the obvious goal of completing the assignment and writing code that runs and
behaves properly, we also aimed to make our code extensible and unified, to make
browsing it and extending it easier. We also did our best to document and/or
comment methods and variables as their complexities demanded. 

Adding New Features:
=====

To add a new force, one must simply go to the force package and create a new force
class that extends the abstract class Force. Then, one must go to either 
PhysicalObject or PhysicalObjectCircle to implement the force on PhysicalObjects.
PhysicalObject should be modified if the force affects walls,
PhsyicalObjectCircle should be modified if the walls are not to be affected because 
all of our objects are subclasses of Mass, which is a subclass of 
PhysicalObjectCircle.

Major Design Choices:
=====

We chose to make an inheritance hierarchy out of the forces, where each force except
wall repulsion extends an abstract superclass called Force. The superclass Force 
features a few convenience methods (i.e. mathematical/physical equations and 
conversions), a universal scaling value (which is now set to 1, so all magnitudes
and amplitudes should not be scaled to anything less/more than they are in their
respective xml files), and the declaration of an abstract method for applying a force
to a PhysicalObject passed in as a parameter.

We also used an inheritance hierarchy for the objects package. Mass is the superclass;
FixedMass and Spring are subclasses; and Muscle extends Spring. 

We chose not to make wall repulsion part of the Force inheritance hierarchy because
creating wall repulsion involved creating walls, and it seemed easier to extend 
PhysicalObjectRect to handle the creation/placement of walls and wall repulsion 
forces.

Pros of our design:
Forces are relatively easy to create.
Each force subclass has its own static boolean variable for whether it is activated
or not, which helps all objects on the screen behave similarly.

Cons of our design:
New forces must be implemented in either PhysicalObject or PhysicalObjectCircle,
which is decentralized and somewhat inconvenient.
Having a static boolean for each force subclass means that each class needs its own
method for turning the force on/off, which means repeated code.

Assumptions/Decisions for Simplification/Disambiguation of Project Functionality:
===== 

See major design choices