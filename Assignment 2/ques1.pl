plus([],List,List):-!.

plus([Head|Tail],List2,[Head|Result]):-
	plus(Tail,List2,Result).


minus([],List,[]):-!.
minus([Head|Tail],[],[Head|Tail]):-!.
minus([Head|Tail],[Head1|Tail1],Result):-
	minus(Tail,Tail1,Result).

successor(List,Result):-
	plus(List,[x],Result).



multiply([],List,[]):-!.
multiply(List,[],[]):-!.
multiply([Head|[]],List,List):-!.
multiply(List,[Head|[]],List):-!.
multiply([Head|Tail],List2,Result):-
	multiply(Tail,List2,X),
	plus(X,List2,Result).


len([],0):-!.
len([Head|Tail],Result):-
	len(Tail,X),
	Result is X+1.

divide(List,[x]):-!.
divide(List,List):-
	len(List,Z),
	Z > 0.

divide(List,List2):-
	len(List2,Z),
	Z > 1,
	minus(List,List2,Result),
	len(Result,X),
	len(List2,Y),
	X > Y,
	divide(Result,List2).

divide(List,List2):-
	len(List2,Z),
	Z > 1,
	minus(List,List2,Result),
	len(Result,X),
	len(List2,Y),
	X == Y.

