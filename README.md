# coinChange

The coin on test6 seems to expect that the change method returns an array of change AND that it
throws a RuntimeException with the accumulated change - I've assumed the latter in the implementation
of the ChangeMachine class.
