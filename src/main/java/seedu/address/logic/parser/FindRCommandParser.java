package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.FindRCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RecordContainsKeywordsPredicate;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new FindRCommand object
 */
public class FindRCommandParser implements Parser<FindRCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindRCommand
     * and returns a FindRCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindRCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_KEYWORDS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_KEYWORDS).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRCommand.MESSAGE_USAGE));
        }

        String keywordsArgs = argMultimap.getValue(PREFIX_KEYWORDS).get();
        String trimmedArgs = keywordsArgs.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        return new FindRCommand(index, new RecordContainsKeywordsPredicate(Arrays.asList(keywords)));
    }
}
