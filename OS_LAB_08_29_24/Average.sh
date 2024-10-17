#!/bin/bash

echo "Enter the number of terms: "
read n


sum=0


echo "Enter the numbers: "
for ((i=0; i<n; i++))
do
	read num
	sum=$((sum + num))
done

average=$(echo "scale=2; $sum / $n" | bc)

echo "The average is: $average"
