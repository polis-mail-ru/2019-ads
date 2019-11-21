package ru.mail.polis.ads.hash;

import java.util.ArrayList;
import java.util.List;

class Cell<Key extends Comparable<Key>, Value>{
    private List<Key> cellKey = new ArrayList<Key>(); // A list with the keys
    private List<Value> cellValue = new ArrayList<Value>(); // A list with the value

        /**
         * @param key - The key by which we look for the value
         * @return the position of the value,
         *         if the key is not in the list, returns -1
         */
        private int getI(Key key){
            for (int i = 0; i < cellKey.size(); i++) {
                if(key.compareTo(cellKey.get(i)) == 0){
                    return i;
                }
            }
           return -1;
        }

    /**
      * @param key - The key by which we look for the value
     * @return The value in the cell under the specified key
     */
    Value get(Key key){
        int position = getI(key);
        return (position == -1)? null: cellValue.get(position);
    }

    /**
     * @param position - index values
     * @return value from this index
     */
    Value getValueFromPosition(int position){
        return cellValue.get(position);
    }

    /**
     * @param position - index key
     * @return key from this index
     */
    Key getKeyFromPosition(int position){
        return cellKey.get(position);
    }

    /**
     * @param key - The key by which we added for the value
     * @param value - The value we need to add
     * @return True - if a new value is added
     *         False - if the old one is changed
     */
    boolean put(Key key, Value value){
        int position = getI(key);
        if (position == -1){
            cellKey.add(key);
            cellValue.add(value);
            return true;
        } else {
            cellValue.add(position,value);
            cellKey.add(position,key);
            return false;
        }

    }

    /**
     * @param key The key by which we look for the value
     * @return The value that we deleted by the given key
     */
    Value remove(Key key){
        int position = getI(key);
        if (position == -1) return null;

        Value value = cellValue.get(position);
        cellValue.remove(position);
        cellKey.remove(position);
        return value;
    }

    /**
     * @return Cardinality of list
     */
    int size(){
        return cellKey.size();
    }
}
