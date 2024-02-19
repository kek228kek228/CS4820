"""
Primary algorithm for k-Means clustering

This file contains the Algorithm class for performing k-means clustering.  While it is
the last part of the assignment, it is the heart of the clustering algorithm.  You
need this class to view the complete visualizer.

YOUR NAME(S) AND NETID(S) HERE
DATE COMPLETED HERE
"""
import math
import random
import numpy


# For accessing the previous parts of the assignment
import a6checks
import a6dataset
import a6cluster


class Algorithm(object):
    """
    A class to manage and run the k-means algorithm.

    INSTANCE ATTRIBUTES:
        _dataset [Dataset]: the dataset which this is a clustering of
        _clusters [list of Cluster]: the clusters in this clustering (not empty)
    """


    # Part A
    def __init__(self, dset, k, seeds=None):
        """
        Initializes the algorithm for the dataset ds, using k clusters.

        If the optional argument seeds is supplied, it will be a list of indices into the
        dataset that specifies which points should be the initial cluster centroids.
        Otherwise, the clusters are initialized by randomly selecting k different points
        from the database to be the cluster centroids.

        Parameter dset: the dataset
        Precondition: dset is an instance of Dataset

        Parameter k: the number of clusters
        Precondition: k is an int, 0 < k <= dset.getSize()

        Paramter seeds: the initial cluster indices (OPTIONAL)
        Precondition seeds is None, or a list of k valid indices into dset.
        """
        # BEGIN REMOVE
        assert isinstance(dset, a6dataset.Dataset), '%s is not a Dataset' % repr(dset)
        assert type(k) == int, '%s is not a valid cluster group' % repr(k)
        assert 0 < k and k < dset.getSize(), '%d is out of range' % k
        assert seeds is None or a6checks.is_seed_list(seeds,k,dset.getSize()),\
                '%s is not a valid seed list' % repr(seeds)
        self._dataset = dset
        self._clusters = []

        if seeds is None:
            seeds = random.sample(range(dset.getSize()), k)

        for i in seeds:
            self._clusters.append(a6cluster.Cluster(dset, dset.getPoint(i)))

        # END REMOVE
        # IMPLEMENT ME
        pass


    def getClusters(self):
        """
        Returns the list of clusters in this object.

        This method returns the attribute _clusters directly.  Any changes made to this
        list will modify the set of clusters.
        """
        # BEGIN REMOVE
        return self._clusters
        # END REMOVE
        # IMPLEMENT ME
        pass


    # Part B
    def _nearest(self, point):
        """
        Returns the cluster nearest to point

        This method uses the distance method of each Cluster to compute the distance
        between point and the cluster centroid. It returns the Cluster that is closest.

        Ties are broken in favor of clusters occurring earlier self._clusters.

        Parameter point: The point to compare.
        Precondition: point is a list of numbers (int or float), with the same dimension
        as the dataset.
        """
        # BEGIN REMOVE
        assert a6checks.is_point(point), '%s is not a point' % repr(point)
        assert len(point) == self._dataset.getDimension(), \
                '%s does not have dimention %d' % (repr(point),self._dataset.getDimension())
        closest = None
        min_distance = float('inf')
        for cluster in self._clusters:
            dist = cluster.distance(point)
            if dist < min_distance:
                min_distance = dist
                closest = cluster
        return closest
        # END REMOVE
        # IMPLEMENT ME
        pass


    def _partition(self):
        """
        Repartitions the dataset so each point is in exactly one Cluster.
        """
        # First, clear each cluster of its points.  Then, for each point in the
        # dataset, find the nearest cluster and add the point to that cluster.

        # BEGIN REMOVE
        for cluster in self._clusters:
            cluster.clear()

        for ind in range(self._dataset.getSize()):
            self._nearest(self._dataset.getPoint(ind)).addIndex(ind)
        # END REMOVE
        # IMPLEMENT ME
        pass


    # Part C
    def _update(self):
        """
        Returns True if all centroids are unchanged after an update; False otherwise.

        This method first updates the centroids of all clusters'.  When it is done, it
        checks whether any of them have changed. It then returns the appropriate value.
        """
        # BEGIN REMOVE
        stable = True
        for cluster in self._clusters:
            cluster_stable = cluster.update()
            stable = stable and cluster_stable
        return stable
        # END REMOVE
        # IMPLEMENT ME
        pass


    def step(self):
        """
        Returns True if the algorithm converges after one step; False otherwise.

        This method performs one cycle of the k-means algorithm. It then checks if
        the algorithm has converged and returns the appropriate value.
        """
        # In a cycle, we partition the points and then update the means.
        # BEGIN REMOVE
        self._partition()
        return self._update()
        # END REMOVE
        # IMPLEMENT ME
        pass


    # Part D
    def run(self, maxstep):
        """
        Continues clustering until either it converges or maxstep steps (which ever comes first).

        Parameter maxstep: an int >= 0.
        """
        # Call step repeatedly, up to maxstep times, until the algorithm
        # converges. Stop after maxstep iterations even if the algorithm has not
        # converged.
        # You do not need a while loop for this.  Just write a for-loop, and exit
        # the for-loop (with a return) if you finish early.
        assert type(maxstep) == int and maxstep >= 0

        # BEGIN REMOVE
        for x in range(maxstep):
            if self.step():
                return
        # END REMOVE
        # IMPLEMENT ME
        pass
    
    
    def findTotalError(self):
        """
        Returns: a float representing the sum of the errors of all the centroids. 

        For example, if we have two centroids and they have errors of 2.0 and 3.0 respectively,
        then the total error would be 5.0 and we would return 5.0.

        Hint: the method and findError() would be helpful in the function. 
        """
        error = 0.0
        for cluster in self._clusters:
            error += cluster.findError()
        return error