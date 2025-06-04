from operator import add, sub
from doctest import run_docstring_examples

# Q1: A Plus Abs B
# Python's operator module contains two-argument functions such as add and sub for Python's built-in arithmetic operators. For example, add(2, 3) evalutes to 5, just like the expression 2 + 3.
# Fill in the blanks in the following function to add a to the absolute value of b, without calling the abs function. You may not modify any of the provided code other than the two blanks.
def a_plus_abs_b(a, b):
    """
    Return m*m + n*n, where m and n are the two smallest members of the
    positive numbers i, j, and k.
    >>> a_plus_abs_b(2, 3)
    5
    >>> a_plus_abs_b(2, -3)
    5
    >>> a_plus_abs_b(-1, 4)
    3
    >>> a_plus_abs_b(-1, -4)
    3
    """
    if b < 0:
        return add(a, sub(0, b))
    else:
        return add(a, b)


# Q2: Two of Three
# Write a function that takes three positive numbers as arguments and returns the sum of the squares of the two smallest numbers. Use only a single line for the body of the function.
# positive numbers i, j, and k.
def two_of_three(i, j, k):
    """Return m*m + n*n, where m and n are the two smallest members of the
    positive numbers i, j, and k.
    >>> two_of_three(1, 2, 3)
    5
    >>> two_of_three(5, 3, 1)
    10
    >>> two_of_three(10, 2, 8)
    68
    >>> two_of_three(5, 5, 5)
    50
    """
    return sorted([i, j, k])[:2][0] ** 2 + sorted([i, j, k])[:2][1] ** 2


# Q3: Largest Factor
# Write a function that takes an integer n that is greater than 1 and returns the largest integer that is smaller than n and evenly divides n.
def largest_factor(n):
    """Return the largest factor of n that is smaller than n.
    >>> largest_factor(15) # factors are 1, 3, 5
    5
    >>> largest_factor(80) # factors are 1, 2, 4, 5, 8, 10, 16, 20, 40
    40
    >>> largest_factor(13) # factor is 1 since 13 is prime
    1"""
    remainder = 1
    factor = n - 1
    while remainder != 0:
        remainder = n % factor
        if remainder != 0:
            factor -= 1
    return factor


# Q4: Hailstone
# Douglas Hofstadter's Pulitzer-prize-winning book, Gödel, Escher, Bach, poses the following mathematical puzzle.
# Pick a positive integer n as the start.
# If n is even, divide it by 2.
# If n is odd, multiply it by 3 and add 1.
# Continue this process until n is 1.
# The number n will travel up and down but eventually end at 1 (at least for all numbers that have ever been tried -- nobody has ever proved that the sequence will terminate). Analogously, a hailstone travels up and down in the atmosphere before eventually landing on earth.
# This sequence of values of n is often called a Hailstone sequence. Write a function that takes a single argument with formal parameter name n, prints out the hailstone sequence starting at n, and returns the number of steps in the sequence:
def hailstone(n):
    """Print the hailstone sequence starting at n and return its
    length.

        >>> a = hailstone(10)
        10
        5
        16
        8
        4
        2
        1
        >>> a
        7
        >>> b = hailstone(1)
        1
        >>> b
        1
    """
    print(n)
    count = 1
    while n != 1:
        count += 1
        if n % 2 == 0:
            n = n // 2
            print(n)
        else:
            n = 3 * n + 1
            print(n)
    return count


run_docstring_examples(a_plus_abs_b, globals(), True)
run_docstring_examples(two_of_three, globals(), True)
run_docstring_examples(largest_factor, globals(), True)