package com.capgemini.nodes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldrygala on 2015-02-09.
 */
public class Node {
	private String id;
	private String description;
	private String predecessorId;

	/**
	 * String reserved for {@link #predecessorId} which means a Node has no
	 * predecessor.
	 */
	public static final String NO_PREDECESSOR = "----";

	/**
	 * @param newId
	 *            should be exactly a 4-character String
	 * @param newDescription
	 *            shouldn't be longer than 128 characters
	 * @param newPredecessorId
	 *            should be exactly a 4-character String. Use
	 *            {@link #NO_PREDECESSOR} if you don't want node to have the
	 *            predecessor.
	 */
	Node(String newId, String newDescription, String newPredecessorId) {
		id = newId;
		description = newDescription;
		predecessorId = newPredecessorId;
	}

	// getters
	public String getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public String getPredecessorId() {
		return predecessorId;
	}

	// setters
	public void setId(String newId) {
		id = newId;
	}
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	public void setPredecessorId(String newPredecessorId) {
		predecessorId = newPredecessorId;
	}

	@Override
	public String toString() {
		return (id + "\n" + description + "\n" + predecessorId);
	}

	/**
	 * Loads nodes from file into a <code>List<></code> of nodes.</p>
	 * As the file is read line by line a valid file should contain nodes in the following format:<br>
	 * <code>id</code><br>
	 * <code>description</code><br>
	 * <code>predecessorId</code><br>
	 * <code>whiteSpace</code> (optionally)</p>
	 * 
	 * @param fileName path to file to load Nodes from.
	 * @return <code>List<></code> of nodes.
	 */
	public static List<Node> loadFromFile(String fileName) {
		List<Node> nodes = new ArrayList<Node>();
		String[] input = new String[3];

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(Node.class.getResourceAsStream(fileName)));
			int i = 0;
			while ((input[i] = br.readLine()) != null) {
				if (input[i].isEmpty() || input[i].contains("\\s\n\t\r")) {
					continue;
				}
				if (i == 2) {
					nodes.add(new Node(input[0], input[1], input[2]));
				}
				i = (i + 1) % 3;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return nodes;
	}

}
