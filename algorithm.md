# Algorithm

In my first read over the prompt for the question, I understand that there will be a list of choices I have to give the
user and that I have to code :)

> 0. Exit
> 1. Input Number
> 2. Reset Numbers
> 3. Print Numbers (Sorted)
> 4. Output mean
> 5. Output median
> 6. Output mode
> 7. Output Standard Deviation

This was my process going forward.

I finished the Client class, and the Main class where "all" I had to code left was the `switch` statement for which
option that I am choosing (look above).

## Case 1

Case 1 was by far the most difficult but still pretty easy. I made a method called `inputNumber()` it returns
a `boolean` that reflects if adding the input to the local data was successful or not. First it takes the input from the
user and splits it by every ", " (comma and space), then it maps it to integers from Strings. After that, the program
does some simple checks to see if it has enough room and if the input is less than 25. After that the program starts
copying down the info into the next squares. After that is done I run `.sort()` to sort the list using Insertion sort.

## Case 3

One thing to keep in mind is that the list is always sorted; At the end of every time somebody inputs numbers into out
list, it automatically sorts the list.

After Case 1 I tackled case 3 because it seemed pretty easy. It was! I use some Java 8 Stream API to filter out the
nonNull objects in my data then for each number in my list, I add it to a string that I am going to print with a ", ".
At the end I remove the trailing ", ". Then I return this string, and it is printed to show the sorted list.

## Case 2

Case two was very easy, I made a method called `reset` in the data class. All it did was go in a loop and turn every
value that it had changed to null. This is `reset()`:

```java
public boolean reset() {
        for (int i = 0; i < filled() + 1; i++) list[i] = null;
        return true;
}
```

You may be asking what that `filled()` method is. That is the rolling length of the array. This list we have now is a
primitive list therefore, everytime you want to know the length, the attribute is always 25 and never changes. It never
returns a value of how many numbers are actually ***in*** your list. I made the `filled()` it will return the index of
the last attribute in the list. To compare this with the `size()` function of an `ArrayList`, it would look like this:

```java
filled() + 1 = size();
```

So everytime you wanted to get the "length" or "size" of our list, I just substituted that with `filled() + 1`.

## Case 4

To find the mean all I did was:

```java
double mean = 0;
int length = filled() + 1;
for (int i = 0; i < length; i++) mean += list[i];
return mean / (double) length;
```

## Case 5

To find the median you have two cases, when the number is positive and negative. There is also a potential third case in
which the list is empty in which case there is no median. But when you have an even amount of elements in the array, you
just take an average of the middle two. When the length of the array is odd, you can just take the central element and
that is the median. In Java, this implementation looks like:

```java
int length = filled() + 1;
if (length % 2 == 0) return (double) (list[length / 2] + list[(length / 2) - 1]) / 2;
else return list[length / 2
```

## Case 6

Outputting mode was a little challenge. I resolved it by realizing something clever. All I had to do was make a nested
loop where I can see the occurrences for each number in the array, this way I can see who is the most. I save this info
to a Set, this way, the `HashSet` throws away all duplicate values for me. Because we have to account for multiple
modes, the code looks a little longer.

```java
HashSet<Integer> modes = new HashSet<>();
int highestOccurrences = 1;
int length = filled() + 1;
for (int i = 0; i < length; i++) {
    int localOccurrences = 0;
    for (int j = 0; j < length; j++) if (list[i].equals(list[j])) localOccurrences++;
    if (localOccurrences > highestOccurrences) {
        highestOccurrences = localOccurrences;
        modes.clear();
        modes.add(list[i]);
    } else if (localOccurrences == highestOccurrences && highestOccurrences > 1) modes.add(list[i]);
}
return modes;
```

## Case 7

Outputting standard deviation. This was easy I just used the equation for finding the standard deviation.

```java
double standardDeviation = 0;
for (int i = 0; i < filled() + 1; i++) standardDeviation += Math.pow((list[i] - mean()), 2);
return Math.pow(standardDeviation / (filled() + 1), 0.5);
```

# Insertion Sort

For the actual code for the sort, I used this:

```java
int length = filled() + 1;
for (int i = 1; i < length; i++) {
    int temp = list[i];
    int j = i - 1;
    while (j >= 0 && list[j] > temp) {
        list[j + 1] = list[j];
        j = j - 1;
    }
    list[j + 1] = temp;
}
```

Before using the value of `filled() + 1` I was actually using `filled() - 1` this caused a lot of troubleshooting but
was found later.