# Algorithms01
Assignments of Algorithms Fourth Edition -- Chapter 1

## package **my.algorithms.hello** 
exercises of learning  `algs4.jar`. `algs4.jar` is an amazing library with many functions. We can use it to paint pics, do other things as a tool.

## package **my.algorithms.unionfind**
implementations of Union-Find algorithms

[UF.java](https://github.com/StephenHuge/Algorithms01/blob/master/src/my/algorithms/unionfind/UF.java) ： abstract class of Union-Find algorithms, all other algorithms extend this class.  

[QuickFind.java](https://github.com/StephenHuge/Algorithms01/blob/master/src/my/algorithms/unionfind/QuickFind.java) ： every element with same root in the array has same value, quick t find, but slowly to union.   

[QuickUnion.java](https://github.com/StephenHuge/Algorithms01/blob/master/src/my/algorithms/unionfind/QuickUnion.java) ： every element's value is its root's index, slowly to find, slowly to union.  

[WeightedQuickUnion.java](https://github.com/StephenHuge/Algorithms01/blob/master/src/my/algorithms/unionfind/WeightedQuickUnion.java) ： every tree has a weight which stands for the amount of this tree's son node. When union, node with smaller weight becomes son of node with larger weight, this greatly improves the performance of QuickUnion.  

[WeightedQUWithPC.java](https://github.com/StephenHuge/Algorithms01/blob/master/src/my/algorithms/unionfind/WeightedQUWithPC.java) ： this can improve WeightedQuickUnion's performance and it does almost the best.  

## package **default** 
assignments of Chapter 1.
[Percolation.java](https://github.com/StephenHuge/Algorithms01/blob/master/src/Percolation.java) & [PercolationStats.java](https://github.com/StephenHuge/Algorithms01/blob/master/src/PercolationStats.java) ： these are my implementation of the assignment.  

This is Assignment Page：  [Assigements Page](https://www.coursera.org/learn/algorithms-part1/programming/Lhp5z/percolation)

There are specific steps in Assignments Page, so just get it there. 