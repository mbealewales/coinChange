package com.mbeale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Class that calculates coins given in change from a pot of coins.
 * @author mbeale
 */
public class ChangeMachine {
    private void accumulateChange(final List<Integer> change, final List<Integer> potOfCoins,
                                  final ListIterator<Integer> potIt, int target) {
        int remaining = target;

        int nextCoinIdxFromPotAfterAddingCoin = Integer.MAX_VALUE;
        Integer coinVal;
        while (potIt.hasNext()) {
            coinVal = potIt.next();
            if (coinVal == remaining) {
                remaining-= coinVal;
                change.add(coinVal);
                break;
            } else if (coinVal < remaining) {
                remaining-= coinVal;
                change.add(coinVal);
                nextCoinIdxFromPotAfterAddingCoin = potIt.nextIndex();
            }
        }

        /**
         * If target hasn't been reached without adding new coins to change, then cannot give change.
         * However, if target hasn't been reached but there are remaining coins to add, throw away last one
         * added to change, as this was the 'wrong coin', and search again from the
         * one in the pot after this wrong coin.
         */
        if (remaining > 0) {
            if (nextCoinIdxFromPotAfterAddingCoin < potOfCoins.size()) {
                int lastChangeAddedIdx = change.size() - 1;
                int lastChangedAddedVal = change.get(lastChangeAddedIdx);
                change.remove(lastChangeAddedIdx);

                this.accumulateChange(change, potOfCoins, potOfCoins.listIterator(nextCoinIdxFromPotAfterAddingCoin), remaining + lastChangedAddedVal);
            } else {
                throw new RuntimeException(change.toString().replaceAll("[\\[\\] ]", ""));
            }
        }
    }

    /**
     * Attempt to produce change to a 'target' value using a 'potOfCoins'.
     *
     * @param potOfCoins The full set of coins with which to make up the change.
     * @param target The amount that is required in change.
     * @return an ordered list of coins that make up the required change.
     * @throws RuntimeException if the required amount cannot be made from the coins, with the coins used in the
     * attempt.
     * @author mbeale.
     */
    public List<Integer> change(final List<Integer> potOfCoins, int target) {
        Collections.sort(potOfCoins, Collections.reverseOrder());
        final List<Integer> change = new ArrayList<>();
        this.accumulateChange(change, potOfCoins, potOfCoins.listIterator(), target);
        return change;
    }
}
