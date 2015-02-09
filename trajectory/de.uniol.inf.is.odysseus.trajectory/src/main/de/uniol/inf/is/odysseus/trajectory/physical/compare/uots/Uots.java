package de.uniol.inf.is.odysseus.trajectory.physical.compare.uots;

import java.util.Map;

import de.uniol.inf.is.odysseus.core.collection.Tuple;
import de.uniol.inf.is.odysseus.core.metadata.ITimeInterval;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.ITrajectoryCompareAlgorithm;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.mapmatch.IMapMatcher;

public class Uots implements ITrajectoryCompareAlgorithm {

	private final int k;
	
	private final IMapMatcher mapMatcher;
	
	public Uots(final int k, final Map<String, String> options) {
		this.k = k;
		
		// Get the options
		this.mapMatcher = null;
	}

	@Override
	public Tuple<ITimeInterval> getKNearest(Tuple<ITimeInterval> incoming) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getK() {
		return this.k;
	}
}
