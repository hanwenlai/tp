package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a record list in the address book.
 */
public class RecordList {
    /* Record List variables */
    // todo: have the commands update the record list through an additional add record command.
    // count of the record list should be accessed through the ArrayList#size() method.
    private final List<Record> recordList;

    public static final String MESSAGE_EMPTY_RECORD_LIST = "Record list is empty!";

    /**
     * Constructs a {@code Record List}.
     */
    public RecordList() {
        this.recordList = new ArrayList<>();
    }

    /**
     * Constructs a {@code Record List} with the given list of records.
     *
     * @param recordList The list of records.
     */
    public RecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    /**
     * Getter for list of records.
     *
     * @return List of records.
     */
    public List<Record> getRecordList() {
        return this.recordList;
    }

    /**
     * Returns a RecordList that matches the given predicate.
     *
     * @param predicate The predicate to filter the RecordList.
     * @return RecordList that matches the given predicate.
     */
    public RecordList getFilteredRecordList(Predicate<Record> predicate) {
        List<Record> filteredList = new ArrayList<>();

        for (Record record : this.recordList) {
            if (predicate.test(record)) {
                filteredList.add(record);
            }
        }

        return new RecordList(filteredList);
    }

    /**
     * Returns the String representation of a RecordList.
     *
     * @return RecordList String representation.
     */
    public String stringifyRecordList() {
        StringBuilder builder = new StringBuilder();

        if (this.recordList.isEmpty()) {
            builder.append(MESSAGE_EMPTY_RECORD_LIST);
            return builder.toString();
        }

        int index = 1;
        for (Record record : this.recordList) {
            builder.append(index)
                    .append(". ")
                    .append(record)
                    .append("\n");
            index++;
        }

        return builder.toString();
    }

    /**
     * Getter for size of list.
     *
     * @return Size of list.
     */
    public int getRecordListCount() {
        return this.recordList.size();
    }

    @Override
    public String toString() {
        return "Number of Records: " + recordList.size();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecordList // instanceof handles nulls
                && recordList.equals(((RecordList) other).recordList)); // state check
    }

    @Override
    public int hashCode() {
        return recordList.hashCode();
    }
}
