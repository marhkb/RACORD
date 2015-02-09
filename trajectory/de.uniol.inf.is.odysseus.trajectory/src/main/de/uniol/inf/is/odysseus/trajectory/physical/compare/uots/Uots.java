package de.uniol.inf.is.odysseus.trajectory.physical.compare.uots;

import java.util.Map;

import com.vividsolutions.jts.geom.LineSegment;
import com.vividsolutions.jts.geom.Point;

import de.uniol.inf.is.odysseus.core.collection.Tuple;
import de.uniol.inf.is.odysseus.core.metadata.ITimeInterval;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.ITrajectoryCompareAlgorithm;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.ITupleToRawTrajectoryConverter;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.RawTrajectory;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.TupleToRawTrajectoryConverterFactory;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.graph.GraphBuilderFactory;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.mapmatch.IMapMatcher;
import de.uniol.inf.is.odysseus.trajectory.physical.compare.uots.mapmatch.MapMatcherFactory;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class Uots implements ITrajectoryCompareAlgorithm {
	
	private final static String MAP_MATCHER_KEY = "mapmatching";
	
	

	private final int k;
	
	private final UndirectedSparseGraph<Point, LineSegment> graph;
	
	private final ITupleToRawTrajectoryConverter tupleToRawTrajectoryConverter;
	private final IMapMatcher mapMatcher;
	
	public Uots(final int k, final int utmZone, final Map<String, String> options) {
		this.k = k;
		
		// Get the options
		this.graph = GraphBuilderFactory.getInstance().load(options.get(""), utmZone);
		this.tupleToRawTrajectoryConverter = TupleToRawTrajectoryConverterFactory.getInstance().create();
		this.mapMatcher = MapMatcherFactory.getInstance().create(options.get(MAP_MATCHER_KEY));
	}

	@Override
	public Tuple<ITimeInterval> getKNearest(Tuple<ITimeInterval> incoming) {
		
		RawTrajectory t = this.tupleToRawTrajectoryConverter.convert(incoming);
		this.mapMatcher.map(t);
		
		return null;
	}
	
	@Override
	public int getK() {
		return this.k;
	}
}
