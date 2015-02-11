package de.uniol.inf.is.odysseus.trajectory.transform;

import java.util.Arrays;
import java.util.List;

import de.uniol.inf.is.odysseus.core.collection.Tuple;
import de.uniol.inf.is.odysseus.core.metadata.ITimeInterval;
import de.uniol.inf.is.odysseus.core.sdf.schema.DirectAttributeResolver;
import de.uniol.inf.is.odysseus.core.sdf.schema.IAttributeResolver;
import de.uniol.inf.is.odysseus.core.sdf.schema.SDFAttribute;
import de.uniol.inf.is.odysseus.core.sdf.schema.SDFExpression;
import de.uniol.inf.is.odysseus.core.server.logicaloperator.AggregateAO;
import de.uniol.inf.is.odysseus.core.server.logicaloperator.PredicateWindowAO;
import de.uniol.inf.is.odysseus.core.server.logicaloperator.RestructHelper;
import de.uniol.inf.is.odysseus.core.server.logicaloperator.SelectAO;
import de.uniol.inf.is.odysseus.core.server.logicaloperator.builder.AggregateItem;
import de.uniol.inf.is.odysseus.core.server.planmanagement.TransformationConfiguration;
import de.uniol.inf.is.odysseus.core.server.predicate.TruePredicate;
import de.uniol.inf.is.odysseus.mep.MEP;
import de.uniol.inf.is.odysseus.parser.pql.relational.RelationalPredicateBuilder;
import de.uniol.inf.is.odysseus.relational.base.predicate.RelationalPredicate;
import de.uniol.inf.is.odysseus.ruleengine.ruleflow.IRuleFlowGroup;
import de.uniol.inf.is.odysseus.trajectory.logicaloperator.TrajectoryConstructAO;
import de.uniol.inf.is.odysseus.trajectory.physical.TrajectoryConstructPO;
import de.uniol.inf.is.odysseus.trajectory.physical.construct.DefaultTrajectoryConstructStrategy;
import de.uniol.inf.is.odysseus.trajectory.physical.construct.FulltrajectoryConstructStrategy;
import de.uniol.inf.is.odysseus.trajectory.physical.construct.ITrajectoryConstructStrategy;
import de.uniol.inf.is.odysseus.trajectory.physical.construct.SubtrajectoryConstructStrategy;
import de.uniol.inf.is.odysseus.transform.flow.TransformRuleFlowGroup;
import de.uniol.inf.is.odysseus.transform.rule.AbstractTransformationRule;


/**
 * 
 * @author marcus
 *
 */
public class TTrajectoryContructAORule extends AbstractTransformationRule<TrajectoryConstructAO> {

	@Override
	public void execute(TrajectoryConstructAO operator, TransformationConfiguration config) {
		
		final List<SDFAttribute> groupOrPartitionBy = Arrays.asList(operator.getTrajectoryId());
		
		final AggregateAO aggregateAO = new AggregateAO();
		aggregateAO.setName("TrajectoryConstruct Aggregate");
		aggregateAO.setGroupingAttributes(groupOrPartitionBy);
		aggregateAO.setAggregationItems(Arrays.asList((AggregateItem)operator.getPositionMapping()));
		
		RestructHelper.insertOperatorBefore(aggregateAO, operator);
		this.insert(aggregateAO);
		
		if(!operator.getSubtrajectories()) {
			final PredicateWindowAO predicateWindowAO = new PredicateWindowAO();
			predicateWindowAO.setSameStarttime(true);
			predicateWindowAO.setPartitionBy(groupOrPartitionBy);
			
			
			IAttributeResolver r = new DirectAttributeResolver(aggregateAO.getInputSchema());
			RelationalPredicateBuilder m = new RelationalPredicateBuilder();
			
			final RelationalPredicate p = (RelationalPredicate)m.createPredicate(r, "State = 0");
			p.init(aggregateAO.getInputSchema(), aggregateAO.getInputSchema());
			predicateWindowAO.setStartCondition(p);
			
			final RelationalPredicate p1 = (RelationalPredicate)m.createPredicate(r, "State = -1");
			p1.init(aggregateAO.getInputSchema(), aggregateAO.getInputSchema());
			predicateWindowAO.setEndCondition(p1);
			
			RestructHelper.insertOperatorBefore(predicateWindowAO, aggregateAO);
			this.insert(predicateWindowAO);
		}
	
		RestructHelper.removeOperator(operator, false);
		this.retract(operator);
	}

	@Override
	 public boolean isExecutable(TrajectoryConstructAO operator, TransformationConfiguration transformConfig) {
		 return true;
	 }
	 
	 @Override
	 public String getName() {
		 return "TrajectoryConstructAO -> PO";
	 }
	  
	 @Override
	 public IRuleFlowGroup getRuleFlowGroup() {
		 return TransformRuleFlowGroup.SUBSTITUTION;
	 }

}
