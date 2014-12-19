/**
 * ************ ISSUE LIST ***************
 *
 * 1. (Andres) Add a "close" method to close the possible linked file or whatever.
 *
 * 2. (Andres) Implements as Iterable();
 *
 */



package eu.amidst.core.database;


import java.util.Iterator;

/**
 * Created by afa on 02/07/14.
 */
public interface DataOnDisk extends DataBase{

    default void restart(){
        //Only needed if iterator implementation is not based on streams.
    }
}