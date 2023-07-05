# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: adi-stef <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2023/07/05 16:02:17 by adi-stef          #+#    #+#              #
#    Updated: 2023/07/05 16:09:29 by adi-stef         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

NAME	= Program

SRC		= $(wildcard *.java)
OBJ		= $(SRC:%.java=%.class)

CC		= javac
JFLAGS	= --release 8
RM		= rm -f
EXEC	= java

$(NAME): $(OBJ)
		@$(CC) $(JFLAGS) $(SRC)

all: $(NAME)

launch: $(NAME)
		@$(EXEC) $(NAME)

clean:
		@$(RM) $(OBJ)

fclean: clean

re: fclean all

.PHONY: all launch clean fclean re

