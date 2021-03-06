/**
 * This file is part of the Joana IFC project. It is developed at the
 * Programming Paradigms Group of the Karlsruhe Institute of Technology.
 *
 * For further details on licensing please read the information at
 * http://joana.ipd.kit.edu or contact the authors.
 */
package edu.kit.joana.ifc.sdg.graph.slicer.conc.nanda.regions;

import edu.kit.joana.ifc.sdg.graph.SDG;

public final class NandaFactory {

	private NandaFactory() {}

	/**
	 * Creates a Nanda backward slicer with all optimizations.
	 *
	 * @param g
	 * @return
	 */
	public static Nanda createNandaBackward(SDG g) {
		return new Nanda(g, new NandaBackward());
	}

	/**
	 * Creates a Nanda forward slicer with all optimizations.
	 *
	 * @param g
	 * @return
	 */
	public static Nanda createNandaForward(SDG g) {
		return new Nanda(g, new NandaForward());
	}

	/**
	 * Creates a Nanda backward slicer with all optimizations, but imprecise MHP information.
	 * All threads are assumed to happen in parallel.
	 *
	 * @param g
	 * @return
	 */
	public static Nanda createNandaFlatBackward(SDG g) {
		return new NandaFlat(g, new NandaBackward());
	}

	/**
	 * Creates a Nanda backward slicer, which is close to the original algorithm.
	 * All threads are assumed to happen in parallel.
	 *
	 * @param g
	 * @return
	 */
	public static Nanda createNandaOriginalBackward(SDG g) {
		return new NandaOriginal(g, new NandaBackward());
	}


	/**
	 * Creates a Nanda forward slicer with all optimizations, but imprecise MHP information.
	 * All threads are assumed to happen in parallel.
	 *
	 * @param g
	 * @return
	 */
	public static Nanda createNandaFlatForward(SDG g) {
		return new NandaFlat(g, new NandaForward());
	}
}