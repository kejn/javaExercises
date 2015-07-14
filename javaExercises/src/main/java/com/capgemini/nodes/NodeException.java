package com.capgemini.nodes;

public class NodeException extends Exception {
	private static final long serialVersionUID = 1L;

	private static String[] whyException = {
		"Node ID doesn't have 4 characters",	
		"Node predecessor ID doesn't have 4 characters",
		"Node description is too long",
		"Nodes create a cycle",
		"Not penultimate node has two subsequent nodes"
	};
	
	public static enum ExceptionCode {
		INVALID_ID,
		INVALID_PREDECESSOR_ID,
		DESCRIPTION_TOO_LONG,
		CYCLE,
		NOT_PENULTIMATE_TWO_SUBSEQUENT 
	}
	public NodeException(ExceptionCode ec) {
		super(whyException[ec.ordinal()]);
	}
}
