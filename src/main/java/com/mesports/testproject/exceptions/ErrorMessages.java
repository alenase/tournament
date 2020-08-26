package com.mesports.testproject.exceptions;

public class ErrorMessages {
    public static final String NO_TOURNAMENT_FOUND = "Tournament with provided id is not found";
    public static final String NOT_ALLOWED_QUANTITY = "The provided quantity of participants is not allowed. " +
            "Please make sure that the provided quantity multiples of 8.";
    public static final String PARTICIPANT_IS_PRESENT = "The provided participant has already been added to the tournament.";
    public static final String MATCH_IS_PRESENT = "The provided match already exists in the tournament.";
}
