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

``
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
``

Plenty o difference as we can see. I'll take two test cases, `567.md` and `571.md`.

## 567.md What went wrong and why?

Below is the content of `567.md`

``
[foo](not a link)

[foo]: /url1
``

and below is the preview from `https://spec.commonmark.org/dingus/`

![](/img/report5_1.png)

where foo points to `https://spec.commonmark.org/url1`

## 571.md

Below is the content of `567.md`

``
![foo](/url "title")
``

and below is the preview from `https://spec.commonmark.org/dingus/`

![](/img/report5_2.png)
