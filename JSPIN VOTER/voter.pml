int hashmap[4];
int res=-1;


inline majority() {
	
	int i;
	
	/*
		We iterate all key/value pairs on hashmap
		and find out if any has majority
	*/
	for(i:0..3){
		if
			::hashmap[i]>3/2 ->
				/*
					After finding a value with majority,
					it is returned.
					No more evaluations are necessary.
				*/
				res=i;
				break;
			::else -> skip;
		fi;
	}
	printf("\n>>>Result is %d\n", res);
}

active proctype A() {
	int a, b, c, d;

	/*
		Since values are kept in a hashmap, we simulate
		one with 3 possible values, as indexes in an array:
		0 - webservice returned error
		1 - webservice returned a value
		2 - webservice returned another value
		3 - webservice returned YET ANOTHER value

		As only 3 webservice responses are evaluated,
		the resulting hashmap's values total must be
		3 or less (in case one or more webservices timed out)
	*/
	select(a : 0 .. (3));
	select(b : 0 .. (3-a));
	select(c : 0 .. (3-(a+b)));
	select(d : 0 .. (3-(a+b+c)));
	
	hashmap[0] = a;
	hashmap[1] = b;
	hashmap[2] = c;
	hashmap[3] = d;

	printf("Values for voter:\n%d errors\n%d wrong\n%d correct\n", hashmap[0], hashmap[1], hashmap[2]);
	
	/*
		Either all responses are in or 4 seconds have passed.
		Time for the voter to check which value has majority,
		if there is such.
	*/
	majority();

	/*
		Check whether the result achieved is correct
		through the use of assertions.
	*/
	if
		::hashmap[0]>3/2->
			//Too many webservices returned error
			assert(res == 0);
		::hashmap[1]>3/2->
			//Consensus achieved for value 1
			assert(res == 1);
		::hashmap[2]>3/2->
			//Consensus achieved for value 2
			assert(res == 2);
		::hashmap[3]>3/2->
			//Consensus achieved for value 3
			assert(res == 3);
		::else->
			//Consensus was not achieved
			assert(res == -1);
	fi
}
