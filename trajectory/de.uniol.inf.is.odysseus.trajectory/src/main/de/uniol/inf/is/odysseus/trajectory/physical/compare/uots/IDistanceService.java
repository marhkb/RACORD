package de.uniol.inf.is.odysseus.trajectory.physical.compare.uots;

public interface IDistanceService {

	
	public double getDistance(UotsTrajectory queryTrajectory, UotsTrajectory dataTrajectory, double upperBound);
	
	public void removeTrajectory(UotsTrajectory queryTrajectory);
}
