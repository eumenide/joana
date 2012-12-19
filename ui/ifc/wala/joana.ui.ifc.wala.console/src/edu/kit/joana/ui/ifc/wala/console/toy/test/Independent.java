/**
 * This file is part of the Joana IFC project. It is developed at the
 * Programming Paradigms Group of the Karlsruhe Institute of Technology.
 *
 * For further details on licensing please read the information at
 * http://joana.ipd.kit.edu or contact the authors.
 */
package edu.kit.joana.ui.ifc.wala.console.toy.test;


/**
 * This example demonstrates that our analysis can detect if the result of a method
 * does not depend on its input.
 * @author Martin Mohr
 *
 */
public class Independent {

    /** secret source */
    private int sec;

    /** public sink */
    private int pub;


    /**
     * This method returns a value that does not depend
     * on its input parameter.
     * @param x parameter of the method
     * @return something which is independent on the parameter value
     */
    public int foo1(int x) {
	return 42;
    }

    /**
     * This method returns a value that depends
     * on its input parameter.
     * @param x parameter of the method
     * @return something which depends on the parameter value
     */
    public int foo2(int x) {
	return 24 * x - 42;
    }

    public static void main(String[] args) {
	Independent i = new Independent();

	/**
	 * Write the result of foo1 applied to the secret source into public sink.
	 * This is okay since the secret source is not actually used for the calculation
	 * of the value returned by foo1().
	 */
	i.pub = i.foo1(i.sec);



    }
}
