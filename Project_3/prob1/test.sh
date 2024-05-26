#!/bin/sh
echo "### Multi Core Computine Project #3 Prob #1 Tester ###"
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

echo "> Testing Static Default"
echo
for i in 1 2 4 6 8 10 12 14 16
do
	echo "Thread Count is $i"
	./prob1 1 $i
	echo
done
echo
echo "> Static Default Test Done..!"

echo
echo "##########"
echo

echo "> Testing Dynamic Default"
echo
for i in 1 2 4 6 8 10 12 14 16
do
	echo "Thread Count is $i"
	./prob1 2 $i
	echo
done
echo
echo "> Dynamic Default Test Done..!"

echo
echo "##########"
echo

echo "> Testing Static Chunk 10"
echo
for i in 1 2 4 6 8 10 12 14 16
do
	echo "Thread Count is $i"
	./prob1 3 $i
	echo
done
echo
echo "> Static Chunk 10 Test Done..!"

echo
echo "##########"
echo

echo "> Testing Dynamic Chunk 10"
echo
for i in 1 2 4 6 8 10 12 14 16
do
	echo "Thread Count is $i"
	./prob1 4 $i
	echo
done
echo
echo "> Dynamic Chunk 10 Test Done..!"

