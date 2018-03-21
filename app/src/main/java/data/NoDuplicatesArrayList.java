package data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by s158270 on 14-3-2018.
 */

class NoDuplicatesArrayList extends ArrayList<Name> {
    /**
     *
     * adds a name object to a list if its not in the list yet
     * @param x object to be added
     * @return whether the object is added
     */
    @Override
    public boolean add(Name x) {
        if (!contains(x.name)) {
            return super.add(x);
        }
        return false;
    }

    /**
     *
     * add several name objects to a list if they are not in the list yet
     * @param xs objects to be added
     * @return if the whole collection was added
     */
    @Override
    public boolean addAll(Collection<? extends Name> xs) {
        boolean allAdded = true;
        for (Name x : xs) {
            if (!contains(x)) {
                add(x);
            } else {
                allAdded = false;
            }
        }
        return allAdded;

    }

    /**
     *
     *  returns the Name object if the input name is contained in the list
     *  this method is case insensitive
     * @param n name
     * @return the Name object with the same name
     */
    public Name get(String n) {
        n = n.toLowerCase();
        for (Name x : this) {
            if (x.name.equals(n)) {
                return x;
            }
        }
        throw new IllegalArgumentException("name doesn't exist");
    }

    /**
     *
     * checks if the input string is contained in the list
     * @param n string to be checked
     * @return true if string name is contained within the list
     */
    public boolean contains(String n) {
        n = n.toLowerCase();
        for (Name x : this) {
            if (n.equals(x.name)) {
                return true;
            }
        }
        return false;
    }

}
