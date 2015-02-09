package de.uniol.inf.is.odysseus.trajectory.physical.compare;

import de.uniol.inf.is.odysseus.trajectory.util.AbstractObjectLoaderFactory;

public class QueryTrajectoryLoaderFactory extends AbstractObjectLoaderFactory<IQueryTrajectoryLoader, RawTrajectory, String, Integer>{

	@Override
	protected String convertKey(String key) {
		return key.substring(key.lastIndexOf('.') + 1).toUpperCase();
	}

	@Override
	protected IQueryTrajectoryLoader createLoader(String convertedKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
