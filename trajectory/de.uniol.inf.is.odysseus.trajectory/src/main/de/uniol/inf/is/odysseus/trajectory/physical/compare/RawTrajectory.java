package de.uniol.inf.is.odysseus.trajectory.physical.compare;

import java.util.Collections;
import java.util.List;

import com.vividsolutions.jts.geom.Point;

public class RawTrajectory {

	private final String id;
	
	private final List<Point> points;

	public RawTrajectory(final String id, final List<Point> points) {
		this.id = id;
		this.points = Collections.unmodifiableList(points);
	}

	public String getId() {
		return id;
	}

	public List<Point> getPoints() {
		return points;
	}
}
