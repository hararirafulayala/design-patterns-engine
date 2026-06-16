package iterators;

import models.Row;

public interface RowIterator {

    boolean hasNext();


    Row next();
}