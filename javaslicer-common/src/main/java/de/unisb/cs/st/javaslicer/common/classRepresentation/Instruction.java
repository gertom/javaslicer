package de.unisb.cs.st.javaslicer.common.classRepresentation;

import java.io.DataOutputStream;
import java.io.IOException;

import de.hammacher.util.StringCacheOutput;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.ArrayInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.FieldInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.IIncInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.IntPush;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.JumpInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.LabelMarker;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.LdcInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.LookupSwitchInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.MethodInvocationInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.MultiANewArrayInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.NewArrayInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.SimpleInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.TableSwitchInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.TypeInstruction;
import de.unisb.cs.st.javaslicer.common.classRepresentation.instructions.VarInstruction;
import de.unisb.cs.st.javaslicer.common.exceptions.TracerException;

/**
 * Interface for all instructions that are stored in a {@link ReadMethod}.
 *
 * @author Clemens Hammacher
 */
public interface Instruction extends Comparable<Instruction> {

    public interface Instance {

        Instruction getInstruction();

        long getOccurenceNumber();

        int getStackDepth();

    }

    /**
     * Used to identify the type of instructions. Should be preferred to multiple instanceof statements.
     *
     * @author Clemens Hammacher
     */
    public enum Type {
        /**
         * the type of {@link ArrayInstruction}s.
         */
        ARRAY,
        /**
         * the type of {@link FieldInstruction}s.
         */
        FIELD,
        /**
         * the type of {@link IIncInstruction}s.
         */
        IINC,
        /**
         * the type of {@link IntPush} instructions.
         */
        INT,
        /**
         * the type of {@link JumpInstruction}s.
         */
        JUMP,
        /**
         * the type of {@link LabelMarker}s.
         */
        LABEL,
        /**
         * the type of {@link LdcInstruction}s.
         */
        LDC,
        /**
         * the type of {@link LookupSwitchInstruction}s.
         */
        LOOKUPSWITCH,
        /**
         * the type of {@link MethodInvocationInstruction}s.
         */
        METHODINVOCATION,
        /**
         * the type of {@link MultiANewArrayInstruction}s.
         */
        MULTIANEWARRAY,
        /**
         * the type of {@link NewArrayInstruction}s.
         */
        NEWARRAY,
        /**
         * the type of {@link SimpleInstruction}s.
         */
        SIMPLE,
        /**
         * the type of {@link TableSwitchInstruction}s.
         */
        TABLESWITCH,
        /**
         * the type of {@link TypeInstruction}s.
         */
        TYPE,
        /**
         * the type of {@link VarInstruction}s.
         */
        VAR
    }

    int getIndex();

    ReadMethod getMethod();

    int getOpcode();

    int getLineNumber();

    int getBackwardInstructionIndex(final TraceIterationInformationProvider infoProv);

    // for internal use only!
    Instance getNextInstance(final TraceIterationInformationProvider infoProv, int stackDepth) throws TracerException;

    void writeOut(final DataOutputStream out, StringCacheOutput stringCache) throws IOException;

    Type getType();

    Instruction getPrevious();

    Instruction getNext();

}
