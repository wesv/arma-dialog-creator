package com.kaylerrenslow.armaDialogCreator.control.sv;

import com.kaylerrenslow.armaDialogCreator.expression.Env;
import com.kaylerrenslow.armaDialogCreator.expression.ExpressionEvaluationException;
import com.kaylerrenslow.armaDialogCreator.expression.ExpressionInterpreter;
import com.kaylerrenslow.armaDialogCreator.expression.Value;

/**
 @author Kayler
 Expression value that is used for {@link com.kaylerrenslow.armaDialogCreator.expression.ExpressionInterpreter} and is storable in {@link com.kaylerrenslow.armaDialogCreator.control.ControlProperty}
 Created on 07/15/2016. */
public class Expression extends SerializableValue {
	private final Env env;
	private Value value;

	public Expression(String exp, Env env) throws ExpressionEvaluationException {
		super(exp);
		this.env = env;
		setExpression(exp);
	}

	public void setExpression(String exp) throws ExpressionEvaluationException {
		this.value = ExpressionInterpreter.getInstance().evaluate(exp, env);
		valuesAsArray[0] = exp;
	}

	public String getExpression() {
		return valuesAsArray[0];
	}

	public Value getValue() {
		return value;
	}

	public Env getEnv() {
		return env;
	}

	/** Returns {@link #getValue()} and casts it to {@link Value.NumVal} */
	public Value.NumVal getNumVal() {
		return (Value.NumVal) value;
	}

	@Override
	public SerializableValue deepCopy() {
		return new Expression(valuesAsArray[0], env);
	}

	@Override
	public String toString() {
		return valuesAsArray[0];
	}
}