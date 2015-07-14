package com.capgemini.nodes;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Test;

public class NodesTest {
	private NodeValidators nv = new NodeValidators();
	private List<Node> nodes;

	@After
	public void initB4NextTest() {
		nodes.clear();
	}

	@Test
	public void nodesInLineAreValid() {
		nodes = Node.loadFromFile("nodesLine.txt");

		try {
			nv.validateMethod(nodes);
			System.out.println("nodesInLineAreValid() --- passed");
		} catch (NodeException ne) {
			fail(ne.getMessage());
		}
	}

	@Test
	public void lastTwoNodesCanHaveCommonParent() {
		nodes = Node.loadFromFile("nodesLastTwoPenultimate.txt");

		try {
			nv.validateMethod(nodes);
			System.out.println("lastTwoNodesCanHaveCommonParent() --- passed");
		} catch (NodeException ne) {
			fail(ne.getMessage());
		}
	}

	@Test
	public void shouldDetectWrongID() {
		nodes = Node.loadFromFile("nodesId.txt");

		try {
			nv.validateMethod(nodes);
			fail("Wrong ID validation.");
		} catch (NodeException ne) {
			System.err.println(ne.getMessage());
		}
	}

	@Test
	public void shouldDetectWrongPredecessorID() {
		nodes = Node.loadFromFile("nodesPredecessorId.txt");

		try {
			nv.validateMethod(nodes);
			fail("Wrong predecessor ID validation.");
		} catch (NodeException ne) {
			System.err.println(ne.getMessage());
		}
	}

	@Test
	public void shouldDetectWrongDescription() {
		nodes = Node.loadFromFile("nodesDescription.txt");

		try {
			nv.validateMethod(nodes);
			fail("Wrong description validation.");
		} catch (NodeException ne) {
			System.err.println(ne.getMessage());
		}
	}

	@Test
	public void shouldDetectCycle1() {
		nodes = Node.loadFromFile("nodesCycle1.txt");

		try {
			nv.validateMethod(nodes);
			fail("Wrong cycle identification.");
		} catch (NodeException ne) {
			System.err.println(ne.getMessage());
		}
	}

	@Test
	public void shouldDetectCycle2() {
		nodes = Node.loadFromFile("nodesCycle2.txt");

		try {
			nv.validateMethod(nodes);
			fail("Wrong cycle identification.");
		} catch (NodeException ne) {
			System.err.println(ne.getMessage());
		}
	}

	@Test
	public void shouldDetectNotPenultimateHavingMoreThan1Subsequent_1() {
		nodes = Node.loadFromFile("nodesNotPenultimateTwoSubsequent1.txt");

		try {
			nv.validateMethod(nodes);
			fail("Should detect that not penultimate has more than 1 subsequent.");
		} catch (NodeException ne) {
			System.err.println(ne.getMessage());
		}
	}

	@Test
	public void shouldDetectNotPenultimateHavingMoreThan1Subsequent_2() {
		nodes = Node.loadFromFile("nodesNotPenultimateTwoSubsequent2.txt");

		try {
			nv.validateMethod(nodes);
			fail("Should detect that not penultimate has more than 1 subsequent.");
		} catch (NodeException ne) {
			System.err.println(ne.getMessage());
		}
	}

}
