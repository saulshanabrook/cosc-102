# Echo in Java
This program reproduces the linux command `echo` in Java.


```bash
$ java Echo Hey there
Hey there
$ java Echo Hello -t world -c happy to -u see -l YoU -n toDay -r -t --123fun
Hello World 5 2 SEE you toDay
-123Fun
```

Designed by Saul Shanabrook Catherine Walsh.

## Flags
It accepts certain flags that change the resaulting print behavior
of the next arguments.

```bash
$ java Echo Hey -u there
Hey THERE
```

It also accepts a few special flags to print newlines and
the `-` character itself.

```bash
$ java Echo --
-
$ java Echo hi -r there
hi
there
```

* `-n`: switch back to normal output
* `-u`: switch to upper case output
* `-l`: switch to lower case output
* `-t`: switch to title case output
* `--`: output `-`
* `-r`: output newline

## Corner Cases
If a flag is not defined, it will simply print the text
of that flag as a regular argument

```bash
$ java Echo -someweirdness
-someweirdness
```

If a double dash proceedes some text, it will be converted
into a dash and then the text will be printed.

```bash
$ java Echo --isnormal
-isnormal
```