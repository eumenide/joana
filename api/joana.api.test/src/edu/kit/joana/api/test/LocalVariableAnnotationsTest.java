/**
 * This file is part of the Joana IFC project. It is developed at the
 * Programming Paradigms Group of the Karlsruhe Institute of Technology.
 *
 * For further details on licensing please read the information at
 * http://joana.ipd.kit.edu or contact the authors.
 */
package edu.kit.joana.api.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.graph.GraphIntegrity.UnsoundGraphException;

import edu.kit.joana.api.IFCAnalysis;
import edu.kit.joana.api.test.util.ApiTestException;
import edu.kit.joana.api.test.util.BuildSDG;
import edu.kit.joana.api.test.util.DumpTestSDG;
import edu.kit.joana.ifc.sdg.core.SecurityNode;
import edu.kit.joana.ifc.sdg.core.violations.IViolation;

/**
 * @author Martin Hecker <martin.hecker@kit.edu>
 */
public class LocalVariableAnnotationsTest {


	public void pass(Class<?> cls) throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		IFCAnalysis ana = BuildSDG.buldAndUseJavaAnnotations(
			cls,
			BuildSDG.top_sequential,
			false);
		DumpTestSDG.dumpSDG(ana.getProgram().getSDG(), cls.getCanonicalName() + ".pdg");
		DumpTestSDG.dumpGraphML(ana.getProgram().getSDG(), cls.getCanonicalName() + ".pdg");

		Collection<? extends IViolation<SecurityNode>> illegal = ana.doIFC();
		assertFalse(illegal.isEmpty());
	}
	
	// tests that fail. Some programs, usually those that just pass around constants via several variables,
	// just cannot be properly annotated, because both in their IR and their PDGs there are no nodes that 
	// sensibly use and/or def those variables present.
	public void fail(Class<?> cls) throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		IFCAnalysis ana = BuildSDG.buldAndUseJavaAnnotations(
			cls,
			BuildSDG.top_sequential,
			false);
		DumpTestSDG.dumpSDG(ana.getProgram().getSDG(), cls.getCanonicalName() + ".pdg");
		DumpTestSDG.dumpGraphML(ana.getProgram().getSDG(), cls.getCanonicalName() + ".pdg");

		Collection<? extends IViolation<SecurityNode>> illegal = ana.doIFC();
		assertTrue(illegal.isEmpty());
	}

	
	@Test
	public void test1() throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		pass(joana.api.testdata.javannotations.LocalVariableAnnotations1.class);
	}
	
	
	@Test
	public void test2() throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		fail(joana.api.testdata.javannotations.LocalVariableAnnotations1.Test2.class);
	}
	
	@Test
	public void test3() throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		fail(joana.api.testdata.javannotations.LocalVariableAnnotations1.Test3.class);
	}
	
	@Test
	public void test4() throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		fail(joana.api.testdata.javannotations.LocalVariableAnnotations1.Test4.class);
	}

	/**
	 * This should fail with an exception because it results in the attempt to annotate a node both as source and sink without annotating it as declassification explicitly.
	 * See commit 3fe7f5a20135c5f12d9e2b45a6d4f0cd98572797
	 */
	@Test(expected=IllegalStateException.class)
	public void test22() throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		fail(joana.api.testdata.javannotations.LocalVariableAnnotations1.Test22.class);
	}
	
	@Test
	public void test23() throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		fail(joana.api.testdata.javannotations.LocalVariableAnnotations1.Test23.class);
	}
	
	@Test
	public void test24() throws ClassHierarchyException, ApiTestException, IOException, UnsoundGraphException,
	CancelException {
		fail(joana.api.testdata.javannotations.LocalVariableAnnotations1.Test24.class);
	}
	



	public static void main(String[] args)
		throws ClassHierarchyException,
		       ApiTestException,
		       IOException,
		       UnsoundGraphException,
		       CancelException {
		LocalVariableAnnotationsTest test = new LocalVariableAnnotationsTest();
		test.test1();
	}

}
