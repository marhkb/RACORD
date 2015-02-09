package de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.mapmatch;

import de.uniol.inf.is.odysseus.trajectory.physical.compare.RawTrajectory;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.UotsTrajectory;


public interface IMapMatcher {

	
	public UotsTrajectory map(final RawTrajectory trajectory);
}
