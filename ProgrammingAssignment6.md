# Isaac Withrow Assignment 6 EECS 293

1. **Define the problem**

  Input:
  List of start and end times for each location, as well as the priority for
  taking pictures at each location

  Preconditions:
  List of locations is sorted by end time.

  List of locations must not be empty

  Output:
  List of each location you should take pictures at to maximize the priority score,
  along with the start times and end times you will be at each location

  Postconditions:
  The returned list of locations maximizes the total priority score

2. **Name the routine**

  optimalLocations

3. **Error Handling**

  Assert that the inputted list of locations is not empty

4. **Libraries**

  n/a for this program

5. **Data**

  Input: List of Locations (sorted based on end time)
  Location: custom class with the following fields - start time,
  end time, priority score, place
  start time and end time will be expressed in minutes

6. **Abstractions/Classes**

The Location class has the following fields:
    start time - when pictures at this location start
    end time - when pictures at this location end
    priority score - how valuable pictures at this location are
    place - where the pictures take place
    Location's default sort will be based on end time

  The Schedule class contains the optimalLocationSchedule method.

  The predecessor(i, List of Locations) method is defined in the Schedule class. It's a private helper that calculates the index of the highest predecessor of i, where i is an index of one of the Locations in the list.



6. **Pseudocode**

  This problem is similar to the telescope scheduling problem. Let Pi be the
  maximum priority score we can get with the first i Locations in the input list.
  Let predecessor(i), j with (j < i) be the index of the Location
  with the latest end time that does NOT overlap with Location i. In the optimal
  schedule, either location Li is included or excluded.
  If Li is included in the optimal schedule, the maximum priority
  equals Location(i)'s priority + Location(predecessor(i))'s priority.
  If Li is excluded in the optimal schedule, the maximum priority equals
  Location(i - 1)'s priority.

  Assert that the list of inputted Locations is not empty.

  Initialize an empty list maxPriorityScore.

  Initialize a list predecessor such that predecessor[i] = predecessor(i) as defined above.

  Initialize an empty list of Locations optimalSchedule

  for each Location in the input list, inputList(i):
  maxPriorityScore[i] = max {maxPriorityScore[i-1], maxPriorityScore[predecessor[i]] + Location's priority}

  for each Location in input list (work backwards):

  if maxPriorityScore[i] == maxPriorityScore[i-1]:

    current Location is not included, do nothing

  else if maxPriorityScore[i] == maxPriorityScore[predecessor[i]] + ith Location's priority:

    add inputList[i] to optimalSchedule

    consider the predecessor[i]th location next

  other wise

    do nothing as the current Location is not included in the optimal schedule


  return optimalSchedule


7. **Run-time: O(n)**

  Uses memoization and dynamic programming to keep track of important information
  along the way. We only consider each Location twice (once when calculating the
  maximum score and again when figuring out the optimalSchedule) so the run time is
  O(n)


8. **Trace through example/Proof of Correctness**

  Input List:

  | Location | Start Time | End Time | Priority |
  | ----------- | ----------- | ----------- | ----------- |
  | Guardians of Transportation | 480 | 660 | 20 |
  | Tower city | 540 | 780 | 40 |
  | West Side Market | 720 | 840 | 40 |
  | Cleveland Museum of Arts | 600 | 1080 | 70 |
  | Severance Hall | 900 | 1020 | 20 |
  | Glennan Building | 960 | 1140 | 10 |

  First calculate the predecessors of each Location
  0th index is 0 automatically
  1st index corresponds with Guardians of Transportation etc
  `predecessor = {0, 0, 0, 1, 0, 3, 3}`

  Next go through each Location and calculate maxPriorityScore[i]

  maxPriorityScore[1 (Guardians of Transportation)] = max {0, 0 + 20}

  maxPriorityScore[1] = 20

  maxPriorityScore[2 (Tower City)] = max {20, 0 + 40}

  maxPriorityScore[2] = 40

  maxPriorityScore[3 (West Side Market)] = max {40, 20 + 40}

  maxPriorityScore[3] = 60

  maxPriorityScore[4 (Cleveland Museum of Arts)] = max {60, 0 + 70}

  maxPriorityScore[4] = 70

  maxPriorityScore[5 (Severance Hall)] = max{70, 60 + 20}

  maxPriorityScore[5] = 80

  maxPriorityScore[6 (Glennan Building)] = max {80, 60 + 10}

  maxPriorityScore = 80

  `maxPriorityScore = {0, 20, 40, 60, 70, 80, 80}`

  At this point, we know the max priority we can reach is 80. Now we need to
  figure out which Locations give us this priority score.

  Go through each Location (starting from the back):

  Does maxPriorityScore[6] = maxPriorityScore[5]?

  Yes so Glennan Building is not included

  Does maxPriorityScore[5] = maxPriorityScore[4]?

  No

  Does maxPriorityScore[5] = maxPriorityScore[predecessor[5]] + 5th Location's priority?

  Does 80 = 60 + 20?

  Yes so Severance Hall is included



  Now consider Location predecessor[5]

  Does maxPriorityScore[3] = maxPriorityScore[2]?

  No

  Does maxPriorityScore[3] = maxPriorityScore[predecessor[3]] + 3rd Location's priority?

  Does 60 = 20 + 40?

  Yes so West Side Market is included




  Now consider Location predecessor[3]

  Does maxPriorityScore[1] = maxPriorityScore[0]?

  No

  Does maxPriorityScore[1] = maxPriorityScore[predecessor[1]] + 1st Location's priority?

  Does 20 = 0 + 20?

  Yes so Guardians of Transportation is included

  Consider predecessor[1] next. This is 0 so the optimal schedule is built.

  The final optimal schedule is as follows:

  Severance Hall, West Side Market, Guardians of Transportation
