#!/bin/bash
echo "Enter basic salary: "
read bs

# Calculate DA (Dearness Allowance) and HRA (House Rent Allowance)
da=$(echo "$bs * 0.10" | bc)
hra=$(echo "$bs * 0.20" | bc)


# Calculate gross salary
gross=$(echo "$bs + $da + $hra" | bc)


echo "Basic Salary: $bs"
echo "Dearness Allowance (10% of Basic Salary): $da"
echo "House Rent Allowance (20% of Basic Salary): $hra"
echo "Gross Salary: $gross"

