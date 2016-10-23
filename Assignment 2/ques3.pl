board(L):-

L = [pizza(hawaiian, Tch, Tvh, Ph),
     pizza(marcopolo, Tcm, Tvm, Pm),
     pizza(pepperoni, Tcp, Tvp, Pp),
     pizza(supersupreme, Tcs, Tvs, Ps),
     pizza(ninja, Tcn, Tvn, Pn)],



Chicken_topping_list = [chicken ,mutton ,prawn ,salami,tuna],

Veg_topping_list = [onion, corn, olive, tomato ,pineapple],

Price_list = [55,65,70,85,100],


Tch = mutton,
member(Ph,[70,85,100]),

Tvm = tomato,
member(Tcm,[mutton,prawn,salami,tuna]),

member(pizza(_, chicken, _, 85), L),

Pp = 70,

member(Tvs,[onion,corn,olive,tomato]),

member(pizza(_, tuna, corn, X), L),
member(X, [55, 70, 85, 100]),

member(pizza(_,Y,olive,55),L),
member(Y,[chicken,mutton,prawn,tuna]),

member(pizza(_,_,pineapple,Z),L),
member(Z,[55,65,70,85]),


member(pizza(_, chicken, _, _), L),
member(pizza(_, mutton, _, _), L),
member(pizza(_, prawn, _, _), L),
member(pizza(_, salami, _, _), L),
member(pizza(_, tuna, _, _), L),

member(pizza(_, _, onion, _), L),
member(pizza(_, _, corn, _), L),
member(pizza(_, _, olive, _), L),
member(pizza(_, _, tomato, _), L),
member(pizza(_, _, pineapple, _), L),

member(pizza(_, _, _, 55), L),
member(pizza(_, _, _, 65), L),
member(pizza(_, _, _, 70), L),
member(pizza(_, _, _, 85), L),
member(pizza(_, _, _, 100), L),


member(Tch,Chicken_topping_list),
member(Tcm,Chicken_topping_list),
member(Tcp,Chicken_topping_list),
member(Tcs,Chicken_topping_list),
member(Tcn,Chicken_topping_list),


member(Tvh,Veg_topping_list),
member(Tvm,Veg_topping_list),
member(Tvp,Veg_topping_list),
member(Tvs,Veg_topping_list),
member(Tvn,Veg_topping_list),

member(Ph,Price_list),
member(Pm,Price_list),
member(Pp,Price_list),
member(Ps,Price_list),
member(Pn,Price_list).



