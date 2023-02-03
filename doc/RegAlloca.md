- [ ] Dominator Tree
- [x] check loopCnt's correctness

  ​

### Interference Graph

*Tiger Book Chapter 11*

- node: Each node in the interference graph represents a temporary value.
- edge: each edge $(t_1,t_2)$ indicates a pair of temporaries that cannot be assigned to the same register.

NPC problem



- Build, Simplify, Spill, and Select.

#### Build

​	Construct the interference graph. We use dataflow analysis (i.e. **live variable analysis**) to compute the set of temporaries that are simultaneously live at each program point, and we add an edge to the graph for each pair of temporaries in the set. 

​	We repeat this for all program points.

#### Simplify

​	For $K$-coloring, Suppose the graph $G$ contains a node $m$ with fewer than $K$ neighbors, where K is the number of
registers on the machine. Let $G'$ be the graph $G − \{m\}$ obtained by removing $m$.

#### Spill

​	Suppose at some point during simplification the graph G has nodes only of significant degree, that is, nodes of degree $≥ K$. Then the simplify heuristic fails, and we mark some node for spilling. That is, we choose some node in the graph (standing for a temporary variable in the program) and decide to represent it **in memory**, not registers, during program execution. 

​	An optimistic approximation to the effect of spilling is that the spilled node does not interfere with any of the other nodes remaining in the graph. It can therefore be removed and pushed on the stack, and the simplify process continued.

#### Select

​	We assign colors to nodes in the graph. 

​	Starting with the empty graph, we rebuild the original graph by repeatedly adding a node from the top of the stack. 

​	When we add a node to the graph, there must be a color for it, as the premise for removing it in the simplify phase was that it could always be assigned a color provided the remaining nodes in the graph could be successfully colored. When *potential spill* node n that was pushed using the Spill heuristic is popped, there is no guarantee that it will be colorable: its neighbors in the
graph may be colored with K different colors already. In this case, we have an actual spill. We do not assign any color, but we continue the Select phase to identify other actual spills. But perhaps some of the neighbors are the same color, so that among them there are fewer than K colors. Then we can color n, and it does not become an *actual spill*. This technique is known as *optimistic coloring*.

#### Start Over

​	If the Select phase is unable to find a color for some node(s), then the program must be rewritten to fetch them from memory just before each use, and store them back after each definition. Thus, a spilled temporary will turn into several new temporaries with **tiny live ranges**. These will interfere with other temporaries in the graph. So the algorithm is repeated on
this rewritten program. 

​	This process iterates until simplify succeeds with no spills; in practice, one or two iterations almost always suffice.

```
           a=
          /   \
  b = ...       c = ...
  ...  = a+b    ... = a+ c
  c = ...       b = ...
       \       /
         ... = b + c
         
the interference graph:
  a
 / \
b - c

            a=
          /   \  a1= a
  b = ...       c = ...
  ...  = a+b    ... = a1+ c
  c = ...       b = ...
       \       /
         ... = b + c
         
the interference graph:
  a   a1
  |   | 
  b - c
```



### Implementation

#### Mv 

- moveList: a mapping from a node to the list of moves it is associated with. (邻接表)
- worklistMoves: moves enabled for possible coalescing
- activeMoves: moves not yet ready for coalescing
- frozenMoves: moves that will no longer be considered for coalescing
- coalescedMoves: moves that have been coalesced



- selectStack: stack containing temporaries removed from the graph.
- coloredNodes: nodes successfully colored.
- coalescedNodes: registers that have been coalesced; when $u←v$ is coalesced, $v$ is added to this set and $u$ put back on some work-list (or vice versa).
- spilledNodes: nodes marked for spilling during this round; initially empty.