# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: adi-stef <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2023/07/05 16:02:17 by adi-stef          #+#    #+#              #
#    Updated: 2023/07/10 16:26:16 by adi-stef         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

NAME	= Program

SRC		= $(wildcard *java)

CC		= javac
JFLAGS	= --release 8
RM		= rm -f
EXEC	= java

$(NAME):
		@printf "Compiling..."
		@$(CC) $(JFLAGS) $(SRC)
		@printf "\r\033[K$(NAME) compiled successfully\n"

all: $(NAME)

launch: $(NAME)
		@printf "Executing $(NAME)...\n"
		@$(EXEC) $(NAME)

clean: 
		@printf "Removing object files"
		@$(shell $(RM) *class)
		@$(RM) result.txt
		@printf "\r\033[KObject files removed\n"

fclean: clean

re: fclean all

.PHONY: all launch clean fclean re

