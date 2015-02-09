package de.uniol.inf.is.odysseus.trajectory.physical.compare.uots;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.Point;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class DijkstraDistanceService implements IDistanceService {

	private final DijkstraDistance<Point, LineSegment> dijkstraDistance;
	
	private final Map<String, Iterator<Point>> map = new HashMap<>();
	
	public DijkstraDistanceService(final UndirectedSparseGraph<Point, LineSegment> graph) {
		this.dijkstraDistance = new DijkstraDistance<>(graph, true);
	}
	
	
	@Override
	public double getDistance(UotsTrajectory queryTrajectory,
			UotsTrajectory dataTrajectory, double upperBound) {
		
		for(final Point ec : queryTrajectory.getGraphPoints()) {
			this.dijkstraDistance.getDistanceMap(ec, dataTrajectory.getGraphPoints()).keySet();
		}
		
		return 0;
	}

}
