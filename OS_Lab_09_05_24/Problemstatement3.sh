#!/bin/bash
echo "Enter a number:"
read num
ori_num=$num
rev_num=0

while [ $num -ne 0 ]
do
	remainder=$((num % 10))
	rev_num=$((rev_num * 10 + remainder))
	num=$((num / 10))
done

if [ $ori_num -eq $rev_num ]; then
	echo "$ori_num is a palindrome."
else
	echo "$ori_num is not a palindrome."
fi
