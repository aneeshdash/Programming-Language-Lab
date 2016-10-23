%base case for addition
plus([],List,List):-!.

%recursion for addition
plus([Head|Tail],List2,[Head|Result]):-
	plus(Tail,List2,Result).


%subtracting anything from 0
minus([],List,[]):-!.

%subtracting 0 base case 
minus([Head|Tail],[],[Head|Tail]):-!.

%recursion for subtraction
minus([Head|Tail],[Head1|Tail1],Result):-
	minus(Tail,Tail1,Result).


%add [x] for successor
successor(List,Result):-
	plus(List,[x],Result).



%multiplication by 0
multiply([],List,[]):-!.
multiply(List,[],[]):-!.

%multiplication by 1 (base case)
multiply([Head|[]],List,List):-!.
multiply(List,[Head|[]],List):-!.

%recursion for multiplication
multiply([Head|Tail],List2,Result):-
	multiply(Tail,List2,X),
	plus(X,List2,Result).


%length of empty array is 0
len([],0):-!.


%recurssion for calculating length
len([Head|Tail],Result):-
	len(Tail,X),
	Result is X+1.


% divide by 1
divide(List,[x]):-!.

%divide by number itself
divide(List,List):-
	len(List,Z),
	Z > 0.


%recursion for divide
divide(List,List2):-
	len(List2,Z),
	Z > 1,
	minus(List,List2,Result),
	len(Result,X),
	len(List2,Y),
	X > Y,
	divide(Result,List2).


% base case for division
divide(List,List2):-
	len(List2,Z),
	Z > 1,
	minus(List,List2,Result),
	len(Result,X),
	len(List2,Y),
	X == Y.

