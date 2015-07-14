package com.capgemini.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldrygala on 2015-02-09.
 * <p/>
 * Write validate for
 * <ul>
 * <li>node id should have 4 characters</li>
 * <li>node description can have maximal 128 characters</li>
 * <li>no cycle</li>
 * <li>only penultimate can have two subsequent</li>
 * </ul>
 */
public class NodeValidators {
	/**
	 * Checks if:
	 * <ul>
	 * <li>node id has 4 characters</li>
	 * <li>node description has max. 128 characters</li>
	 * <li>there is a cycle</li>
	 * <li>only penultimate possibly has two subsequent</li>
	 * </ul>
	 * 
	 * @param nodes
	 *            List of nodes to validate.
	 * 
	 * @throws NodeException
	 *             with proper message if <b>nodes</b> are invalid.
	 */
	public void validateMethod(List<Node> nodes) throws NodeException {
		for (Node node : nodes) {
			if (node.getId().length() != 4) {
				throw new NodeException(NodeException.ExceptionCode.INVALID_ID);
			}
			if (node.getPredecessorId().length() != 4) {
				throw new NodeException(NodeException.ExceptionCode.INVALID_PREDECESSOR_ID);
			}
			if (node.getDescription().length() > 128) {
				throw new NodeException(NodeException.ExceptionCode.DESCRIPTION_TOO_LONG);
			}
		}

		int nodesInitSize = nodes.size();
		List<Node> organizedNodes = organize(nodes);

		if (organizedNodes.isEmpty()) {
			throw new NodeException(NodeException.ExceptionCode.CYCLE);
		}
		if (organizedNodes.size() < nodesInitSize) {
			throw new NodeException(NodeException.ExceptionCode.NOT_PENULTIMATE_TWO_SUBSEQUENT);
		}

	}

	/**
	 * Converts <b>nodes</b> into a valid list of nodes (see conditions in
	 * {@link #validateMethod(List) validateMethod}).
	 * </p>
	 * !!! May remove elements from <b>nodes</b>. If you need it later unchanged
	 * copy it !!!
	 * 
	 * @param nodes
	 *            List of nodes to convert.
	 * 
	 * @return valid List of nodes.
	 */
	private List<Node> organize(List<Node> nodes) {
		List<Node> organized = new ArrayList<Node>();
		String previousNode = Node.NO_PREDECESSOR;
		int orgSize = 0;

		for (int i = 0; i < nodes.size(); ++i) {
			if (nodes.get(i).getPredecessorId().equals(previousNode)) {
				previousNode = nodes.get(i).getId();
				organized.add(nodes.remove(i));
				++orgSize;
				i = -1;
			}
		}

		if (nodes.size() == 1 && orgSize > 1
				&& nodes.get(0).getPredecessorId().equals(organized.get(orgSize - 2).getId())) {
			organized.add(nodes.remove(0));
		}
		return organized;
	}
}
