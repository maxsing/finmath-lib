package net.finmath.climate.models.dice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.finmath.climate.models.ClimateModel;
import net.finmath.climate.models.Temperature;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFromArrayFactory;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;

public class DICEModelTest {

	@Test
	public void testTimeStep6M() {
		final double timeStep = 0.5;
		final double timeHorizon = 500.0;
		final double savingsRate = 0.259029014481802;
		final double discountRate = 0.03;

		final double abatementInitial = 0.03;
		final double abatementMax = 1.00;
		final double abatementMaxTime = 40.0;		// years

		final double abatementIncrease = (abatementMax-abatementInitial)/abatementMaxTime;

		final RandomVariableFactory randomFactory = new RandomVariableFromArrayFactory();
		final Function<Double, RandomVariable> abatementFunction = time -> randomFactory.createRandomVariable(Math.min(abatementInitial + abatementIncrease * time, abatementMax));

		final TimeDiscretization timeDiscretization = new TimeDiscretizationFromArray(0.0, (int)Math.round(timeHorizon / timeStep), timeStep);

		final ClimateModel climateModel = new DICEModel(timeDiscretization, t -> abatementFunction.apply(t).doubleValue(), t -> savingsRate, discountRate);

		double value = climateModel.getValue().getAverage();
		double temperature = Arrays.stream(climateModel.getTemperature()).map(Temperature::getExpectedTemperatureOfAtmosphere).max(Comparator.naturalOrder()).orElseThrow();
		System.out.println("\nDice with timeStep=" + timeStep);
		System.out.println("\tValue.......... " + value);
		System.out.println("\tTemperature.... " + temperature);

		Assertions.assertEquals(523781.24195844505, value, 1E-5, "value");
		Assertions.assertEquals(2.8907990428384087, temperature, 1E-5, "value");
	}

	@Test
	public void testTimeStep1Y() {
		final double timeStep = 1.0;
		final double timeHorizon = 500.0;
		final double savingsRate = 0.259029014481802;
		final double discountRate = 0.03;

		final double abatementInitial = 0.03;
		final double abatementMax = 1.00;
		final double abatementMaxTime = 40.0;		// years

		final double abatementIncrease = (abatementMax-abatementInitial)/abatementMaxTime;

		final RandomVariableFactory randomFactory = new RandomVariableFromArrayFactory();
		final Function<Double, RandomVariable> abatementFunction = time -> randomFactory.createRandomVariable(Math.min(abatementInitial + abatementIncrease * time, abatementMax));

		final TimeDiscretization timeDiscretization = new TimeDiscretizationFromArray(0.0, (int)Math.round(timeHorizon / timeStep), timeStep);

		final ClimateModel climateModel = new DICEModel(timeDiscretization, t -> abatementFunction.apply(t).doubleValue(), t -> savingsRate, discountRate);

		double value = climateModel.getValue().getAverage();
		double temperature = Arrays.stream(climateModel.getTemperature()).map(Temperature::getExpectedTemperatureOfAtmosphere).max(Comparator.naturalOrder()).orElseThrow();
		System.out.println("\nDice with timeStep=" + timeStep);
		System.out.println("\tValue.......... " + value);
		System.out.println("\tTemperature.... " + temperature);

		Assertions.assertEquals(528053.9324187033, value, 1E-5, "value");
		Assertions.assertEquals(2.9161173964016283, temperature, 1E-5, "value");
	}

	@Test
	public void testTimeStep5Y() {
		final double timeStep = 5.0;
		final double timeHorizon = 500.0;
		final double savingsRate = 0.259029014481802;
		final double discountRate = 0.03;

		final double abatementInitial = 0.03;
		final double abatementMax = 1.00;
		final double abatementMaxTime = 40.0;		// years

		final double abatementIncrease = (abatementMax-abatementInitial)/abatementMaxTime;

		final RandomVariableFactory randomFactory = new RandomVariableFromArrayFactory();
		final Function<Double, RandomVariable> abatementFunction = time -> randomFactory.createRandomVariable(Math.min(abatementInitial + abatementIncrease * time, abatementMax));

		final TimeDiscretization timeDiscretization = new TimeDiscretizationFromArray(0.0, (int)Math.round(timeHorizon / timeStep), timeStep);

		final ClimateModel climateModel = new DICEModel(timeDiscretization, t -> abatementFunction.apply(t).doubleValue(), t -> savingsRate, discountRate);

		double value = climateModel.getValue().getAverage();
		double temperature = Arrays.stream(climateModel.getTemperature()).map(Temperature::getExpectedTemperatureOfAtmosphere).max(Comparator.naturalOrder()).orElseThrow();
		System.out.println("\nDice with timeStep=" + timeStep);
		System.out.println("\tValue.......... " + value);
		System.out.println("\tTemperature.... " + temperature);

		Assertions.assertEquals(563167.5684517919, value, 1E-5, "value");
		Assertions.assertEquals(3.121837361062374, temperature, 1E-5, "value");
	}
}
