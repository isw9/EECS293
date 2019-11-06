# Error Handling Document

Does the program protect itself from bad input data?
Yes by using a Barricade pattern to clean any input data before it is used in the program.

Does the architecture or high-level design specify a specific set of error-handling techniques?
Yes, the main techniques that will be used are using local error handling and closest valid value.
An example of this is how the camera feeds are only supposed to display a 0 or 1 to indicate
the presence of a box or not. If a data value is corrupted to a 2 for example, I substitute in a 1 for it.

Does the architecture or high-level design specify a specific set of error-handling techniques?
Yes, this document does that.

Does the architecture or high-level design specify whether error handling should favor robustness or correctness?
While my first priority was to make sure my program was correct, I also wanted to make sure
that it was robust as well and could recover from a variety of different errors. To do this,
I try to anticipate the ways that input could be corrupted and try my best to substitute the closest
legal values and try to see if the input data would make any more sense. An example of this is
that the camera values are supposed to be 0 or 1. If a value has noise and becomes any digit from 2-9,
I substitute a 1 for it.

Have barricades been created to contain the damaging effect of errors and reduce the amount of code that has to be concerned about error process-ing?
I plan to use a Barricade pattern as discussed in class to "clean" any data that
is coming into my program. I am going to use a FileBarricade to make sure that everything goes
smoothly when reading from the input file. I am going to use an InputBarricade to make sure
that the actual contents of the input file are valid and can be read. The goal for this program
is to have strong robustness in addition to correctness.

Is the amount of defensive programming code appropriate—neither too much nor too little?
I believe so based on the architecture document I created.  

Has your project defined a standardized approach to exception handling?
Yes, I use a global enum value to indicate whether the alarm should be triggered or not. If there is an
exception my program can't recover from, I indicate an invalid enum type.

Is the error handled locally rather than throwing a nonlocal exception, if possible?
Yes, the majority of potential errors are handled locally

Is the code free of empty catch blocks? (Or if an empty catch block truly is appropriate, is it documented?)
Yes

Are all exceptions caught?
Yes

Do error messages avoid providing information that would help an attacker break into this system?
Yes, while still being useful to developers and users.



**Misc - Local vs Global**

I will handle the majority of my errors globally using an enum. After the input data
is fed through the Barricades, I use an AlarmDriver class to determine whether the
alarm should be triggered or not. The main function in this class returns an AlarmStatus
enum value. This enum value determines whether the alarm should be triggered or not. When I
say I am going to handle the majority of my errors locally, I mean that I will return an
AlarmStatus.TRIGGERED enum value whenever my program encounters an error it can't handle
using some of the techniques we discussed in class such as neutral value or closest legal value.
If I am unable to use these techniques in the Barricades, I also halt the program after the Barricades,
which is an example of offensive programming.
