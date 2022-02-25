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
```
    @Test
    public void snip1() throws IOException {
        String contents= Files.readString(Path.of("./snip1.md"));
        List<String> expect = List.of("'google.com", "google.com", "ucsd.edu");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
```

Result from my code: ![](/img/report4_snip1_mine.png)

Result from the code I reviewed: ![](/img/report4_snip1_reviewed.png)

### Snippet 2

#### Preview for Snippet 2

![](/img/report4_2.png)

Where:

`nested link` points to `a.com`

`a nested parenthesized url` points to `a.com(())`

`some escaped [ brckets ]` points to `example.com`.

So the result should be `[a.com, a.com(()), example.com]`.

#### Test for Snippet 2
```
    @Test
    public void snip2() throws IOException {
        String contents= Files.readString(Path.of("./snip2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
```

Result from my code: ![](/img/report4_snip2_mine.png)

Result from the code I reviewed: ![](/img/report4_snip2_reviewed.png)

### Snippet 3

#### Preview for Snippet 3

![](/img/report4_3.png)

Where:
`this title text is really long and takes up more than one line` points to `https://ucsd-cse15l-w22.github.io/`

So the result should be `[https://ucsd-cse15l-w22.github.io/]`.

#### Test for Snippet 3
```
    @Test
    public void snip3() {
        String contents= Files.readString(Path.of("./snip2.md"));
        List<String> expect = List.of("https://ucsd-cse15l-w22.github.io/");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
```

Result from my code: ![](/img/report4_snip3_mine.png)

Result from the code I reviewed: ![](/img/report4_snip3_reviewed.png)

## Answer to the three questions