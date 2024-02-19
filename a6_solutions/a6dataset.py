"""
Dataset for k-Means clustering

This file contains the class Dataset, which is the very first part of the assignment.
You cannot do anything in this assignment (except run the unit test) before this class
is finished.

YOUR NAME(S) AND NETID(S) HERE
DATE COMPLETED HERE
"""
import math
import random
import numpy
import a6helpers


# For checking preconditions
import a6checks

# CLASSES FOR THE ASSIGNMENT
class Dataset(object):
    """
    A class representing a dataset for k-means clustering.

    The data is stored as a list of list of numbers (ints or floats).  Each component
    list is a data point.

    INSTANCE ATTRIBUTES:
        _dimension: the point dimension for this dataset
                    [int > 0. Value never changes after initialization]
        _contents:  the dataset contents
                    [a list of lists of numbers (float or int), possibly empty.
    EXTRA INVARIANTS:
        The number of columns in _contents is equal to _dimension.  That is, for every
        item _contents[i] in the list _contents, len(_contents[i]) == dimension.

    None of the attributes should be accessed directly outside of the class Dataset
    (e.g. in the methods of class Cluster or KMeans). Instead, this class has getter and
    setter style methods (with the appropriate preconditions) for modifying these values.
    """

    def __init__(self, dim, contents=None,song_ids=[]):
        """
        Initializes a database for the given point dimension.

        The optional parameter contents is the initial value of the attribute _contents.
        When assigning contents to the attribute _contents it COPIES the list contents.
        If contents is None, the initializer assigns _contents an empty list. The
        parameter contents is None by default.

        The optional parameter song_ids is the initial value of the attribute _song_ids.
        If song_ids is the empty list, the initializer assigns _song_ids an empty list. The
        parameter song_ids is the empty list by default.

        Parameter dim: The dimension of the dataset
        Precondition: dim is an int > 0

        Parameter contents: the dataset contents
        Precondition: contents is either None or it is a table of numbers (int or float).
        If contents is not None, then contents if not empty and the number of columns is
        equal to dim.

        Parameter song_ids: the ids of songs in the playlist. 
        Precondition: song_ids is either the empty list or a list of the same length of contents
        """
        # BEGIN REMOVE
        assert type(dim) == int and dim > 0, '%d is not a valid dimension' % dim
        assert (contents is None) or a6checks.is_point_list(contents), \
                '%s is not a valid content list' % repr(contents)
        assert (contents is None) or (len(contents) > 0 and a6checks.is_point_list(contents))
        assert (song_ids == []) or len(song_ids) == len(contents)

        self._dimension = dim
        self._contents  = []
        self._song_ids = song_ids
        if not contents is None:
            for x in contents:
                self.addPoint(x)
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getDimension(self):
        """
        Returns the point dimension of this data set
        """
        # BEGIN REMOVE
        return self._dimension
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getSongIds(self):
        """
        Returns the song ids of this data set
        """
        # BEGIN REMOVE
        return self._song_ids
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getSize(self):
        """
        Returns the number of elements in this data set.
        """
        # BEGIN REMOVE
        return len(self._contents)
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getContents(self):
        """
        Returns the contents of this data set as a list.

        This method returns the attribute _contents directly.  Any changes made to this
        list will modify the data set.  If you want to access the data set, but want to
        protect yourself from modifying the data, use getPoint() instead.
        """
        # BEGIN REMOVE
        return self._contents
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getPoint(self, i):
        """
        Returns a COPY of the point at index i in this data set.

        Often, we want to access a point in the data set, but we want a copy to make sure
        that we do not accidentally modify the data set.  That is the purpose of this
        method.

        If you actually want to modify the data set, use the method getContents().
        That returns the list storing the data set, and any changes to that
        list will alter the data set.

        Parameter i: the index position of the point
        Precondition: i is an int that refers to a valid position in 0..getSize()-1
        """
        # BEGIN REMOVE
        assert type(i) == int
        assert 0 <= i and i < self.getSize()
        return self._contents[i][:]
        # END REMOVE
        # IMPLEMENT ME
        pass


    def addPoint(self,point):
        """
        Adds a COPY of point at the end of _contents.

        This method does not add the point directly. It adds a copy of the point.

        Precondition: point is a list of numbers (int or float),  len(point) = _dimension."""
        # BEGIN REMOVE
        assert a6checks.is_point(point), \
                '%s is not a valid point' % repr(point)
        assert len(point) == self._dimension, \
                '%s does not have dimention %d' % (repr(point),self._dimension)
        self._contents.append(point[:])
        # END REMOVE
        # IMPLEMENT ME
        pass


    def standardize(self):
        """
        Standardizes the contents of dataset.

        Modifies every point.
        """
        l = self.getContents()
        if len(l) == 0:
            return
        for dim in range(len(l[0])):
            mean = a6helpers.find_mean(l,dim)
            std = a6helpers.find_std(l,dim)
            for x in range(len(l)):
                if std != 0:
                    l[x][dim] = (l[x][dim] - mean)/std