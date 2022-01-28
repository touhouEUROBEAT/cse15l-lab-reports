### Change 1:

---
#### Screenshot of the code change diff from Github:

---
#### Link to the test file for a failure-inducing input that prompted you to make that change: [Here](https://github.com/touhouEUROBEAT/markdown-parse/blob/main/test-file1.md)

---
#### Symptom: Infinite loop when the test file is not empty and does not end with ")". Terminal output below:

```
spaike97@localhost:~/Documents/UCSDw22/CSE15L/lab3/markdown-parse> java *.java *.md
^Z
[1]+  Stopped                 java *.java *.md
```

---
#### The underlying bug is that the program assumed that the file must end with ")", when it's not neccesarily the case.

The condition for the while loop to end is currentIndex >= markdown.length(). Since currentIndex = closeParen + 1, unless markdown.length = 0 from the get go in which case we don't get into the loop at all, it is impossible to be out unless, at the very least, there exists a close parenthesis whose index = markdown.length() - 1. 

In the failure-inducing input, the file did not end with ")". As a result, the currentIndex was always smaller than markdown.length() - 1, causing the while to never end.

Bug is eliminated by letting program terminate prematurely if at least one of the []() is no present in the rest of the file.

---
### Change 2:

Show a screenshot of the code change diff from Github (a page like this)
Link to the test file for a failure-inducing input that prompted you to make that change
Show the symptom of that failure-inducing input by showing the output of running the file at the command line for the version where it was failing (this should also be in the commit message history)
Write 2-3 sentences describing the relationship between the bug, the symptom, and the failure-inducing input.

Change 3:

Show a screenshot of the code change diff from Github (a page like this)
Link to the test file for a failure-inducing input that prompted you to make that change
Show the symptom of that failure-inducing input by showing the output of running the file at the command line for the version where it was failing (this should also be in the commit message history)
Write 2-3 sentences describing the relationship between the bug, the symptom, and the failure-inducing input.
