# Q1: Num Eights
def num_eights(n):
    """Returns the number of times 8 appears as a digit of n.

    >>> num_eights(3)
    0
    >>> num_eights(8)
    1
    >>> num_eights(88888888)
    8
    >>> num_eights(2638)
    1
    >>> num_eights(86380)
    2
    >>> num_eights(12345)
    0
    >>> num_eights(8782089)
    3
    """
    result = 0
    last_digit, remain_digit = n % 10, n // 10
    if last_digit == 8:
        result += 1
    if remain_digit == 0:
        return result
    return result + num_eights(remain_digit)


# Q2: Digit Distance
# For a given integer, the digit distance is the sum of the absolute differences between consecutive digits. For example:
# The digit distance of 61 is 5, as the absolute value of 6 - 1 is 5.
# The digit distance of 71253 is 12 (abs(7-1) + abs(1-2) + abs(2-5) + abs(5-3) = 6 + 1 + 3 + 2).
# The digit distance of 6 is 0 because there are no pairs of consecutive digits.
# Write a function that determines the digit distance of a positive integer. You must use recursion or the tests will fail.
def digit_distance(n):
    """Determines the digit distance of n.

    >>> digit_distance(3)
    0
    >>> digit_distance(777) # 0 + 0
    0
    >>> digit_distance(314) # 2 + 3
    5
    >>> digit_distance(31415926535) # 2 + 3 + 3 + 4 + ... + 2
    32
    >>> digit_distance(3464660003)  # 1 + 2 + 2 + 2 + ... + 3
    16
    """
    result = 0
    last_digit, remain_digit = n % 10, n // 10
    if remain_digit == 0:
        return result
    result = abs(remain_digit % 10 - last_digit)
    return result + digit_distance(remain_digit)


# Q3: Interleaved Sum
# Write a function interleaved_sum, which takes in a number n and two one-argument functions: odd_func and even_func. It applies odd_func to every odd number and even_func to every even number from 1 to n inclusive and returns the sum.
# For example, executing interleaved_sum(5, lambda x: x, lambda x: x * x) returns 1 + 2*2 + 3 + 4*4 + 5 = 29.
# Important: Implement this function without using any loops or directly testing if a number is odd or even (no using %). Instead of directly checking whether a number is even or odd, start with 1, which you know is an odd number.
# Hint: Introduce an inner helper function that takes an odd number k and computes an interleaved sum from k to n (including n).
def interleaved_sum(n, odd_func, even_func):
    """Compute the sum odd_func(1) + even_func(2) + odd_func(3) + ..., up
    to n.

    >>> identity = lambda x: x
    >>> square = lambda x: x * x
    >>> triple = lambda x: x * 3
    >>> interleaved_sum(5, identity, square) # 1   + 2*2 + 3   + 4*4 + 5
    29
    >>> interleaved_sum(5, square, identity) # 1*1 + 2   + 3*3 + 4   + 5*5
    41
    >>> interleaved_sum(4, triple, square)   # 1*3 + 2*2 + 3*3 + 4*4
    32
    >>> interleaved_sum(4, square, triple)   # 1*1 + 2*3 + 3*3 + 4*3
    28
    """

    def helper(k, is_odd_turn):
        if k > n:
            return 0
        if is_odd_turn:
            return odd_func(k) + helper(k + 1, False)
        else:
            return even_func(k) + helper(k + 1, True)

    return helper(1, True)


# Q4: Count Dollars
def next_smaller_dollar(bill):
    """Returns the next smaller bill in order."""
    if bill == 100:
        return 50
    if bill == 50:
        return 20
    if bill == 20:
        return 10
    elif bill == 10:
        return 5
    elif bill == 5:
        return 1


def count_dollars(total):
    """Return the number of ways to make change.

    >>> count_dollars(15)  # 15 $1 bills, 10 $1 & 1 $5 bills, ... 1 $5 & 1 $10 bills
    6
    >>> count_dollars(10)  # 10 $1 bills, 5 $1 & 1 $5 bills, 2 $5 bills, 10 $1 bills
    4
    >>> count_dollars(20)  # 20 $1 bills, 15 $1 & $5 bills, ... 1 $20 bill
    10
    >>> count_dollars(45)  # How many ways to make change for 45 dollars?
    44
    >>> count_dollars(100) # How many ways to make change for 100 dollars?
    344
    >>> count_dollars(200) # How many ways to make change for 200 dollars?
    3274
    """
    def helper(total, bill):
        if total == 0:
            return 1
        if total < 0 or bill is None:
            return 0
        return helper(total - bill, bill) + helper(total, next_smaller_dollar(bill))
    return helper(total, 100)