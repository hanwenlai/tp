package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.RecordContainsKeywordsPredicate;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORDS;

/**
 * Finds and lists all records for a specific patient whose records contain any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindRCommand extends Command {

    public static final String COMMAND_WORD = "findR";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds records for a specific patient "
            + "with a description that matches the keyword(s) specified.\n"
            + "Parameters: PATIENT_INDEX "
            + PREFIX_KEYWORDS + "KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_KEYWORDS + "covid";

    public static final String MESSAGE_SUCCESS = "Here is the list of matching records for this patient:\n";

    private final Index index;

    private final RecordContainsKeywordsPredicate predicate;

    public FindRCommand(Index index, RecordContainsKeywordsPredicate predicate) {
        this.index = index;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFindRecords = lastShownList.get(index.getZeroBased());

        String matchingRecords = personToFindRecords.getRecordList()
                .getFilteredRecordList(predicate)
                .stringifyRecordList();

        System.out.println(MESSAGE_SUCCESS + matchingRecords);

        return new CommandResult(MESSAGE_SUCCESS + matchingRecords);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindRCommand)) {
            return false;
        }

        // state check
        FindRCommand f = (FindRCommand) other;
        return index.equals(f.index)
                && predicate.equals(f.predicate);
    }
}
