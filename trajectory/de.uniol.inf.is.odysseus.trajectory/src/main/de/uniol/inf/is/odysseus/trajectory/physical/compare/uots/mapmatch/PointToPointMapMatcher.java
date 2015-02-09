package de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.mapmatch;

import java.util.Iterator;

import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.Point;

import de.uniol.inf.is.odysseus.trajectory.physical.compare.RawTrajectory;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.UotsTrajectory;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class PointToPointMapMatcher implements IMapMatcher {

	
	public UotsTrajectory map(RawTrajectory trajectory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UotsTrajectory map(RawTrajectory trajectory,
			UndirectedSparseGraph<Point, LineSegment> graph) {
		
		//ersten finden
		final Iterator<Point> rawIt = trajectory.getPoints().iterator();
		Point rawPoint = rawIt.next();
		
		final Iterator<Point> graphIt = graph.getVertices().iterator();
		Point minGraphPoint = graphIt.next();
		double minDistance = rawPoint.distance(minGraphPoint);
		
		while(graphIt.hasNext()) {
			Point nextGraphPoint = graphIt.next();
			final double nextDistance = rawPoint.distance(nextGraphPoint);
			if(nextDistance < minDistance) {
				minGraphPoint = nextGraphPoint;
				minDistance = nextDistance;
			}
		}
		
		
		return null;
	}


}
