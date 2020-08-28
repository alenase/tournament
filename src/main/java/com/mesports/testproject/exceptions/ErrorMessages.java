package com.mesports.testproject.exceptions;

public class ErrorMessages {
    public static final String NO_TOURNAMENT_FOUND = "Tournament with provided id is not found";
    public static final String NOT_ALLOWED_QUANTITY = "The provided quantity of participants is not allowed. " +
            "Please make sure that the provided quantity multiples of 8.";
    public static final String PARTICIPANT_IS_PRESENT = "The provided participant has already been added to the " +
            "tournament.";
    public static final String MATCH_IS_PRESENT = "The provided match already exists in the tournament.";
    public static final String NOT_ALLOWED_QUANTITY_OF_PARTICIPANTS = "You have reached the allowed limit of " +
            "participants for match";
    public static final String PARTICIPANT_ALREADY_ADDED_TO_MATCH = "The provided participant already assigned to the match";
    public static final String TOURNAMENT_HAS_BEEN_STARTED = "The routnament has been started so " +
            "it is not possible to make operations with participants";
}
