#!/bin/bash
echo "Enter a number from 1 to 7:"
read num

case $num in
	1) echo "Sunday";;
	2) echo "Monday";;
	3) echo "Tuesday";;
	4) echo "Wednesday";;
	5) echo "Thrusday";;
	6) echo "Friday";;
	7) echo "Saturday";;
	*) echo "Invalid input. Please enter a number between 1 and 7";;
esac
