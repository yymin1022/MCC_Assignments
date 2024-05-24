CC		= gcc-14
CFLAGS	= -Wall -Werror -Wextra

TARGET	= prob2
SRC		= prob2.c
OBJ		= $(SRC:.c=.o)

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CC) $(CFLAGS) $^ -fopenmp -o $(TARGET)

%.o: %.c
	$(CC) $(CFLAGS) -fopenmp -c $^

clean:
	rm -rf $(OBJ)

fclean: clean
	rm -rf $(TARGET)

re: fclean all

.PHONY: $(TARGET) all clean fclean re