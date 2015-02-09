package de.uniol.inf.is.odysseus.trajectory.physical.compare;

import java.util.Map;

import de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.Uots;


public class TrajectoryCompareAlgorithmFactory {
	
	private final static TrajectoryCompareAlgorithmFactory INSTANCE = 
			new TrajectoryCompareAlgorithmFactory();
	
	public static TrajectoryCompareAlgorithmFactory getInstance() {
		return INSTANCE;
	}
	
	
	private TrajectoryCompareAlgorithmFactory() { }

	public ITrajectoryCompareAlgorithm create(final String name, int k, int utmZone, Map<String, String> options) {
		switch(name.toUpperCase()) {
			case "OWD" : return new Owd(k);
			case "UOTS" : return new Uots(k, utmZone, options);
		}
		return null;
	}
}
