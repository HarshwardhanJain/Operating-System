#!/bin/bash

echo "Enter the number of terms for Fibonacci series:"
read n

if [ $n -eq 0 ]; then
    echo "Fibonacci series: 0"
    exit 0
elif [ $n -eq 1 ]; then
    echo "Fibonacci series: 0 1"
    exit 0
fi

a=0
b=1

echo "Fibonacci series:"
echo -n "$a $b "

for (( i=2; i<n; i++ ))
do
    fib=$((a + b))  # Calculate next Fibonacci number
    echo -n "$fib "
    a=$b
    b=$fib
done
echo
