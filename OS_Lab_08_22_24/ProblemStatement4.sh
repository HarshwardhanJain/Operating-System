#!/bin/bash


while true; do

	# Display the Menu
	echo "Menu:"
	echo "1. List of Files"
	echo "2. Process Status"
	echo "3. Date"
	echo "4. Users Currently Logged In"
	echo "5. Quit"

	# Read User Choice
	echo "Enter you choice [1-5]: "
	read ch

	# Execute the Corresponding Action
	case $ch in
		1)
			echo "List of files in the current directory:"
			ls -l
			;;
		2)
			echo "Process Status:"
			ps
			;;
		3)
			echo "Current date and time:"
			date
			;;
		4)
			echo "Users currently logged in:"
			# Why /who/ command not working?
			# Because Cygwin isn't a full linux OS-
			# it operates on top of windows.
			# Therefore, user session management is handled
			# differently compared to traditional Linux system.
			whoami
			;;
		5)
			echo "Exiting the program."
			break
			;;
		*)
			echo "Invalid option! Choose again."
			;;
	esac

	echo ""
done
