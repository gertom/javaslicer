package de.unisb.cs.st.javaslicer.instructionSimulation;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.RandomAccess;

import de.unisb.cs.st.javaslicer.variables.StackEntry;
import de.unisb.cs.st.javaslicer.variables.Variable;


/**
 * Object for the variable usages of a SWAP instruction, at the same time the
 * list of used variables of the SWAP instruction (which is the same as the defined
 * variables).
 *
 * @author Clemens Hammacher
 */
public class SwapVariableUsages<InstanceType> extends AbstractList<Variable>
        implements DynamicInformation, RandomAccess {

    private StackEntry<InstanceType> lowerStackEntry;
    private StackEntry<InstanceType> upperStackEntry;

    public SwapVariableUsages(ExecutionFrame<InstanceType> frame) {
        int stackOffset = frame.operandStack.get() - 2;
        assert stackOffset >= 0 || frame.interruptedControlFlow;
        this.lowerStackEntry = frame.getStackEntry(stackOffset);
        this.lowerStackEntry = frame.getStackEntry(stackOffset+1);
    }

    public Map<Long, Collection<Variable>> getCreatedObjects() {
        return Collections.emptyMap();
    }

    public Collection<Variable> getDefinedVariables() {
        return this;
    }

    public Collection<Variable> getUsedVariables() {
        return this;
    }

    public Collection<Variable> getUsedVariables(
            Variable definedVariable) {
        if (definedVariable == this.lowerStackEntry) {
            return Collections.<Variable>singleton(this.upperStackEntry);
        } else {
            assert definedVariable == this.upperStackEntry;
            return Collections.<Variable>singleton(this.lowerStackEntry);
        }
    }

    public boolean isCatchBlock() {
        return false;
    }

    @Override
    public StackEntry<InstanceType> get(int index) {
        assert index >= 0 && index <= 1;
        return index == 0 ? this.lowerStackEntry : this.upperStackEntry;
    }

    @Override
    public int size() {
        return 2;
    }

}
