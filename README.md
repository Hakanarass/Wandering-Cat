# Wandering-Cat
Simulate a randomly walking cat in a 2D discrete world.

This assignment simulates randomly walking cat in a 2D discrete world, one JavaFX figure, as shown in Figures. 
Firstly, my code reads all data from input text files for how many rows and column are there and keep all the data in temp array (2 dimensional). 
Code creates object multidimensional array based on these numbers and creates objects in the array. 
Program displays dimensional screen based on row and column; moreover, this program allows to create screen dimension based on the input.txt data. 
Therefore, when you change input.txt name, you do not have to change the screen size. 
After this creation, code matches all data with array of object. 
In “for” loop, code draws blue rectangles and white rectangles with Cell class variables according to 0 which means a cell of type Land, and 1 means a cell of type Sea. 
In other “for” loop, cat will move in my algorithm. 
This algorithm starts with generated random number between 1 and 8. 
In “if” statements, code checks random number and also check whether this row and column are inside the map. 
There is also one more “if” statements inside the previous “if” statements. 
This code checks the cell type whether is it 1 (Sea) or 0 (Land). 
If cell type is 0, cat goes to this cell and calls drawCircles method based on these row and column coordinates. 
When the number of steps increase, cat goes more lands arounds itself but cat cannot go island; therefore, it cannot visit all the lands in the map.
