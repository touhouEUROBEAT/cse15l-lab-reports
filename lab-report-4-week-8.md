## Links

[Mine](https://github.com/touhouEUROBEAT/markdown-parse1)
[The one I reviewed](https://github.com/touhouEUROBEAT/CSE15L-TheLunaMoths)

## Tests
### Snippet 1

#### Preview for Snippet 1

![](/img/report4_1.png)

Where: 
`another lin'` points to `'google.com`
`code[e` points to `google.com`
`code]` points to `ucsd.edu`

So the result should be `['google.com, google.com, ucsd.edu]`.

#### Test for Snippet 1

Result from my code:

Result from the code I reviewed:

### Snippet 2

#### Preview for Snippet 2

![](/img/report4_2.png)

Where:
`nested link` points to `a.com`
`a nested parenthesized url` points to `a.com(())`
`some escaped [ brckets ]` points to `example.com`.

So the result should be `[a.com. a.com(()), example.com]`.

#### Test for Snippet 2

Result from my code:

Result from the code I reviewed:

### Snippet 3

#### Preview for Snippet 3

![](/img/report4_3.png)

Where:
`this title text is really long and takes up more than one line` points to `https://ucsd-cse15l-w22.github.io/`

So the result should be `[https://ucsd-cse15l-w22.github.io/]`.

#### Test for Snippet 3

Result from my code:

Result from the code I reviewed: