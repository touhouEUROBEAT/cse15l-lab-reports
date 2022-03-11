## Links to the codes

[My group](https://github.com/Aziiz0/markdown-parse-Fireflies)

[The one from lab 9](https://github.com/ucsd-cse15l-w22/markdown-parse)

## Tests
Since we would like to compare the difference in test results from these two codes, one way to do it is just to use bash script to run all tests on both, and redirect the output into two seperate files.

Then, we could use `diff` to compare them. This way may take a bit more labor, but we get to keep two original copies of the test reslults for future use, and we could also automate this with script if
this becomes a recurring task, so I think it's a good middle-ground approach.

Below is the code for `script.sh`. Notice I used `echo -n $file` to output the name of test-file for each test case so it's easier to locate.

``
for file in test-files/*.md;
do
  echo -n $file
  java MarkdownParse $file
done
``

We put this into the directory to the two versions of the code, and do:

`bash script.sh > result_(insert version name)`

Then:

`diff result_(name1) result_(name2)`

Below is the output.

```
104c104
< test-files/194.md[url]
---
> test-files/194.md[]
113c113
< test-files/201.md[baz]
---
> test-files/201.md[]
144c144
< test-files/22.md[]
---
> test-files/22.md[/bar\* "ti\*tle"]
255c255
< test-files/32.md[]
---
> test-files/32.md[/f&ouml;&ouml; "f&ouml;&ouml;"]
355c355
< test-files/41.md[]
---
> test-files/41.md[url &quot;tit&quot;]
423c423
< test-files/481.md[]
---
> test-files/481.md[/uri "title"]
429,431c429,432
< test-files/487.md[]
< test-files/488.md[]
< test-files/489.md[]
---
> test-files/487.md[/my uri]
> test-files/488.md[</my uri>]
> test-files/489.md[foo
> bar]
433c434,435
< test-files/490.md[]
---
> test-files/490.md[<foo
> bar>]
438,441c440,443
< test-files/495.md[foo(and(bar))]
< test-files/496.md[]
< test-files/497.md[]
< test-files/498.md[]
---
> test-files/495.md[foo(and(bar)]
> test-files/496.md[foo(and(bar)]
> test-files/497.md[foo\(and\(bar\)]
> test-files/498.md[<foo(and(bar]
449,450c451,452
< test-files/504.md[]
< test-files/505.md[]
---
> test-files/504.md[/url "title", /url 'title', /url (title)]
> test-files/505.md[/url "title \"&quot;"]
452,454c454,457
< test-files/507.md[]
< test-files/508.md[]
< test-files/509.md[]
---
> test-files/507.md[/url "title "and" title"]
> test-files/508.md[/url 'title "and" title']
> test-files/509.md[   /uri
>   "title"  ]
456c459
< test-files/510.md[/uri]
---
> test-files/510.md[]
518c521
< test-files/567.md[]
---
> test-files/567.md[not a link]
523c526
< test-files/571.md[]
---
> test-files/571.md[/url "title"]
530c533
< test-files/578.md[]
---
> test-files/578.md[/path/to/train.jpg  "title"   ]
```

Plenty o difference as we can see. I'll take two test cases, `567.md` and `571.md`.

## 567.md: What went wrong and why?

Below is the content of `567.md`

```
[foo](not a link)

[foo]: /url1
```

and below is the preview from `https://spec.commonmark.org/dingus/`

![](/img/report5_1.png)

where foo points to `https://spec.commonmark.org/url1`

### Who is right?

Both are incorrect.

This seems kind of weird, intuitively, the one from week 9 is correct in that there are no links, but dingus is telling us that there actually is one.

Not just that, as we can see, dingus first gave us the link from the second `[foo]`, and printed out the message after the first `[foo]`, as if two `[foo]` got merged together.

### Why is that.

After some tinkering, I'm convinced that this is indeed the case:

If we change the content of test-file to

```
[foo1](not a link)

[foo]: /url1
```

the output becomes

```
[foo1](not a link)
```

and if we change the content to

```
[foo](not a link)

[foo1]: /url1
```

then the output becomes

```
[foo](not a link)
```

So it seems that the statement `[something]: \url` works like standardized formating in `printf()` in C/C++, where:

If you have both a `[something]: \url` and `[something](not a link)`, it merges two `[something]`, uses `\url` as a link with tag `something`, and then prints out `(not a link)` (order of two statements
does not matter).

If only `[something]: \url` presents, and there are no `[something](not a link)`, then `[something]: \url` gets ignored.

If we have both `[something]: \url` and `[something](a valid link)`, both are treated as link normally.

### A fix:

Since both are incorrect, I'll choose to take a look at our code. It's apparent we were not aware of this feature at all, as we did not implement anything to check this case at all. To fix this, I think the whole
code needs to be re-written, but since lab report requires me to show where specifically it is wrong, and it's impractical to include the entire code here, below is the core of the problem, starting at `line 59`:

```

            if (openParen-nextCloseBracket == 1) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
```

There are two things wrong here. First, we didn't check what's inside the `()` to see if it's a valid link, and two, we didn't check if there's a `[something]: \url` that we could merge with if what's inside the `()` is
not a valid link.

So what we need to do is, first, after finding the first `[]` pair, we need to check if what follows is of the form `: \url`. If it is, we need to store that in a HashMap, with `[]` as key. We also need a method to check whether what's
between `()` is a valid link. If not, we need to store that in the same HashMap with `[something]` as key.

Whenever we see that the HashMap contains key `[something]`, instead of storing `: \url` or `(not a link)`, we should merge these two, add it to `toReturn`, and remove `[something]` from the HashMap.

## 571.md: What went wrong and why?

Below is the content of `567.md`

```
![foo](/url "title")
```

and below is the preview from `https://spec.commonmark.org/dingus/`

![](/img/report5_2.png)

### Who is right?

As we can see, this is an image and should not be recognized as a link, so our implementation is wrong, and the one from week 9 is correct.

### Why is that.

Upon reviewing our code, I think the problem is here, starting at `line 38`:

```
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket == -1) break;
```

We just assumed that if there's a "[", it'd be the start of a valid link.

### A fix:

We should add in a `else if` statement below to check whether `nextOpenBracket == 0`, or `markdown.charAt(nextOpenBracket - 1) == '!'`.

If either of the two happens, then this `[` is just the start of a image, not a link, and we should immediately `break`.
