#Location class:

##of Method:

Location is null throws IllegalArgumentException (structured basis, bad data, boundary)

Priority is negative throws IllegalArgumentException (structured basis, bad data, boundary)

Start time is negative throws IllegalArgumentException (structured basis, bad data, boundary)

Start time is 0 throws IllegalArgumentException (structured basis, bad data, boundary)

End time is negative throws IllegalArgumentException (structured basis, bad data, boundary)

End time is 0 throws IllegalArgumentException (structured basis, bad data, boundary)

Start time greater than end time throws IllegalArgumentException (structured basis, bad data, boundary)

Start time equal to end time throws IllegalArgumentException (structured basis, bad data, boundary)

Valid parameters, good data (structured basis, good data)


#Scheduler class:

##optimalLocations Method:

Valid parameters, example from class (structured basis, good data)

Only one location (structured basis, good data)

Empty list of locations throws IllegalArgumentException (structured basis, bad data)

##seedPredecessors Method:

Empty list of locations (structured basis, bad data)

Nominal case (structured basis, good data)

#predecessor Method:

Index is 0 (structured basis)

Index is 1 (structured basis)

first tested Location is valid (structured basis, boundary)

first tested Location is invalid (boundary)

None of the tested Locations are valid (structured basis)

#validPredecessor Method:

true case (structured basis)

false case where times are equal (branch coverage)

nominal false case (branch coverage)

#seedMaxPriorityScore Method:

Empty list of locations (structured basis, bad data)

2 Locations where both are included (structured basis)

2 Locations where 1st is included and 2nd isn't (branch coverage)

2 Locations where 1st isn't included and 2nd is (branch coverage)

#calculateOptimalSchedule Method:

Last Location is not included (structured basis)

Last Location is included (structured basis)

Last Location is not included (branch coverage)

List of Locations is empty (bad data)

Single Location (good data)
