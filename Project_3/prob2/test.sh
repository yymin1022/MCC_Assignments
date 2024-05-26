#!/bin/sh
echo "### Multi Core Computine Project #3 Prob #2 Tester ###"
echo

echo "> Cleaning Up Project..."
make fclean > /dev/null
echo "> Cleaning Done..!"

echo
echo "##########"
echo

echo "> Compiling Project..."
make all > /dev/null
echo "> Compile Done..!"

echo
echo "##########"
echo

echo "> Testing Static Threading"
echo
for i in 1 5 10 100
do
	echo "Chunk Size is $i"
	for j in 1 2 4 6 8 10 12 14 16
	do
		echo "Thread Count is $j"
		./prob2 1 $i $j
		echo
	done
	echo "----------"
done
echo
echo "> Static Threading Test Done..!"

echo
echo "##########"
echo

echo "> Testing Dynamic Threading"
echo
for i in 1 5 10 100
do
	echo "Chunk Size is $i"
	for j in 1 2 4 6 8 10 12 14 16
	do
		echo "Thread Count is $j"
		./prob2 1 $i $j
		echo
	done
	echo "----------"
done
echo
echo "> Dynamic Threading Test Done..!"

echo
echo "##########"
echo

echo "> Testing Guided Threading"
echo
for i in 1 5 10 100
do
	echo "Chunk Size is $i"
	for j in 1 2 4 6 8 10 12 14 16
	do
		echo "Thread Count is $j"
		./prob2 1 $i $j
		echo
	done
	echo "----------"
done
echo
echo "> Guided Threading Test Done..!"
