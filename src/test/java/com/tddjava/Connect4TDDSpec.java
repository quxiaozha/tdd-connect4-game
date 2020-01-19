package com.tddjava;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Connect4TDDSpec {
    private Connect4TDD tested;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void beforeEachTest() {
        tested = new Connect4TDD();
    }

    @Test
    public void whenTheGameIsStartedTheBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs(), is(0));
    }

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        int column = -1;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Invalid column " + column);
        tested.putDiscInColumn(column);
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        int column = 1;
        assertThat(tested.putDiscInColumn(column), is(0));
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.putDiscInColumn(column), is(1));
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscIncreases() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getNumberOfDiscs(), is(1));
    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {
        int column = 1;
        int maxDiscsInColumn = 6;
        for (int times = 1; times <= maxDiscsInColumn; times++) {
            tested.putDiscInColumn(column);
        }
        exception.expect(RuntimeException.class);
        exception.expectMessage("No more room in column " + column);
        tested.putDiscInColumn(column);
    }

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed(){
        assertThat(tested.getCurrentPlayer(), is("R"));
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen(){
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getCurrentPlayer(), is("G"));
    }

}
