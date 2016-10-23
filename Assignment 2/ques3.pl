board(L):-



% pizza is a object with name and 3 other variables
% L is the list of pizzas available
%first is non-veg topping ,second is veg topping and last one is price


L = [pizza(hawaiian, Tch, Tvh, Ph),
     pizza(marcopolo, Tcm, Tvm, Pm),
     pizza(pepperoni, Tcp, Tvp, Pp),
     pizza(supersupreme, Tcs, Tvs, Ps),
     pizza(ninja, Tcn, Tvn, Pn)],



% given Chicken topping list
Chicken_topping_list = [chicken ,mutton ,prawn ,salami,tuna],

% given veg topping list
Veg_topping_list = [onion, corn, olive, tomato ,pineapple],

%given price list
Price_list = [55,65,70,85,100],



% rules given for individual ingredients of various pizzas
Tch = mutton,

% member function tells that ph is a member of given array
member(Ph,[70,85,100]),

% equality gives value to variable
Tvm = tomato,
member(Tcm,[mutton,prawn,salami,tuna]),


% _ indicates it could be anything
member(pizza(_, chicken, _, 85), L),

Pp = 70,

member(Tvs,[onion,corn,olive,tomato]),

member(pizza(_, tuna, corn, X), L),
member(X, [55, 70, 85, 100]),

member(pizza(_,Y,olive,55),L),
member(Y,[chicken,mutton,prawn,tuna]),

member(pizza(_,_,pineapple,Z),L),
member(Z,[55,65,70,85]),



%atleast one pizza should have given non-veg topping
member(pizza(_, chicken, _, _), L),
member(pizza(_, mutton, _, _), L),
member(pizza(_, prawn, _, _), L),
member(pizza(_, salami, _, _), L),
member(pizza(_, tuna, _, _), L),


%atleast one pizza should have given veg topping
member(pizza(_, _, onion, _), L),
member(pizza(_, _, corn, _), L),
member(pizza(_, _, olive, _), L),
member(pizza(_, _, tomato, _), L),
member(pizza(_, _, pineapple, _), L),



% atleast one pizza should have one cost in price list
member(pizza(_, _, _, 55), L),
member(pizza(_, _, _, 65), L),
member(pizza(_, _, _, 70), L),
member(pizza(_, _, _, 85), L),
member(pizza(_, _, _, 100), L),



% Non-Veg topping should be from list of veg topping given
member(Tch,Chicken_topping_list),
member(Tcm,Chicken_topping_list),
member(Tcp,Chicken_topping_list),
member(Tcs,Chicken_topping_list),
member(Tcn,Chicken_topping_list),



% Veg topping should be from list of veg topping given
member(Tvh,Veg_topping_list),
member(Tvm,Veg_topping_list),
member(Tvp,Veg_topping_list),
member(Tvs,Veg_topping_list),
member(Tvn,Veg_topping_list),


% cost for pizzas should be from list of prices given
member(Ph,Price_list),
member(Pm,Price_list),
member(Pp,Price_list),
member(Ps,Price_list),
member(Pn,Price_list).



