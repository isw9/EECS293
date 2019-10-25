# optimalLocations

| Symbol | Goal | Notes | Condition |
| ----------- | ----------- | ----------- | ----------- |
| CC | code coverage | all conditions true | locations is not empty |
| B1 | branch | empty check true | locations is empty |


| Test Condition | Conditions Satisfied | Assertions |
| ----------- | ----------- | ----------- |
| locations is not empty | CC | optimalLocations is correct |
| locations is empty | B1 | IllegalArgumentException is thrown |


# seedPredecessors

| Symbol | Goal | Notes | Condition |
| ----------- | ----------- | ----------- | ----------- |
| CC | code coverage | all conditions true | predecessors.length > i |
| b1 | boundary | first for loop | predecessors.length = i |
| b2 | boundary | first for loop |  predecessors.length < i (impossible)|

| Test Condition | Conditions Satisfied | Assertions |
| ----------- | ----------- | ----------- |
| locations is empty | b1 | array of size 1 with value 0 is returned |
| locations is not empty | CC | predecessors array is seeded correctly |

# predecessor

| Symbol | Goal | Notes | Condition |
| ----------- | ----------- | ----------- | ----------- |
| CC | code coverage | all conditions true | currentLocationIndex > 1, validPredecessor exists |
| B1 | branch | first if statement | currentLocationIndex = 0 |
| B2 | branch | first if statement |  currentLocationIndex = 1 |
| b1 | boundary | first for loop | i > 0 |
| b2 | boundary | first for loop |  i = 0 |
| b3 | boundary | first for loop |  i < 0 (impossible)|
| B3 | branch | validPredecessor check | validPredecessor exists |
| B4 | branch | validPredecessor check | validPredecessor does not exist |

| Test Condition | Conditions Satisfied | Assertions |
| ----------- | ----------- | ----------- |
| 1st Location is not valid, 2nd Location is valid | CC, b1, b2, B3, B4 | proper index is returned |
| currentLocationIndex = 0 | B1 | 0 is returned |
| currentLocationIndex = 1 | B2 | 0 is returned |

# validPredecessor

| Symbol | Goal | Notes | Condition |
| ----------- | ----------- | ----------- | ----------- |
| CC | code coverage | all conditions true | potentialPredecessor < location |
| b1 | boundary | only statement of method | potentialPredecessor = location |
| b2 | boundary | only statement of method  |  potentialPredecessor > location |

| Test Condition | Conditions Satisfied | Assertions |
| ----------- | ----------- | ----------- |
| potentialPredecessor < location | CC | true is returned |
| potentialPredecessor = location | b1 | false is returned |
| potentialPredecessor > location | b2 | false is returned |

# seedMaxPriorityScore

| Symbol | Goal | Notes | Condition |
| ----------- | ----------- | ----------- | ----------- |
| CC | code coverage | all conditions true | locations.size() > i |
| b1 | boundary | first for loop | locations.size() = i |
| b2 | boundary | first for loop |  locations.size() < i (impossible)|

| Test Condition | Conditions Satisfied | Assertions |
| ----------- | ----------- | ----------- |
| locations is empty | b1 | array of size 1 with value 0 is returned |
| locations is not empty | CC | maxPriorityScore array is seeded correctly |

# calculateOptimalSchedule

| Symbol | Goal | Notes | Condition |
| ----------- | ----------- | ----------- | ----------- |
| CC | code coverage | all conditions true | i > 0, both if statements are true |
| b1 | boundary | first for loop | i > 0 |
| b2 | boundary | first for loop |  i = 0 |
| b3 | boundary | first for loop | i < 0 |
| B1 | branch | first if statement | Location not included |
| B2 | branch | second if statement |  Location is included |

| Test Condition | Conditions Satisfied | Assertions |
| ----------- | ----------- | ----------- |
| locations is empty, 1 Location included, 1 Location not included | CC, b1, b2, B1, B2 | includes Location returned |
| locations is empty | b3 | returns an empty list |
