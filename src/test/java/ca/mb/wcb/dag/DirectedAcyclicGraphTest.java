package ca.mb.wcb.dag;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DirectedAcyclicGraphTest {

	@Test
	void test() {
		DirectedAcyclicGraph dag = new DirectedAcyclicGraph();

		dag.addEdge('A', 'B');
		dag.addEdge('B', 'C');
		dag.addEdge('C', 'D');
		var result = dag.findPath('A', 'D');
		print("Test Case 1: A->B->C->D (Path exists)", result);

		DirectedAcyclicGraph dag2 = new DirectedAcyclicGraph();
		dag2.addEdge('A', 'B');
		dag2.addEdge('C', 'B');
		dag2.addEdge('D', 'C');
		result = !dag2.findPath('A', 'D');
		print("Test Case 2: A->B<-C<-D (Path does not exist)", result);

		DirectedAcyclicGraph singleNodeGraph = new DirectedAcyclicGraph();
		singleNodeGraph.addEdge('X', 'X'); // Self-loop for testing purposes
		assertTrue(singleNodeGraph.findPath('X', 'X'));
		result = !dag2.findPath('A', 'D');
		print("Test Case 3: Single node graph (Path exists to itself)", result);

		DirectedAcyclicGraph disconnectedGraph = new DirectedAcyclicGraph();
		disconnectedGraph.addEdge('P', 'Q');
		disconnectedGraph.addEdge('R', 'S');
		result = !disconnectedGraph.findPath('P', 'S'); // Expected: false
		print("Test Case 4: Disconnected graph (No path exists)", result);

		DirectedAcyclicGraph complexDag = new DirectedAcyclicGraph();
		complexDag.addEdge('A', 'B');
		complexDag.addEdge('B', 'C');
		complexDag.addEdge('C', 'D');
		complexDag.addEdge('B', 'E');
		complexDag.addEdge('E', 'F');
		result = complexDag.findPath('A', 'F'); // Expected: true
		print("Test Case 5.1: Path exists in complex DAG", result);
		result = !complexDag.findPath('E', 'D'); // Expected: false
		print("Test Case 5.2: Path doesn't exists in complex DAG", result);

		result = !complexDag.findPath('X', 'Y'); // Expected: false
		print("Test Case 6: Non-existent nodes", result);
	}

	/**
	 * @param msg
	 * @param result
	 */
	private void print(String msg, boolean result) {
		assertTrue(result, msg);
		System.out.println(msg + ": " + result);
	}

}
