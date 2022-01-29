## Change 1:

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


## Change 2:

#### Screenshot of the code change diff from Github:

---
#### Link to the test file for a failure-inducing input that prompted you to make that change: [Here](https://github.com/touhouEUROBEAT/markdown-parse/blob/main/test-file1.md)

---
#### Symptom: Does not distinguish normal use of []() and link. Terminal output below:

```
spaike97@localhost:~/Documents/UCSDw22/CSE15L/lab3/markdown-parse> java *.java test-file2.md
[shouldn't be here]
```

---
#### The underlying bug is that the program only looked for the first ( after ], and did not check what was in between these two symbols. 

Therefore, in the test-file2.md, although the two pairs of [] and () were clearly used for seperate purpose, the program still paired them together and considered it a link.

The problem is solved by introducing a rule that for a link to be considered, ( must follow immediately after ]/


### Change 3:

---
#### Screenshot of the code change diff from Github:

---
#### Link to the test file for a failure-inducing input that prompted you to make that change: [Here](https://github.com/touhouEUROBEAT/markdown-parse/blob/main/test-file3.md)

---
#### Symptom: Does not distinguish image and link. Terminal output below:

```
spaike97@localhost:~/Documents/UCSDw22/CSE15L/lab3/markdown-parse> java *.java test-file3.md
[Link and should be here, Image and shouldn't be here]
```

---
#### The underlying bug is that the program did not check whether [] is prefixed by !, and, therefore, treated image as links.

In test-file3.md, there exists a image tag of the fom \!\[\*](\*). The program only saw that it has "[]", and treated it as a link.

The problem is solved by introducing a check for whether "\[" is preceeded by "!".
