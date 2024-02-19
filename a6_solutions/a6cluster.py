"""
Cluster class for k-Means clustering

This file contains the class cluster, which is the second part of the assignment.  With
this class done, the visualization can display the centroid of a single cluster.

YOUR NAME(S) AND NETID(S) HERE
DATE COMPLETED HERE
"""
import math
import random
import numpy


# For accessing the previous parts of the assignment
import a6checks
import a6dataset


class Cluster(object):
    """
    A class representing a cluster, a subset of the points in a dataset.

    A cluster is represented as a list of integers that give the indices in the dataset
    of the points contained in the cluster.  For instance, a cluster consisting of the
    points with indices 0, 4, and 5 in the dataset's data array would be represented by
    the index list [0,4,5].

    A cluster instance also contains a centroid that is used as part of the k-means
    algorithm.  This centroid is an n-D point (where n is the dimension of the dataset),
    represented as a list of n numbers, not as an index into the dataset. (This is because
    the centroid is generally not a point in the dataset, but rather is usually in between
    the data points.)

    INSTANCE ATTRIBUTES:
        _dataset [Dataset]: the dataset this cluster is a subset of
        _indices [list of int]: the indices of this cluster's points in the dataset
        _centroid [list of numbers]: the centroid of this cluster
        _name [str]: an optional label for the centroid. Can be the empty string
    EXTRA INVARIANTS:
        len(_centroid) == _dataset.getDimension()
        0 <= _indices[i] < _dataset.getSize(), for all 0 <= i < len(_indices)
    """

    # Part A
    def __init__(self, dset, centroid,name=""):
        """
        Initializes a new empty cluster whose centroid is a copy of <centroid>

        Parameter dset: the dataset
        Precondition: dset is an instance of Dataset

        Parameter centroid: the cluster centroid
        Precondition: centroid is a list of dset.getDimension() numbers

        Parameter name: the name of the cluster centroid
        Precondition: a string, possibly empty
        """
        # BEGIN REMOVE
        assert isinstance(dset, a6dataset.Dataset), '%s is not a Dataset' % repr(dset)
        assert a6checks.is_point(centroid), '%s is not a point' % repr(centroid)
        assert len(centroid) == dset.getDimension()
        assert type(name) == str
        self._dataset = dset
        self._indices = []
        self._centroid = centroid[:]
        self._name = name
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getCentroid(self):
        """
        Returns the centroid of this cluster. Does not return a copy. 

        This getter method is to protect access to the centroid.
        """
        # BEGIN REMOVE
        return self._centroid
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getName(self):
        """
        Returns the name of this centroid.

        This getter method is to protect access to the centroid.
        """
        # BEGIN REMOVE
        return self._name
        # END REMOVE
        # IMPLEMENT ME
        pass
    

    def setName(self,name):
        """
        Sets the name of this centroid.

        Precondition: name is a string
        """
        # BEGIN REMOVE
        assert type(name) == str
        self._name = name
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getIndices(self):
        """
        Returns the indices of points in this cluster

        This method returns the attribute _indices directly.  Any changes made to this
        list will modify the cluster.
        """
        # BEGIN REMOVE
        return self._indices
        # END REMOVE
        # IMPLEMENT ME
        pass


    def addIndex(self, index):
        """
        Adds the given dataset index to this cluster.

        If the index is already in this cluster, this method leaves the
        cluster unchanged.

        Precondition: index is a valid index into this cluster's dataset.
        That is, index is an int in the range 0.._dataset.getSize()-1.
        """
        # BEGIN REMOVE
        assert type(index) == int, '%s is not a valid index' % repr(index)
        assert 0 <= index and index < self._dataset.getSize(), '%d is out of range' % index
        if not index in self._indices:
            self._indices.append(index)
        # END REMOVE
        # IMPLEMENT ME
        pass


    def clear(self):
        """
        Removes all points from this cluster, but leave the centroid unchanged.
        """
        # BEGIN REMOVE
        self._indices = []
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getContents(self):
        """
        Returns a new list containing copies of the points in this cluster.

        The result is a list of list of numbers.  It has to be computed from the indices.
        """
        # BEGIN REMOVE
        result = []
        for i in self._indices:
            result.append(self._dataset.getPoint(i))
        return result
        # END REMOVE
        # IMPLEMENT ME
        pass


    # Part B
    def distance(self, point):
        """
        Returns the euclidean distance from point to this cluster's centroid.

        Parameter point: The point to be measured
        Precondition: point is a list of numbers (int or float), with the same dimension
        as the centroid.
        """
        # BEGIN REMOVE
        assert a6checks.is_point(point), '%s is not a point' % repr(point)
        assert len(point) == self._dataset.getDimension()
        sum = 0
        for n in range(len(self._centroid)):
            d = self._centroid[n] - point[n]
            sum += d * d
        return math.sqrt(sum)
        # END REMOVE
        # IMPLEMENT ME
        pass


    def getRadius(self):
        """
        Returns the maximum distance from any point in this cluster, to the centroid.

        This method loops over the contents to find the maximum distance from the centroid.
        """
        # BEGIN REMOVE
        maximum = 0
        for i in self._indices:
            distance = self.distance(self._dataset.getPoint(i))
            if distance > maximum:
                maximum = distance
        return maximum
        # END REMOVE
        # IMPLEMENT ME
        pass


    def update(self):
        """
        Returns True if the centroid remains the same after recomputation; False otherwise.

        This method recomputes the _centroid attribute of this cluster. The new _centroid
        attribute is the average of the points of _contents (To average a point, average
        each coordinate separately).

        Whether the centroid "remained the same" after recomputation is determined by
        numpy.allclose.  The return value should be interpreted as an indication of whether
        the starting centroid was a "stable" position or not.

        If there are no points in the cluster, the centroid. does not change.
        """
        # BEGIN REMOVE
        if self._indices == []:
            return True

        dim = self._dataset.getDimension()
        new_centroid = [0] * dim
        for x in self._indices:
            p = self._dataset.getPoint(x)
            for n in range(dim):
                new_centroid[n] += p[n]

        for n in range(dim):
            new_centroid[n] /= float(len(self._indices))

        stable = numpy.allclose(self._centroid, new_centroid)
        self._centroid = new_centroid
        return stable
        # END REMOVE
        # IMPLEMENT ME
        pass


    # PROVIDED METHODS: Do not modify!
    def __str__(self):
        """
        Returns a String representation of the centroid of this cluster.
        """
        return str(self._centroid)


    def __repr__(self):
        """
        Returns an unambiguous representation of this cluster.
        """
        return str(self.__class__) + str(self)


    def findError(self):
        """
        Returns: a float representing the total error of the centroid. 

        The total error is calculated as the total squared distance from every point 
        belonging to that centroid to the center of the centroid.

        For example, if their is 2 points, one 1 unit from the center and another point 
        2 units from the center the error for this centroid would be 5 as we have
        1 * 1 + 2 * 2 = 5.

        Thus we would return 5.0

        Hint: The method distance() will he helpful.
        """
        contents = self.getContents()
        result = 0.0
        for point in contents:
            result += self.distance(point) ** 2
        return result