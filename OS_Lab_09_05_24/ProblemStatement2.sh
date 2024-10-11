#!/bin/bash
echo "Enter a number:"
read num
is_prime=1

if [ $num -lt 2 ]; then
	is_prime=0
fi

for ((i=2; i*i<=num; i++))
do
	if [ $((num % i)) -eq 0 ]; then
		is_prime=0
		break
	fi
done

if [ $is_prime -eq 1 ]; then
	echo "$num is a prime number."
else
	echo "$num is not a prime."
fi