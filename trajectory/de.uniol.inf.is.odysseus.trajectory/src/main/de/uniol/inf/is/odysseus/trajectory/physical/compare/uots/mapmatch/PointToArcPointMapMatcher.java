package de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.mapmatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.javatuples.Pair;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.Point;

import de.uniol.inf.is.odysseus.trajectory.physical.compare.RawTrajectory;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.UotsTrajectory;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class PointToArcPointMapMatcher implements IMapMatcher {
	
	@Override
	public UotsTrajectory map(RawTrajectory trajectory,
			UndirectedSparseGraph<Point, LineSegment> graph) {
		
		//ersten finden
		final Iterator<Point> rawIt = trajectory.getPoints().iterator();
		Point rawPoint = rawIt.next();
		
		final Iterator<LineSegment> graphEdgeIt = graph.getEdges().iterator();
		LineSegment minEdge = graphEdgeIt.next();
		double minDistance = minEdge.distance(rawPoint.getCoordinate());
		
		while(graphEdgeIt.hasNext()) {
			LineSegment nextGraphEdge = graphEdgeIt.next();
			final double nextDistance = nextGraphEdge.distance(rawPoint.getCoordinate());
			if(nextDistance < minDistance) {
				minEdge = nextGraphEdge;
				minDistance = nextDistance;
			}
		}
		
		final LinkedHashSet<Point> graphPoints = new LinkedHashSet<Point>();
		
		while(rawIt.hasNext()) {
			final Pair<Point, Double> result = 
					this.search(rawPoint, rawPoint = rawIt.next(), minEdge, minDistance, graph);
			graphPoints.add(result.getValue0());
			minDistance = result.getValue1();
		}
		
		
		return new UotsTrajectory(trajectory, new ArrayList<>(graphPoints));
	}

	private Pair<LineSegment, Double> search(Point lastRawPoint, Point rawPoint, Point lastGraphPoint, double currDistance, 
			UndirectedSparseGraph<Point, LineSegment> graph) {	
		this.globalGraphPoint = null;
		this.globalMinDistance = Double.MAX_VALUE;
		
		this.search(rawPoint, 
				lastRawPoint,
				lastGraphPoint,
				currDistance,
				currDistance + rawPoint.distance(lastRawPoint),
				new HashSet<Point>(),
				graph);
		
		return new Pair<LineSegment, Double>(this.globalGraphPoint, this.globalMinDistance);
	}
	
	private LineSegment globalGraphEdge;
	private double globalMinDistance;
	
	private void search(Point lastRawPoint, Point rawPoint, Point currGraphPoint, double currDistance, double maxDistance, Set<Point> visitedGraphPoints,
			UndirectedSparseGraph<Point, LineSegment> graph) {
		if(visitedGraphPoints.contains(currGraphPoint)) {
			return;
		}
		if(currDistance > maxDistance) {
			return;
		}
		final double distance = rawPoint.distance(currGraphPoint);
		if(distance < this.globalMinDistance) {
			this.globalGraphPoint = currGraphPoint;
			this.globalMinDistance = distance;
		}
		
		visitedGraphPoints.add(currGraphPoint);
		
		for(final Point neigbor : graph.getNeighbors(currGraphPoint)) {
			this.search(rawPoint, lastRawPoint, neigbor, lastRawPoint.distance(neigbor) , maxDistance, visitedGraphPoints, graph);
		}
	}

}
