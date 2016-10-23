% get encoding for direction
get_orientation(0,north).
get_orientation(1,east).
get_orientation(2,south).
get_orientation(3,west).

%rotate left and get new direction
left_orientate(X,Result):-
	X < 4,
	X > -1,
	Y is X-1,
	Result is Y mod 4.

%rotate right and get new direction
right_orientate(X,Result):-
	X < 4,
	X > -1,
	Y is X+1,
	Result is Y mod 4.

%move in x axis corresponding to facing direction
move_x(X,0,Result):-
	Result is X.

move_x(X,1,Result):-
	Result is X+1.

move_x(X,2,Result):-
	Result is X.

move_x(X,3,Result):-
	Result is X-1.

%move in y axis corresponding to facing direction
move_y(Y,0,Result):-
	Result is Y+1.

move_y(Y,1,Result):-
	Result is Y.

move_y(Y,2,Result):-
	Result is Y-1.

move_y(Y,3,Result):-
	Result is Y .

%when all commands executed
recur([],X_cod,Y_cod,Ort,X_cod,Y_cod,Ort).

%execute a move command
recur([move|Tail],X_cod,Y_cod,Ort,X,Y,O):-
	move_x(X_cod,Ort,Z),
	move_y(Y_cod,Ort,W),
	recur(Tail,Z,W,Ort,X,Y,O).

%left rotate command
recur([left|Tail],X_cod,Y_cod,Ort,X,Y,O):-
	left_orientate(Ort,Z),
	recur(Tail,X_cod,Y_cod,Z,X,Y,O).

%rotate right command
recur([right|Tail],X_cod,Y_cod,Ort,X,Y,O):-
	right_orientate(Ort,Z),
	recur(Tail,X_cod,Y_cod,Z,X,Y,O).

%get final position and Orientation after executing the commands given in List
status(List,(X,Y),Orientation):-
	X_cod = 0,
	Y_cod = 0,
	Ort = 1,
	recur(List,X_cod,Y_cod,Ort,X,Y,O),
	get_orientation(O,Orientation).


%generate movement list to reach from Cur co-ordinates to Fin co-ordinates
movement((Cur_X,Cur_Y),O, (Fin_X,Fin_Y)):-
	get_movement(Cur_X,Cur_Y,O,Fin_X,Fin_Y,Result),
	write(Result).

%no movement required if already in same position
get_movement(Cur_X,Cur_Y,O,Cur_X,Cur_Y,[]).

%NORTH
%move in north if we still move towards north
get_movement(Cur_X,Cur_Y,north,Fin_X,Fin_Y, [move|Result]):-
	Fin_Y > Cur_Y,
	Y is Cur_Y+1,
	get_movement(Cur_X,Y,north,Fin_X,Fin_Y,Result).

%rotate right if we need to move towards east
get_movement(Cur_X,Cur_Y,north,Fin_X,Fin_Y,[right|Result]):-
	Fin_Y =< Cur_Y,
	Fin_X > Cur_X,
	get_movement(Cur_X,Cur_Y,east,Fin_X,Fin_Y,Result).

%rotate left if we need to move towards west
get_movement(Cur_X,Cur_Y,north,Fin_X,Fin_Y,[left|Result]):-
	Fin_Y =< Cur_Y,
	Fin_X < Cur_X,
	get_movement(Cur_X,Cur_Y,west,Fin_X,Fin_Y,Result).

%similar commands for other directions as for north

%SOUTH
get_movement(Cur_X,Cur_Y,south,Fin_X,Fin_Y,[move|Result]):-
	Fin_Y < Cur_Y,
	Y is Cur_Y-1,
	get_movement(Cur_X,Y,south,Fin_X,Fin_Y,Result).

get_movement(Cur_X,Cur_Y,south,Fin_X,Fin_Y,[left|Result]):-
	Fin_Y >= Cur_Y,
	Fin_X > Cur_X,
	get_movement(Cur_X,Cur_Y,east,Fin_X,Fin_Y,Result).

get_movement(Cur_X,Cur_Y,south,Fin_X,Fin_Y,[right|Result]):-
	Fin_Y >= Cur_Y,
	Fin_X < Cur_X,
	get_movement(Cur_X,Cur_Y,west,Fin_X,Fin_Y,Result).


%EAST
get_movement(Cur_X,Cur_Y,east,Fin_X,Fin_Y,[move|Result]):-
	Fin_X > Cur_X,
	X is Cur_X+1,
	get_movement(X,Cur_Y,east,Fin_X,Fin_Y,Result).

get_movement(Cur_X,Cur_Y,east,Fin_X,Fin_Y,[left|Result]):-
	Fin_X =< Cur_X,
	Fin_Y > Cur_Y,
	get_movement(Cur_X,Cur_Y,north,Fin_X,Fin_Y,Result).

get_movement(Cur_X,Cur_Y,east,Fin_X,Fin_Y,[right|Result]):-
	Fin_X =< Cur_X,
	Fin_Y < Cur_Y,
	get_movement(Cur_X,Cur_Y,south,Fin_X,Fin_Y,Result).


%WEST
get_movement(Cur_X,Cur_Y,west,Fin_X,Fin_Y,[move|Result]):-
	Fin_X < Cur_X,
	X is Cur_X-1,
	get_movement(X,Cur_Y,west,Fin_X,Fin_Y,Result).

get_movement(Cur_X,Cur_Y,west,Fin_X,Fin_Y,[right|Result]):-
	Fin_X >= Cur_X,
	Fin_Y > Cur_Y,
	get_movement(Cur_X,Cur_Y,north,Fin_X,Fin_Y,Result).

get_movement(Cur_X,Cur_Y,west,Fin_X,Fin_Y,[left|Result]):-
	Fin_X >= Cur_X,
	Fin_Y < Cur_Y,
	get_movement(Cur_X,Cur_Y,south,Fin_X,Fin_Y,Result).