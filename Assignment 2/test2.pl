recursive(100).
recursive(X) :- add, Y is X + 1, recursive(Y).

add :- nb_getval(counter, C), CNew is C + 1, nb_setval(counter, CNew).

testRecursion
:-
    % Set counter to zero
    nb_setval(counter, 0),

    % Run some code with 'add'
    recursive(0), !,

    % Print the results
    nb_getval(counter, CounterValue),
    write('Steps: '), writeln(CounterValue).


t(X,Y,(X,Y)).