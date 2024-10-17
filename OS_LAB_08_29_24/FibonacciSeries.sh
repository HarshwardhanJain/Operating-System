#!/bin/bash
echo "Enter a number to find it's Fibonacci Series: "
read n


a=0
b=1


echo "Fibonacci Series:"
for((i=0; i<n; i++))
do
	echo -n "$a "
	fib=$((a + b))
	a=$b
	b=$fib
done
echo

