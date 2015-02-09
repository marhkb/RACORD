package de.uniol.inf.is.odysseus.trajectory.physical.compare;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Point;

import de.uniol.inf.is.odysseus.core.collection.Tuple;
import de.uniol.inf.is.odysseus.core.metadata.ITimeInterval;

public class DefaultTupleToRawTrajectoryConverter implements ITupleToRawTrajectoryConverter {

	@Override
	public RawTrajectory convert(Tuple<ITimeInterval> tuple) {
		final Tuple<ITimeInterval> tupleList = tuple.getAttribute(1);
		
		final List<Point> points = new ArrayList<Point>();
		
		return new RawTrajectory((String)tuple.getAttribute(0), points);
	}
}
