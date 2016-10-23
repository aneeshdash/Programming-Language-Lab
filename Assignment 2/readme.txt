%%First Question

Plus predicate returns the second value if first value is zero. If the first value is non zero then it adds one to the result returned but sum of second value and first value decremented by one.
Minus predicate returns the first value if second value is zero. Else it removes one element from first and second value each and continues.
Multiply predicate adds second value to the result returned by multiplication of second number with first number decremented by one.
Divide predicate subtracts the second element from the first element until first element is less than or equal to second value. If they are equal then the first number is divisible by the second else it is not divisible.

%%Second Question

Recur predicate checks for the current command and performs corresponding operations
Move_x and Move_y predicate changes the x and y co-ordinate respectively given the facing direction
Get_orientation predicate returns the direction given the code for direction
left_orientate and right_orientate rotates left and right respectively and returns the new direction
Get_movement predicate compares the final and current co-ordiantes and decides the next step

2. a) status predicate initializes the values, takes the operations as input and passes it to recur predicate. Recur reads the first operation and moves or rotates accordingly and then performs recursively on the rest of the operations.

2. b) movement predicate takes the final position, current position and current orientation as input and passes it to get_movement predicate. Get_movement predicate check the direction and current and final position. If we can move in the facing direction then we move, add a move operation, else we check if we need to rotate right or left, depending on the current and final co-ordinate of the perpendicular axis. Repeat this until the current position is same as final position.

%% Third question

Board predicate  returns the list of pizza predicates.
Pizza predicate is defiend by four items which are name of the pizza ,veg topping on the pizza,non-veg topping on the pizza and price of the pizza.
The veg and non-veg ingredients given are stored in three lists
mainly the member function are used to impse restrictions to find the variables in the pizza predicate.
A member function construct of prolog which returns true if the first argument is present in the list provided as second argument
