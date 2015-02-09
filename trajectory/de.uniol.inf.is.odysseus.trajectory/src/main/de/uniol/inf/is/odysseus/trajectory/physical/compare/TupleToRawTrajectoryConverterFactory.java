package de.uniol.inf.is.odysseus.trajectory.physical.compare;

import de.uniol.inf.is.odysseus.trajectory.util.AbstractSimpleFactory;

public class TupleToRawTrajectoryConverterFactory extends AbstractSimpleFactory<ITupleToRawTrajectoryConverter> {

	private final static TupleToRawTrajectoryConverterFactory INSTANCE = new TupleToRawTrajectoryConverterFactory();
	
	public static TupleToRawTrajectoryConverterFactory getInstance() {
		return INSTANCE;
	}
	
	private TupleToRawTrajectoryConverterFactory() { }
	
	@Override
	protected ITupleToRawTrajectoryConverter internalCreate() {
		return new DefaultTupleToRawTrajectoryConverter();
	}

}
